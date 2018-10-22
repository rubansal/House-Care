package com.jskgmail.housecaree;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyaccountActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();
        final EditText nam=findViewById(R.id.editText);
        final EditText pho=findViewById(R.id.editText6);
        final EditText add=findViewById(R.id.editText8);
        SharedPreferences prefs = getSharedPreferences("me",MODE_PRIVATE);
        final String namm = prefs.getString("name", null);

        final String mob= prefs.getString("mob", null);
        final String addd= prefs.getString("add", null);
        nam.setText(namm);

        pho.setText(mob);
        add.setText(addd);
        final RelativeLayout rl=findViewById(R.id.rl);
        Button don=findViewById(R.id.button);
        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor= getSharedPreferences("me",MODE_PRIVATE).edit();
                editor.putString("name",nam.getText().toString());

                editor.putString("mob",pho.getText().toString());

                editor.putString("add",add.getText().toString());

                editor.apply();


                if (pho.getText().toString()!=null) {


                    DatabaseReference myRef1 = database.getReference(pho.getText().toString());



                    myRef1.child("Status").setValue("111");
                    Intent i = new Intent(MyaccountActivity.this, PhoneAuthActivity.class);
                    startActivity(i);
                }

            }
        });
    }
}
