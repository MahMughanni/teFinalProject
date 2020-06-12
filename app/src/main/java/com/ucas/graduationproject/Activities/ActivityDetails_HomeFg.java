package com.ucas.graduationproject.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucas.graduationproject.Model.AdsData;
import com.ucas.graduationproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityDetails_HomeFg extends AppCompatActivity {

    private TextView mToolbarTitle;
    private Toolbar mToolbar;
    private ImageView mImageDetails;
    private TextView mTvDescriptionDetails;
    private ArrayList<AdsData> adsData = new ArrayList<>();

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
                Picasso.with(ActivityDetails_HomeFg.this).load(image).into(mImageDetails);
            }

            if (!listImage.isEmpty() && image.contains("$")) {
                Picasso.with(ActivityDetails_HomeFg.this).load(listImage.get(0)).into(mImageDetails);
            }

        }
    }


    //Initialization "FindView"
    void init() {
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbar = findViewById(R.id.toolbar);
        mImageDetails = findViewById(R.id.imageDetails);
        mTvDescriptionDetails = findViewById(R.id.tvDescriptionDetails);
    }
}
