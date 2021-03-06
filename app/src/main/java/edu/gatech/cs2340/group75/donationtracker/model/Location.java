package edu.gatech.cs2340.group75.donationtracker.model;


import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;
import org.json.JSONException;


/**
 * Model object representing a single donation location, holding location and contact information 
 *
 * @author bholmes34@gatech.edu
 * @author mhromiak3@gatech.edu
 * @author mlewis61@gatech.edu
 * @author spadi29@gatech.edu
 * @author sszczepaniuk3@gatech.edu
 *
 */
public class Location implements Serializable {
	
	private static List<Location> locationsList = new ArrayList<>();
	
	/**
	 * Get the static list of donation locations for the app
	 *
	 * @return an unmodifiable reference to the list of donation locations
	 */
	public static List<Location> getLocationsList() {
		return Collections.unmodifiableList(locationsList);
	}
	
	/**
	 * Set the static list of donation locations for the app
	 *
	 * @param	list	the new list of locations to be used for the app
	 */
	public static void setLocationsList(List<Location> list) {
		locationsList = Collections.unmodifiableList(list);
	}
	
	 /**
      * getter method for names of registered donation centers
      * @return list of names of registered donation centers
      */
	public static List<String> getLocationNames() {
		List<String> names = new ArrayList<>();
		for (Location l : locationsList) {
			if((l != null) && !names.contains(l.getName())) {
				names.add(l.getName());
			}
		}
		return names;
	}
	
	
	/**
	 * Read in a location object from a JSON object
	 *
	 * @param	json	the JSON object representing a Location
	 * 
	 * @return a Location object representing the information in the JSON object
	 *
	 * @throws	org.json.JSONException	when a JSON formatting error is encountered
	 */
	public static Location fromJson(JSONObject json) throws JSONException {
		Location location = new Location
		(
			json.getString("Name"),
			LocationType.valueOf(json.getString("Type")),
			json.getDouble("Latitude"),
			json.getDouble("Longitude")
		);
		
		location.setContactInfo
		(
			json.getString("Street Address"),
			json.getString("City"),
			json.getString("State"),
			json.getString("Zip"),
			json.getString("Phone")
		);
		
		return location;
	}
	
	
	private final String name;
	
	private final LocationType locationType;
	
	private final double longitude;
	private final double latitude;
	
	private String address;
	private String city;
	private String state;
	private String zip;
	
	private String phoneNumber;

	/**
	 * constructor for a new Location object
	 * @param name location name
	 * @param locationType location's type
	 * @param latitude location's latitude
	 * @param longitude location's longitude
	 */
	public Location
	(
		String name,
		LocationType locationType,
		double latitude, double longitude
	) {
		this.name = name;
		this.locationType = locationType;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		
		if (!(other instanceof Location)) {
			return false;
		}
		
		Location that = (Location) other;
		
		return that.name.equals(this.name)
				&& (that.locationType == this.locationType)
				&& (that.longitude == this.longitude)
				&& (that.latitude == this.latitude);
	}
	
	@Override
	public int hashCode() {
		return 1;
	}
	
	/**
	 * Set auxiliary contact information for a location object
	 *
	 * @param	address		the location's street address
	 * @param	city		the location's city
	 * @param	state		the location's state
	 * @param	zip			the location's zip code
	 * @param	phoneNumber	the location's phone number
	 */
	public void setContactInfo
	( 
		String address, String city, String state, String zip,
		String phoneNumber
	)
	{
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
	}
	
	
	/**
	 * getter for location's name
	 * @return location's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the string version of this location's type
	 * This method only exists to silence Law of Demeter lint issues
	 *
	 * @return the location's type in string form
	 */
	public String getLocationTypeString() {
		return locationType.toString();
	}

	/**
	 * getter for location longitude
	 * @return location's longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * getter for location latitude
	 * @return location's latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * getter for location's coordinates
	 * @return location's coordinates
	 */
	public LatLng getCoordinates() {
		return new LatLng(latitude, longitude);
	}

	/**
	 * getter for location's address
	 * @return location's address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * getter for location's phone number
	 * @return location's phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * getter method for location's city
	 * @return location's city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * getter method for location's state
	 * @return location's state
	 */
	public String getState() {
		return state;
	}

	
	/**
	 * Get the full, formatted address from the component parts
	 * The address is formatted as follows:
	 * {address}
	 * {city}, {state} {zip}
	 *
	 * @return the full address represented by this location
	 */
	public String getFullAddress() {
		String fullAddress = address + "\n";
		fullAddress = fullAddress + city + ", ";
		fullAddress = fullAddress + state + ", ";
		fullAddress = fullAddress + zip;
		
		return fullAddress;
	}
	
	
	@NonNull
    @Override
	public String toString() {
		return (name + ", " + address + " : " + longitude + "," + latitude + " : " + phoneNumber);
	}
	
	
	/**
	 * Convert this location to a Google Map "pin"
	 *
	 * @return a MarkerOptions object representing this location
	 */
	public MarkerOptions toMarkerOptions() {
		
		MarkerOptions options = new MarkerOptions();
		options.position(getCoordinates());
		options.title(name);
		options.snippet(phoneNumber);
		
		return options;
	}
}
