package com.mahm.finalproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mahm.finalproject.Adapters.CustomAdapter_HomeFg_ListNews;
import com.mahm.finalproject.Adapters.CustomAdapter_HomeFg_ViewPager;
import com.mahm.finalproject.Model.Item_HomeFg;
import com.mahm.finalproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private CustomAdapter_HomeFg_ViewPager adapter;
    private CustomAdapter_HomeFg_ListNews adapter1;
    private List<Item_HomeFg> data;

    ListView listView;
    private Timer timer;
    private int page = 0;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);

        listView = view.findViewById(R.id.homeFg_list_item);
        viewPager = view.findViewById(R.id.viewPager);
        data = new ArrayList<>();


        data.add(new Item_HomeFg(R.drawable.login_img0_family, "العنوان الرئيسي  ", "تفصيل بسيط عن النشاط"));
        data.add(new Item_HomeFg(R.drawable.login_img_family, "العنوان الرئيسي 1  ", "تفصيل بسيط عن النشاط"));
        data.add(new Item_HomeFg(R.drawable.splash_img, "العنوان الرئيسي 2", "تفصيل بسيط عن النشاط"));
        data.add(new Item_HomeFg(R.drawable.login_img0_family, "العنوان الرئيسي 3  ", "تفصيل بسيط عن النشاط"));
        data.add(new Item_HomeFg(R.drawable.splash_img, "العنوان الرئيسي 4 ", "تفصيل بسيط عن النشاط"));
        data.add(new Item_HomeFg(R.drawable.splash_img, "العنوان الرئيسي 5 ", "تفصيل بسيط عن النشاط"));
        data.add(new Item_HomeFg(R.drawable.splash_img, "العنوان الرئيسي 6 ", "تفصيل بسيط عن النشاط"));
        data.add(new Item_HomeFg(R.drawable.splash_img, "العنوان الرئيسي 7 ", "تفصيل بسيط عن النشاط"));


        adapter = new CustomAdapter_HomeFg_ViewPager(data, getContext());
        viewPager.setAdapter(adapter);
//        setupAutoPager();

        viewPager.setPadding(32, 0, 32, 0);


        adapter1 = new CustomAdapter_HomeFg_ListNews(data, getContext());
        listView.setAdapter(adapter1);


        return view;
    }


    //تنقل تلقائي

//    private void setupAutoPager() {
//        final Handler handler = new Handler();
//
//        final Runnable update = new Runnable() {
//            public void run() {
//
//                viewPager.setCurrentItem(page, true);
//                if (page == Integer.MAX_VALUE) {
//                    page = 0;
//                } else {
//                    ++page;
//                }
//            }
//        };
//
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                handler.post(update);
//            }
//        }, 500, 2500);
//
//
//    }


}
