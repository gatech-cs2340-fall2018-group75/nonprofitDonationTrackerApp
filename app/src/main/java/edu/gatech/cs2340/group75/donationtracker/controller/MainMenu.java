package edu.gatech.cs2340.group75.donationtracker.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import edu.gatech.cs2340.group75.donationtracker.model.Location;
import edu.gatech.cs2340.group75.donationtracker.model.LocationType;
import edu.gatech.cs2340.group75.donationtracker.model.Locations;
import edu.gatech.cs2340.group75.donationtracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Activity for containing all menu fragments and bottom navigation
 *
 * <p>Queries database for current list of locations so fragment is pre-populated
 *
 * @author Benjamin Holmes
 * @see HomeFragment
 * @see LocationsFragment
 */
public class MainMenu extends AppCompatActivity {

    /**
     * Listener for change in selected navigation view
     * Sets current layout fragment based on selection
     */
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

    /**
     * Method called on activity creation to initialize layout
     * Sets current fragment to home fragment
     * Sets click listeners and makes call for getting locations from db
     * @param savedInstanceState Current instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        inflateInitialFragment();
        populateLocationsInstance();
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

    private void populateLocationsInstance() {
        String URL=getString(R.string.API_base) + "/locations/get";
        Log.d("REST response", "starting... " + URL);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Locations locationsInstance = Locations.getInstance();
                            List<Location> locations = new ArrayList<>();
                            for(int i = 0; i < response.length(); i++) {
                                JSONObject json = response.getJSONObject(i);
                                Location location = new Location(
                                        json.getString("Name"),
                                        LocationType.valueOf(json.getString("Type")),
                                        json.getDouble("Latitude"),
										json.getDouble("Longitude"),
                                        json.getString("Street Address"),
                                        json.getString("City"),
                                        json.getString("State"),
                                        json.getString("Zip"),
                                        json.getString("Phone")
                                );
                                locations.add(location);
                            }
                            locationsInstance.set(locations);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REST response", error.toString());
                    }
                }
        );
        requestQueue.add(request);
    }

    private void switchToHome() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private void switchToLocations() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new LocationsFragment()).commit();
    }
}
