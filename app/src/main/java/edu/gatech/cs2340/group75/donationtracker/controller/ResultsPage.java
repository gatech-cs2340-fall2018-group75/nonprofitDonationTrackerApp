package edu.gatech.cs2340.group75.donationtracker.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import edu.gatech.cs2340.group75.donationtracker.model.DonationItem;
import edu.gatech.cs2340.group75.donationtracker.R;

import java.util.List;

/**
 * Activity for viewing search results stored in donation items singleton
 *
 * @author Markian Hromiak
 * @see ItemSearch
 * @see DonationItem
 */
public class ResultsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page_);

        ListView resultsList = findViewById(R.id.resultList);
        List<DonationItem> results = DonationItem.getItemsList();

        if (!results.isEmpty()) {
            ListAdapter resultContents = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1 , results);
                    resultsList.setAdapter(resultContents);
        } else {
            setContentView(R.layout.activity_results_page_no_results);
        }
    }

}
