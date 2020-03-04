package com.mahm.finalproject.Activites;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.mahm.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityDetails_HomeFg extends AppCompatActivity {

    private TextView mFgDetailsTvTitle;
    private ImageView mFgDetailsImg;
    private MultiAutoCompleteTextView mFgDetailsMTvDescription;
    private Toolbar mDetailsActToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_details__home);

        init();

        setSupportActionBar(mDetailsActToolbar);

    }


    //Initialization "FindView"
    void init() {
        mDetailsActToolbar = findViewById(R.id.detailsAct_toolbar);

        mFgDetailsImg = findViewById(R.id.fg_details_img);
        mFgDetailsMTvDescription = findViewById(R.id.fg_details_mTv_description);
    }
}
