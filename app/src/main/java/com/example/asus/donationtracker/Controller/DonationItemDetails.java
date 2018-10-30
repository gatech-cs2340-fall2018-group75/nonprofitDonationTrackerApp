package com.example.asus.donationtracker.Controller;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.donationtracker.Model.DonationItem;
import com.example.asus.donationtracker.Model.Location;
import com.example.asus.donationtracker.R;

import org.w3c.dom.Text;

public class DonationItemDetails extends AppCompatActivity {

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

        title.setText(name);
        desc.setText(description);
        cat.setText(donationType);

    }
}
