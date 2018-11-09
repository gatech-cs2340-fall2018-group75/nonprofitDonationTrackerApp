package edu.gatech.cs2340.group75.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.gatech.cs2340.group75.donationtracker.R;

/**
 * Activity displayed on startup with options to register as a new user or log in
 *
 * @author Markian Hromiak
 * @see RegisterAccount
 * @see LogIn
 */
public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener {

    /**
     * Method called on activity creation to initialize layout elements
     * Sets click listeners to display either register or login view
     * @param savedInstanceState Current instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    /**
     * Method to handle click events on page
     * @param v View clicked to compare against
     */
	@Override
    public void onClick(View v) {
        if(v.getId() == R.id.welcomeLoginBtn){
            Intent intent = new Intent(WelcomeScreen.this, LogIn.class);
            startActivity(intent);

        }else if(v.getId() == R.id.registerbtn){
            Intent intent = new Intent(WelcomeScreen.this, RegisterAccount.class);
            startActivity(intent);
        }
    }
}

