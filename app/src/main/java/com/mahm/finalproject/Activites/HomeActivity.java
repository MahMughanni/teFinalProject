package com.mahm.finalproject.Activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.mahm.finalproject.Fragments.CameraFragment;
import com.mahm.finalproject.Fragments.FinancialFragment;
import com.mahm.finalproject.Fragments.HomeFragment;
import com.mahm.finalproject.Fragments.Nav_AboutUsFragment;
import com.mahm.finalproject.Fragments.Nav_SettingFragment;
import com.mahm.finalproject.Fragments.NoteFragment;
import com.mahm.finalproject.Fragments.TravelFragment;
import com.mahm.finalproject.R;

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

        mHomeActBottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mHomeActToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        mNavView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                    .replace(R.id.fragment_container,
                            new HomeFragment()).commit();
            mNavView.setCheckedItem(R.id.drawer_ic_home);
        }

    }


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
                            break;

                        case R.id.menu_btn_nav_camer:
                            selectedFrag = new CameraFragment();
                            break;

                        case R.id.menu_btn_nav_travel:
                            selectedFrag = new TravelFragment();
                            break;

                        case R.id.menu_btn_nav_notes:
                            selectedFrag = new NoteFragment();
                            break;

                        case R.id.menu_btn_nav_fin_record:
                            selectedFrag = new FinancialFragment();
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

            case R.id.drawer_ic_setting:
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                        .replace(R.id.fragment_container,
                                new Nav_SettingFragment()).commit();
                break;

            case R.id.drawer_ic_info_about:
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
                        .replace(R.id.fragment_container,
                                new Nav_AboutUsFragment()).commit();
                break;

            case R.id.drawer_ic_logout:
                startActivity(new Intent(getApplicationContext(), LoginActvivty.class));
                finish();
                break;


        }
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//
//        inflater.inflate(R.menu.custom_main_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        Fragment selectFrags = null;
//
//        switch (item.getItemId()) {
//            case R.id.custom_menu_home:
////                selectFrags = new HomeFragment();
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectFrags).commit();
//                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.custom_menu_about:
//                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.custom_menu_logout:
//                startActivity(new Intent(HomeActivity.this, LoginActvivty.class));
//                finish();
//                break;
//        }
//
//
//        return true;
//
//    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
