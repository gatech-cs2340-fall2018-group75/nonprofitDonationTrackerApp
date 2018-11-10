package edu.gatech.cs2340.group75.donationtracker.model;


import android.support.annotation.NonNull;

/**
 * Created by Markian Hromiak on 9/24/2018
 *
 * Represents a single user and their information and login status
 *
 * Information holder
 */
public class User {
	
	private static User currentUser;
	
	public static User getCurrentUser() {
		return currentUser;
	}
	
	public static void setCurrentUser(User user) {
		currentUser = user;
	}
	
	
    /** Tells us if the user is currently logged in or not **/
    private final boolean isLoggedIn;

    /** User's e-mail used to login **/
    private final String email;

    /** User's password **/
    private final String password;

    private final AccountType accountType;

    /**
     * Creates a new user
     * @param email			user's email address
     * @param password		user's password
	 * @param accountType	user's account type
     */
    public User(String email, String password, AccountType accountType) {
        this(email, password, accountType, false);
    }

    /**
     * Chained constructor for a new user
     * @param email user's e-mail
     * @param password user's password
     * @param accountType user's account's type
     * @param isLoggedIn boolean flag telling the system if the user is currently logged in
     */
    public User(String email, String password, AccountType accountType, boolean isLoggedIn) {
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.isLoggedIn = isLoggedIn;

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User that = (User) other;
		
        return that.email.equals(this.email)
                && that.password.equals(this.getPassword())
                && (that.isLoggedIn == this.isLoggedIn)
                && that.accountType.equals(this.accountType);

    }
	
	@Override
	public int hashCode() {
		return 1;
	}


    /* *************************************
     * All property getters and setters
     */

    /**
     * getter method for user's isLoggedIn field
     * @return if user is logged in or not
     */
    @SuppressWarnings("unused")
    public boolean getIsLoggedIn() {return isLoggedIn;}

    /**
     * getter method for user's e-mail
     * @return user's e-mail
     */
    public String getEmail() {return email;}

    /**
     * getter method for user's password
     * @return user's password
     */
    public String getPassword() {return password;}

    /**
     * getter method for user's account tye
     * @return user's account type
     */
	public AccountType getAccountType() {
		return accountType;
	}
	
	public String getAccountTypeString() {
		return accountType.toString();
	}

    /**********************************************/


    @NonNull
    @Override
    public String toString() {
        return (email + ":" + password);
    }

}
