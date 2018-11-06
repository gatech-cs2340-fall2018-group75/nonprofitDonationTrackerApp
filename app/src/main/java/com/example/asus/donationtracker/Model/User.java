package com.example.asus.donationtracker.Model;

import com.example.asus.donationtracker.R;


/**
 * Created by Markian Hromiak on 9/24/2018
 *
 * Represents a single user and their information and login status
 *
 * Information holder
 */
public class User {

    /** Tells us if the user is currently logged in or not **/
    private boolean isLoggedIn;

    /** User's e-mail used to login **/
    private String email;

    /** User's password **/
    private String password;

    private AccountType accountType;

    /**
     * Creates a new user
     * @param email user's email address
     * @param password user's password
     */
    public User(String email, String password, AccountType accountType) {
        this(email, password, accountType, false);
    }

    public User(String email, String password, AccountType accountType, boolean isLoggedIn) {
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.isLoggedIn = isLoggedIn;

    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        return (((User) o).getEmail().equals(this.email)
                && ((User) o).getPassword().equals(this.getPassword())
                && ((User) o).isLoggedIn == this.isLoggedIn)
                && ((User) o).accountType.equals(this.accountType);

    }


    /* *************************************
     * All property getters and setters
     */

    public boolean getIsLoggedIn() {return isLoggedIn;}
    public void setIsLoggedIn(boolean status) {isLoggedIn = status;}

    public String getEmail() {return email;}
    public void setEmail(String address) {email = address;}

    public String getPassword() {return password;}
    public void setPassword(String updated) {password = updated;}

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType type) {
        accountType = type;
    }

    /**********************************************/


    @Override
    public String toString() {
        return (email + ":" + password);
    }

}
