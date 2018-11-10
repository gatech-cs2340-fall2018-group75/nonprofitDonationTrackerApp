package edu.gatech.cs2340.group75.donationtracker.model;


import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Singleton holder for the app's list of locations
 *
 * @author mlewis61@gatech.edu
 */
public final class Locations implements Serializable {
    private static final Locations _instance = new Locations();
	
	/**
	 * Get the instance of this singleton class
	 *
	 * @return the single instance of this class
	 */
    public static Locations getInstance() { return _instance; }
	
	
    private List<Location> locations;

    private Locations() {
        locations = new ArrayList<>();
    }

    /**
     * getter method for list of registered locations
     * @return list of registered donation centers
     */
    public List<Location> get() {
        return Collections.unmodifiableList(locations);
    }

    /**
     * getter method for names of registered donation centers
     * @return list of names of registered donation centers
     */
    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        for (Location l : locations) {
            if(l != null && !names.contains(l.getName())) {
                names.add(l.getName());
            }
        }
        return names;
    }

    /**
     * Check if the given name and address exists in the list of locations 
	 *
     * @param	name	the name of the location being checked
     * @param	address	the address of the location being checked
	 *
     * @return	`true` if the name and address is shared by another location
     */
    public boolean contains(String name, String address) {
        for (Location place : locations) {
            if (place.getName().equals(name) && place.getAddress().equals(address)) {
                return true;
			}
        }
        return false;
    }

    /**
     * method searching for a location in the set of registered locations
     * @param location location to be searched for
     * @return true if location is in list, false otherwise
     */
    public boolean contains(Location location) {
        return locations.contains(location);
    }

    /**
     * removes a location from the list of locations
     * @param location location to be removed
     * @return true if removing was successful
     */
    public boolean remove(Location location) {
        return locations.remove(location);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Location location : locations) {
            str.append(location);
			str.append("\n, ");
        }
        return str.toString();
    }

    /**
     * sets a list of locations
     * @param locations list of locations to be set
     */
    @SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
    public void set(List<Location> locations) {
    	this.locations = locations;
	}
}
