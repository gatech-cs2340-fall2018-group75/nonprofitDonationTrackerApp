package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.donationtracker.Model.DonationItem;
import com.example.asus.donationtracker.Model.DonationItems;
import com.example.asus.donationtracker.Model.Location;
import com.example.asus.donationtracker.R;

import org.w3c.dom.Text;

public class LocationDetails extends AppCompatActivity {

    private Location location;

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
        TextView loc = (TextView) findViewById(R.id.location);
        loc.setText(wholeAddress);

        TextView phoneNmbr = (TextView) findViewById(R.id.phone);
        phoneNmbr.setText(phone);

        TextView coords = (TextView) findViewById(R.id.coords);
        String fullCoords = latitude + "/" + longitude;
        coords.setText(fullCoords);

        TextView nameView = (TextView) findViewById(R.id.name);
        nameView.setText(name);

        TextView loctype = (TextView) findViewById(R.id.type);
        loctype.setText(type);

        FloatingActionButton donateFAB = findViewById(R.id.donate_fab);

        donateFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(LocationDetails.this, enterDonationItem.class);
                    intent.putExtra("LOCATION", location);
                    startActivity(intent);

            }
        });

        inflateInitialFragment();
    }

    private void inflateInitialFragment() {
        if(findViewById(R.id.donation_items_fragment_container) == null)
            return;
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
