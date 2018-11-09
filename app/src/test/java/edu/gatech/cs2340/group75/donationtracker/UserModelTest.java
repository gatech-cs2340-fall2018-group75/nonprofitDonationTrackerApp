package edu.gatech.cs2340.group75.donationtracker;

import  edu.gatech.cs2340.group75.donationtracker.model.AccountType;
import  edu.gatech.cs2340.group75.donationtracker.model.DonationItem;
import  edu.gatech.cs2340.group75.donationtracker.model.User;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test of nontrivial methods of the User Model
 */
public class UserModelTest {
    @Test
    public void equalUsersEqualsReturnsTrue() {
        User fake1 = new User(
                "fake@gmail.com",
                "1234",
                AccountType.ADMIN,
                false
        );

        User fake2 = new User(
                "fake@gmail.com",
                "1234",
                AccountType.ADMIN,
                false
        );

        assertTrue(fake1.equals(fake2));
    }

    @Test
    public void unequalUsersEqualsReturnsFalse() {
        User fake1 = new User(
                "fake@gmail.com",
                "1234",
                AccountType.ADMIN,
                false
        );

        User unequalFakes[] = {
                new User(
                        "totallyReal@gmail.com",
                        "1234",
                        AccountType.ADMIN,
                        false
                ),
                new User(
                        "fake@gmail.com",
                        "5678",
                        AccountType.ADMIN,
                        false
                ),
                new User(
                        "fake@gmail.com",
                        "1234",
                        AccountType.USER,
                        false
                ),
                new User(
                        "fake@gmail.com",
                        "1234",
                        AccountType.ADMIN,
                        true
                ),
                new User(
                        "veryUnequal@aol.com",
                        "password",
                        AccountType.LOCEMP,
                        true
                )
        };

        for (User fake : unequalFakes) {
            assertFalse(fake1.equals(fake));
        }
    }

    @Test
    public void nullUserEqualsReturnsFalse() {
        User fake1 = new User(
                "fake@gmail.com",
                "1234",
                AccountType.ADMIN,
                false
        );

        assertFalse(fake1.equals(null));
    }

    @Test
    public void notInstanceOfUserEqualsReturnsFalse() {
        User fake1 = new User(
                "fake@gmail.com",
                "1234",
                AccountType.ADMIN,
                false
        );

        assertFalse(fake1.equals(new DonationItem("", "", "", 0, null)));
    }
}
