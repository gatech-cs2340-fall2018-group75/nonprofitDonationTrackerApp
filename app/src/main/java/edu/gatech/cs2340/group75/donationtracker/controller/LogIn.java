package edu.gatech.cs2340.group75.donationtracker.controller;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import edu.gatech.cs2340.group75.donationtracker.model.AccountType;
import edu.gatech.cs2340.group75.donationtracker.model.User;
import edu.gatech.cs2340.group75.donationtracker.model.UserSingleton;
import edu.gatech.cs2340.group75.donationtracker.R;

import static android.Manifest.permission.READ_CONTACTS;


/**
 * Activity for logging in an existing user
 *
 * <p>Queries database with constructed json string to check for matching user
 * <p>Requests permission to access user contacts for auto-filling email field
 *
 * @author Markian Hromiak
 * @author Benjamin Holmes
 * @see UserSingleton
 * @see User
 */
public class LogIn extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    /**
     * Method called on activity creation to initialize layout elements
     * Sets click listeners and edit listeners
     * @param savedInstanceState Current instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        populateAutoComplete();

        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if ((id == EditorInfo.IME_ACTION_DONE) || (id == EditorInfo.IME_NULL)) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        TextView regFromLogin = findViewById(R.id.regFromLogin);
        regFromLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRegister = new Intent(LogIn.this, RegisterAccount.class);
                startActivity(toRegister);
            }
        });

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

		LoaderManager manager = getLoaderManager();
		manager.initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
			Snackbar snackbar = Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE);
			snackbar.setAction(android.R.string.ok, new View.OnClickListener() {
				@Override
				@TargetApi(Build.VERSION_CODES.M)
				public void onClick(View v) {
					requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
				}
			});
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback to populate autocomplete based on contact access permission
     * @param requestCode Code of permission request to check against
     * @param permissions Array of permissions user is able to grant
     * @param grantResults Array of permissions granted by users
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
			int granted = PackageManager.PERMISSION_GRANTED;
            if ((grantResults.length == 1) && (grantResults[0] == granted)) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
		Editable emailText = mEmailView.getText();
        String email = emailText.toString();
		
		Editable passwordText = mPasswordView.getText();
        String password = passwordText.toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            authenticateUser(email, password);
        }
    }

    private void authenticateUser(final String email, final String password) {
        String URL = getString(R.string.API_base);
		URL = URL + "/users/login?email=" + email;
		URL = URL + "&password=" + password;
		
        Log.d("REST response", "starting... " + URL);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("REST response", response.getString("accountType"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        UserSingleton userInstance = UserSingleton.getInstance();
                        try {
                            User user = new User(
                                    response.getString("email"),
                                    response.getString("password"),
                                    AccountType.valueOf(response.getString("accountType")),
                                    true
                            );
                            userInstance.setUser(user);

                            Intent toMainMenu =  new Intent(LogIn.this, MainMenu.class);
                            startActivity(toMainMenu);
                        } catch (JSONException e) {
                            mPasswordView.setError(getString(R.string.error_incorrect_password));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REST response", error.toString());
                        mPasswordView.setError(getString(R.string.error_incorrect_password));
                    }
                }
        );
        requestQueue.add(request);
    }


    private boolean isEmailValid(String email) {
        return (email.contains("@"));
    }

    /**
     * Method to create loader of user contacts
     * @param i Unused cursor in bundle
     * @param bundle Unused bundle of loader options
     * @return Cursor to track for load results
     */
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    /**
     * Callback on retrieval of user contacts
     * @param cursorLoader The loader object used
     * @param cursor Cursor to track for load results
     */
    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LogIn.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };
		
		int ADDRESS = 0;
    }
}

