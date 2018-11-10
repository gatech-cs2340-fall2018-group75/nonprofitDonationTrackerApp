package edu.gatech.cs2340.group75.donationtracker.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Model object representing a single donation item, holding description and location information
 *
 * @author mlewis61@gatech.edu
 */
public class DonationItem implements Serializable {

    /** Item's name**/
    private final String name;

    /** Short description of item **/
    private final String description;

    private final String locationName;

    private final double value;

    private final DonationItemType category;

    /**
     * Creates a new item.
     * @param name item's name
     * @param description item's description
     * @param locationName the location where the item is located
     * @param value The quantity of items available
     * @param category The category type of the item. This is for search purposes
     */
    public DonationItem
	(
		String name, String description,
		String locationName,
		double value,
		DonationItemType category
	) {
        this.name = name;
        this.description = description;
        this.locationName = locationName;
        this.value = value;
        this.category = category;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DonationItem)) {
            return false;
        }
        DonationItem that = (DonationItem) other;

        return (that.name.equals(this.name)
                && that.description.equals(this.description));
    }
	
	@Override
	public int hashCode() {
		return 1;
	}

    /**
    * This is the getter method for name.
    * @return the item name
    **/
    public String getName() {return name;}

    /**
    * This is the getter method for description.
    * @return the description of the item
    **/
    public String getDescription() {return description;}
	
    /**
    * This is the getter method for location name.
    * @return the location name.
    **/
    public String getLocationName() {return locationName;}
	
    /**
    * This is the getter method for the quantity
    * @return the quantity
    **/
    public double getValue() { return value;}
    
    /**
    * This is the getter method for the category type of the item
    * @return the category of the item
    **/
    public DonationItemType getCategory() {
		return category;
	}
	
	public String getCategoryString() {
		return category.toString();
	}



    @NonNull
    @Override
    public String toString() {
        return (name + ":" + description + ":" + locationName);
    }

}
