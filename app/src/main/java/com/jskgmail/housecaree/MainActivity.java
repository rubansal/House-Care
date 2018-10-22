package com.jskgmail.housecaree;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener {

    private SliderLayout mDemoSlider;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String stat;
    private AdView mAdView;
    ListView listServices;
    ArrayList<Service> services = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle("HouseCare");

//        MobileAds.initialize(this, "ca-app-pub-9293221301322595~5482426001");
//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
        database = FirebaseDatabase.getInstance();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        Button fab1 = findViewById(R.id.fab5);
//        fab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, ProblemActivity.class);
//                startActivity(i);
//            }
//        });
//        Button fab11 = findViewById(R.id.rate);
//        fab11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, RateActivity.class);
//                startActivity(i);
//            }
//        });
        // Write a message to the database
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();

        file_maps.put("Daily Maids", R.mipmap.maid);
        file_maps.put("Guards", R.mipmap.guard);
        file_maps.put("Full time maids", R.mipmap.fullltime);
        file_maps.put("Babysitting", R.mipmap.babys);
        file_maps.put("Dusting", R.mipmap.maidft);

        file_maps.put("Washing clothes", R.mipmap.wash);
        file_maps.put("Adult Care", R.mipmap.adu);


        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        // mDemoSlider.addOnPageChangeListener(this);


        SharedPreferences prefs = getSharedPreferences("me", MODE_PRIVATE);
        String no = prefs.getString("mob", "1");
        if (!no.equals("1")) {
            myRef = database.getReference(no);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.child("Status").getValue(String.class);
                    if (value.equals("0")) {
                        stat = "Your booking is getting processed. You will get a confirmation call regarding it";
                    } else {
                        stat = value + "";
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                }
            });
        } else
            stat = "You have not booked any house helper yet.";

        listServices = findViewById(R.id.listServices);
        services.add(new Service("Maid/Servants",R.drawable.maidservices));
        services.add(new Service("Plumbers/Electrician/Carpenters",R.drawable.electricchceck));
        services.add(new Service("Security Guards/Drivers",R.drawable.home4mhg9));
        services.add(new Service("Car wash/Bike wash",R.drawable.carwash));
        //services.add(new Service("Dish washing/Cloth Washing",R.drawable.maidwash));
        services.add(new Service("Appliances Repair and Services",R.drawable.plumber));
        services.add(new Service("Paint and Renovations",R.drawable.maid_services_nyc));
        //services.add(new Service("One Day Helper",R.drawable.cleaning));
        ServiceAdapter adapter = new ServiceAdapter(getBaseContext(), services);
        listServices.setAdapter(adapter);

        listServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service service=services.get(position);
                if(service==services.get(1)){
                    Intent i=new Intent(MainActivity.this,Plumber_Activity.class);
                    startActivity(i);
                }
                if(service==services.get(2)){
                    Intent i=new Intent(MainActivity.this,SecurityGuard.class);
                    startActivity(i);
                }

            }
        });

        //DividerItemDecoration itemDecoration=new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        //rvServices.addItemDecoration(itemDecoration);

    }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences("me", MODE_PRIVATE);
        String no = prefs.getString("mob", "1");
        if (!no.equals("1")) {
            myRef = database.getReference(no);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.child("Status").getValue(String.class);
                    if (value.equals("0")) {
                        stat = "Your booking is getting processed. You will get a confirmation call regarding it";
                    } else if (value.equals("111")) {
                        stat = "You have not booked any house helper yet.";
                    } else {
                        stat = value + "";
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                }
            });
        } else
            stat = "You have not booked any house helper yet.";


    }


    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.acc) {
            Intent i = new Intent(MainActivity.this, MyaccountActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_camera) {

            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.status, null);

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            final TextView sta = (TextView) alertLayout.findViewById(R.id.s);

            // this is set the view from XML inside AlertDialog
            alert.setView(alertLayout);
            // disallow cancel of AlertDialog on click of back button and outside touch
            alert.setTitle("Status ");
            alert.setIcon(R.drawable.ic_show_chart_black_24dp);

            sta.setText(stat);

            alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();


            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(MainActivity.this, RateActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {

            LayoutInflater inflater = getLayoutInflater();
            final View alertLayout = inflater.inflate(R.layout.contact, null);
            final FloatingTextButton call = (FloatingTextButton) alertLayout.findViewById(R.id.fab);
            final FloatingTextButton mail = (FloatingTextButton) alertLayout.findViewById(R.id.fab1);

            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            // this is set the view from XML inside AlertDialog
            alert.setView(alertLayout);
            // disallow cancel of AlertDialog on click of back button and outside touch
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (ContextCompat.checkSelfPermission(
                            alertLayout.getContext(), android.Manifest.permission.CALL_PHONE) !=
                            PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) alertLayout.getContext(), new
                                String[]{android.Manifest.permission.CALL_PHONE}, 0);

                    } else {
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9560462692")));
                    }
                }
            });
            mail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "housecare4uapp@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Reg. HOUSECARE app");
                    alertLayout.getContext().startActivity(Intent.createChooser(emailIntent, null));
                }
            });


            AlertDialog dialog = alert.create();
            dialog.show();


        } else if (id == R.id.nav_send) {

            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.about, null);

            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            // this is set the view from XML inside AlertDialog
            alert.setView(alertLayout);
            // disallow cancel of AlertDialog on click of back button and outside touch
            alert.setTitle("About ");
            alert.setIcon(R.drawable.ic_help_black_24dp);


            alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
