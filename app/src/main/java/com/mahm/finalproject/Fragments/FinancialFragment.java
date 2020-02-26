package com.mahm.finalproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahm.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinancialFragment extends Fragment {


    public FinancialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_financial, container, false);
    }

}
