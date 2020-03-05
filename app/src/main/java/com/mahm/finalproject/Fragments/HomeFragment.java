package com.mahm.finalproject.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.mahm.finalproject.Activities.ActivityDetails_HomeFg;
import com.mahm.finalproject.Adapters.ActivitiesAdapter;
import com.mahm.finalproject.Adapters.Custom_RvAdapter_HomeFg_News;
import com.mahm.finalproject.Model.ActivitiesData;
import com.mahm.finalproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements Custom_RvAdapter_HomeFg_News.OnClickItemListener {

    private View view;
    private ViewFlipper viewFlipper;
    private ActivitiesAdapter adapter;
    private Custom_RvAdapter_HomeFg_News adapter1;
    private ArrayList<ActivitiesData> data;
    private RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);
        init();

        data = new ArrayList<>();


        data.add(new ActivitiesData(R.drawable.login_img0_family, "العنوان الرئيسي  ", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.login_img_family, "العنوان الرئيسي 1  ", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.splash_img, "العنوان الرئيسي 2", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.login_img0_family, "العنوان الرئيسي 3  ", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.splash_img, "العنوان الرئيسي 4 ", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.splash_img, "العنوان الرئيسي 5 ", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.splash_img, "العنوان الرئيسي 6 ", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.splash_img, "العنوان الرئيسي 7 ", "تفصيل بسيط عن النشاط"));


        adapter1 = new Custom_RvAdapter_HomeFg_News(getActivity(), data, new Custom_RvAdapter_HomeFg_News.OnClickItemListener() {
            @Override
            public void ClickItemListener(int position) {
                Toast.makeText(getActivity(), "Soon", Toast.LENGTH_SHORT).show();
            }
        });


        recyclerView.setAdapter(adapter1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);


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


    @Override
    public void ClickItemListener(int position) {

        Intent intent = new Intent(getActivity(), ActivityDetails_HomeFg.class);
        startActivity(intent);

    }
}
