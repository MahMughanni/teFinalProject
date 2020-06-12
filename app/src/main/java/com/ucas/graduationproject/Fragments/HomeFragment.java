package com.ucas.graduationproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ucas.graduationproject.Activities.ActivityDetails_HomeFg;
import com.ucas.graduationproject.Adapters.Custom_RvAdapter_HomeFg_News;
import com.ucas.graduationproject.Adapters.SliderAdapterHomeFragment;
import com.ucas.graduationproject.Model.ActivitiesData;
import com.ucas.graduationproject.Model.AdsData;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.ucas.graduationproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View view;
    private SliderAdapterHomeFragment sliderAdapter;
    private Custom_RvAdapter_HomeFg_News adapter;
    private ArrayList<ActivitiesData> data_activity;
    private ProgressBar progressBarHomeF;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    public static final String url = "http://siteproject-001-site1.btempurl.com/api/activities";
    private SliderView sliderView;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);
        init();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.stopAutoCycle();
            }
        });

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
        linearLayout = view.findViewById(R.id.LinearLayout);
        progressBarHomeF = view.findViewById(R.id.progressBarHomeF);
        sliderView = view.findViewById(R.id.imageSlider);


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
                data_activity = new ArrayList<>();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);

                        String title = object.getString("activitieName");
                        String Description = object.getString("description");
                        String img = object.getString("actPathImage");

                        data_activity.add(new ActivitiesData(title, Description, img));
                    }

                    progressBarHomeF.setVisibility(View.GONE);
                    adapter = new Custom_RvAdapter_HomeFg_News(getActivity(), data_activity
                            , new Custom_RvAdapter_HomeFg_News.OnClickItemListener() {
                        @Override
                        public void ClickItemListener(ActivitiesData data, List<String> list) {

                            Intent intent = new Intent(getActivity(), ActivityDetails_HomeFg.class);
                            intent.putExtra("title", data.getTitle());
                            intent.putExtra("description", data.getDescription());
                            intent.putExtra("listImage", (Serializable) list);
                            intent.putExtra("image", data.getImg());

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

        String url = "http://siteproject-001-site1.btempurl.com/api/ads";
        ArrayList<AdsData> adsData = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        String adsTitle = object.getString("adTitle");
                        String adsImage = object.getString("adPathImage");

                        adsData.add(new AdsData(adsTitle, adsImage));

                    }

                    sliderView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                    sliderAdapter = new SliderAdapterHomeFragment(getActivity(), adsData);
                    sliderView.setSliderAdapter(sliderAdapter);

                    sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                    sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
                    sliderView.setIndicatorSelectedColor(Color.WHITE);
                    sliderView.setIndicatorUnselectedColor(Color.GRAY);
                    sliderView.setScrollTimeInSec(4);
                    sliderView.setAutoCycle(true);
                    sliderView.startAutoCycle();


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
