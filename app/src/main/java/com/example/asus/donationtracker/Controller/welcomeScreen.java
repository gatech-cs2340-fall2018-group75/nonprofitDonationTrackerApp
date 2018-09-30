package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.asus.donationtracker.R;

public class welcomeScreen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button loginbtn = (Button) findViewById(R.id.welcomeLoginBtn);
        Button registerbtn = (Button) findViewById(R.id.registerbtn);



    }

    public void onClick(View v) {
        if(v.getId() == R.id.welcomeLoginBtn){
            Intent intent = new Intent(welcomeScreen.this, login.class);
            startActivity(intent);

        }else if(v.getId() == R.id.registerbtn){
            Intent intent = new Intent(welcomeScreen.this, registerAccount.class);
            startActivity(intent);
        }
    }
}

