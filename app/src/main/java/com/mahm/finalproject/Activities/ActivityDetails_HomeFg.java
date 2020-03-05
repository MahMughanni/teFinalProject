package com.mahm.finalproject.Activities;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahm.finalproject.R;

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


    }


    //Initialization "FindView"
    void init() {
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbar = findViewById(R.id.toolbar);
        mImageDetails = findViewById(R.id.imageDetails);
        mTvDescriptionDetails = findViewById(R.id.tvDescriptionDetails);
    }
}
