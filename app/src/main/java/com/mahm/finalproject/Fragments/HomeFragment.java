package com.mahm.finalproject.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mahm.finalproject.Activities.ActivityDetails_HomeFg;
import com.mahm.finalproject.Adapters.AdapterViewFlipperAdapter;
import com.mahm.finalproject.Adapters.Custom_RvAdapter_HomeFg_News;
import com.mahm.finalproject.Model.ActivitiesData;
import com.mahm.finalproject.Model.AdsData;
import com.mahm.finalproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View view;
    private AdapterViewFlipper adapterViewFlipper;
    private Custom_RvAdapter_HomeFg_News adapter;
    private ArrayList<ActivitiesData> data_activity;
    private RecyclerView recyclerView;
    public static final String url = "http://mohamedd8f8w-001-site1.dtempurl.com/api/activities";


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);
        init();
        if (!checkInternetConnected()) {
            Toast.makeText(getActivity(), "عذرا لا يوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();

        } else {
            data_activity = new ArrayList<>();

            activityAPI();
            loadAds();
        }

        return view;
    }

    //Initialization "FindView"
    void init() {
        recyclerView = view.findViewById(R.id.homeFg_list_item);
        adapterViewFlipper = view.findViewById(R.id.viewFlipper);
    }

    void setupRecyclerView() {

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
    }


    void activityAPI() {

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
                data_activity = new ArrayList<>();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);

                        ActivitiesData data = new ActivitiesData();

                        data.setTitle(object.getString("activitieName"));
                        data.setDescription(object.getString("description"));
                        data.setImg(object.getString("actPathImage"));

                        data_activity.add(data);
                    }

                    adapter = new Custom_RvAdapter_HomeFg_News(getActivity(), data_activity
                            , new Custom_RvAdapter_HomeFg_News.OnClickItemListener() {
                        @Override
                        public void ClickItemListener(int position) {

                            Intent intent = new Intent(getActivity(), ActivityDetails_HomeFg.class);

                            intent.putExtra("title", data_activity.get(position).getTitle());
                            intent.putExtra("description", data_activity.get(position).getDescription());
                            intent.putExtra("image", data_activity.get(position).getImg());

                            startActivity(intent);
                        }
                    });

                    setupRecyclerView();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        Volley.newRequestQueue(getActivity()).add(request);

    }

    private void loadAds() {

        String url = "http://mohamedd8f8w-001-site1.dtempurl.com/api/ads";
        ArrayList<AdsData> adsData = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        int adsId = object.getInt("adId");
                        String adsTitle = object.getString("adTitle");
                        String adsImage = object.getString("adPathImage");

                        adsData.add(new AdsData(adsTitle, adsImage));

                    }

                    Activity activity = getActivity();
                    if (activity != null && isAdded()) {
                        adapterViewFlipper.setAdapter(new AdapterViewFlipperAdapter(getActivity(), adsData));
                        adapterViewFlipper.setFlipInterval(5000);
                        adapterViewFlipper.startFlipping();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("AdsHomeFragment", error.getMessage() + "");
            }
        });

        Volley.newRequestQueue(Objects.requireNonNull(getActivity())).add(request);
    }

    private boolean checkInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }


}
