package edu.gatech.cs2340.group75.donationtracker.controller;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

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
 * @author bholmes34@gatech.edu
 * @author mhromiak3@gatech.edu
 * @author mlewis61@gatech.edu
 * @author spadi29@gatech.edu
 * @author sszczepaniuk3@gatech.edu
 *
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		
		ActivityClasses.add(this.getClass());
		
		FragmentManager manager = getSupportFragmentManager();
		SupportMapFragment mapFragment = (SupportMapFragment) Objects.requireNonNull
		(
			manager.findFragmentById(R.id.map)
		);
		
		mapFragment.getMapAsync(this);
	}
	
	//The entire point of Model classes is to separate features into distinct objects
	//Moving functionality from the model to this class will violate many design principles
	@SuppressWarnings("FeatureEnvy")
	@Override
	public void onMapReady(GoogleMap googleMap) {
		
		List<Location> locationsList = Location.getLocationsList();
		for (Location location : locationsList)
		{
			googleMap.addMarker(location.toMarkerOptions());
		}
		
		Location initialLocation = locationsList.get(0);
		LatLng initialCoordinates = initialLocation.getCoordinates();
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialCoordinates, 10));
	}
}
