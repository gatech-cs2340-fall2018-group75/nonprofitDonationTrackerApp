package edu.gatech.cs2340.group75.donationtracker.model;

import android.support.annotation.NonNull;

/**
 * Enum for the type of account the user registered for
 *
 * @author mlewis61@gatech.edu
 */
public enum AccountType {
    ADMIN ("Administrator"),
    USER ("User"),
	///This is not a typo, just an abbreviation
    @SuppressWarnings("SpellCheckingInspection") LOCEMP ("Location Employee");

    private final String type;

    AccountType(String type) {
        this.type = type;
    }

    /**
     * getter method for account type
     * @return account's type
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
