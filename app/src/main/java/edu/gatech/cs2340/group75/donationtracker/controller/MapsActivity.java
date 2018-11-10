package edu.gatech.cs2340.group75.donationtracker.controller;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.gatech.cs2340.group75.donationtracker.R;
import edu.gatech.cs2340.group75.donationtracker.model.Location;

import java.util.List;
import java.util.Objects;

/**
 * Activity for viewing locations in Google Map view
 *
 * <p>Requires Google Maps API key defined in a local keys.xml
 * <p>See <a href="https://developers.google.com/maps/documentation/android-sdk/signup"></a>
 *
 * @author Mike Lewis
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		
		FragmentManager manager = getSupportFragmentManager();
		SupportMapFragment mapFragment = (SupportMapFragment) Objects.requireNonNull
		(
			manager.findFragmentById(R.id.map)
		);
		
		mapFragment.getMapAsync(this);
	}
	
	@SuppressWarnings("FeatureEnvy")
    @Override
	public void onMapReady(GoogleMap googleMap) {

		List<Location> locationsList = Location.getLocationsList();
		for (Location location : locationsList)
		{
			MarkerOptions options = new MarkerOptions();
			options.position(location.getCoordinates());
			options.title(location.getName());
			options.snippet(location.getPhoneNumber());
			googleMap.addMarker(options);
		}
		
		Location initialLocation = locationsList.get(0);
		LatLng initialCoordinates = initialLocation.getCoordinates();
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialCoordinates, 10));
	}
}
