package com.ucas.graduationproject.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.ucas.graduationproject.Fragments.CameraFragment;
import com.ucas.graduationproject.Fragments.FinancialFragment;
import com.ucas.graduationproject.Fragments.HolidaysFragment;
import com.ucas.graduationproject.Fragments.HomeFragment;
import com.ucas.graduationproject.Fragments.NoteFragment;
import com.ucas.graduationproject.Fragments.TravelFragment;
import com.ucas.graduationproject.R;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mHomeActBottomNav;
    private Toolbar mHomeActToolbar;
    private FrameLayout mFragmentContainer;
    private NavigationView mNavView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        setSupportActionBar(mHomeActToolbar);
        mHomeActToolbar.setTitle("الرئيسية");

        mHomeActBottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mHomeActToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                Log.e("fcm_token", instanceIdResult.getToken());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("onFailure", e.getMessage());
            }
        });

        SharedPreferences sp = getSharedPreferences(LoginActivity.USERS_SHARED, MODE_PRIVATE);
        String name = sp.getString(LoginActivity.STUDENT_NAME, "");
        String phone = sp.getString(LoginActivity.STUDENT_PHONE, "");
        getMenuData(name, phone);

        mNavView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                    .replace(R.id.fragment_container,
                            new HomeFragment()).commit();
            mNavView.setCheckedItem(R.id.drawer_ic_home);
        }

    }

    //Initialization "FindView"
    void init() {

        mHomeActBottomNav = findViewById(R.id.home_act_bottom_nav);
        mHomeActToolbar = findViewById(R.id.homeAct_toolbar);
        mFragmentContainer = findViewById(R.id.fragment_container);
        mNavView = findViewById(R.id.nav_View);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout = findViewById(R.id.drawer_layout);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFrag = null;
                    switch (item.getItemId()) {
                        case R.id.menu_btn_nav_home:
                            selectedFrag = new HomeFragment();
                            mHomeActToolbar.setTitle("الرئيسية");
                            break;

                        case R.id.menu_btn_nav_holidays:
                            selectedFrag = new HolidaysFragment();
                            mHomeActToolbar.setTitle("الاجازات");
                            break;

                        case R.id.menu_btn_nav_travel:
                            selectedFrag = new TravelFragment();
                            mHomeActToolbar.setTitle("الرحلات");
                            break;

                        case R.id.menu_btn_nav_notes:
                            selectedFrag = new NoteFragment();
                            mHomeActToolbar.setTitle("الملاحظات");
                            break;

                        case R.id.menu_btn_nav_fin_record:
                            selectedFrag = new FinancialFragment();
                            mHomeActToolbar.setTitle("السجل المالي");
                            break;
                    }

                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                            .replace(
                                    R.id.fragment_container, selectedFrag).commit();
                    return true;
                }
            };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.drawer_ic_home:
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                        .replace(R.id.fragment_container,
                                new HomeFragment()).commit();
                break;

            case R.id.drawer_ic_camere:
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                        .replace(R.id.fragment_container,
                                new CameraFragment()).commit();
                break;

            case R.id.drawer_ic_setting:
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));

                break;

            case R.id.drawer_ic_info_about:
                startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
                break;

            case R.id.drawer_ic_logout:
                SharedPreferences sp = getSharedPreferences(LoginActivity.USERS_SHARED, MODE_PRIVATE);
                SharedPreferences.Editor spEdit = sp.edit();
                spEdit.clear();
                spEdit.apply();

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;


        }
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void getMenuData(String name, String phoneNumber) {

        View header = mNavView.getHeaderView(0);
        TextView tv_name = header.findViewById(R.id.tvNavigationStudentName);
        TextView tv_phone = header.findViewById(R.id.tvNavigationStudentPhoneNumber);

        tv_name.setText(name);
        tv_phone.setText(phoneNumber);

    }


}
