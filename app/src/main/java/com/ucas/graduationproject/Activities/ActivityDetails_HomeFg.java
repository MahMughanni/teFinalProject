package com.ucas.graduationproject.Activities;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;
import com.ucas.graduationproject.Adapters.SliderAdapterHomeFragment;
import com.ucas.graduationproject.Model.AdsData;
import com.ucas.graduationproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class ActivityDetails_HomeFg extends AppCompatActivity {

    private TextView mToolbarTitle;
    private Toolbar mToolbar;
    private ImageView mImageDetails;
    private TextView mTvDescriptionDetails;
    private ArrayList<AdsData> adsData = new ArrayList<>();
    private SliderView mSliderDetails;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_details__home);

        init();
        setSupportActionBar(mToolbar);


        Intent intent = getIntent();

        if (intent != null) {

            mToolbarTitle.setText(intent.getStringExtra("title"));
            mTvDescriptionDetails.setText(intent.getStringExtra("description"));
            String image = intent.getStringExtra("image");
            List<String> listImage = intent.getStringArrayListExtra("listImage");

            if (image != null) {
                mImageDetails.setVisibility(View.VISIBLE);
                mSliderDetails.setVisibility(View.GONE);

                Picasso.with(ActivityDetails_HomeFg.this).load(image).into(mImageDetails);
            }

            if (!Objects.requireNonNull(listImage).isEmpty() && Objects.requireNonNull(image).contains("$")) {

                mImageDetails.setVisibility(View.GONE);
                mSliderDetails.setVisibility(View.VISIBLE);

                for (int i = 0; i < listImage.size(); i++) {
                    segmentation(listImage.get(i), list);
                }

                setUpSliderView(mSliderDetails, list);

            }

        }

    }


    //Initialization "FindView"
    void init() {
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbar = findViewById(R.id.toolbar);
        mImageDetails = findViewById(R.id.imageDetails);
        mTvDescriptionDetails = findViewById(R.id.tvDescriptionDetails);
        mSliderDetails = findViewById(R.id.sliderDetails);
    }

    private void setUpSliderView(SliderView sliderView, List<String> list) {

        for (int i = 0; i < list.size(); i++) {
            adsData.add(new AdsData(list.get(i)));
        }

        SliderAdapterHomeFragment sliderAdapter = new SliderAdapterHomeFragment(ActivityDetails_HomeFg.this, adsData);
        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

    }

    private void segmentation(String s, List<String> list) {
        StringTokenizer st = new StringTokenizer(s, "$");
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }

    }

}
