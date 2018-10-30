package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.donationtracker.Model.DonationItem;
import com.example.asus.donationtracker.Model.DonationItemType;
import com.example.asus.donationtracker.Model.DonationItems;
import com.example.asus.donationtracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemSearch extends AppCompatActivity {
    private EditText searchName;
    private Button submitSearch;
    private Spinner searchSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);
        submitSearch = (Button) findViewById(R.id.submitSearch);
        searchSpinner = (Spinner) findViewById(R.id.searchSpnr);
        searchName = (EditText) findViewById(R.id.srchName);
        ArrayAdapter<Enum> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, DonationItemType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchSpinner.setAdapter(adapter);

        submitSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerms = searchName.getText().toString();
                DonationItemType submittedType = (DonationItemType) searchSpinner.getSelectedItem();
                getMatchingResults(searchTerms, submittedType);
            }
        });
    }

    private void getMatchingResults(final String searchTerms, final DonationItemType searchType) {
        Log.d("Why", searchTerms + searchTerms.length());
        String query = (searchTerms.length() <= 0) ? "category="+searchType.name() : "terms="+searchTerms+"&category="+searchType.name();
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
                                        DonationItemType.valueOf(json.getString("Category"))
                                );
                                items.add(item);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        donationItemsInstance.set(items);
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


