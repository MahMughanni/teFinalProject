package com.mahm.finalproject.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mahm.finalproject.Adapters.Custom_RvAdapter_HomeFg_News;
import com.mahm.finalproject.Adapters.Custom_RvAdapter_TravelFg;
import com.mahm.finalproject.Model.ActivitiesData;
import com.mahm.finalproject.Model.Item_TravelFg;
import com.mahm.finalproject.R;

import java.util.ArrayList;

public class TravelFragment extends Fragment {

    private View view;
    private RecyclerView mTravelFgRv;
    private ArrayList<Item_TravelFg> data;
    private Custom_RvAdapter_TravelFg adapter1;

    public TravelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_travel, container, false);
        mTravelFgRv = view.findViewById(R.id.travelFg_rv);


        data = new ArrayList<>();


        data.add(new Item_TravelFg(R.drawable.test_img, "العنوان الرئيسي  "));
        data.add(new Item_TravelFg(R.drawable.login_img0_family, "العنوان الرئيسي  "));
        data.add(new Item_TravelFg(R.drawable.test_img, "العنوان الرئيسي  "));
        data.add(new Item_TravelFg(R.drawable.login_img0_family, "العنوان الرئيسي  "));
        data.add(new Item_TravelFg(R.drawable.test_img, "العنوان الرئيسي  "));
        data.add(new Item_TravelFg(R.drawable.login_img0_family, "العنوان الرئيسي  "));


        adapter1 = new Custom_RvAdapter_TravelFg(getContext(), data);


        mTravelFgRv.setAdapter(adapter1);
        mTravelFgRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mTravelFgRv.setHasFixedSize(true);


        return view;
    }


}
