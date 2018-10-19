package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asus.donationtracker.Model.DonationItem;
import com.example.asus.donationtracker.Model.DonationItems;
import com.example.asus.donationtracker.Model.Location;
import com.example.asus.donationtracker.R;

public class enterDonationItem extends AppCompatActivity implements View.OnClickListener{

    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView pic;
    private EditText name;
    private EditText description;
    private Button submit;
    private Button imgBtn;
    private Location center;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_donation_item);
        Bundle bd = getIntent().getExtras();
        center = (Location) bd.get("location");
        submit = (Button) findViewById(R.id.submit_donation_item);
        submit.setOnClickListener(this);
        imgBtn = (Button) findViewById(R.id.addPicBtn);
        imgBtn.setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.itemPic);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.addPicBtn :
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;
            case R.id.submit_donation_item :
                name = (EditText) findViewById(R.id.itemName);
                description = (EditText) findViewById(R.id.itemDesc);
                String title = name.getText().toString();
                if (!title.equals("")) {
                    if (!description.getText().toString().equals("")) {
                        DonationItem item = new DonationItem(title, description.getText()
                                .toString(), center);
                        DonationItems donated = DonationItems.getInstance();
                        donated.add(item);
                        Intent toLocationDetails = new Intent(enterDonationItem.this,
                                LocationDetails.class);
                        startActivity(toLocationDetails);
                    } else {
                        DonationItem item = new DonationItem(title, "", center);
                        DonationItems donated = DonationItems.getInstance();
                        donated.add(item);
                        Intent toLocationDetails = new Intent(enterDonationItem.this,
                                LocationDetails.class);
                        startActivity(toLocationDetails);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Give the name of your donation item",
                            Toast.LENGTH_LONG).show();
                }
                break;
            default :
                break;
        }
    }

}
