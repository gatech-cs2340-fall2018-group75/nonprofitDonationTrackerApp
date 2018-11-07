package edu.gatech.cs2340.group75.donationtracker.Model;


import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class Locations implements Serializable {
    private static final Locations _instance = new Locations();
    public static Locations getInstance() { return _instance; }
    private List<Location> locations;


    private Locations() {
        locations = new ArrayList<>();
    }

    /**
     * getter method for list of registered loations
     * @return list of registered donation centers
     */
    public List<Location> get() {
        return locations;
    }

    /**
     * getter method for names of registeres donation centers
     * @return list of names of registered donation centers
     */
    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        for (Location l : locations) {
            names.add(l.getName());
        }
        return names;
    }

    /**
     * 
     * @param name
     * @param address
     * @return
     */
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

    public void set(List<Location> locations) {
    	this.locations = locations;
	}
}
