package com.example.asus.donationtracker.Model;

import android.util.Log;

import java.util.ArrayList;


public class Users {

    private static final Users _instance = new Users();
    public static Users getInstance() { return _instance; }
    private ArrayList<User> users;
	
	private User currentUser;


    private Users() {
        users = new ArrayList<>();
    }

    public boolean add(User user) {
        users.add(user);
        return true;
    }
	
	public User get(String email, String password) {
		for (User user : users) {
            if (user.getPassword().equals(password) && user.getEmail().equals(email)) {
                return user;
			}
        }
		
        return null;
	}

    public boolean contains(String email, String password) {
        return (get(email, password) != null);
    }

    public boolean contains(User user) {
        return users.contains(user);
    }

    public boolean remove(User user) {
        users.remove(user);
        return true;
    }

    public String toString() {
        String str = "";
        for (User user : users) {
            str += user + "\n, ";
        }
        return str;
    }
	
	
	public User getCurrentUser()
	{
		return currentUser;
	}
	
	public void setCurrentUser(User user)
	{
		currentUser = user;
	}
}
