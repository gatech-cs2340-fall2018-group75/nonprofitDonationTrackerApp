package edu.gatech.cs2340.group75.donationtracker.Controller;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import edu.gatech.cs2340.group75.donationtracker.Model.DonationItem;
import edu.gatech.cs2340.group75.donationtracker.Model.Location;
import edu.gatech.cs2340.group75.donationtracker.R;

import org.w3c.dom.Text;

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

        TextView title = (TextView) findViewById(R.id.Name);
        TextView desc = (TextView) findViewById(R.id.Description);
        ImageView pic = (ImageView) findViewById(R.id.itemPic);
        TextView cat = (TextView) findViewById(R.id.Category);
        TextView value = (TextView) findViewById(R.id.donation_item_value);

        title.setText(name);
        desc.setText(description);
        cat.setText(donationType);

        NumberFormat format = NumberFormat.getCurrencyInstance();
        value.setText(format.format(item.getValue()));

    }
}
