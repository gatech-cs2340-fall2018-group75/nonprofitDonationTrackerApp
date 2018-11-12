package edu.gatech.cs2340.group75.donationtracker;

import  edu.gatech.cs2340.group75.donationtracker.model.AccountType;
import  edu.gatech.cs2340.group75.donationtracker.model.DonationItem;
import  edu.gatech.cs2340.group75.donationtracker.model.User;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test of nontrivial methods of the User Model
 *
 * @author bholmes34@gatech.edu
 */
//Unit tests do not need documentation
@SuppressWarnings("JavaDoc")
public class UserModelTest {
    @Test
    public void equalUsersEqualsReturnsTrue() {
        User fake1 = new User(
                "fake@gmail.com",
                "1234",
                AccountType.ADMIN
        );

        User fake2 = new User(
                "fake@gmail.com",
                "1234",
                AccountType.ADMIN
        );

        assertEquals(fake1, fake2);
    }

    @Test
    public void unequalUsersEqualsReturnsFalse() {
        User fake1 = new User(
                "fake@gmail.com",
                "1234",
                AccountType.ADMIN
        );

        User unequalFakes[] = {
                new User(
                        "totallyReal@gmail.com",
                        "1234",
                        AccountType.ADMIN
                ),
                new User(
                        "fake@gmail.com",
                        "5678",
                        AccountType.ADMIN
                ),
                new User(
                        "fake@gmail.com",
                        "1234",
                        AccountType.USER
                ),
                new User(
                        "fake@gmail.com",
                        "1234",
                        AccountType.ADMIN
                ),
                new User(
                        "veryUnequal@aol.com",
                        "password",
                        AccountType.LOCEMP
                )
        };

        for (User fake : unequalFakes) {
            assertNotEquals(fake1, fake);
        }
    }

	//This unit is explicitly checking constant conditions to ensure they evaluate as expected
    @SuppressWarnings("ConstantConditions")
    @Test
    public void nullUserEqualsReturnsFalse() {
        User fake1 = new User(
                "fake@gmail.com",
                "1234",
                AccountType.ADMIN
        );

		//This unit is explicitly checking for null-inequality
        //noinspection ObjectEqualsNull
        assertNotEquals(fake1, null);
    }

	//This unit is explicitly checking inconvertible types for inequality
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    public void notInstanceOfUserEqualsReturnsFalse() {
        User fake1 = new User(
                "fake@gmail.com",
                "1234",
                AccountType.ADMIN
        );

        assertNotEquals(fake1, new DonationItem("", "", "", 0, null));
    }
}
