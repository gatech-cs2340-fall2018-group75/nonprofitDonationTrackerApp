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

        User currentUser = User.getCurrentUser();

        TextView userEmail = fragment.findViewById(R.id.userEmail);
        String emailText = currentUser.getEmail();
        userEmail.setText(emailText);

        TextView userType = fragment.findViewById(R.id.userType);
        String accountTypeText = currentUser.getAccountTypeString();
        userType.setText(accountTypeText);

        return fragment;
    }
	
	
	class LogoutListener implements View.OnClickListener {
		private Map<String, Class> classes;
		
		public LogoutListener(Map<String, Class> classes) {
			this.classes = classes;
		}
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getActivity(), classes.get("LogIn"));
			startActivity(intent);
		}
	}
	
	class SearchListener implements View.OnClickListener {
		private Map<String, Class> classes;
		
		public LogoutListener(Map<String, Class> classes) {
			this.classes = classes;
		}
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getActivity(), classes.get("ItemSearch"));
			startActivity(intent);
		}
	}
	
	class MapsListener implements View.OnClickListener {
		private Map<String, Class> classes;
		
		public LogoutListener(Map<String, Class> classes) {
			this.classes = classes;
		}
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getActivity(), classes.get("MapsActivity"));
			startActivity(intent);
		}
	}
}
