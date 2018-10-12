package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.donationtracker.R;
import com.example.asus.donationtracker.Model.User;
import com.example.asus.donationtracker.Model.Users;
import com.example.asus.donationtracker.Model.LocationDatabaseAdapter;

public class mainMenu extends AppCompatActivity {

    private LocationDatabaseAdapter dbAdapter;

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

        dbAdapter = new LocationDatabaseAdapter(getApplicationContext());
        dbAdapter = dbAdapter.open();
        // TODO: Remove this call. DB should stay on disk
        dbAdapter.clearEntries();

        createDummyData();
        
        Users users = Users.getInstance();
        User currentUser = users.getCurrentUser();
        
        TextView userEmail = findViewById(R.id.userEmail);
        userEmail.setText(currentUser.getEmail());
        
        TextView userType = findViewById(R.id.userType);
        userType.setText(currentUser.getAccountType().toString());
    }

    private void createDummyData() {
        String[] data = {"AFD Station 4", "BOYS & GIRLS CLUB", "PATHWAY UPPER ROOM CHRISTIAN MINISTRIES"};
        for (String item : data) {
            dbAdapter.insertEntry(item);
        }

        Cursor c = dbAdapter.getEntries();
        while(c.moveToNext()) {
            String item = c.getString(
                    c.getColumnIndexOrThrow("NAME")
            );
            Log.d("DB", item);
        }
        c.close();
    }
}
