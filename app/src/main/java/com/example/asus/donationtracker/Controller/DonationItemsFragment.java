package com.example.asus.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
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
import com.example.asus.donationtracker.Model.DonationItem;
import com.example.asus.donationtracker.Model.DonationItemType;
import com.example.asus.donationtracker.Model.DonationItems;
import com.example.asus.donationtracker.Model.Location;
import com.example.asus.donationtracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DonationItemsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.fragment_donation_items, container, false);

        Location location = (Location) getArguments().getSerializable("LOCATION");

        DonationItemsList listAdapter = new DonationItemsList(inflater, DonationItems.getInstance().get());
        final ListView list = fragment.findViewById(R.id.donation_item_list);
        populateDonationItems(inflater, list, location.getName());
        list.setAdapter(listAdapter);
        setListViewHeightBasedOnItems(list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                DonationItem itemClicked = DonationItems.getInstance().get().get(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("DONATION_ITEM", itemClicked);
                Intent listDetails = new Intent(DonationItemsFragment.this.getActivity(), DonationItemDetails.class);
                listDetails.putExtras(bundle);

                startActivity(listDetails);
        }
        });

        return fragment;
    }

    private void populateDonationItems(final LayoutInflater inflater, final ListView list, String locationName) {
        String URL=getString(R.string.API_base) + "/donationitems/getByLocation?name=" + locationName;
        Log.d("REST response", "starting... " + URL);

        RequestQueue requestQueue = Volley.newRequestQueue(DonationItemsFragment.this.getActivity());
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        DonationItems donationItemsInstance = DonationItems.getInstance();
                        List<DonationItem> items = new ArrayList<>();
                        try {
                            for(int i = 0; i < response.length(); i++) {
                                JSONObject json = response.getJSONObject(i);
                                Log.d("REST response", json.toString());
                                DonationItem item = new DonationItem(
                                        json.getString("Name"),
                                        json.getString("Description"),
                                        json.getString("Location"),
                                        DonationItemType.valueOf(json.getString("Category"))
                                );
                                items.add(item);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        donationItemsInstance.set(items);
                        DonationItemsList listAdapter = new DonationItemsList(inflater, donationItemsInstance.get());
                        list.setAdapter(listAdapter);
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

    private class DonationItemsList extends ArrayAdapter<DonationItem> {

        private final LayoutInflater inflater;
        private final List<DonationItem> donationItems;

        public DonationItemsList(LayoutInflater inflater, List<DonationItem> donationItems) {
            super(inflater.getContext(), R.layout.fragment_donation_items_item, donationItems);
            this.inflater = inflater;
            this.donationItems = donationItems;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            DonationItem item = donationItems.get(position);
            View rowView= inflater.inflate(R.layout.fragment_donation_items_item, null, true);
            TextView name = rowView.findViewById(R.id.item_name);
            TextView location = rowView.findViewById(R.id.item_location);

            name.setText(item.getName());
            location.setText(item.getLocationName());
            return rowView;
        }
    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

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

            return true;

        } else {
            return false;
        }

    }
}
