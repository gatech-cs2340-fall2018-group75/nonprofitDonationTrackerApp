package edu.gatech.cs2340.group75.donationtracker.Controller;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import edu.gatech.cs2340.group75.donationtracker.Model.AccountType;
import edu.gatech.cs2340.group75.donationtracker.Model.User;
import edu.gatech.cs2340.group75.donationtracker.Model.UserSingleton;
import edu.gatech.cs2340.group75.donationtracker.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Activity for registering a new user account
 *
 * <p>Queries database with constructed json string generated from user object
 *
 * @author Markian Hromiak
 * @author Benjamin Holmes
 * @see UserSingleton
 * @see User
 */
public class registerAccount extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Spinner accountTypeSpinner;
    private Button submitBtn;
    private TextView goToLoginLink;

    /**
     * Method called on activity creation to initialize layout elements
     * Sets click listeners and list adapter
     * @param savedInstanceState Current instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        email = findViewById(R.id.regEmail);
        pass = findViewById(R.id.regPass);
        accountTypeSpinner = findViewById(R.id.regAccountType);
        submitBtn = findViewById(R.id.registerBtn);
        goToLoginLink = findViewById(R.id.regGoToLogin);

        ArrayAdapter<Enum> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, AccountType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        goToLoginLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(registerAccount.this, LogIn.class));
            }
        });
    }

    private void submit() {
        String regPassword = pass.getText().toString();
        String regEmail = email.getText().toString();
        AccountType regAccountType = (AccountType) accountTypeSpinner.getSelectedItem();
        User newUser = new User(regEmail, regPassword, regAccountType);
        try {
            addUser(newUser);
        } catch (JSONException e) {
            Toast.makeText(this.getBaseContext(), "There was a problem registering your account",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    private void addUser(final User newUser) throws JSONException {
        String URL=getString(R.string.API_base) + "/users/add";
        Log.d("REST response", "starting... " + URL);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JSONObject jsonBody = new JSONObject("{" +
                "\"email\": \"" + newUser.getEmail() + "\", " +
                "\"password\": \"" + newUser.getPassword()+ "\", " +
                "\"accountType\": \"" + newUser.getAccountType().name() + "\", " +
                "\"isLoggedIn\": \"" + "true" + "\"" +
                "}");
        final Context context = this.getBaseContext();
        Log.d("REST response", jsonBody.toString());

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        UserSingleton userInstance = UserSingleton.getInstance();
                        userInstance.setUser(newUser);

                        Intent toMainMenu =  new Intent(registerAccount.this, MainMenu.class);
                        startActivity(toMainMenu);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REST response", error.toString());
                        if (error.networkResponse != null && error.networkResponse.statusCode == 409) {
                            Toast.makeText(context, "Account already exists",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "There was a problem registering your account",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        requestQueue.add(request);
    }
}
