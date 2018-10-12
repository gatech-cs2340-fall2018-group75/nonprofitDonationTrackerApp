package com.example.asus.donationtracker.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Locations implements Serializable {
    private static final Locations _instance = new Locations();
    public static Locations getInstance() { return _instance; }
    private ArrayList<Location> locations;


    private Locations() {
        locations = new ArrayList<>();
    }

    public boolean add(Location place) {
        locations.add(place);
        return true;
    }

    public boolean contains(String name, String address) {
        for (Location place : locations) {
            if (place.getName().equals(name) && place.getAddress().equals(address))
                return true;
        }
        return false;
    }

    public boolean contains(Location location) {
        return locations.contains(location);
    }

    public boolean remove(Location location) {
        locations.remove(location);
        return true;
    }

    public String toString() {
        String str = "";
        for (Location location : locations) {
            str += location + "\n, ";
        }
        return str;
    }
}
