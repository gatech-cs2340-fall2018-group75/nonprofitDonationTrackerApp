package edu.gatech.cs2340.group75.donationtracker.model;


public class UserSingleton {

    private static final UserSingleton _instance = new UserSingleton();
    public static UserSingleton getInstance() { return _instance; }
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
