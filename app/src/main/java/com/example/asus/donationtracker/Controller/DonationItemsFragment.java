package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.donationtracker.Model.DonationItem;
import com.example.asus.donationtracker.Model.DonationItems;
import com.example.asus.donationtracker.Model.Location;
import com.example.asus.donationtracker.Model.Locations;
import com.example.asus.donationtracker.Model.User;
import com.example.asus.donationtracker.Model.Users;
import com.example.asus.donationtracker.R;

import java.util.List;

public class DonationItemsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.fragment_donation_items, container, false);

        Bundle bd = getArguments().getBundle("LOCATION");
        Location location = (Location) getArguments().getSerializable("LOCATION");

        final List<DonationItem> donationItemsList = DonationItems.getInstance().getByLocation(location);

        DonationItemsList listAdapter = new DonationItemsList(inflater, donationItemsList);
        final ListView list = fragment.findViewById(R.id.donation_item_list);
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                DonationItem itemClicked = donationItemsList.get(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("DONATION_ITEM", itemClicked);
//                Intent listDetails = new Intent(DonationItemsFragment.this.getActivity(), LocationDetails.class);
//                listDetails.putExtras(bundle);
//
//            startActivity(listDetails);
        }
        });

        return fragment;
    }

    private class DonationItemsList extends ArrayAdapter<DonationItem> {

        private final LayoutInflater inflater;
        private final List<DonationItem> donationItems;

        public DonationItemsList(LayoutInflater inflater, List<DonationItem> donationItems) {
            super(inflater.getContext(), R.layout.fragment_donation_items_item, donationItems);
            this.inflater = inflater;
            this.donationItems = donationItems;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            DonationItem item = donationItems.get(position);
            View rowView= inflater.inflate(R.layout.fragment_donation_items_item, null, true);
            TextView name = rowView.findViewById(R.id.item_name);
            TextView location = rowView.findViewById(R.id.item_location);

            name.setText(item.getName());
            location.setText(item.getLocation().getName());
            return rowView;
        }
    }
}
