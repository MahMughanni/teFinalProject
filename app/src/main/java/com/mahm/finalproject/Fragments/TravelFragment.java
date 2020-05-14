package com.mahm.finalproject.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mahm.finalproject.Adapters.Custom_RvAdapter_HomeFg_News;
import com.mahm.finalproject.Adapters.Custom_RvAdapter_TravelFg;
import com.mahm.finalproject.Model.ActivitiesData;
import com.mahm.finalproject.Model.Item_TravelFg;
import com.mahm.finalproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TravelFragment extends Fragment {

    private View view;
    private RecyclerView mTravelFgRv;
    private ArrayList<Item_TravelFg> trave_data;
    private Custom_RvAdapter_TravelFg adapter;
    public static final String url = "http://mohamedd8f8w-001-site1.dtempurl.com/api/trips";

    public TravelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_travel, container, false);
        mTravelFgRv = view.findViewById(R.id.travelFg_rv);
        if (!checkInternetConnected()) {
            Toast.makeText(getActivity(), "عذرا لا يوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();

        } else {
            getTripsApi();
        }


        return view;
    }


    void getTripsApi() {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                trave_data = new ArrayList<>();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);

                        Item_TravelFg data = new Item_TravelFg();

                        data.setTitle(object.getString("tripTitle"));
                        data.setImg(object.getString("tripImagePath"));

                        trave_data.add(data);

                    }

                    setupRecyclerView();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), " Error" + error + "", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(getActivity()).add(request);

    }


    void setupRecyclerView() {

        adapter = new Custom_RvAdapter_TravelFg(getContext(), trave_data);
        mTravelFgRv.setAdapter(adapter);
        mTravelFgRv.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mTravelFgRv.setHasFixedSize(true);

    }

    private boolean checkInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
