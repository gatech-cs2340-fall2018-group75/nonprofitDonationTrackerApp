package edu.gatech.cs2340.group75.donationtracker;



import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import edu.gatech.cs2340.group75.donationtracker.model.Location;

import static edu.gatech.cs2340.group75.donationtracker.model.LocationType.DR;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("JavaDoc")
public class LocationsTest {
    private static final int TIMEOUT = 200;
    private static final List<Location> currReg = Location.getLocationsList();
    @SuppressWarnings("TypeMayBeWeakened")
    private static final List<String> answer = new ArrayList<>();

    @SuppressWarnings({"ConstantConditions", "MagicNumber"})
    @Before
    public void setUp() {
        Location place1 = new Location("DropOff1", DR, 38.8951, 38.8951);
		place1.setContactInfo("111 temp Way", "Fort Myers", "FL", "33993", "(345)135-1468");
        Location RLyeh = new Location("R'lyeh", DR, 47.9, 126.43);
		RLyeh.setContactInfo
		(
			"Madness Way", "Pacific Ocean", "The Beyond", "00000",
			"(366)822-5548"
		);
        Location nulled = null;
        currReg.add(place1);
        currReg.add(RLyeh);
        currReg.add(nulled);
        answer.clear();
        answer.add("DropOff1");
        answer.add(("R'lyeh"));
    }




    //tests the "for" branch case where no locations are added in the singleton
    @Test(timeout = TIMEOUT)
    public void testNoContents() {
		assertEquals(answer, Locations.getNames());
    }

    //tests the "for" branch when items are in the singleton
    @Test(timeout = TIMEOUT)
    public void testWithContents() {
        assertEquals(answer, Locations.getNames());

    }

    //tests the "for" branch when Null item is in the singleton
    @Test(timeout = TIMEOUT)
    public void testWithNull() {
        assertEquals(answer, Locations.getNames());

    }
}
