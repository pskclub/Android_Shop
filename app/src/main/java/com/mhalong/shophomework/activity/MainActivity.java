package com.mhalong.shophomework.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.mhalong.shophomework.R;
import com.mhalong.shophomework.fragment.AccountFragment;
import com.mhalong.shophomework.fragment.CategoryFragment;
import com.mhalong.shophomework.fragment.MainFragment;
import com.mhalong.shophomework.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance())
                    .commit();
        }

        setNavBottom();


    }

    private void setNavBottom() {

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.nav_home, R.drawable.ic_home_white_24dp, R.color.colorPrimaryDark);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.nav_search, R.drawable.ic_search_white_24dp, R.color.colorPrimaryDark);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.nav_account, R.drawable.ic_account_circle_white_24dp, R.color.colorPrimaryDark);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);


// Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);
// Set current item programmatically
        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contentContainer, MainFragment.newInstance())
                                .commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contentContainer, SearchFragment.newInstance())
                                .commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contentContainer, AccountFragment.newInstance())
                                .commit();
                        break;

                }
                return true;
            }
        });
    }
}
