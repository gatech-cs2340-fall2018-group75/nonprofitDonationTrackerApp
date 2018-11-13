package edu.gatech.cs2340.group75.donationtracker.controller;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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

import edu.gatech.cs2340.group75.donationtracker.model.DonationItem;
import edu.gatech.cs2340.group75.donationtracker.model.DonationItemType;
import edu.gatech.cs2340.group75.donationtracker.model.Location;
import edu.gatech.cs2340.group75.donationtracker.R;

import java.net.HttpURLConnection;
import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Activity for entering donation item fields and submitting to database
 *
 * <p>Queries database with constructed json string generated from User object
 *
 * @author bholmes34@gatech.edu
 * @author mhromiak3@gatech.edu
 * @author mlewis61@gatech.edu
 * @author spadi29@gatech.edu
 * @author sszczepaniuk3@gatech.edu
 *
 * @see Location
 * @see DonationItem
 */
public class EnterDonationItem extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;
    private Location location;

    /**
     * Method called on activity creation to initialize layout elements
     * Sets list adapters and click listeners
     * @param savedInstanceState Current instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_donation_item);
		
		Intent intent = getIntent();
        location = (Location) intent.getSerializableExtra("LOCATION");
        Button submit = findViewById(R.id.submit_donation_item);
        submit.setOnClickListener(this);
        Button imgBtn = findViewById(R.id.addPicBtn);
        imgBtn.setOnClickListener(this);
        Spinner typesSpinner = findViewById(R.id.itemType);
        ArrayAdapter<Enum> adapter = new ArrayAdapter<Enum>
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

        if ((requestCode == RESULT_LOAD_IMAGE) && (resultCode == RESULT_OK) && (null != data)) {
            Uri selectedImage = Objects.requireNonNull(data.getData());
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

			ContentResolver resolver = getContentResolver();
            Cursor cursor = Objects.requireNonNull
			(
				resolver.query
				(
					selectedImage,
                    filePathColumn,
					null, null, null
				)
			);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = findViewById(R.id.itemPic);
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
                EditText name = findViewById(R.id.itemName);
                EditText description = findViewById(R.id.itemDesc);
				
				Editable nameText = name.getText();
                String title = nameText.toString();
                EditText value = findViewById(R.id.enter_donation_value);
                Spinner catSpinner = findViewById(R.id.itemType);
                DonationItemType submittedType = (DonationItemType) catSpinner.getSelectedItem();
                if (!"".equals(title)) {

                    if (!"Choose a category".equals(submittedType.toString())) {
						Editable descriptionText = description.getText();
						Editable valueText = value.getText();
                        DonationItem item = new DonationItem
						(
							title, descriptionText.toString(),
							location.getName(),
							Double.parseDouble(valueText.toString()),
							submittedType
						);
                        try {
                            submitDonationItem(item);
                        } catch (JSONException e) {
							Toast toast = Toast.makeText
							(
								this.getBaseContext(),
								"There was a problem submitting your donation",
								Toast.LENGTH_LONG
							);
							toast.show();
                            e.printStackTrace();
                        }
                    } else {
                        Toast toast = Toast.makeText
						(
							getApplicationContext(),
							"Choose a category for your donation",
							Toast.LENGTH_SHORT
						);
						toast.show();
                    }

                }
                else {
					Toast toast = Toast.makeText
					(
						getApplicationContext(),
						"Give the name of your donation item",
						Toast.LENGTH_SHORT
					);
					toast.show();
                }
                break;
            default :
                break;
        }
    }
	
    private void submitDonationItem(DonationItem item) throws JSONException {
		//The API URL is not a typo
        //noinspection SpellCheckingInspection
        String URL=getString(R.string.API_base) + "/donationitems/add";
        Log.d("REST response", "starting... " + URL);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JSONObject jsonBody = item.toJson();
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
						int conflict = HttpURLConnection.HTTP_CONFLICT;
                        if ((response != null) && (response.statusCode == conflict)) {
							Toast toast = Toast.makeText
							(
								context,
								"Item already exists",
								Toast.LENGTH_LONG
							);
							toast.show();
                        } else {
							Toast toast = Toast.makeText
							(
								context,
								"There was a problem submitting your donation",
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
