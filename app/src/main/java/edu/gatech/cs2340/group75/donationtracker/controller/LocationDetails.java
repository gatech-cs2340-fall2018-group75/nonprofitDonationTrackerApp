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
	//The entire point of Model classes is to separate features into distinct objects
	//Moving functionality from the model to this class will violate many design principles
	@SuppressWarnings("FeatureEnvy")
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

        setText(location);

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
	
	//The entire point of Model classes is to separate features into distinct objects
	//Moving functionality from the model to this class will violate many design principles
	@SuppressWarnings("FeatureEnvy")
	private void setText(Location location) {
		final String name = location.getName();
        final String type = location.getLocationTypeString();
        final String longitude = Double.toString(location.getLongitude());
        final String latitude = Double.toString(location.getLatitude());
        final String phone = location.getPhoneNumber();

        final String wholeAddress = location.getFullAddress();
        TextView loc = findViewById(R.id.location);
        loc.setText(wholeAddress);

        TextView phoneNumber = findViewById(R.id.phone);
        phoneNumber.setText(phone);

        TextView coordinates = findViewById(R.id.coordinates);
        String formattedCoordinates = latitude + "/" + longitude;
        coordinates.setText(formattedCoordinates);

        TextView nameView = findViewById(R.id.name);
        nameView.setText(name);

        TextView locationType = findViewById(R.id.type);
        locationType.setText(type);
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
