package com.mahm.finalproject.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahm.finalproject.R;
import com.squareup.picasso.Picasso;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_details__home);

        init();
        setSupportActionBar(mToolbar);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            mToolbarTitle.setText(bundle.getString("title"));
            mTvDescriptionDetails.setText(bundle.getString("description"));
            Picasso.with(ActivityDetails_HomeFg.this).load(bundle.getString("image")).into(mImageDetails);

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
