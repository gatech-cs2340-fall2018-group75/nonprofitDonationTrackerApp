package edu.gatech.cs2340.group75.donationtracker.controller;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import edu.gatech.cs2340.group75.donationtracker.model.DonationItem;
import edu.gatech.cs2340.group75.donationtracker.model.DonationItemType;
import edu.gatech.cs2340.group75.donationtracker.model.DonationItems;
import edu.gatech.cs2340.group75.donationtracker.model.Locations;
import edu.gatech.cs2340.group75.donationtracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for searching for Donation Items
 *
 * <p>Queries database for search results and updates the donation items singleton with return list
 *
 * @author Markian Hromiak
 * @author Benjamin Holmes
 * @see DonationItem
 * @see DonationItems
 */
public class ItemSearch extends AppCompatActivity {
    private EditText searchName;
    private Spinner searchSpinner;
    private Spinner locationSpinner;

    private final String LOCATION_SPINNER_DEFAULT = "All locations";

    /**
     * Method called on activity creation to initialize layout elements
     * Sets list adapters and click listeners
     * @param savedInstanceState Current instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);
        Button submitSearch = findViewById(R.id.submitSearch);
        searchSpinner = findViewById(R.id.searchSpnr);
        locationSpinner = findViewById(R.id.locationSpnr);
        searchName = findViewById(R.id.srchName);
        ArrayAdapter<Enum> typeAdapter = new ArrayAdapter<Enum>
		(
			this,
			android.R.layout.simple_spinner_item,
			DonationItemType.values()
		);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchSpinner.setAdapter(typeAdapter);

        List<String> currentLocations = new ArrayList<>();
        currentLocations.add(LOCATION_SPINNER_DEFAULT);
		
		Locations locations = Locations.getInstance();
        currentLocations.addAll(locations.getNames());
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        currentLocations
                );
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        submitSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				Editable searchNameText = searchName.getText();
                String searchTerms = searchNameText.toString();
                DonationItemType submittedType = (DonationItemType) searchSpinner.getSelectedItem();
                String submittedLocation = (String) locationSpinner.getSelectedItem();
                getMatchingResults(searchTerms, submittedType, submittedLocation);
            }
        });
    }

    private void getMatchingResults
	(
		final String searchTerms,
		final DonationItemType searchType,
		final String searchLocation
	) {
        String query = "";
        if (!searchTerms.isEmpty()) {
            query += "terms=" + searchTerms;
		}
        if (searchType != DonationItemType.DEFAULT) {
            if (!query.isEmpty()) {
                query += "&";
			}
            query += "category=" + searchType.name();
        }
        if (!LOCATION_SPINNER_DEFAULT.equals(searchLocation)) {
            if (!query.isEmpty()) {
                query += "&";
			}
            query += "location=" + searchLocation;
        }

        //noinspection SpellCheckingInspection
        String URL=getString(R.string.API_base) + "/donationitems/search?" + query;
        Log.d("REST response", "starting... " + URL);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        DonationItems donationItemsInstance = DonationItems.getInstance();
                        List<DonationItem> items = new ArrayList<>();
                        try {
                            for(int i = 0; i < response.length(); i++) {
                                JSONObject json = response.getJSONObject(i);
                                DonationItem item = new DonationItem(
                                        json.getString("Name"),
                                        json.getString("Description"),
                                        json.getString("Location"),
                                        json.getDouble("Value"),
                                        DonationItemType.valueOf(json.getString("Category"))
                                );
                                items.add(item);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        donationItemsInstance.setItemsList(items);
                        Intent goToResultsPage = new Intent(getBaseContext(), ResultsPage.class);
                        goToResultsPage.putExtra("QUERY", searchTerms);
                        goToResultsPage.putExtra("CATEGORY", searchType);
                        startActivity(goToResultsPage);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REST response", error.toString());
                    }
                }
        );
        requestQueue.add(request);
    }
}


