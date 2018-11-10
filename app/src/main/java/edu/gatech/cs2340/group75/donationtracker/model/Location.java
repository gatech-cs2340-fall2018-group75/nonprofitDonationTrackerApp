package edu.gatech.cs2340.group75.donationtracker.model;


import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;


/**
 * Model object representing a single donation location, holding location and contact information 
 *
 * @author mlewis61@gatech.edu
 */
public class Location implements Serializable {
	
	private static List<Location> locationsList = new ArrayList<>();
	
	public static List<Location> getLocationsList() {
		return Collections.unmodifiableList(locationsList);
	}
	
	public static void setLocationsList(List<Location> list) {
		locationsList = Collections.unmodifiableList(list);
	}
	
	
	private final String name;
	
	private final LocationType locationType;
	
	private final double longitude;
	private final double latitude;
	
	private final String address;
	private final String city;
	private final String state;
	private final String zip;
	
	private final String phoneNumber;

	/**
	 * constructor for a new Location object
	 * @param name location name
	 * @param locationType location's type
	 * @param latitude location's latitude
	 * @param longitude location's longitude
	 * @param address location's address
	 * @param city location's city
	 * @param state location's state
	 * @param zip location's zip code
	 * @param phoneNumber location's phoneNumber
	 */
	public Location
	(
		String name,
		LocationType locationType,
		double latitude, double longitude,
		String address, String city, String state, String zip,
		String phoneNumber
	) {
		this.name = name;
		this.locationType = locationType;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
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
				&& (that.latitude == this.latitude)
				&& that.address.equals(this.address)
				&& that.city.equals(this.city)
				&& that.state.equals(this.state)
				&& that.zip.equals(this.zip)
				&& that.phoneNumber.equals(this.phoneNumber);
	}
	
	@Override
	public int hashCode() {
		return 1;
	}

	/**
	 * getter for location's name
	 * @return location's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter for type of location
	 * @return type of location
	 */
	@SuppressWarnings("unused")
	public LocationType getLocationType() {
		return locationType;
	}
	
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
	 * getter method for location's zip code
	 * @return location's zip code
	 */
	@SuppressWarnings("unused")
	private String getZip() {
		return zip;
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
}
