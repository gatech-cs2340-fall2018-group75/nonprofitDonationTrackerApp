package edu.gatech.cs2340.group75.donationtracker;




import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import edu.gatech.cs2340.group75.donationtracker.model.Location;

import static edu.gatech.cs2340.group75.donationtracker.model.LocationType.DR;
import static org.junit.Assert.assertEquals;

/**
 * Test of the Locations model class
 *
 * @author mhromiak3@gatech.edu
 */
//Unit tests do not need documentation
@SuppressWarnings("JavaDoc")
public class LocationsTest {
    private static final int TIMEOUT = 200;
    private static final List<Location> currReg = new ArrayList<>(Location.getLocationsList());
	
	//This test compares this and a List from the Model, so the ordering should be identical
    @SuppressWarnings("TypeMayBeWeakened")
    private static final List<String> answer = new ArrayList<>();

	//This unit test explicitly checks constant conditions to ensure they evaluate as expected
	//Magic numbers are not an issue in unit tests, as it is just testing data
	//This unit test is testing the location class, so feature envy of it can be ignored
    @SuppressWarnings({"ConstantConditions", "MagicNumber", "FeatureEnvy"})





    //tests the "for" branch case where no locations are added in the singleton
    @Test(timeout = TIMEOUT)
    public void testNoContents() {
        assertEquals(answer, Location.getLocationNames());
    }

	//Magic numbers are not an issue in unit tests, as it is just testing data
	@SuppressWarnings("MagicNumber")
    //tests the "for" branch when items are in the singleton
    @Test(timeout = TIMEOUT)
    public void testWithContents() {
        Location place1 = new Location("DropOff1", DR, 38.8951, 38.8951);
        place1.setContactInfo("111 temp Way", "Fort Myers", "FL", "33993", "(345)135-1468");
        //This is not a typo, just a fictional name
        //noinspection SpellCheckingInspection
        Location RLyeh = new Location("R'lyeh", DR, 47.9, 126.43);
        RLyeh.setContactInfo
                (
                        "Madness Way", "Pacific Ocean", "The Beyond", "00000",
                        "(366)822-5548"
                );

        currReg.add(place1);
        currReg.add(RLyeh);
        Location.setLocationsList(currReg);
        answer.add("DropOff1");
        answer.add("R'lyeh");
        assertEquals(answer, Location.getLocationNames());
        answer.clear();
    }

	//Magic numbers are not an issue in unit tests, as it is just testing data
	@SuppressWarnings("MagicNumber")
    //tests the "for" branch when Null item is in the singleton
    @Test(timeout = TIMEOUT)
    public void testWithNull() {
        Location place1 = new Location("DropOff1", DR, 38.8951, 38.8951);
        place1.setContactInfo("111 temp Way", "Fort Myers", "FL", "33993", "(345)135-1468");
        //This is not a typo, just a fictional name
        //noinspection SpellCheckingInspection
        Location RLyeh = new Location("R'lyeh", DR, 47.9, 126.43);
        RLyeh.setContactInfo
                (
                        "Madness Way", "Pacific Ocean", "The Beyond", "00000",
                        "(366)822-5548"
                );
        
        currReg.add(place1);
        currReg.add(RLyeh);
        currReg.add(null);

        Location.setLocationsList(currReg);
        answer.add("DropOff1");
        answer.add("R'lyeh");
        assertEquals(answer, Location.getLocationNames());

    }
}
