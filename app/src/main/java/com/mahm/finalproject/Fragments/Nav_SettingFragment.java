package com.mahm.finalproject.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.mahm.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Nav_SettingFragment extends Fragment {


    private View view;
    private Switch mSwitch1;
    private TextView mSettingFgTvNotif;
    private TextView mSettingFgTvChangePassword;
    private TextView mSettingFgTvChangePhnoeNumber;


    public Nav_SettingFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_nav__setting, container, false);
        init();

        mSettingFgTvNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "تفعيل  الاشعارات ", Toast.LENGTH_SHORT).show();
            }
        });





        return view;
    }

    void init() {
        mSwitch1 = view.findViewById(R.id.switch1);
        mSettingFgTvNotif = view.findViewById(R.id.settingFg_tv_Notif);
        mSettingFgTvChangePassword = view.findViewById(R.id.settingFg_tv_ChangePassword);
        mSettingFgTvChangePhnoeNumber = view.findViewById(R.id.settingFg_tv_ChangePhnoe_Number);

    }

}
