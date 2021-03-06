package edu.gatech.cs2340.group75.donationtracker.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import edu.gatech.cs2340.group75.donationtracker.model.AccountType;
import edu.gatech.cs2340.group75.donationtracker.model.User;
import edu.gatech.cs2340.group75.donationtracker.R;

import java.net.HttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Activity for registering a new user account
 *
 * <p>Queries database with constructed json string generated from user object
 *
 * @author bholmes34@gatech.edu
 * @author mhromiak3@gatech.edu
 * @author mlewis61@gatech.edu
 * @author spadi29@gatech.edu
 * @author sszczepaniuk3@gatech.edu
 *
 * @see User
 */
public class RegisterAccount extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Spinner accountTypeSpinner;

    /**
     * Method called on activity creation to initialize layout elements
     * Sets click listeners and list adapter
     * @param savedInstanceState Current instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
		
		
		ActivityClasses.add(this.getClass(), MainMenu.class);

        email = findViewById(R.id.regEmail);
        pass = findViewById(R.id.regPass);
        accountTypeSpinner = findViewById(R.id.regAccountType);
        Button submitBtn = findViewById(R.id.registerBtn);
        TextView goToLoginLink = findViewById(R.id.regGoToLogin);

        ArrayAdapter<Enum> adapter = new ArrayAdapter<Enum>
		(
			this,
			android.R.layout.simple_spinner_item,
			AccountType.values()
		);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        goToLoginLink.setOnClickListener(new View.OnClickListener() {
			@Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterAccount.this, ActivityClasses.get("LogIn")));
            }
        });
    }

    private void submit() {
		Editable passwordText = pass.getText();
        String regPassword = passwordText.toString();
		
		Editable emailText = email.getText();
        String regEmail = emailText.toString();
        AccountType regAccountType = (AccountType) accountTypeSpinner.getSelectedItem();
        User newUser = new User(regEmail, regPassword, regAccountType);
        try {
            addUser(newUser);
        } catch (JSONException e) {
			Toast toast = Toast.makeText
			(
				this.getBaseContext(),
				"There was a problem registering your account",
				Toast.LENGTH_LONG
			);
			toast.show();
            e.printStackTrace();
        }

    }

    private void addUser(final User newUser) throws JSONException {
        String URL=getString(R.string.API_base) + "/users/add";
        Log.d("REST response", "starting... " + URL);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JSONObject jsonBody = newUser.toJson();
        final Context context = this.getBaseContext();
        Log.d("REST response", jsonBody.toString());

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        User.setCurrentUser(newUser);

                        Intent toMainMenu =  new Intent
						(
							RegisterAccount.this,
							ActivityClasses.get("MainMenu")
						);
                        startActivity(toMainMenu);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REST response", error.toString());
						NetworkResponse response = error.networkResponse;
						int conflict = HttpURLConnection.HTTP_CONFLICT;
                        if ((response != null) && (response.statusCode == conflict)) {
                            Toast toast = Toast.makeText(context, "Account already exists",
                                    Toast.LENGTH_LONG);
							toast.show();
                        } else {
							Toast toast = Toast.makeText
							(
								context,
								"There was a problem registering your account",
								Toast.LENGTH_LONG
							);
							toast.show();
                        }
                    }
                }
        );
        requestQueue.add(request);
    }
}
