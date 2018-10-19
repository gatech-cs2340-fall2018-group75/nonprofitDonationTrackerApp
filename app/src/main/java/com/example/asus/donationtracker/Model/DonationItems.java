package com.example.asus.donationtracker.Model;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DonationItems implements Serializable {

    private static final DonationItems _instance = new DonationItems();
    public static DonationItems getInstance() { return _instance; }
    private ArrayList<DonationItem> donationItems;

    private DonationItems() {
        donationItems = new ArrayList<>();
    }

    public boolean add(DonationItem item) {
        donationItems.add(item);
        return true;
    }

    public List<DonationItem> getByLocation(Location location) {
        List<DonationItem> matchingLocations = new ArrayList<>();
        for (DonationItem item : donationItems) {
            if (item.getLocation().equals(location)) {
                matchingLocations.add(item);
            }
        }
        return matchingLocations;
    }

    public boolean contains(DonationItem item) {
        return donationItems.contains(item);
    }

    public String toString() {
        String str = "";
        for (DonationItem item : donationItems) {
            str += item.getName() + "\n, ";
        }
        return str;
    }
}
