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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_item_details);
		
		Intent intent = getIntent();
        DonationItem item = (DonationItem) intent.getSerializableExtra("DONATION_ITEM");

        TextView title = findViewById(R.id.Name);
        TextView desc = findViewById(R.id.Description);
        TextView cat = findViewById(R.id.Category);
        TextView value = findViewById(R.id.donation_item_value);

		bindName(title, item);
		bindDescription(desc, item);
		bindType(cat, item);
		
		NumberFormat format = NumberFormat.getCurrencyInstance();
		bindValue(value, item, format);
    }
	
	protected void bindName(TextView view, DonationItem item) {
		view.setText(item.getName());
	}
	
	protected void bindDescription(TextView view, DonationItem item) {
		view.setText(item.getDescription());
	}
	
	protected void bindType(TextView view, DonationItem item) {
		view.setText(item.getCategoryString());
	}
	
	protected void bindValue(TextView view, DonationItem item, NumberFormat format) {
		view.setText(format.format(item.getValue()));
	}
}
