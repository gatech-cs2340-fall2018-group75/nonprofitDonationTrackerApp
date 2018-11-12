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

import edu.gatech.cs2340.group75.donationtracker.model.DonationItem;
import edu.gatech.cs2340.group75.donationtracker.model.DonationItemType;
import edu.gatech.cs2340.group75.donationtracker.model.Location;
import edu.gatech.cs2340.group75.donationtracker.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Fragment for Donation Items list inflated by LocationsFragment
 *
 * <p>Contains a method to poll the database for donation items matching a specified location
 * <p>Individual list items are created with a listAdapter and a .xml layout for each
 *
 * @author Benjamin Holmes
 * @see LocationsFragment
 */
public class DonationItemsFragment extends Fragment {
    /**
     * Method called on Fragment creation to inflate donation items layout
     * Sets click listeners and list adapter
     * @param inflater Inflater to create fragment
     * @param container Where fragment will be inflated to
     * @param savedInstanceState Current instance state
     * @return donation items fragment view
     */
    @SuppressWarnings("FeatureEnvy")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		ActivityClasses classes = new ActivityClasses();
		classes.add(DonationItemDetails.class);
		
        View fragment = inflater.inflate(R.layout.fragment_donation_items, container, false);

		Bundle arguments = Objects.requireNonNull(getArguments());
		Location location = (Location) Objects.requireNonNull
		(
			arguments.getSerializable("LOCATION")
		);

		List<DonationItem> donationItemsList = DonationItem.getItemsList();
        ListAdapter listAdapter = new DonationItemsList
		(
			inflater,
			donationItemsList
		);
        final ListView list = fragment.findViewById(R.id.donation_item_list);
        populateDonationItems(inflater, list, location.getName());
        list.setAdapter(listAdapter);
        setListViewHeightBasedOnItems(list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
				List<DonationItem> donationItemsList = DonationItem.getItemsList();
                DonationItem itemClicked = donationItemsList.get(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("DONATION_ITEM", itemClicked);
				
				ActivityClasses classes = new ActivityClasses();
                Intent listDetails = new Intent
				(
					DonationItemsFragment.this.getActivity(),
					classes.get("DonationItemDetails")
				);
                listDetails.putExtras(bundle);

                startActivity(listDetails);
        }
        });

        return fragment;
    }

    @SuppressWarnings("FeatureEnvy")
    private void populateDonationItems
	(
		final LayoutInflater inflater,
		final ListView list,
		String locationName
	) {
        String URL=getString(R.string.API_base);
        //noinspection SpellCheckingInspection
        URL = URL + "/donationitems/getByLocation?name=";
		try
		{
			URL = URL + URLEncoder.encode(locationName, StandardCharsets.UTF_8.toString());
		}
		
		catch (UnsupportedEncodingException exception)
		{
			exception.printStackTrace();
		}
		
        Log.d("REST response", "starting... " + URL);

        RequestQueue requestQueue = Volley.newRequestQueue
		(
                Objects.requireNonNull(DonationItemsFragment.this.getActivity())
		);
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @SuppressWarnings("FeatureEnvy")
                    @Override
                    public void onResponse(JSONArray response) {
                        List<DonationItem> items = new ArrayList<>();
                        try {
                            for(int i = 0; i < response.length(); i++) {
                                JSONObject json = response.getJSONObject(i);
                                Log.d("REST response", json.toString());
                                DonationItem item = new DonationItem(
                                        json.getString("Name"),
                                        json.getString("Description"),
                                        json.getString("Location"),
                                        json.getDouble("Value"),
                                        DonationItemType.valueOf(json.getString("Category"))
                                );
                                items.add(item);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        DonationItem.setItemsList(items);
                        ListAdapter listAdapter = new DonationItemsList
						(
							inflater, DonationItem.getItemsList()
						);
                        list.setAdapter(listAdapter);
                        setListViewHeightBasedOnItems(list);
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
        TextView locationView;
    }

    private class DonationItemsList extends ArrayAdapter<DonationItem> {

        private final LayoutInflater inflater;
        private final List<DonationItem> donationItems;
        private View inflatedView;

        DonationItemsList(LayoutInflater inflater, List<DonationItem> donationItems) {
            super(inflater.getContext(), R.layout.fragment_donation_items_item, donationItems);
            this.inflater = inflater;
            this.donationItems = donationItems;
        }

        @NonNull
        @Override
        public View getView(int position, View view, @NonNull ViewGroup parent) {
            DonationItem item = donationItems.get(position);
            if (item == null) {
                return view;
            }
            ViewHolder donationViewHolder = new ViewHolder();
            if (view == null) {
                inflatedView = inflater.inflate(R.layout.fragment_donation_items_item, parent,
                        false);
                donationViewHolder.nameView = inflatedView.findViewById(R.id.item_name);
                donationViewHolder.locationView = inflatedView.findViewById(R.id.item_location);
            } else {
                inflatedView = view;
                donationViewHolder = (ViewHolder) inflatedView.getTag();
            }

            donationViewHolder.nameView.setText(item.getName());
            donationViewHolder.locationView.setText(item.getLocationName());
            return inflatedView;
        }
    }

    private static void setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();
        } 
    }
}
