package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.donationtracker.Model.User;
import com.example.asus.donationtracker.Model.Users;
import com.example.asus.donationtracker.R;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.fragment_home, container, false);

        fragment.findViewById(R.id.logoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOut = new Intent(getActivity(), login.class);
                startActivity(logOut);
            }
        });

        fragment.findViewById(R.id.settingsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(getActivity(), Settings.class);
                startActivity(goToSettings);
            }
        });

        fragment.findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSearch = new Intent(getActivity(), ItemSearch.class);
                startActivity(goToSearch);
            }
        });


        Users users = Users.getInstance();
        User currentUser = users.getCurrentUser();

        TextView userEmail = fragment.findViewById(R.id.userEmail);
        userEmail.setText(currentUser.getEmail());

        TextView userType = fragment.findViewById(R.id.userType);
        userType.setText(currentUser.getAccountType().toString());

        return fragment;
    }
}
