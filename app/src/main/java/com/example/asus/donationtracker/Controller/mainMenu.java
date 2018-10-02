package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.donationtracker.R;
import com.example.asus.donationtracker.Model.User;
import com.example.asus.donationtracker.Model.Users;

public class mainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button logout = (Button) findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOut = new Intent(mainMenu.this, login.class);
                startActivity(logOut);
            }
        });
        
        
        Users users = Users.getInstance();
        User currentUser = users.getCurrentUser();
        
        TextView userEmail = (TextView) findViewById(R.id.userEmail);
        userEmail.setText(currentUser.getEmail());
        
        TextView userType = (TextView) findViewById(R.id.userType);
        userType.setText(currentUser.getAccountType().toString());
    }
}
