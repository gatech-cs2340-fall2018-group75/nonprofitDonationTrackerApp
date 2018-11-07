package edu.gatech.cs2340.group75.donationtracker.Model;

public enum AccountType {
    ADMIN ("Administrator"),
    USER ("User"),
    LOCEMP ("Location Employee");

    private final String type;

    AccountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return type;
    }
}
