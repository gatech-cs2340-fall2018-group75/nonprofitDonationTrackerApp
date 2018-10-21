package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asus.donationtracker.Model.DonationItem;
import com.example.asus.donationtracker.Model.DonationItemType;
import com.example.asus.donationtracker.Model.DonationItems;
import com.example.asus.donationtracker.R;
import com.example.asus.donationtracker.ResultsPage;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class ItemSearch extends AppCompatActivity implements View.OnClickListener {


    private Spinner searchSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);

        searchSpinner = (Spinner) findViewById(R.id.searchSpnr);
        ArrayAdapter<Enum> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, DonationItemType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchSpinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        EditText searchName = (EditText) findViewById(R.id.srchName);
        switch(v.getId()) {
            case R.id.submitSearch:
                String[] searchTerms = searchName.getText().toString().split(" ");
                DonationItemType submittedType = (DonationItemType) searchSpinner.getSelectedItem();
                DonationItems inventory = DonationItems.getInstance();
                ArrayList<DonationItem> searchResults = new ArrayList<>();
                if (!searchSpinner.getSelectedItem().toString().equals("Choose a category")) {
                    for (DonationItem item : inventory.getByCategory(submittedType)) {
                        for (String term : searchTerms) {
                            if (item.getName().contains(term)) {
                                searchResults.add(item);
                                continue;
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
    }

}
