package com.jskgmail.housecaree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InsideService extends AppCompatActivity {

    Button btnNext;
    ImageView background;
    TextView textViewWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_service);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnNext=findViewById(R.id.btnNext);
        background=findViewById(R.id.background);
        textViewWorker=findViewById(R.id.textViewWorker);

        int image=getIntent().getIntExtra("image",0);
        final String serviceProvided;

        if(image==1) {
            background.setImageResource(R.drawable.plumber);
            textViewWorker.setText("Skilled and Verified Plumber");
            serviceProvided="Plumber";
        }
        else if(image==2) {
            background.setImageResource(R.drawable.electricchceck);
            textViewWorker.setText("Skilled and Verified Electrician");
            serviceProvided="Electrician";
        }
        else {
            background.setImageResource(R.drawable.carpenter);
            textViewWorker.setText("Skilled and Verified Carpenter");
            serviceProvided="Carpenter";
        }

        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle(serviceProvided);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(InsideService.this,ProblemActivity.class);
                i.putExtra("serviceProvided",serviceProvided);
                startActivity(i);
            }
        });
    }
}
