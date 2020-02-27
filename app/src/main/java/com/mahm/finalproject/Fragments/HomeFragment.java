package com.mahm.finalproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.mahm.finalproject.Adapters.C_RvAdapter_HomeFg_ListNews;
import com.mahm.finalproject.Model.ActivitiesData;
import com.mahm.finalproject.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements C_RvAdapter_HomeFg_ListNews.OnClickItemListener {

    private View view;
    private ViewFlipper viewFlipper;
    //    private C_VPagerAdapter_HomeFg viewpager_adapter;
    private C_RvAdapter_HomeFg_ListNews recViwe_Adapter;
    private List<ActivitiesData> data;
    private RecyclerView mHome_rView;

    private Timer timer;
    private int page = 0;


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


        recViwe_Adapter = new C_RvAdapter_HomeFg_ListNews(getContext(), data, this);
        mHome_rView.setLayoutManager(new LinearLayoutManager(getContext()));
        mHome_rView.setHasFixedSize(true);
        mHome_rView.setAdapter(recViwe_Adapter);

        int[] images = {R.drawable.splash_img, R.drawable.login_img_family, R.drawable.login_img0_family};

        for (int image :
                images) {
            slideImages(image);
        }

        return view;
    }

    //Initialization "FindView"
    void init() {
        mHome_rView = view.findViewById(R.id.homeFg_list_item);
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

        getFragmentManager().beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                .replace(R.id.fragment_container,
                        new Details_HomeFragment()).commit();
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
