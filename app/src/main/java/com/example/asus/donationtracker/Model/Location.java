package com.example.asus.donationtracker.Model;

public class Location {

    /** Location's name**/
    private String name;

    /** Location's type**/
    private LocationType locationType;

    /** Location's longitude**/
    private String longitude;

    /** Location's latitude**/
    private String latitude;

    /** Location's address**/
    private String address;

    /** Location's phone number**/
    private String phoneNumber;



    /**
     * creates a new location
     */
    public Location(String name, LocationType locationType, String longitude, String latitude,
                    String address, String phoneNumber) {
        this.name = name;
        this.locationType = locationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        Location maybeSame = (Location) o;

        return (((Location) o).getName().equals(this.name)
                && ((Location) o).getLocationtype().equals(this.locationType)
                && (((Location) o).getLongitude() == this.longitude))
                && ((Location) o).getLatitude().equals(this.latitude)
                && ((Location) o).getAddress().equals(this.address)
                && ((Location) o).getPhoneNumber().equals(this.phoneNumber);

    }


    /* *************************************
     * All property getters and setters
     */

    public String getName() {return name;}
    public void setName(String call) {name = call;}

    public String getLocationtype() {return locationType.toString();}
    public void setLocationType(LocationType type) {locationType = type;}

    public String getLongitude() {return longitude;}
    public void setLongitude(String longy) {longitude = longy;}

    public String getLatitude() {return latitude;}
    public void setLatitude(String laty) {latitude = laty;}

    public String getAddress() {return address;}
    public void setAddress(String place) {address = place;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String num) {phoneNumber = num;}

    /**********************************************/


    @Override
    public String toString() {
        return (name + ", " + address + " : " + longitude + "," + latitude + " : " + phoneNumber);
    }
}
