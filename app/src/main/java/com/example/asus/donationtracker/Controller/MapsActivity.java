package com.example.asus.donationtracker.Controller;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.asus.donationtracker.R;
import com.example.asus.donationtracker.Model.Location;
import com.example.asus.donationtracker.Model.Locations;

import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
	
	private GoogleMap mMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
			.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}
	
	@Override
	public void onMapReady(GoogleMap googleMap) {
		
		mMap = googleMap;
		
		Locations locations = Locations.getInstance();
		List<Location> locationsList = locations.get();
		for (Location location : locationsList)
		{
			LatLng coordinates = location.getCoordinates();
			mMap.addMarker(
				new MarkerOptions()
					.position(coordinates)
					.title(location.getName())
					.snippet(location.getPhoneNumber())
			);
		}
		
		LatLng initial = locationsList.get(0).getCoordinates();
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initial, 10));
	}
}
