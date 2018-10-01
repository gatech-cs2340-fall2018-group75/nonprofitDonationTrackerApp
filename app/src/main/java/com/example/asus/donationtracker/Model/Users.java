package com.example.asus.donationtracker.Model;

import java.util.ArrayList;
import java.util.HashMap;

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

    public boolean contains(String password, String email) {
        for (User user : users) {
            if (user.getPassword().equals(password) && user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public boolean contains(User user) {
        return users.contains(user);
    }

    public String remove(User user) {
        users.remove(user);
        return "The following acount was removed:" + user.getEmail();
    }

    public String toString() {
        String str = "";
        for (User user : users) {
            str += user + "\n, ";
        }
        return str;
    }
}
