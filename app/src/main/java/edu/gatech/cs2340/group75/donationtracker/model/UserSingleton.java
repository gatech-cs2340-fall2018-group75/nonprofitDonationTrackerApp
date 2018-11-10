package edu.gatech.cs2340.group75.donationtracker.model;


/**
 * Singleton holder for the app's list of users
 *
 * @author mlewis61@gatech.edu
 */
public final class UserSingleton {

    private static final UserSingleton _instance = new UserSingleton();
	
	/**
	 * Get the instance of this singleton class
	 *
	 * @return the single instance of this class
	 */
    public static UserSingleton getInstance() { 
		return _instance;
	}
	
	public static User getInstanceUser() { 
		return _instance.getUser();
	}
	
	public static void setInstanceUser(User user) { 
		return _instance.setUser(user);
	}
	
	
    private User user;

    private UserSingleton() {
        //noinspection AssignmentToNull
        user = null;
    }

    /**
     * Getter method for our set of Users
     * @return User object from singleton
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets a User in our set
     * @param user User item to be set
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
