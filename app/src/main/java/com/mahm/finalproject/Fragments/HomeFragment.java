package com.mahm.finalproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.mahm.finalproject.Adapters.ActivitiesAdapter;
import com.mahm.finalproject.Model.ActivitiesData;
import com.mahm.finalproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ViewFlipper viewFlipper;
    private ActivitiesAdapter adapter;
    private ArrayList<ActivitiesData> data;

    private RecyclerView recyclerView;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.homeFg_list_item);
        viewFlipper = view.findViewById(R.id.viewFlipper);
        data = new ArrayList<>();

        data.add(new ActivitiesData(R.drawable.login_img0_family, "العنوان الرئيسي  ", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.login_img_family, "العنوان الرئيسي 1  ", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.splash_img, "العنوان الرئيسي 2", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.login_img0_family, "العنوان الرئيسي 3  ", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.splash_img, "العنوان الرئيسي 4 ", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.splash_img, "العنوان الرئيسي 5 ", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.splash_img, "العنوان الرئيسي 6 ", "تفصيل بسيط عن النشاط"));
        data.add(new ActivitiesData(R.drawable.splash_img, "العنوان الرئيسي 7 ", "تفصيل بسيط عن النشاط"));


        int[] images = {R.drawable.login_img0_family, R.drawable.login_img_family,
                R.drawable.splash_img};

        for (int image :
                images) {
            sliderImage(image);
        }


        adapter = new ActivitiesAdapter(data, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        return view;
    }

    private void sliderImage(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
    }

}
