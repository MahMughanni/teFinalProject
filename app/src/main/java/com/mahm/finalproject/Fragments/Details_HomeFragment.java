package com.mahm.finalproject.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mahm.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_HomeFragment extends Fragment {

    private View view;
    private TextView mFgDetailsTvTitle;
    private ImageView mFgDetailsImg;
    private MultiAutoCompleteTextView mFgDetailsMTvDescription;

    public Details_HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = LayoutInflater
                .from(getContext())
                .inflate(R.layout.fragment_details__home, container, false);

        init();

        return view;

    }


    //Initialization "FindView"
    void init() {
        mFgDetailsTvTitle = view.findViewById(R.id.fg_details_tv_Title);
        mFgDetailsImg = view.findViewById(R.id.fg_details_img);
        mFgDetailsMTvDescription = view.findViewById(R.id.fg_details_mTv_description);
    }
}
