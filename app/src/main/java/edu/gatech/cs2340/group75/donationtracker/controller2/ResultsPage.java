package edu.gatech.cs2340.group75.donationtracker.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import edu.gatech.cs2340.group75.donationtracker.Model.DonationItem;
import edu.gatech.cs2340.group75.donationtracker.Model.DonationItemType;
import edu.gatech.cs2340.group75.donationtracker.Model.DonationItems;
import edu.gatech.cs2340.group75.donationtracker.Model.Location;
import edu.gatech.cs2340.group75.donationtracker.R;

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
        List<DonationItem> results = DonationItems.getInstance().getItemsList();

        if (results.size() > 0) {
            ArrayAdapter<DonationItem> resultContents = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1 , results);
                    resultsList.setAdapter(resultContents);
        } else {
            setContentView(R.layout.activity_results_page_no_results);
        }
    }

}
