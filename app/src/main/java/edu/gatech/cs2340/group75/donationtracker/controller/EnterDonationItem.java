package edu.gatech.cs2340.group75.donationtracker.controller;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import edu.gatech.cs2340.group75.donationtracker.model.AccountType;
import edu.gatech.cs2340.group75.donationtracker.model.DonationItem;
import edu.gatech.cs2340.group75.donationtracker.model.DonationItemType;
import edu.gatech.cs2340.group75.donationtracker.model.DonationItems;
import edu.gatech.cs2340.group75.donationtracker.model.Location;
import edu.gatech.cs2340.group75.donationtracker.R;

import java.net.HttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Activity for entering donation item fields and submitting to database
 *
 * <p>Queries database with constructed json string generated from User object
 *
 * @author Markian Hromiak
 * @author Benjamin Holmes
 * @see Location
 * @see DonationItem
 */
public class EnterDonationItem extends AppCompatActivity implements View.OnClickListener {

    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView pic;
    private EditText name;
    private EditText description;
    private EditText value;
    private Button submit;
    private Button imgBtn;
    private Location location;
    private Spinner catSpinner;

    /**
     * Method called on activity creation to initialize layout elements
     * Sets list adapters and click listeners
     * @param savedInstanceState Current instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_donation_item);
        location = (Location) getIntent().getSerializableExtra("LOCATION");
        submit = (Button) findViewById(R.id.submit_donation_item);
        submit.setOnClickListener(this);
        imgBtn = (Button) findViewById(R.id.addPicBtn);
        imgBtn.setOnClickListener(this);
        Spinner typesSpinner = (Spinner) findViewById(R.id.itemType);
        ArrayAdapter<Enum> adapter = new ArrayAdapter
		(
			this,
			android.R.layout.simple_spinner_item,
			DonationItemType.values()
		);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typesSpinner.setAdapter(adapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.itemPic);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


    }

    /**
     * Method to handle all button clicks in activity
     * @param v View clicked by user to check against known button IDs
     */

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.addPicBtn :
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;
            case R.id.submit_donation_item :
                name = (EditText) findViewById(R.id.itemName);
                description = (EditText) findViewById(R.id.itemDesc);
                String title = name.getText().toString();
                value = findViewById(R.id.enter_donation_value);
                catSpinner = (Spinner) findViewById(R.id.itemType);
                DonationItemType submittedType = (DonationItemType) catSpinner.getSelectedItem();
                if (!title.equals("")) {

                    if (!submittedType.toString().equals("Choose a category")) {
                        DonationItem item = new DonationItem
						(
							title, description.getText().toString(),
							location.getName(),
							Double.parseDouble(value.getText().toString()),
							submittedType
						);
                        try {
                            submitDonationItem(item);
                        } catch (JSONException e) {
                            Toast.makeText
							(
								this.getBaseContext(),
								"There was a problem submitting your donation",
								Toast.LENGTH_LONG
							).show();
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText
						(
							getApplicationContext(),
							"Choose a category for your donation",
							Toast.LENGTH_SHORT
						).show();
                    }

                }
                else {
                Toast.makeText(getApplicationContext(), "Give the name of your donation item",
                        Toast.LENGTH_SHORT).show();
                }
                break;
            default :
                break;
        }
    }
    private void submitDonationItem(DonationItem item) throws JSONException {
        String URL=getString(R.string.API_base) + "/donationitems/add";
        Log.d("REST response", "starting... " + URL);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JSONObject jsonBody = new JSONObject("{" +
                "\"name\": \"" + item.getName() + "\", " +
                "\"description\": \"" + item.getDescription()+ "\", " +
                "\"category\": \"" + item.getCategory().name() + "\", " +
                "\"location\": \"" + item.getLocationName() + "\", " +
                "\"value\": \"" + item.getValue() + "\"" +
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
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REST response", error.toString());
						NetworkResponse response = error.networkResponse;
                        if (response != null && response.statusCode == HttpURLConnection.HTTP_CONFLICT) {
                            Toast.makeText(context, "Item already exists",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "There was a problem submitting your donation",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        requestQueue.add(request);
    }

}
