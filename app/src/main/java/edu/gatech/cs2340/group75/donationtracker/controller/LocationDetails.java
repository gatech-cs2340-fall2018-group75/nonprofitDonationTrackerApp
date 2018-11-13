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
 * @author bholmes34@gatech.edu
 * @author mhromiak3@gatech.edu
 * @author mlewis61@gatech.edu
 * @author spadi29@gatech.edu
 * @author sszczepaniuk3@gatech.edu
 *
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
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);
		
		ActivityClasses.add(this.getClass(), EnterDonationItem.class);
		
        Toolbar toolbar = findViewById(R.id.toolbar);

        Intent intent = getIntent();
        Bundle bd = Objects.requireNonNull(intent.getExtras());
        location = (Location) Objects.requireNonNull(bd.get("LOCATION"));
        toolbar.setTitle(location.getName());

        setSupportActionBar(toolbar);
		ActionBar actionBar =  Objects.requireNonNull(getSupportActionBar());
		actionBar.setDisplayHomeAsUpEnabled(true);

        setText(location);

        FloatingActionButton donateFAB = findViewById(R.id.donate_fab);

        donateFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				Intent intent = new Intent(LocationDetails.this, ActivityClasses.get("EnterDonationItem"));
				intent.putExtra("LOCATION", location);
				startActivity(intent);

            }
        });

        inflateInitialFragment();
    }
	
	private void setText(Location location) {
		TextView loc = findViewById(R.id.location);
		bindFullAddress(loc, location);
		
		TextView phoneNumber = findViewById(R.id.phone);
		bindPhone(phoneNumber, location);
		
		TextView coordinates = findViewById(R.id.coordinates);
		bindCoordinates(coordinates, location);
		
		TextView nameView = findViewById(R.id.name);
		bindName(nameView, location);
		
		TextView locationType = findViewById(R.id.type);
		bindType(locationType, location);
	}
	
	private void bindName(TextView view, Location location) {
		view.setText(location.getName());
	}
	
	private void bindType(TextView view, Location location) {
		view.setText(location.getLocationTypeString());
	}
	
	private void bindCoordinates(TextView view, Location location) {
		String latitude = Double.toString(location.getLatitude());
		String longitude = Double.toString(location.getLongitude());
		view.setText(getString(R.string.latLongFormat, latitude, longitude));
	}
	
	private void bindPhone(TextView view, Location location) {
		view.setText(location.getPhoneNumber());
	}
	
	private void bindFullAddress(TextView view, Location location) {
		view.setText(location.getFullAddress());
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
