package com.example.asus.donationtracker.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.donationtracker.Controller.DonationItemsFragment;
import com.example.asus.donationtracker.Model.DonationItem;
import com.example.asus.donationtracker.Model.DonationItemType;
import com.example.asus.donationtracker.Model.Location;
import com.example.asus.donationtracker.R;

import java.util.ArrayList;


public class ResultsPage extends AppCompatActivity {

    private ArrayList<DonationItem> results;
    private String query;
    private DonationItemType donationType;
    private ListView resultsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page_);

        Bundle bd = getIntent().getExtras();
        results = (ArrayList<DonationItem>) bd.get("RESULTS");
        query = (String) bd.get("QUERY");
        donationType = (DonationItemType) bd.get("CATEGORY");
        resultsList = (ListView) findViewById(R.id.resultList);
        String noResults = "No current donations match your search criteria";

        if (results != null) {
            ArrayAdapter<DonationItem> resultContents = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1 ,results);
                    resultsList.setAdapter(resultContents);
        } else {
            Toast.makeText(getApplicationContext(),
                    "No donations marched your search criteria", Toast.LENGTH_LONG).show();

        }
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle(query == null ? "Search results for " + query + " in " +
//                donationType.toString(): "Search results in " + donationType.toString());
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//
//

    }

}
