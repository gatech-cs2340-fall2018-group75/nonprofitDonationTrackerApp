package edu.gatech.cs2340.group75.donationtracker.model;

import android.support.annotation.NonNull;

/**
 * Enum for the category of a donation item
 *
 * @author mlewis61@gatech.edu
 */
public enum DonationItemType {
    DEFAULT ("All categories"),
    TOYSANDGAMES ("Toys and Games"),
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
    @SuppressWarnings("unused")
    public String getType() {
        return type;
    }

    @NonNull
    public String toString() {
        return type;
    }

}
