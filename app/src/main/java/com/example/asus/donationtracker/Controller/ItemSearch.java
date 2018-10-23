package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asus.donationtracker.Model.DonationItem;
import com.example.asus.donationtracker.Model.DonationItemType;
import com.example.asus.donationtracker.Model.DonationItems;
import com.example.asus.donationtracker.R;

import java.util.ArrayList;

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
                String[] searchTerms = searchName.getText().toString().split(" ");
                DonationItemType submittedType = (DonationItemType) searchSpinner.getSelectedItem();
                DonationItems inventory = DonationItems.getInstance();
                ArrayList<DonationItem> searchResults = new ArrayList<>();
                if (!searchSpinner.getSelectedItem().toString().equals("Choose a category")) {
                    for (DonationItem item : inventory.getByCategory(submittedType)) {
                        for (String term : searchTerms) {
                            String lowered = term.toLowerCase();
                            if (item.getName().toLowerCase().contains(lowered)
                                    & !searchResults.contains(item)) {
                                searchResults.add(item);
                            }
                        }
                    }

                    Intent goToResultsPage = new Intent(getBaseContext(), ResultsPage.class);
                    goToResultsPage.putExtra("RESULTS", searchResults);
                    goToResultsPage.putExtra("QUERY", searchName.getText().toString());
                    goToResultsPage.putExtra("CATEGORY", submittedType);
                    startActivity(goToResultsPage);
                } else {
                    Toast.makeText(getApplicationContext(), "Choose an item category",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


