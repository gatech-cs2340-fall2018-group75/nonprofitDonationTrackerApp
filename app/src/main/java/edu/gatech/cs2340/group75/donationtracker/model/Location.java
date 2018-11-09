package edu.gatech.cs2340.group75.donationtracker.model;


import android.support.annotation.NonNull;

import java.io.Serializable;

import com.google.android.gms.maps.model.LatLng;


/**
 * Model object representing a single donation location, holding location and contact information 
 *
 * @author mlewis61@gatech.edu
 */
public class Location implements Serializable {
	
	private String name;
	
	private LocationType locationType;
	
	private double longitude;
	private double latitude;
	
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
		
		return that.getName().equals(this.name)
				&& (that.getLocationType() == this.locationType)
				&& (that.getLongitude() == this.longitude)
				&& (that.getLatitude() == this.latitude)
				&& that.getAddress().equals(this.address)
				&& that.getCity().equals(this.city)
				&& that.getState().equals(this.state)
				&& that.getZip().equals(this.zip)
				&& that.getPhoneNumber().equals(this.phoneNumber);
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
	 * setter for location's name
	 * @param name location's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter for type of location
	 * @return type of location
	 */
	public LocationType getLocationType() {
		return locationType;
	}

	/**
	 * setter for type of location
	 * @param locationType location's new type
	 */
	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	/**
	 * getter for location longitude
	 * @return location's longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * setter for location longitude
	 * @param longitude location's new longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * getter for location latitude
	 * @return location's latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * setter for location's latitude
	 * @param latitude location's new latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
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
	 * setter for location's address
	 * @param address location's new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * getter for location's phone number
	 * @return location's phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * setter method for location's phone number
	 * @param phoneNumber location's new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * getter method for location's city
	 * @return location's city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * setter method for location's city
	 * @param city location's new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * getter method for location's state
	 * @return location's state
	 */
	public String getState() {
		return state;
	}

	/**
	 * setter method for location's state
	 * @param state location's new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * getter method for location's zip code
	 * @return location's zip code
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * setter method for location's zip code
	 * @param zip location's new zip code
	 */
	public void setZip(String zip) {
		this.zip = zip;
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
