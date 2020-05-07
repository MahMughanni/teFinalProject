package com.mahm.finalproject.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

        SharedPreferences sp = getSharedPreferences(LoginActivity.USERS_SHARED, MODE_PRIVATE);

        String name = sp.getString(LoginActivity.STUDENT_NAME, "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashActivityProgBar.setVisibility(View.GONE);

                if (!name.isEmpty()) {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }

            }
        }, 3000);
    }
}
