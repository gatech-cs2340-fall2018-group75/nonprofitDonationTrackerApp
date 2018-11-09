package edu.gatech.cs2340.group75.donationtracker.model;

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

    public String getType() {
        return type;
    }

    public String toString() {
        return type;
    }

}
