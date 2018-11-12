package edu.gatech.cs2340.group75.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

import edu.gatech.cs2340.group75.donationtracker.model.Location;
import edu.gatech.cs2340.group75.donationtracker.R;

/**
 * Activity for viewing details relating to specified location
 *
 * <p>Contains list view for all donation items relating to specified location
 *
 * @author Markian Hromiak
 * @author Benjamin Holmes
 * @see Location
 * @see DonationItemsFragment
 */
public class LocationDetails extends AppCompatActivity {

    private Location location;

    /**
     * Method called on activity creation to initialize layout methods
     * Sets click listeners and retrieves donation items singleton
     * @param savedInstanceState Current instance state
     */
    @SuppressWarnings("FeatureEnvy")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);
		
		ActivityClasses classes = new ActivityClasses();
		classes.add(this.getClass());
		classes.add(EnterDonationItem.class);
		
        Toolbar toolbar = findViewById(R.id.toolbar);

        Intent intent = getIntent();
        Bundle bd = Objects.requireNonNull(intent.getExtras());
        location = (Location) Objects.requireNonNull(bd.get("LOCATION"));
        toolbar.setTitle(location.getName());

        setSupportActionBar(toolbar);
		ActionBar actionBar =  Objects.requireNonNull(getSupportActionBar());
		actionBar.setDisplayHomeAsUpEnabled(true);

        final String name = location.getName();
        final String type = location.getLocationTypeString();
        final String longitude = Double.toString(location.getLongitude());
        final String latitude = Double.toString(location.getLatitude());
        final String phone = location.getPhoneNumber();

        final String wholeAddress = location.getFullAddress();
        TextView loc = findViewById(R.id.location);
        loc.setText(wholeAddress);

        TextView phoneNmbr = findViewById(R.id.phone);
        phoneNmbr.setText(phone);

        TextView coords = findViewById(R.id.coords);
        String fullCoords = latitude + "/" + longitude;
        coords.setText(fullCoords);

        TextView nameView  = findViewById(R.id.name);
        nameView.setText(name);

        TextView loctype = findViewById(R.id.type);
        loctype.setText(type);

        FloatingActionButton donateFAB = findViewById(R.id.donate_fab);

        donateFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				ActivityClasses classes = new ActivityClasses();
				Intent intent = new Intent(LocationDetails.this, classes.get("EnterDonationItem"));
				intent.putExtra("LOCATION", location);
				startActivity(intent);

            }
        });

        inflateInitialFragment();
    }

    private void inflateInitialFragment() {
        if(findViewById(R.id.donation_items_fragment_container) == null) {
            return;
		}
        // set initial fragment layout to the home view
        DonationItemsFragment fragment = new DonationItemsFragment();
        Bundle args = new Bundle();
        args.putSerializable("LOCATION", location);
        fragment.setArguments(args);

        FragmentManager manager = getSupportFragmentManager();
		
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.donation_items_fragment_container, fragment);
		transaction.commit();
    }
}
