package com.ucas.graduationproject.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ucas.graduationproject.R;

public class SettingActivity extends AppCompatActivity {


    private Switch mSwitch1;
    private TextView mSettingFgTvNotif;
    private TextView mSettingFgTvChangePassword;
    private TextView mSettingFgTvChangePhnoeNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nav__setting);

        init();


        mSettingFgTvNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplication(), "تفعيل  الاشعارات ", Toast.LENGTH_SHORT).show();
            }
        });

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

    }

    void init() {
        mSwitch1 = findViewById(R.id.switch1);
        mSettingFgTvNotif = findViewById(R.id.settingFg_tv_Notif);
        mSettingFgTvChangePassword = findViewById(R.id.settingFg_tv_ChangePassword);
        mSettingFgTvChangePhnoeNumber = findViewById(R.id.settingFg_tv_ChangePhnoe_Number);

    }

}
