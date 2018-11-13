package edu.gatech.cs2340.group75.donationtracker.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

/**
 * Model object representing a single donation item, holding description and location information
 *
 * @author bholmes34@gatech.edu
 * @author mhromiak3@gatech.edu
 * @author mlewis61@gatech.edu
 * @author spadi29@gatech.edu
 * @author sszczepaniuk3@gatech.edu
 */
public class DonationItem implements Serializable {
	
	private static List<DonationItem> itemsList = new ArrayList<>();
	
	/**
	 * Get the static list of donation items for the app
	 *
	 * @return an unmodifiable reference to the list of donation items
	 */
	public static List<DonationItem> getItemsList() {
		return Collections.unmodifiableList(itemsList);
	}
	
	/**
	 * Set the static list of donation items for the app
	 *
	 * @param	list	the new list of items to be used for the app
	 */
	public static void setItemsList(List<DonationItem> list) {
		itemsList = Collections.unmodifiableList(list);
	}
	
	
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
                && that.description.equals(this.description)
                && that.locationName.equals(this.locationName)
                && (that.value == this.value)
                && that.category.equals(this.category));
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
	
	/**
	 * Get the string version of this item's category
	 * This method only exists to silence Law of Demeter lint issues
	 *
	 * @return the item's category in string form
	 */
	public String getCategoryString() {
		return category.toString();
	}



    @NonNull
    @Override
    public String toString() {
        return (name + ":" + description + ":" + locationName);
    }
	
	public JSONObject toJson() {
		return new JSONObject(
			"{" +
				"\"name\": \"" + name + "\", " +
				"\"description\": \"" + description + "\", " +
				"\"category\": \"" + name + "\", " +
				"\"location\": \"" + locationName + "\", " +
				"\"value\": \"" + value + "\"" +
			"}"
		);
	}
}
