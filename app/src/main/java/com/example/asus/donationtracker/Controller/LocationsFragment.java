package com.example.asus.donationtracker.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.donationtracker.R;

public class LocationsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = this.getArguments();
        String name = bundle.getString("NAME", "");

        View fragment = inflater.inflate(R.layout.fragment_locations, container, false);
        TextView header = fragment.findViewById(R.id.header);
        header.setText(name);

        return fragment;
    }
}
