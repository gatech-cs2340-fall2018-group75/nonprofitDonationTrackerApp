package com.example.asus.donationtracker.Model;

import java.util.HashMap;

public class Users {

    private static final Users _instance = new Users();
    public static Users getInstance() { return _instance; }

    private HashMap<String, String> userAccts = new HashMap<>();


    private Users() {
        userAccts = new HashMap<>();
        loadDummyAccts();
    }





    public void addAccount(String email, String password){
        userAccts.put(email, password);
    }

    private void loadDummyAccts() {
        userAccts.put("donator3@yahoo.com","password3");
    }

    public String getPassword(String email) {
        String pass = userAccts.get(email);
        if (userAccts.get(email) == null) {
            return "There is not account associated with that e-mail";
        }
        return pass;
    }

    public boolean accountExists(String email) {
        return userAccts.get(email) != null;
    }

    public String removeAccount(String email) {
        userAccts.remove(email);
        return "The following acount was removed:" + email;
    }






}
