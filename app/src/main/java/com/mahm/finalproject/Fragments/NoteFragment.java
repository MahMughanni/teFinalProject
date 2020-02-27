package com.mahm.finalproject.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mahm.finalproject.Adapters.C_RvAdapter_HolidaysFg;
import com.mahm.finalproject.Adapters.C_RvAdapter_HomeFg_ListNews;
import com.mahm.finalproject.Adapters.C_RvAdapter_NoteFg;
import com.mahm.finalproject.Model.Item_NoteFg;
import com.mahm.finalproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment {

    private View view;
    private RecyclerView mNotefgRecView;
    private List<Item_NoteFg> mData;
    private C_RvAdapter_NoteFg recViwe_Adapter;



    public NoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_note, container, false);

        init();

        mData  = new ArrayList<>();

        mData.add(new Item_NoteFg("رياضيات"
                , "احمد"
                , "متابعة السلوك"
                ,
                "الفاظ غير مناسبة", R.drawable.splash_img)) ;

        mData.add(new Item_NoteFg("رياضيات"
                , "احمد"
                , "متابعة السلوك"
                ,
                "الفاظ غير مناسبة", R.drawable.splash_img)) ;
        mData.add(new Item_NoteFg("رياضيات"
                , "احمد"
                , "متابعة السلوك"
                ,
                "الفاظ غير مناسبة", R.drawable.splash_img)) ;
        mData.add(new Item_NoteFg("رياضيات"
                , "احمد"
                , "متابعة السلوك"
                ,
                "الفاظ غير مناسبة", R.drawable.splash_img)) ;



        recViwe_Adapter = new C_RvAdapter_NoteFg(getContext(), mData);
        mNotefgRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        mNotefgRecView.setHasFixedSize(true);
        mNotefgRecView.setAdapter(recViwe_Adapter);




        return view;


    }

    void init() {

        mNotefgRecView = view.findViewById(R.id.notefg_RecView);

    }

}
