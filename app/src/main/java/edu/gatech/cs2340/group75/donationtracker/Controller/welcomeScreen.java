package edu.gatech.cs2340.group75.donationtracker.Controller;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import edu.gatech.cs2340.group75.donationtracker.R;

import org.json.JSONObject;

/**
 * Activity displayed on startup with options to register as a new user or log in
 *
 * @author Markian Hromiak
 * @see registerAccount
 * @see login
 */
public class welcomeScreen extends AppCompatActivity implements View.OnClickListener {

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
    public void onClick(View v) {
        if(v.getId() == R.id.welcomeLoginBtn){
            Intent intent = new Intent(welcomeScreen.this, login.class);
            startActivity(intent);

        }else if(v.getId() == R.id.registerbtn){
            Intent intent = new Intent(welcomeScreen.this, registerAccount.class);
            startActivity(intent);
        }
    }
}

