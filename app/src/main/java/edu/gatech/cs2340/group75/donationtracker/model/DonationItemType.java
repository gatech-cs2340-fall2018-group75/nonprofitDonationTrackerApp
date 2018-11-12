package edu.gatech.cs2340.group75.donationtracker.model;

import android.support.annotation.NonNull;

/**
 * Enum for the category of a donation item
 *
 * @author bholmes34@gatech.edu
 * @author mhromiak3@gatech.edu
 * @author mlewis61@gatech.edu
 * @author spadi29@gatech.edu
 * @author sszczepaniuk3@gatech.edu
 */
public enum DonationItemType {
    DEFAULT ("All categories"),
	//This is not a typo, just a simplification
    @SuppressWarnings("SpellCheckingInspection") TOYSANDGAMES ("Toys and Games"),
    FURNITURE ("Furniture"),
    OUTDOOR ("Outdoor"),
    ELECTRONICS ("Electronics"),
    NECESSITIES ("Necessities"),
    SCHOOL ("School"),
    BOOKS ("Books"),
    KITCHEN ("Kitchen supplies"),
    FOOD ("Food"),
    CLOTHES ("Clothes"),
    HEALTH ("Health");




    private final String type;

    DonationItemType(String type) {
        this.type = type;
    }

	/**
     * Getter for the human-readable account type
	 *
     * @return human-readable donation item type
     */
	//This is a model class, and accessing the type directly may be used in the future
    @SuppressWarnings("unused")
    public String getType() {
        return type;
    }

    @NonNull
    public String toString() {
        return type;
    }

}
