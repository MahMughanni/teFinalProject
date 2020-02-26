package com.mahm.finalproject.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.mahm.finalproject.R;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar mSplashActivityProgBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mSplashActivityProgBar = findViewById(R.id.splash_activity_ProgBar);
        mSplashActivityProgBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashActivityProgBar.setVisibility(View.GONE);

                startActivity(new Intent(SplashActivity.this, LoginActvivty.class));
                finish();

            }
        }, 3000);
    }
}
