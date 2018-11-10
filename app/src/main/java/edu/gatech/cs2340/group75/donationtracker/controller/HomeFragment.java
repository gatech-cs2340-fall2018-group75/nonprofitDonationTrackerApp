package edu.gatech.cs2340.group75.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.gatech.cs2340.group75.donationtracker.model.User;
import edu.gatech.cs2340.group75.donationtracker.R;

/**
 * Fragment for Home page in main Activity
 *
 * @author Markian Hromiak
 * @author Mike Lewis
 * @author Benjamin Holmes
 * @see MainMenu
 */
public class HomeFragment extends Fragment {

    /**
     * Method called on Fragment creation to inflate home layout
     * @param inflater Inflater to create fragment
     * @param container Where fragment will be inflated to
     * @param savedInstanceState Current instance state
     * @return home fragment view
     */
    @SuppressWarnings("FeatureEnvy")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.fragment_home, container, false);
		
		View logoutButton = fragment.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logOut = new Intent(getActivity(), LogIn.class);
                startActivity(logOut);
            }
        });

		View searchButton = fragment.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSearch = new Intent(getActivity(), ItemSearch.class);
                startActivity(goToSearch);
            }
        });
		
		View mapsButton = fragment.findViewById(R.id.mapsButton);
		mapsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent goToMaps = new Intent(getActivity(), MapsActivity.class);
				startActivity(goToMaps);
			}
		});

        User currentUser = User.getCurrentUser();

        TextView userEmail = fragment.findViewById(R.id.userEmail);
        //noinspection LawOfDemeter
        userEmail.setText(currentUser.getEmail());

        TextView userType = fragment.findViewById(R.id.userType);

        //noinspection LawOfDemeter
        userType.setText(currentUser.getAccountTypeString());

        return fragment;
    }
}
