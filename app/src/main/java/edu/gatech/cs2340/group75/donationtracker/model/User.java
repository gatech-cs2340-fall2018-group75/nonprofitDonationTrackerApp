package edu.gatech.cs2340.group75.donationtracker.model;




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
                && (((User) o).isLoggedIn == this.isLoggedIn))
                && ((User) o).accountType.equals(this.accountType);

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
    public boolean getIsLoggedIn() {return isLoggedIn;}

    /**
     * setter method for user's isLoggedIn field
     * @param status current login status
     */
    public void setIsLoggedIn(boolean status) {isLoggedIn = status;}

    /**
     * getter method for user's e-mail
     * @return user's e-mail
     */
    public String getEmail() {return email;}

    /**
     * setter method for user's e-mail
     * @param address user's new e-mail
     */
    public void setEmail(String address) {email = address;}

    /**
     * getter method for user's password
     * @return user's password
     */
    public String getPassword() {return password;}

    /**
     * setter method for user's password
     * @param updated user's new password
     */
    public void setPassword(String updated) {password = updated;}

    /**
     * getter method for user's account tye
     * @return user's account type
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * setter method for user's account type
     * @param type user's new account type
     */
    public void setAccountType(AccountType type) {
        accountType = type;
    }

    /**********************************************/


    @Override
    public String toString() {
        return (email + ":" + password);
    }

}
