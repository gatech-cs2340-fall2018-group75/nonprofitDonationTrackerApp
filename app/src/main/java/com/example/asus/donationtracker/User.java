package com.example.asus.donationtracker;


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

    /**
     * Creates a new user
     * @param email user's email address
     * @param password user's password
     */
    public User(String email, String password) {
        isLoggedIn = false;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User maybeSame = (User) o;

        return (((User) o).getEmail().equals(this.email)
                && ((User) o).getPassword().equals(this.getPassword())
                && (((User) o).isLoggedIn == this.isLoggedIn));
    }


    /* *************************************
     * All property getters and setters
     */

    public boolean getIsLoggedIn() {return isLoggedIn;}
    public void setIsLoggedIn(boolean status) {isLoggedIn = status;}

    public String getEmail() {return email;}
    public void setEmail(String address) {email = address;}

    public String getPassword() {return password;}
    public String setPassword(String updated) {password = updated;}

    /**********************************************/


    @Override
    public String toString() {
        return (email + ":" + password);
    }

}
