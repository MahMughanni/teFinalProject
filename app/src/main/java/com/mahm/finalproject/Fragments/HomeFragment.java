package com.mahm.finalproject.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mahm.finalproject.Activities.ActivityDetails_HomeFg;
import com.mahm.finalproject.Adapters.Custom_RvAdapter_HomeFg_News;
import com.mahm.finalproject.Model.ActivitiesData;
import com.mahm.finalproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View view;
    private ViewFlipper viewFlipper;
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

        data_activity = new ArrayList<>();


        activityAPI();

        int[] images = {R.drawable.splash_img, R.drawable.login_img_family, R.drawable.login_img0_family};

        for (int image :
                images) {
            slideImages(image);
        }

        return view;
    }

    //Initialization "FindView"
    void init() {
        recyclerView = view.findViewById(R.id.homeFg_list_item);
        viewFlipper = view.findViewById(R.id.viewFlipper);

    }

    private void slideImages(int image) {

        ImageView imageView = new ImageView(getActivity());

        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        // animation
        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);


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
                ActivitiesData data;
//                Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
                data_activity = new ArrayList<>();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);

                        data = new ActivitiesData();

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

//    @Override
//    public void ClickItemListener(int position) {
//
//        Bundle b = new Bundle();
//
//
//        Intent intent = new Intent(getActivity(), ActivityDetails_HomeFg.class);
//        startActivity(intent);
//
//    }


}
