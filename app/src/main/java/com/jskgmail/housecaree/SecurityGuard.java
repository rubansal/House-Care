package com.jskgmail.housecaree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SecurityGuard extends AppCompatActivity {

    ListView listSecurityGuards;
    ArrayList<Service> securityGuardServices=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_guard);

        listSecurityGuards=findViewById(R.id.listSecurityGuards);

        securityGuardServices.add(new Service("Driver", R.drawable.home4mhg9));
        securityGuardServices.add(new Service("Commercial Security Guard", R.drawable.securityservices2));
        securityGuardServices.add(new Service("Home Security", R.drawable.securityservices2));

        ServiceAdapter serviceAdapter=new ServiceAdapter(this,securityGuardServices);
        listSecurityGuards.setAdapter(serviceAdapter);

        listSecurityGuards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service service=securityGuardServices.get(position);
                if(service==securityGuardServices.get(0)){
                    Intent i=new Intent(SecurityGuard.this,InsideServiceGuard.class);
                    i.putExtra("imageGuard",1);
                    startActivity(i);
                }
                else if(service==securityGuardServices.get(1)){
                    Intent i=new Intent(SecurityGuard.this,InsideServiceGuard.class);
                    i.putExtra("imageGuard",2);
                    startActivity(i);
                }
                else if(service==securityGuardServices.get(2)){
                    Intent i=new Intent(SecurityGuard.this,InsideServiceGuard.class);
                    i.putExtra("imageGuard",3);
                    startActivity(i);
                }
            }
        });
    }
}
