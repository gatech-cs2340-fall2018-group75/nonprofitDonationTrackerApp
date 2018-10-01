package com.example.asus.donationtracker.Model;

import android.util.Log;

import java.util.ArrayList;

public class Users {

    private static final Users _instance = new Users();
    public static Users getInstance() { return _instance; }
    private ArrayList<User> users;


    private Users() {
        users = new ArrayList<>();
    }

    public boolean add(User user) {
        users.add(user);
        return true;
    }

    public boolean contains(String email, String password) {
        for (User user : users) {
            if (user.getPassword().equals(password) && user.getEmail().equals(email))
                return true;
        }
        return false;
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
}
