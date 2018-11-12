package edu.gatech.cs2340.group75.donationtracker.model;

import android.support.annotation.NonNull;


/**
 * Enum for the type of a location
 *
 * @author bholmes34@gatech.edu
 * @author mhromiak3@gatech.edu
 * @author mlewis61@gatech.edu
 * @author spadi29@gatech.edu
 * @author sszczepaniuk3@gatech.edu
 */
public enum LocationType {
    DR ("Drop Off"),
	//These two enum types are actually used, but only through the inherited `valueOf`
	//Thus, the lint rules think the types aren't used since they're never directly accessed
    @SuppressWarnings("unused") ST ("Store"),
    @SuppressWarnings("unused") WA ("Warehouse");

    private final String type;

    LocationType(String type) {
        this.type = type;
    }

    /**
     * getter method for enum
     * @return string type of location
     */
	//This is a model class, and accessing the type directly may be used in the future
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
