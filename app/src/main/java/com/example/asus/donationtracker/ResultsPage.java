package com.example.asus.donationtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.example.asus.donationtracker.Controller.DonationItemsFragment;
import com.example.asus.donationtracker.Model.DonationItem;
import com.example.asus.donationtracker.Model.DonationItemType;

import java.util.ArrayList;


public class ResultsPage extends AppCompatActivity {

    private ArrayList<DonationItem> results;
    private String query;
    private DonationItemType donationType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page_);

        Bundle bd = getIntent().getExtras();
        results = (ArrayList<DonationItem>) bd.get("RESULTS");
        query = (String) bd.get("QUERY");
        donationType = (DonationItemType) bd.get("CATEGORY");


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(query == null ? "Search results for " + query + " in " +
                donationType.toString(): "Search results in " + donationType.toString());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        inflateInitialFragment();

    }

    private void inflateInitialFragment() {
        if(findViewById(R.id.ResultsFragmentContainer) == null)
            return;
        // set initial fragment layout to the home view
        DonationItemsFragment fragment = new DonationItemsFragment();
        Bundle args = new Bundle();
        args.putSerializable("RESULTS", results);
        fragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.ResultsFragmentContainer, fragment)
                .commit();
    }
}
