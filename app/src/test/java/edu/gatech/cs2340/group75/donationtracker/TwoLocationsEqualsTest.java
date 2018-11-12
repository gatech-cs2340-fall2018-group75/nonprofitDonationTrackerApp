package edu.gatech.cs2340.nonprofitdonationtracker;

import org.junit.Test;

import edu.gatech.cs2340.group75.donationtracker.model.Location;
import edu.gatech.cs2340.group75.donationtracker.model.LocationType;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * test to check if two locations are recognized as equal
 */
public class TwoLocationsEqualsTest {

    /**
     * The test that checks if two locations are recognized as equal
     */
    @Test
    public void testEquals() {

        Location location1 = new Location(3, "Location", 12,
                                30, "52 Baker Street", "Evans", "GA",
                                "zipcode", LocationType.DROPOFF, 706750933);

        Location location2 = new Location(2, "Location 2", 52,
                60, "129 Harris Street", "Raleigh", "NC",
                "zipcode", LocationType.STORE, 12341231);

        Location location3 = new Location(3, "Location 3", 23,
                90, "123 Beach Drive", "Columbia", "SC",
                "zipcode", LocationType.WAREHOUSE, 12341231);

        Location location4 = null;

        assertEquals(location1,location1);
        assertNotEquals(location1, location2);
        assertNotEquals(location1, location4);
        assertEquals(location1, location3);


    }
}
