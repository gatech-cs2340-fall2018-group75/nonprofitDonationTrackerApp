package edu.gatech.cs2340.group75.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.gatech.cs2340.group75.donationtracker.R;

/**
 * Activity displayed on startup with options to register as a new user or log in
 *
 * @author bholmes34@gatech.edu
 * @author mhromiak3@gatech.edu
 * @author mlewis61@gatech.edu
 * @author spadi29@gatech.edu
 * @author sszczepaniuk3@gatech.edu
 *
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
		
		
		ActivityClasses classes = new ActivityClasses();
		classes.add(this.getClass(), LogIn.class, RegisterAccount.class);
    }

    /**
     * Method to handle click events on page
     * @param v View clicked to compare against
     */
	@Override
    public void onClick(View v) {
		ActivityClasses classes = new ActivityClasses();
		
        if(v.getId() == R.id.welcomeLoginButton){
            Intent intent = new Intent(WelcomeScreen.this, classes.get("LogIn"));
            startActivity(intent);

        }else if(v.getId() == R.id.registerButton){
            Intent intent = new Intent(WelcomeScreen.this, classes.get("RegisterAccount"));
            startActivity(intent);
        }
    }
}

