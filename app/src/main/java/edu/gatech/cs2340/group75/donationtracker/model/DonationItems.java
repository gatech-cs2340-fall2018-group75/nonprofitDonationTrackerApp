package edu.gatech.cs2340.group75.donationtracker.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Singleton holder for the app's list of donation items
 *
 * @author mlewis61@gatech.edu
 */
public final class DonationItems implements Serializable {

    private static final DonationItems itemsInstance = new DonationItems();
	
	/**
	 * Get the instance of this singleton class
	 *
	 * @return the single instance of this class
	 */
    public static DonationItems getInstance() { 
		return itemsInstance; 
	}
	
	
    private List<DonationItem> donationItems;

    private DonationItems() {
        donationItems = new ArrayList<>();
    }
    /**
    * This method is to get the item's list.
    * @return the list of items.
    **/
    public List<DonationItem> getItemsList() {
        return Collections.unmodifiableList(donationItems);
    }
    /**
    * This method is used to add a new item to the donationItem's list
    * @param item is the new item to add to the list.
    **/
    public void add(DonationItem item) {
        donationItems.add(item);
    }
    /**
    * This method is used to set the list of items.
    * @param items the list of items to set donationItems equal to.
    **/
    @SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
    public void setItemsList(List<DonationItem> items) {
        donationItems = items;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (DonationItem item : donationItems) {
            str.append(item.getName());
			str.append(":");
			str.append(item.getLocationName());
			str.append(",\n ");
        }
        return str.toString();
    }
}
