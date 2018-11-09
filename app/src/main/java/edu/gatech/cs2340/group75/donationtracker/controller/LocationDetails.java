package edu.gatech.cs2340.group75.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.group75.donationtracker.model.DonationItems;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        location = (Location) bd.get("LOCATION");
        toolbar.setTitle(location.getName());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DonationItems items = DonationItems.getInstance();

        final String name = location.getName();
        final String type = location.getLocationType().toString();
        final String longitude = Double.toString(location.getLongitude());
        final String latitude = Double.toString(location.getLatitude());
        final String address = location.getAddress();
        final String city = location.getCity();
        final String state = location.getState();
        final String zip = location.getZip();
        final String phone = location.getPhoneNumber();

        final String wholeAddress = address + "\n" + city + ", " + state + ", " + zip;
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
                    Intent intent = new Intent(LocationDetails.this, EnterDonationItem.class);
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

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.donation_items_fragment_container, fragment)
                .commit();
    }
}
