package edu.gatech.cs2340.group75.donationtracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import edu.gatech.cs2340.group75.donationtracker.model.DonationItem;
import edu.gatech.cs2340.group75.donationtracker.R;

import java.text.NumberFormat;

/**
 * Activity for viewing details of specified donation item
 *
 * @author Markian Hromiak
 * @see DonationItem
 */
public class DonationItemDetails extends AppCompatActivity {

    /**
     * Method called on activity creation to initialize layout elements
     * Retrieves donation item to display as intent extra
     * @param savedInstanceState Current instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_item_details);
        DonationItem item = (DonationItem) getIntent().getSerializableExtra("DONATION_ITEM");

        String name = item.getName();
        String description = item.getDescription();
        String donationType = item.getCategory().toString();

        TextView title = findViewById(R.id.Name);
        TextView desc = findViewById(R.id.Description);
        ImageView pic = findViewById(R.id.itemPic);
        TextView cat = findViewById(R.id.Category);
        TextView value = findViewById(R.id.donation_item_value);

        title.setText(name);
        desc.setText(description);
        cat.setText(donationType);

        NumberFormat format = NumberFormat.getCurrencyInstance();
        value.setText(format.format(item.getValue()));

    }
}