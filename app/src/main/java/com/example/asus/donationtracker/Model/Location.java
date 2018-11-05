package com.example.asus.donationtracker.Model;


import android.util.Log;

import java.io.Serializable;


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
	
	
	public Location(String name, LocationType locationType, double longitude, double latitude, String address, String city, String state, String zip, String phoneNumber) {
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
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public LocationType getLocationType() {
		return locationType;
	}
	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
	@Override
	public String toString() {
		return (name + ", " + address + " : " + longitude + "," + latitude + " : " + phoneNumber);
	}
}
