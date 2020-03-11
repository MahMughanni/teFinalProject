package com.mahm.finalproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahm.finalproject.Adapters.FinancialAdapter;
import com.mahm.finalproject.Model.FinancialData;
import com.mahm.finalproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinancialFragment extends Fragment {

    private RecyclerView rv;
    private ArrayList<FinancialData> data = new ArrayList<>();
    private FinancialAdapter adapter;

    public FinancialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_financial, container, false);
        rv = v.findViewById(R.id.rvFinancialFragment);

        data.add(new FinancialData(10, 234, "26/07/2015", 2830219));
        data.add(new FinancialData(10, 234, "26/07/2015", 2830219));
        data.add(new FinancialData(10, 234, "26/07/2015", 2830219));
        data.add(new FinancialData(10, 234, "26/07/2015", 2830219));
        data.add(new FinancialData(10, 234, "26/07/2015", 2830219));
        data.add(new FinancialData(10, 234, "26/07/2015", 2830219));
        data.add(new FinancialData(10, 234, "26/07/2015", 2830219));

        adapter = new FinancialAdapter(data, getActivity());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);

        return v;
    }

}
