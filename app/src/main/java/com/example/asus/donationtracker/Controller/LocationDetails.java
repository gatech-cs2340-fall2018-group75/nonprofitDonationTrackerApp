package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.example.asus.donationtracker.Model.Location;
import com.example.asus.donationtracker.R;

import org.w3c.dom.Text;

public class LocationDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        Location place = (Location) bd.get("LOCATION");
        String location = place.getName();
        String type = place.getLocationType();
        String longitude = Double.toString(place.getLongitude());
        String latitude = Double.toString(place.getLatitude());
        String address = place.getAddress();
        String city = place.getCity();
        String state = place.getState();
        String zip = place.getZip();
        String phone = place.getPhoneNumber();

        String wholeAddress = address + "\n" + city + ", " + state + ", " + zip;
        TextView loc = (TextView) findViewById(R.id.location);
        loc.setText(wholeAddress);

        TextView phoneNmbr = (TextView) findViewById(R.id.phone);
        phoneNmbr.setText(phone);

        TextView coords = (TextView) findViewById(R.id.coords);
        String fullCoords = latitude + "/" + longitude;
        coords.setText(fullCoords);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(location);

        TextView loctype = (TextView) findViewById(R.id.type);
        loctype.setText(type);
    }

}
