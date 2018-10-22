package com.jskgmail.housecaree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InsideServiceGuard extends AppCompatActivity {

    Button btnNext;
    ImageView background;
    TextView textViewWorker;
    TextView textViewTrained;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_service_guard);

        btnNext=findViewById(R.id.btnNext);
        background=findViewById(R.id.background);
        textViewWorker=findViewById(R.id.textViewWorker);
        textViewTrained=findViewById(R.id.textViewTrained);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int image=getIntent().getIntExtra("imageGuard",0);
        final String serviceProvided;

        if(image==1) {
            background.setImageResource(R.drawable.home4mhg9);
            textViewWorker.setText("Skilled and Verified Driver");
            textViewTrained.setText("Trained Drivers");
            serviceProvided="Driver";
        }
        else if(image==2) {
            background.setImageResource(R.drawable.securityservices2);
            textViewWorker.setText("Skilled and Verified Guards");
            textViewTrained.setText("Trained Guards");
            serviceProvided="Commercial Security Guard";
        }
        else {
            background.setImageResource(R.drawable.securityservices2);
            textViewWorker.setText("Skilled and Verified Guards");
            textViewTrained.setText("Trained Guards");
            serviceProvided="Home Security";
        }

        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle(serviceProvided);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InsideServiceGuard.this,ProblemActivity.class);
                i.putExtra("serviceProvided",serviceProvided);
                startActivity(i);
            }
        });
    }
}
