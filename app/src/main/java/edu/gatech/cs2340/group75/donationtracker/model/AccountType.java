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
    LOCATION_EMPLOYEE ("Location Employee");

    private final String type;

    AccountType(String type) {
        this.type = type;
    }

    /**
     * getter method for account type
     * @return account's type
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
