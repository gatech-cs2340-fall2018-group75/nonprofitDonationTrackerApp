package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.example.asus.donationtracker.Model.Location;
import com.example.asus.donationtracker.Model.LocationType;
import com.example.asus.donationtracker.Model.Locations;
import com.example.asus.donationtracker.R;
import com.example.asus.donationtracker.Model.User;
import com.example.asus.donationtracker.Model.Users;

public class mainMenu extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchToHome();
                    return true;
                case R.id.navigation_locations:
                    switchToLocations();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        inflateInitialFragment();
		
		
		readLocations();
    }

    private void inflateInitialFragment() {
        if(findViewById(R.id.fragment_container) == null)
            return;

        // set initial fragment layout to the home view
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new HomeFragment())
                .commit();
    }

    private void switchToHome() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private void switchToLocations() {
        Locations locations = Locations.getInstance();
        /*locations.add(new Location(
                "AFD Station 4",
                LocationType.DR,
                33.75416,
                -84.37742,
                "309 EDGEWOOD AVE SE",
                "Atlanta",
                "GA",
                "30330",
                "(404) 555 - 3456"
        ));*/

        Bundle bundle = new Bundle();
        bundle.putSerializable("LOCATIONS", locations);
        LocationsFragment fragment = new LocationsFragment();
        fragment.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
	
	
	private void readLocations()
	{
		Locations locations = Locations.getInstance();
		
		try {
			InputStream stream = getResources().openRawResource(R.raw.locations);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
			
			//Discard header
			reader.readLine();
			
			locations.readFromCsv(reader);
			reader.close();
		}
		
		catch (IOException exception) {
			Log.e("cs2340.donationTracker", "Error reading `locations.csv`");
			
			//Add default location in case of I/O Error
			locations.add(new Location(
                "AFD Station 4",
                LocationType.DR,
                33.75416,
                -84.37742,
                "309 EDGEWOOD AVE SE",
                "Atlanta",
                "GA",
                "30330",
                "(404) 555 - 3456"
			));
		}
	}
}
