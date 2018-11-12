package edu.gatech.cs2340.group75.donationtracker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import edu.gatech.cs2340.group75.donationtracker.model.DonationItem;
import edu.gatech.cs2340.group75.donationtracker.R;

import java.text.NumberFormat;

/**
 * Activity for viewing details of specified donation item
 *
 * @author bholmes34@gatech.edu
 * @author mhromiak3@gatech.edu
 * @author mlewis61@gatech.edu
 * @author spadi29@gatech.edu
 * @author sszczepaniuk3@gatech.edu
 *
 * @see DonationItem
 */
public class DonationItemDetails extends AppCompatActivity {

    /**
     * Method called on activity creation to initialize layout elements
     * Retrieves donation item to display as intent extra
     * @param savedInstanceState Current instance state
     */
	//The entire point of Model classes is to separate features into distinct objects
	//Moving functionality from the model to this class will violate many design principles
    @SuppressWarnings("FeatureEnvy")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_item_details);
		
		Intent intent = getIntent();
        DonationItem item = (DonationItem) intent.getSerializableExtra("DONATION_ITEM");

        String name = item.getName();
        String description = item.getDescription();
        String donationType = item.getCategoryString();

        TextView title = findViewById(R.id.Name);
        TextView desc = findViewById(R.id.Description);
        TextView cat = findViewById(R.id.Category);
        TextView value = findViewById(R.id.donation_item_value);

        title.setText(name);
        desc.setText(description);
        cat.setText(donationType);

        NumberFormat format = NumberFormat.getCurrencyInstance();
        value.setText(format.format(item.getValue()));

    }
}
