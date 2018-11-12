package edu.gatech.cs2340.group75.donationtracker;
import  edu.gatech.cs2340.group75.donationtracker.model.DonationItem;
import  edu.gatech.cs2340.group75.donationtracker.model.DonationItemType;
import org.junit.Test;
 import static org.junit.Assert.*;
 /**
 * Donation Item Model tests
 *
 * @author sszczepaniuk3@gatech.edu
 */
//Unit tests do not need documentation
@SuppressWarnings("JavaDoc")
public class DonationItemModelTest {
	@Test
	public void nullItemReturnsFalse() {
		//This is not a true magic number, as it is just testing data
		//noinspection MagicNumber
		DonationItem item1 = new DonationItem(
			"Generic Clothes",
			"Some clothes we found in the garage",
			"Goodwill",
			24,
			DonationItemType.CLOTHES
		);
		assertNotEquals(item1, null);
	}
	
	@Test
	public void equalItemsReturnsTrue() {
		DonationItem item1 = new DonationItem(
		"Stylish clothes",
		"Wow those are some nice clothes",
		"Goodwill",
		5,
		DonationItemType.CLOTHES
		);
		DonationItem item2 = new DonationItem(
			"Stylish clothes",
			"Wow those are some nice clothes",
			"Goodwill",
			5,
			DonationItemType.CLOTHES
		);
		assertEquals(item1, item2);
	}
	
	//Misspelling of testing data does not indicate an issue
	//These are not true magic numbers, as it is just testing data
	@SuppressWarnings({"SpellCheckingInspection", "MagicNumber"})
	@Test
	public void unequalItemsReturnsFalse() {
		DonationItem item1 = new DonationItem(
			"Real Yeezys",
			"Wow someone paid $500 for shoes???",
			"Goodwill",
			5,
			DonationItemType.CLOTHES
		);
		DonationItem itemCollection[] = {
			new DonationItem(
				"Fake Yeezys",
				"Wow someone paid $500 for shoes???",
				"Goodwill",
				5,
				DonationItemType.CLOTHES
			),
			new DonationItem(
				"Discount Yeezys",
				"Chinese knockoffs are just as good.",
				"Goodwill",
				84,
				DonationItemType.CLOTHES
			),
			new DonationItem(
				"Real Yeezys",
				"Wow someone paid $500 for shoes???",
				"Thrift Store",
				40,
				DonationItemType.CLOTHES
			),
			new DonationItem(
				"Jordan 1's",
				"Made with real leather.",
				"Goodwill",
				1,
				DonationItemType.CLOTHES
			),
			new DonationItem(
				"Fake Yeezys",
				"Wow someone paid $800 for shoes???",
				"Goodwill",
				5,
				DonationItemType.CLOTHES
			),
		};
		
		for (DonationItem fakeItem : itemCollection) {
			assertNotEquals(item1, fakeItem);
		}
	}
}
