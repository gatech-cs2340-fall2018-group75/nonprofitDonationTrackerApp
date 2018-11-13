package edu.gatech.cs2340.group75.donationtracker.model;


import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents a single user and their information and login status
 *
 * @author bholmes34@gatech.edu
 * @author mhromiak3@gatech.edu
 * @author mlewis61@gatech.edu
 * @author spadi29@gatech.edu
 * @author sszczepaniuk3@gatech.edu
 */
public class User {
	
	private static User currentUser;
	
	/**
	 * Set the (static) currently-logged-in user
	 *
	 * @param	user	the current user to set as logged in
	 */
	public static void setCurrentUser(User user) {
		currentUser = user;
	}
	
	
	/**
	 * Get the email address of the currently-logged-in user
	 *
	 * @return the email address of the current user, or null if the user is null
	 */
	public static String getCurrentEmail() {
		if (currentUser == null) {
			return null;
		}
		
		return currentUser.email;
	}
	
	/**
	 * Get the account type of the currently-logged-in user
	 *
	 * @return the account type string of the current user, or null is the user is null
	 */
	public static String getCurrentAccountType() {
		if (currentUser == null) {
			return null;
		}
		
		return currentUser.accountType.toString();
	}

    /** User's e-mail used to login **/
    private final String email;

    /** User's password **/
    private final String password;

    private final AccountType accountType;
	
	
    /**
     * Creates a new user
     * @param email user's e-mail
     * @param password user's password
     * @param accountType user's account's type
     */
    public User(String email, String password, AccountType accountType) {
        this.email = email;
        this.password = password;
        this.accountType = accountType;
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
				&& that.password.equals(this.password)
                && that.accountType.equals(this.accountType);

    }
	
	@Override
	public int hashCode() {
		return 1;
	}

    /**********************************************/


    @NonNull
    @Override
    public String toString() {
        return (email + ":" + password);
    }
	
	/**
	 * Convert this object to a JSON string
	 *
	 * @return a JSON object representing this user object
	 *
	 * @throws	org.json.JSONException	when a JSON formatting error is encountered
	 */
	public JSONObject toJson() throws JSONException {
		return new JSONObject(
			"{" +
				"\"email\": \"" + email + "\", " +
				"\"password\": \"" + password + "\", " +
				"\"accountType\": \"" + accountType + "\", " +
				"\"isLoggedIn\": \"" + "true" + "\"" +
			"}"
		);
	}

}
