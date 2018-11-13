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
            //wrong Name
			new DonationItem(
				"Fake Yeezys",
				"Wow someone paid $500 for shoes???",
				"Goodwill",
				5,
				DonationItemType.CLOTHES
			),
            //wrong Description and Name
			new DonationItem(
				"Discount Yeezys",
				"Chinese knockoffs are just as good.",
				"Goodwill",
				5,
				DonationItemType.CLOTHES
			),
            //wrong Location and Quantity
			new DonationItem(
				"Real Yeezys",
				"Wow someone paid $500 for shoes???",
				"Thrift Store",
				40,
				DonationItemType.CLOTHES
			),
            //wrong Name, Description, and Quantity
			new DonationItem(
				"Jordan 1's",
				"Made with real leather.",
				"Goodwill",
				1,
				DonationItemType.CLOTHES
			),
            //wrong Item type and Name
			new DonationItem(
				"Fake Yeezys",
				"Yeezys but edible this time",
				"Goodwill",
				5,
				DonationItemType.FOOD
			),
            //wrong Item type
			new DonationItem(
				"Real Yeezys",
				"Wow someone paid $500 for shoes???",
				"Goodwill",
				5,
				DonationItemType.FOOD
			),
            //wrong quantity
			new DonationItem(
				"Real Yeezys",
				"Wow someone paid $500 for shoes???",
				"Goodwill",
				9,
				DonationItemType.CLOTHES
			),
            //wrong location
			new DonationItem(
				"Real Yeezys",
				"Wow someone paid $500 for shoes???",
				"Thrift Store",
				5,
				DonationItemType.CLOTHES
			),
            // wrong description
			new DonationItem(
				"Real Yeezys",
				"Yeezys but edible this time",
				"Goodwill",
				5,
				DonationItemType.CLOTHES
			),
            //Wrong everything
			new DonationItem(
				"A lamp",
				"An inconspicuous lamp",
				"Rag-o-Rama",
				42,
				DonationItemType.FURNITURE
			),
		};

		for (DonationItem fakeItem : itemCollection) {
			assertNotEquals(item1, fakeItem);
		}
	}
	@Test
	public void sameObjectReturnsTrue() {
		DonationItem item1 = new DonationItem(
			"A white lamp",
			"A nice lamp to brighten up the room",
			"John's Store",
			2,
			DonationItemType.FURNITURE
		);
		assertEquals(item1, item1);
	}

	@Test
	public void differentClassReturnsFalse() {
		DonationItem item1 = new DonationItem(
			"Canned tuna",
			"Ah yes, canned tuna.",
			"GOODWILL",
			10,
			DonationItemType.FOOD
		);
		String string1 = "String beans";

		assertNotEquals(item1, string1);
	}
}
