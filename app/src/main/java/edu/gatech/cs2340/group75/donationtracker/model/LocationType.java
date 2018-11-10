package edu.gatech.cs2340.group75.donationtracker.model;

import android.support.annotation.NonNull;


/**
 * Enum for the type of a location
 *
 * @author mlewis61@gatech.edu
 */
public enum LocationType {
    DR ("Drop Off"),
    ST ("Store"),
    WA ("Warehouse");

    private final String type;

    LocationType(String type) {
        this.type = type;
    }

    /**
     * getter method for enum
     * @return string type of location
     */
    @SuppressWarnings("unused")
    public String getType() {
            return type;
    }

    @NonNull
    @Override
    public String toString() {
        return type;
    }
}
