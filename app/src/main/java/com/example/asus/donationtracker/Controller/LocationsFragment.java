package com.example.asus.donationtracker.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.donationtracker.Model.Locations;
import com.example.asus.donationtracker.R;

public class LocationsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_locations, container, false);

        Bundle bundle = this.getArguments();
        if (bundle == null)
            return fragment;

        Locations locations = (Locations) bundle.getSerializable("LOCATIONS");
        return fragment;
    }
}
