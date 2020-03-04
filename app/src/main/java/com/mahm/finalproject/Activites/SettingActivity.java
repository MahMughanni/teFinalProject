package com.mahm.finalproject.Activites;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.mahm.finalproject.R;

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


    }

    void init() {
        mSwitch1 = findViewById(R.id.switch1);
        mSettingFgTvNotif = findViewById(R.id.settingFg_tv_Notif);
        mSettingFgTvChangePassword = findViewById(R.id.settingFg_tv_ChangePassword);
        mSettingFgTvChangePhnoeNumber = findViewById(R.id.settingFg_tv_ChangePhnoe_Number);

    }

}
