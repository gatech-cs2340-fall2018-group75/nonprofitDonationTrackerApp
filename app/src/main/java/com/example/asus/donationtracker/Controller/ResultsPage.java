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
import com.example.asus.donationtracker.Model.DonationItems;
import com.example.asus.donationtracker.Model.Location;
import com.example.asus.donationtracker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for viewing search results stored in donation items singleton
 *
 * @author Markian Hromiak
 * @see ItemSearch
 * @see DonationItems
 * @see DonationItem
 */
public class ResultsPage extends AppCompatActivity {

    private ListView resultsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page_);

        resultsList = findViewById(R.id.resultList);
        List<DonationItem> results = DonationItems.getInstance().get();

        if (results.size() > 0) {
            ArrayAdapter<DonationItem> resultContents = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1 , results);
                    resultsList.setAdapter(resultContents);
        } else {
            setContentView(R.layout.activity_results_page_no_results);
        }
    }

}
