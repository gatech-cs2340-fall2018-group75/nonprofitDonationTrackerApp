package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.donationtracker.Model.Users;
import com.example.asus.donationtracker.R;

public class registerAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        Button register = (Button) findViewById(R.id.registerAcctBtn);
        final TextView email = (TextView) findViewById(R.id.regEmail);
        final TextView pass = (TextView) findViewById(R.id.regPass);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users userAccts = Users.getInstance();
                String regPassword = pass.getContentDescription().toString();
                String regEmail = email.getContentDescription().toString();
                userAccts.addAccount(regEmail, regPassword);

                Intent goToLogin = new Intent(registerAccount.this, login.class);
                startActivity(goToLogin);
            }
        });
    }
}
