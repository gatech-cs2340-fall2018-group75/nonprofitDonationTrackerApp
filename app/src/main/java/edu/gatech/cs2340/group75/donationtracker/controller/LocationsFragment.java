package edu.gatech.cs2340.group75.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
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
import edu.gatech.cs2340.group75.donationtracker.model.Locations;
import edu.gatech.cs2340.group75.donationtracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment for locations page in main Activity
 *
 * <p>Queries database for all locations to display in list view
 * <p>Each list view item has independent layout inflated by list adapter
 *
 * @author Benjamin Holmes
 * @see MainMenu
 * @see Locations
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
    @SuppressWarnings("FeatureEnvy")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.fragment_locations, container, false);

        ListAdapter listAdapter = new LocationList(inflater, Locations.getInstance().get());
        ListView list = fragment.findViewById(R.id.location_list);
        list.setAdapter(listAdapter);

        populateLocations(inflater, list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Location itemClicked = Locations.getInstance().get().get(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("LOCATION", itemClicked);
                Intent listDetails = new Intent
				(
					LocationsFragment.this.getActivity(),
					LocationDetails.class
				);
                listDetails.putExtras(bundle);

                startActivity(listDetails);
            }
        });

        return fragment;
    }

    @SuppressWarnings("FeatureEnvy")
    private void populateLocations(final LayoutInflater inflater, final ListView list) {
        String URL=getString(R.string.API_base) + "/locations/get";
        Log.d("REST response", "starting... " + URL);

        RequestQueue requestQueue = Volley.newRequestQueue(LocationsFragment.this.getActivity());
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @SuppressWarnings("FeatureEnvy")
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Locations locationsInstance = Locations.getInstance();
                            List<Location> locations = new ArrayList<>();
                            for(int i = 0; i < response.length(); i++) {
                                JSONObject json = response.getJSONObject(i);
                                Location location = new Location(
                                        json.getString("Name"),
                                        LocationType.valueOf(json.getString("Type")),
                                        json.getDouble("Latitude"),
										json.getDouble("Longitude"),
                                        json.getString("Street Address"),
                                        json.getString("City"),
                                        json.getString("State"),
                                        json.getString("Zip"),
                                        json.getString("Phone")
                                );
                                locations.add(location);
                            }
                            locationsInstance.set(locations);
                            ListAdapter listAdapter = new LocationList
							(
								inflater,
								locationsInstance.get()
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

    private class LocationList extends ArrayAdapter<Location> {

        private final LayoutInflater inflater;
        private final List<Location> locations;

        LocationList(LayoutInflater inflater, List<Location> locations) {
            super(inflater.getContext(), R.layout.fragment_locations_item, locations);
            this.inflater = inflater;
            this.locations = locations;
        }

        @SuppressWarnings("FeatureEnvy")
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            Location location = locations.get(position);
            View rowView= inflater.inflate(R.layout.fragment_locations_item, null, true);
            TextView name = rowView.findViewById(R.id.location_name);
            TextView address = rowView.findViewById(R.id.location_address);
            TextView cityState = rowView.findViewById(R.id.location_city_state);

            name.setText(location.getName());
            address.setText(location.getAddress());
            cityState.setText(location.getCity() + ", " + location.getState());
            return rowView;
        }
    }
}
