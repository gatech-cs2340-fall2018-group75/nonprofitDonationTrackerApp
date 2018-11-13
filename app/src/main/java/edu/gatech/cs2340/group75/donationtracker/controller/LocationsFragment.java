package edu.gatech.cs2340.group75.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import edu.gatech.cs2340.group75.donationtracker.model.Location;
import edu.gatech.cs2340.group75.donationtracker.model.LocationType;
import edu.gatech.cs2340.group75.donationtracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Fragment for locations page in main Activity
 *
 * <p>Queries database for all locations to display in list view
 * <p>Each list view item has independent layout inflated by list adapter
 *
 * @author bholmes34@gatech.edu
 * @author mhromiak3@gatech.edu
 * @author mlewis61@gatech.edu
 * @author spadi29@gatech.edu
 * @author sszczepaniuk3@gatech.edu
 *
 * @see MainMenu
 * @see Location
 */
public class LocationsFragment extends Fragment {

    /**
     * Method called on Fragment creation to inflate locations layout
     * Sets click listeners and list adapter
     * @param inflater Inflater to create fragment
     * @param container Where fragment will be inflated to
     * @param savedInstanceState Current instance state
     * @return locations fragment view
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		ActivityClasses.add(LocationDetails.class);
		
        View fragment = inflater.inflate(R.layout.fragment_locations, container, false);

		List<Location> locationsList = Location.getLocationsList();
        ListAdapter listAdapter = new LocationList(inflater, locationsList);
        ListView list = fragment.findViewById(R.id.location_list);
        list.setAdapter(listAdapter);

        populateLocations(inflater, list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
				List<Location> locationsList = Location.getLocationsList();
                Location itemClicked = locationsList.get(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("LOCATION", itemClicked);
				
                Intent listDetails = new Intent
				(
					LocationsFragment.this.getActivity(),
					ActivityClasses.get("LocationDetails")
				);
                listDetails.putExtras(bundle);

                startActivity(listDetails);
            }
        });

        return fragment;
    }

    private void populateLocations(final LayoutInflater inflater, final ListView list) {
        String URL=getString(R.string.API_base) + "/locations/get";
        Log.d("REST response", "starting... " + URL);

        RequestQueue requestQueue = Volley.newRequestQueue
		(
			Objects.requireNonNull(LocationsFragment.this.getActivity())
		);
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<Location> locations = new ArrayList<>();
                            for(int i = 0; i < response.length(); i++) {
                                JSONObject json = response.getJSONObject(i);
                                locations.add(Location.fromJson(json));
                            }
							
                            Location.setLocationsList(locations);
                            ListAdapter listAdapter = new LocationList
							(
								inflater,
								Location.getLocationsList()
							);
                            list.setAdapter(listAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REST response", error.toString());
                    }
                }
        );
        requestQueue.add(request);
    }

    static class ViewHolder {
        TextView nameView;
        TextView addressView;
        TextView cityStateView;
    }

    private final class LocationList extends ArrayAdapter<Location> {

        private final LayoutInflater inflater;
        private final List<Location> locations;
        private View inflatedView;

        private LocationList(LayoutInflater inflater, List<Location> locations) {
            super(inflater.getContext(), R.layout.fragment_locations_item, locations);
            this.inflater = inflater;
            this.locations = locations;
        }

		//The entire point of Model classes is to separate features into distinct objects
		//Moving functionality from the model to this class will violate many design principles
        @SuppressWarnings("FeatureEnvy")
        @Override
        @NonNull
        public View getView(int position, View view, @NonNull ViewGroup parent) {
            Location location = locations.get(position);
            if (location == null) {
                return view;
            }
            ViewHolder viewHolderItem = new ViewHolder();
            if (view == null) {
                inflatedView = inflater.inflate(R.layout.fragment_locations_item, parent, false);
                viewHolderItem.nameView = inflatedView.findViewById(R.id.location_name);
                viewHolderItem.addressView = inflatedView.findViewById(R.id.location_address);
                viewHolderItem.cityStateView = inflatedView.findViewById(R.id.location_city_state);
                inflatedView.setTag(viewHolderItem);
            } else {
                inflatedView = view;
                viewHolderItem = (ViewHolder) inflatedView.getTag();
            }

            viewHolderItem.nameView.setText(location.getName());
            viewHolderItem.addressView.setText(location.getAddress());
            viewHolderItem.cityStateView.setText(getString(R.string.cityStateFormat,
                    location.getCity(), location.getState()));
            return inflatedView;
        }
    }
}
