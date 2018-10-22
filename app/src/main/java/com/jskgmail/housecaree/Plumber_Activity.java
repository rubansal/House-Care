package com.jskgmail.housecaree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Plumber_Activity extends AppCompatActivity {

    ListView listPlumbers;
    ArrayList<Service> plumberServices=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumber_);

        listPlumbers=findViewById(R.id.listPlumbers);

            plumberServices.add(new Service("Plumber", R.drawable.plumber));
            plumberServices.add(new Service("Electrician", R.drawable.electricchceck));
            plumberServices.add(new Service("Carpenter", R.drawable.carpenter));

        ServiceAdapter serviceAdapter=new ServiceAdapter(this,plumberServices);
        listPlumbers.setAdapter(serviceAdapter);

        listPlumbers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service service=plumberServices.get(position);
                if(service==plumberServices.get(0)){
                    Intent i=new Intent(Plumber_Activity.this,InsideService.class);
                    i.putExtra("image",1);
                    startActivity(i);
                }
                else if(service==plumberServices.get(1)){
                    Intent i=new Intent(Plumber_Activity.this,InsideService.class);
                    i.putExtra("image",2);
                    startActivity(i);
                }
                else if(service==plumberServices.get(2)){
                    Intent i=new Intent(Plumber_Activity.this,InsideService.class);
                    i.putExtra("image",3);
                    startActivity(i);
                }
            }
        });

//        ServiceAdapter serviceAdapter=new ServiceAdapter(plumberServices,rvPlumbers,this);
//        rvPlumbers.setAdapter(serviceAdapter);
    }
}
