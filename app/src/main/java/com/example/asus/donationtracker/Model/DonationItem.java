package com.example.asus.donationtracker.Model;

import java.io.Serializable;

public class DonationItem implements Serializable {

    //TODO image field

    /** Item's name**/
    private String name;

    /** Short description of item **/
    //TODO 150 char limit???
    private String description;

    private Location parentLocation;

    private DonationItemType category;

    /**
     * Creates a new user
     * @param name item's name
     * @param description item's description
     */
    public DonationItem(String name, String description, Location parentLocation, DonationItemType category) {
        this.name = name;
        this.description = description;
        this.parentLocation = parentLocation;
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

    public Location getLocation() {return parentLocation;}
    public void setLocation(Location updated) {parentLocation = updated;}

    public DonationItemType getCategory() { return category;}
    public void setCategory(DonationItemType newType) {category = newType;}

    /**********************************************/


    @Override
    public String toString() {
        return (name + ":" + description + ":" + parentLocation);
    }

}
