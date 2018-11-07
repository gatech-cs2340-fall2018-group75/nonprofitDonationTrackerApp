package edu.gatech.cs2340.group75.donationtracker.Model;

import android.util.Log;

import java.util.ArrayList;


public class UserSingleton {

    private static final UserSingleton _instance = new UserSingleton();
    public static UserSingleton getInstance() { return _instance; }
    private User user;


    private UserSingleton() {
        user = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return user.toString();
    }
}
