package edu.gatech.cs2340.group75.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.Map;

import edu.gatech.cs2340.group75.donationtracker.model.User;
import edu.gatech.cs2340.group75.donationtracker.R;

/**
 * Fragment for Home page in main Activity
 *
 * @author bholmes34@gatech.edu
 * @author mhromiak3@gatech.edu
 * @author mlewis61@gatech.edu
 * @author spadi29@gatech.edu
 * @author sszczepaniuk3@gatech.edu
 *
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
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		Map<String,Class> classes = new ActivityClasses();
		ActivityClasses.add(ItemSearch.class, MapsActivity.class);
		
        View fragment = inflater.inflate(R.layout.fragment_home, container, false);
		
		View logoutButton = fragment.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new LogoutListener(classes));

		View searchButton = fragment.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new SearchListener(classes));
		
		View mapsButton = fragment.findViewById(R.id.mapsButton);
		mapsButton.setOnClickListener(new MapsListener(classes));

        TextView userEmail = fragment.findViewById(R.id.userEmail);
        String emailText = User.getCurrentEmail();
        userEmail.setText(emailText);

        TextView userType = fragment.findViewById(R.id.userType);
        String accountTypeText = User.getCurrentAccountType();
        userType.setText(accountTypeText);

        return fragment;
    }
	
	
	class LogoutListener implements View.OnClickListener {
		private final Map<String, Class> classes;
		
		LogoutListener(Map<String, Class> classes) {
			this.classes = Collections.unmodifiableMap(classes);
		}
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getActivity(), classes.get("LogIn"));
			startActivity(intent);
		}
	}
	
	class SearchListener implements View.OnClickListener {
		private final Map<String, Class> classes;
		
		SearchListener(Map<String, Class> classes) {
			this.classes = Collections.unmodifiableMap(classes);
		}
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getActivity(), classes.get("ItemSearch"));
			startActivity(intent);
		}
	}
	
	class MapsListener implements View.OnClickListener {
		private final Map<String, Class> classes;
		
		MapsListener(Map<String, Class> classes) {
			this.classes = Collections.unmodifiableMap(classes);
		}
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getActivity(), classes.get("MapsActivity"));
			startActivity(intent);
		}
	}
}
