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
	//The entire point of Model classes is to separate features into distinct objects
	//Moving functionality from the model to this class will violate many design principles
    @SuppressWarnings("FeatureEnvy")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		ActivityClasses classes = new ActivityClasses();
		classes.add(ItemSearch.class);
		classes.add(MapsActivity.class);
		
        View fragment = inflater.inflate(R.layout.fragment_home, container, false);
		
		View logoutButton = fragment.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				ActivityClasses classes = new ActivityClasses();
                Intent logOut = new Intent(getActivity(), classes.get("LogIn"));
                startActivity(logOut);
            }
        });

		View searchButton = fragment.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				ActivityClasses classes = new ActivityClasses();
                Intent goToSearch = new Intent(getActivity(), classes.get("ItemSearch"));
                startActivity(goToSearch);
            }
        });
		
		View mapsButton = fragment.findViewById(R.id.mapsButton);
		mapsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityClasses classes = new ActivityClasses();
				Intent goToMaps = new Intent(getActivity(), classes.get("MapsActivity"));
				startActivity(goToMaps);
			}
		});

        User currentUser = User.getCurrentUser();

        TextView userEmail = fragment.findViewById(R.id.userEmail);
		
		//This statement is just accessing a property of the User model class
		//Changing the interface would only make the model class less usable
        //noinspection LawOfDemeter
        userEmail.setText(currentUser.getEmail());

        TextView userType = fragment.findViewById(R.id.userType);

		//This statement is just accessing a property of the User model class
		//Changing the interface would only make the model class less usable
        //noinspection LawOfDemeter
        userType.setText(currentUser.getAccountTypeString());

        return fragment;
    }
}
