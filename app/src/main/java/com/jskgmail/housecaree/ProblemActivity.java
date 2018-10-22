package com.jskgmail.housecaree;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class ProblemActivity extends AppCompatActivity {
    int problem = 0;
    RelativeLayout rl;

    TextView cno;

    TextView spinner;

    final String[] problems = {"Click here and select "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);

        // Write a message to the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Read from the database


        rl = findViewById(R.id.r);

        cno = findViewById(R.id.textView17);

        spinner=findViewById(R.id.spinner2);

        Spinner currsemin = (Spinner) findViewById(R.id.spinner);
        final String[] gender = new String[1];
        List<String> category1 = new ArrayList<String>();
        category1.add("** SELECT **");
        category1.add("MALE Housekeeper");
        category1.add("FEMALE Housekeeper");

        String serviceProvided=getIntent().getStringExtra("serviceProvided");
        problems[0]=serviceProvided;

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currsemin.setAdapter(dataAdapter1);
        currsemin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender[0] = (parent.getItemAtPosition(position).toString().replaceAll(" ", "").replaceAll("th", "").replace("st", "").replace("nd", "").replace("rd", ""));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //final CheckedTextView spinner = findViewById(R.id.spinner2);
        final EditText name = findViewById(R.id.editText3);
        SharedPreferences prefs = getSharedPreferences("me", MODE_PRIVATE);
        final String nam = prefs.getString("name", null);

        final String mob = prefs.getString("mob", null);
        final String add = prefs.getString("add", null);

        name.setText(nam);
        final TextView mobb = findViewById(R.id.editText4);
        final EditText addr = findViewById(R.id.editText5);

        mobb.setText(mob);
        addr.setText(add);
        name.setText(nam);

//        spinner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LayoutInflater inflater = getLayoutInflater();
//                View alertLayout = inflater.inflate(R.layout.problems, null);
//
//                AlertDialog.Builder alert = new AlertDialog.Builder(ProblemActivity.this);
//                // this is set the view from XML inside AlertDialog
//                alert.setView(alertLayout);
//                // disallow cancel of AlertDialog on click of back button and outside touch
//                alert.setTitle("Choose from below: ");
//
//                final CheckedTextView checkedTextView = alertLayout.findViewById(R.id.checkedTextView);
//                final CheckedTextView checkedTextView1 = alertLayout.findViewById(R.id.checkedTextView1);
//                final CheckedTextView checkedTextView2 = alertLayout.findViewById(R.id.checkedTextView2);
//                final CheckedTextView checkedTextView3 = alertLayout.findViewById(R.id.checkedTextView3);
//                final CheckedTextView checkedTextView4 = alertLayout.findViewById(R.id.checkedTextView4);
//                final CheckedTextView checkedTextView5 = alertLayout.findViewById(R.id.checkedTextView5);
//                if (checkedTextView.isChecked()) {
//                    checkedTextView.setChecked(false);
//                    checkedTextView.setCheckMarkDrawable(R.drawable.ic_done_black_24dp);
//                } else {
//                    checkedTextView.setCheckMarkDrawable(R.drawable.ic_arrow_back_black_24dp);
//                    checkedTextView.setChecked(true);
//                }
//                if (checkedTextView1.isChecked()) {
//                    checkedTextView1.setChecked(false);
//                    checkedTextView1.setCheckMarkDrawable(R.drawable.ic_done_black_24dp);
//                } else {
//                    checkedTextView1.setCheckMarkDrawable(R.drawable.ic_arrow_back_black_24dp);
//                    checkedTextView1.setChecked(true);
//                }
//                if (checkedTextView2.isChecked()) {
//                    checkedTextView2.setChecked(false);
//                    checkedTextView2.setCheckMarkDrawable(R.drawable.ic_done_black_24dp);
//                } else {
//                    checkedTextView2.setCheckMarkDrawable(R.drawable.ic_arrow_back_black_24dp);
//                    checkedTextView2.setChecked(true);
//                }
//                if (checkedTextView3.isChecked()) {
//                    checkedTextView3.setChecked(false);
//                    checkedTextView3.setCheckMarkDrawable(R.drawable.ic_done_black_24dp);
//                } else {
//                    checkedTextView3.setCheckMarkDrawable(R.drawable.ic_arrow_back_black_24dp);
//                    checkedTextView3.setChecked(true);
//                }
//                if (checkedTextView4.isChecked()) {
//                    checkedTextView4.setChecked(false);
//                    checkedTextView4.setCheckMarkDrawable(R.drawable.ic_done_black_24dp);
//                } else {
//                    checkedTextView4.setCheckMarkDrawable(R.drawable.ic_arrow_back_black_24dp);
//                    checkedTextView4.setChecked(true);
//                }
//                if (checkedTextView5.isChecked()) {
//                    checkedTextView5.setChecked(false);
//                    checkedTextView5.setCheckMarkDrawable(R.drawable.ic_done_black_24dp);
//                } else {
//                    checkedTextView5.setCheckMarkDrawable(R.drawable.ic_arrow_back_black_24dp);
//                    checkedTextView5.setChecked(true);
//                }
//                checkedTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (checkedTextView.isChecked()) {
//                            checkedTextView.setChecked(false);
//                            checkedTextView.setCheckMarkDrawable(R.drawable.ic_done_black_24dp);
//                        } else {
//                            checkedTextView.setCheckMarkDrawable(R.drawable.ic_arrow_back_black_24dp);
//                            checkedTextView.setChecked(true);
//                        }
//                    }
//                });
//                checkedTextView1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (checkedTextView1.isChecked()) {
//                            checkedTextView1.setChecked(false);
//                            checkedTextView1.setCheckMarkDrawable(R.drawable.ic_done_black_24dp);
//                        } else {
//                            checkedTextView1.setCheckMarkDrawable(R.drawable.ic_arrow_back_black_24dp);
//                            checkedTextView1.setChecked(true);
//                        }
//                    }
//                });
//                checkedTextView2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (checkedTextView2.isChecked()) {
//                            checkedTextView2.setChecked(false);
//                            checkedTextView2.setCheckMarkDrawable(R.drawable.ic_done_black_24dp);
//                        } else {
//                            checkedTextView2.setCheckMarkDrawable(R.drawable.ic_arrow_back_black_24dp);
//                            checkedTextView2.setChecked(true);
//                        }
//                    }
//                });
//                checkedTextView3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (checkedTextView3.isChecked()) {
//                            checkedTextView3.setChecked(false);
//                            checkedTextView3.setCheckMarkDrawable(R.drawable.ic_done_black_24dp);
//                        } else {
//                            checkedTextView3.setCheckMarkDrawable(R.drawable.ic_arrow_back_black_24dp);
//                            checkedTextView3.setChecked(true);
//                        }
//                    }
//                });
//                checkedTextView4.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (checkedTextView4.isChecked()) {
//                            checkedTextView4.setChecked(false);
//                            checkedTextView4.setCheckMarkDrawable(R.drawable.ic_done_black_24dp);
//                        } else {
//                            checkedTextView4.setCheckMarkDrawable(R.drawable.ic_arrow_back_black_24dp);
//                            checkedTextView4.setChecked(true);
//                        }
//                    }
//                });
//                checkedTextView5.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (checkedTextView5.isChecked()) {
//                            checkedTextView5.setChecked(false);
//                            checkedTextView5.setCheckMarkDrawable(R.drawable.ic_done_black_24dp);
//                        } else {
//                            checkedTextView5.setCheckMarkDrawable(R.drawable.ic_arrow_back_black_24dp);
//                            checkedTextView5.setChecked(true);
//                        }
//                    }
//                });
//                alert.setIcon(R.drawable.ic_report_problem_black_24dp);
//                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//
//                alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {
//
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        problems[0] = ("");
//                        if (!checkedTextView.isChecked())
//                            problems[0] = problems[0] + ("Maids for sweeping ");
//                        if (!checkedTextView1.isChecked())
//                            problems[0] = problems[0] + ("Full time Housekeepers ");
//                        if (!checkedTextView2.isChecked())
//                            problems[0] = problems[0] + ("Babysitting / Adult Care ");
//                        if (!checkedTextView3.isChecked()) problems[0] = problems[0] + ("Guards ");
//                        if (!checkedTextView4.isChecked())
//                            problems[0] = problems[0] + ("Washing clothes ");
//                        if (!checkedTextView5.isChecked())
//                            problems[0] = problems[0] + ("Washing dishes ");
//                        if (problems[0].equals("")) problems[0] = "Click here and select";
//                        spinner.setText(problems[0]);
//
//
//                    }
//                });
//
//
//                AlertDialog dialog = alert.create();
//                dialog.show();
//
//
//            }
//        });

        spinner.setText(serviceProvided);
        final RelativeLayout r = findViewById(R.id.r);
        FloatingTextButton don = findViewById(R.id.fab);
        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mob != null) {


                    DatabaseReference myRef1 = database.getReference(mob);


                    myRef1.child("Prob").setValue(problems[0]);
                    myRef1.child("Gender").setValue(gender[0] + "");
                    myRef1.child("Name").setValue(name.getText().toString());
                    myRef1.child("Mobile").setValue(mob);
                    myRef1.child("Address").setValue(addr.getText().toString());
                    myRef1.child("Status").setValue("0");

                    Snackbar.make(rl, "You will get confirmation call within a day. Check the status in 'STATUS' ", Snackbar.LENGTH_LONG).show();

                }


            }
        });


    }


}
