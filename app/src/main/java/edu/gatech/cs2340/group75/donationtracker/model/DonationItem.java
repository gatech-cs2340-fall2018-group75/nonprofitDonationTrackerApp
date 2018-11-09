package edu.gatech.cs2340.group75.donationtracker.model;

import java.io.Serializable;

public class DonationItem implements Serializable {

    //TODO image field

    /** Item's name**/
    private String name;

    /** Short description of item **/
    //TODO 150 char limit???
    private String description;

    private String locationName;

    private double value;

    private DonationItemType category;

    /**
     * Creates a new item.
     * @param name item's name
     * @param description item's description
     * @param locationName the location where the item is located
     * @param value The quantity of items available
     * @param category The category type of the item. This is for search purposes
     */
    public DonationItem(String name, String description, String locationName, double value, DonationItemType category) {
        this.name = name;
        this.description = description;
        this.locationName = locationName;
        this.value = value;
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

    /**
    * This is the getter method for name.
    * @return the item name
    **/
    public String getName() {return name;}

    /**
    * This is the setter method for name.
    * @param itemName item name to set.
    **/
    public void setName(String itemName) {name = itemName;}

    /**
    * This is the getter method for description.
    * @return the description of the item
    **/
    public String getDescription() {return description;}
    /**
    * This is the setter method for description.
    * @param newDescription the new description of the item.
    **/
    public void setDescription(String newDescription) {description = newDescription;}
    /**
    * This is the getter method for location name.
    * @return the location name.
    **/
    public String getLocationName() {return locationName;}
    /**
    * This is the setter method for location Name.
    * @param newName the new location of the item
    **/
    public void setLocationName(String newName) {locationName = newName;}
    /**
    * This is the getter method for the quantity
    * @return the quantity
    **/
    public double getValue() { return value;}
    /**
    * This is the setter method for quantity
    * @param quantity the new quantity
    **/
    public void setValue(double quantity) {value = quantity;}
    /**
    * This is the getter method for the category type of the item
    * @return the category of the item
    **/
    public DonationItemType getCategory() { return category;}
    /**
    * This is the setter method for the category type of the item
    * @param newType the new category type
    **/
    public void setCategory(DonationItemType newType) {category = newType;}



    @Override
    public String toString() {
        return (name + ":" + description + ":" + locationName);
    }

}
