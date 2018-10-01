package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.donationtracker.Model.Users;
import com.example.asus.donationtracker.Model.User;
import com.example.asus.donationtracker.R;

public class registerAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        Button register = findViewById(R.id.registerAcctBtn);
        final EditText email = findViewById(R.id.regEmail);
        final EditText pass = findViewById(R.id.regPass);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = Users.getInstance();
                String regPassword = pass.getText().toString();
                String regEmail = email.getText().toString();
                users.add(new User(regEmail, regPassword));
                Log.e("users", users.toString());

                Intent goToLogin = new Intent(registerAccount.this, login.class);
                startActivity(goToLogin);
            }
        });
    }
}
