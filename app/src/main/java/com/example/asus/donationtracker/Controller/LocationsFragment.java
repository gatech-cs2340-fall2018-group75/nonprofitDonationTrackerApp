package com.example.asus.donationtracker.Controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.donationtracker.Model.Location;
import com.example.asus.donationtracker.Model.Locations;
import com.example.asus.donationtracker.R;

import java.util.List;

public class LocationsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_locations, container, false);
//
//        Bundle bundle = this.getArguments();
//        if (bundle == null)
//            return fragment;
//
//        Locations locations = (Locations) bundle.getSerializable("LOCATIONS");
        LocationList listAdapter = new LocationList(inflater, Locations.getInstance().get());
        ListView list = fragment.findViewById(R.id.location_list);
        list.setAdapter(listAdapter);

        return fragment;
    }

    private class LocationList extends ArrayAdapter<Location> {

        private final LayoutInflater inflater;
        private final List<Location> locations;

        public LocationList(LayoutInflater inflater, List<Location> locations) {
            super(inflater.getContext(), R.layout.fragment_locations_item, locations);
            this.inflater = inflater;
            this.locations = locations;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            Location location = locations.get(position);
            View rowView= inflater.inflate(R.layout.fragment_locations_item, null, true);
            TextView name = rowView.findViewById(R.id.location_name);
            TextView address = rowView.findViewById(R.id.location_address);
            TextView cityState = rowView.findViewById(R.id.location_city_state);

            name.setText(location.getName());
            address.setText(location.getAddress());
            return rowView;
        }
    }
}