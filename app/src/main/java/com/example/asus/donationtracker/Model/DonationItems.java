package com.example.asus.donationtracker.Model;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DonationItems implements Serializable {

    private static final DonationItems _instance = new DonationItems();
    public static DonationItems getInstance() { return _instance; }
    private List<DonationItem> donationItems;

    private DonationItems() {
        donationItems = new ArrayList<>();
    }

    public List<DonationItem> get() {
        return donationItems;
    }

    public boolean add(DonationItem item) {
        donationItems.add(item);
        return true;
    }

    public void set(List<DonationItem> items) {
        donationItems = items;
    }

    public String toString() {
        String str = "";
        for (DonationItem item : donationItems) {
            str += item.getName() + ":" + item.getLocationName() + ",\n ";
        }
        return str;
    }
}
