package com.ucas.graduationproject.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ucas.graduationproject.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingActivity extends AppCompatActivity {


    private TextView mSettingFgTvChangePassword;
    private TextView mSettingFgTvChangePhnoeNumber;
    private Toolbar mToolbarSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nav__setting);

        init();

        setSupportActionBar(mToolbarSettings);


        mSettingFgTvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), UpdatePasswordActivity.class));
            }
        });

        mSettingFgTvChangePhnoeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), UpdatePhoneNumberActivity.class));
            }
        });

        mToolbarSettings.setNavigationIcon(R.drawable.ic_back);

        mToolbarSettings.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    void init() {
        mToolbarSettings = findViewById(R.id.toolbarSettings);
        mSettingFgTvChangePassword = findViewById(R.id.settingFg_tv_ChangePassword);
        mSettingFgTvChangePhnoeNumber = findViewById(R.id.settingFg_tv_ChangePhnoe_Number);

    }

}
