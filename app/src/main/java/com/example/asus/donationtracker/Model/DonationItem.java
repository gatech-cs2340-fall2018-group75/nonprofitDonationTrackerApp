package com.example.asus.donationtracker.Model;

import java.io.Serializable;

public class DonationItem implements Serializable {

    //TODO image field

    /** Item's name**/
    private String name;

    /** Short description of item **/
    //TODO 150 char limit???
    private String description;

    private String locationName;

    private DonationItemType category;

    /**
     * Creates a new user
     * @param name item's name
     * @param description item's description
     */
    public DonationItem(String name, String description, String locationName, DonationItemType category) {
        this.name = name;
        this.description = description;
        this.locationName = locationName;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DonationItem)) {
            return false;
        }
        DonationItem maybeSame = (DonationItem) o;

        return (((DonationItem) o).getName().equals(this.name)
                && ((DonationItem) o).getDescription().equals(this.description));
    }


    /* *************************************
     * All property getters and setters
     */



    public String getName() {return name;}
    public void setName(String address) {name = address;}

    public String getDescription() {return description;}
    public void setDescription(String updated) {description = updated;}

    public String getLocationName() {return locationName;}
    public void setLocationName(String updated) {locationName = updated;}

    public DonationItemType getCategory() { return category;}
    public void setCategory(DonationItemType newType) {category = newType;}

    /**********************************************/


    @Override
    public String toString() {
        return (name + ":" + description + ":" + locationName);
    }

}
