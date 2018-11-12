package edu.gatech.cs2340.group75.donationtracker;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.gatech.cs2340.group75.donationtracker.model.Location;
import edu.gatech.cs2340.group75.donationtracker.model.LocationType;


public class LocationEqualsTest {
	
	private final String testName = "Georgia Tech";
	private final LocationType testType = LocationType.ST;
	private final double testLatitude = 33.7766;
	private final double testLongitude = -84.3982;
	
	private Location testLocation;
	
	
	@Before
	public void setUp() {
		testLocation = new Location (
			testName,
			testType,
			testLatitude, testLongitude
		);
	}
	
	@Test
	public void sameObjectReturnsTrue() {
		assertEquals(testLocation, testLocation);
	}
	
	@Test
	public void nullObjectReturnsFalse() {
		assertNotEquals(testLocation, null);
	}
	
	@Test
	public void otherClassReturnsFalse() {
		String testString = "Georgia Tech";
		
		assertNotEquals(testLocation, testString);
	}
	
	@Test
	public void samePropertiesReturnsTrue() {
		Location otherLocation = new Location(
			testName,
			testType,
			testLatitude, testLongitude
		);
		
		assertEquals(testLocation, otherLocation);
	}
	
	@Test
	public void differentNameReturnsFalse() {
		Location otherLocation = new Location(
			"university (sic) of Georgia",
			testType,
			testLatitude, testLongitude
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	@Test
	public void differentTypeReturnsFalse() {
		Location otherLocation = new Location(
			testName,
			LocationType.WA,
			testLatitude, testLongitude
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	@Test
	public void differentLatitudeReturnsFalse() {
		Location otherLocation = new Location(
			testName,
			testType,
			34.00, testLongitude
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	@Test
	public void differentLongitudeReturnsFalse() {
		Location otherLocation = new Location(
			testName,
			testType,
			testLatitude, -161.78
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	@Test
	public void differentNameAndTypeReturnsFalse() {
		Location otherLocation = new Location(
			"Georgia State University",
			LocationType.DR,
			testLatitude, testLongitude
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	@Test
	public void differentNameAndLatitudeReturnsFalse() {
		Location otherLocation = new Location(
			"Georgia Southern University",
			testType,
			-58.05, testLongitude
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	@Test
	public void differentNameAndLongitudeReturnsFalse() {
		Location otherLocation = new Location(
			"Kennesaw State University",
			testType,
			testLatitude, 160.50
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	@Test
	public void differentTypeAndLatitudeReturnsFalse() {
		Location otherLocation = new Location(
			testName,
			LocationType.WA,
			149.68, testLongitude
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	
	@Test
	public void differentTypeAndLongitudeReturnsFalse() {
		Location otherLocation = new Location(
			testName,
			testType,
			testLatitude, 50.02
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	@Test
	public void differentLatitudeAndLongitudeReturnsFalse() {
		Location otherLocation = new Location(
			testName,
			testType,
			49.06, -67.59
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	@Test
	public void onlySameLongitudeReturnsFalse() {
		Location otherLocation = new Location(
			"Emory University",
			LocationType.DR,
			131.35, testLongitude
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	@Test
	public void onlySameLatitudeReturnsFalse() {
		Location otherLocation = new Location(
			"Agnes Scott College",
			LocationType.WA,
			testLatitude, -10.71
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	@Test
	public void onlySameTypeReturnsFalse() {
		Location otherLocation = new Location(
			"Mercer University",
			testType,
			-49.38, -118.30
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	@Test
	public void onlySameNameReturnsFalse() {
		Location otherLocation = new Location(
			testName,
			LocationType.DR,
			0.50, -87.66
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	@Test
	public void allDifferentReturnsFalse() {
		Location otherLocation = new Location(
			"Morehouse College",
			LocationType.WA,
			9.07, -52.64
		);
		
		assertNotEquals(testLocation, otherLocation);
	}
	
	
	@Test
	public void differentContactInfoReturnsTrue() {
		Location location1 = new Location(
			testName,
			testType,
			testLatitude, testLongitude
		);
		
		Location location2 = new Location(
			testName,
			testType,
			testLatitude, testLongitude
		);
		
		
		location1.setContactInfo(
			"1 Rocket Rd", "McGregor", "TX", "76657",
			"(254) 840-5771"
		);
		location2.setContactInfo(
			"35961 State Highway 54", "Van Horn", "TX", "79855",
			"(253) 437-9300"
		);
		
		
		assertEquals(location1, location2);
	}
}
