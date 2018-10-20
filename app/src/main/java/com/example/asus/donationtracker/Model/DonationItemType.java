package com.example.asus.donationtracker.Model;

public enum DonationItemType {
    DEFAULT ("Choose a category"),
    TOYSANDGAMES ("Toys and Games"),
    FURNITURE ("Furniture"),
    OUTDOOR ("Outdoor"),
    ELECTRONICS ("Electronics"),
    NECESSITIES ("Necessities"),
    SCHOOL ("School"),
    BOOKS ("Books"),
    KITCHEN ("Kitchen supplies"),
    FOOD ("Food"),
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
