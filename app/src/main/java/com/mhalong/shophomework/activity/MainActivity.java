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
import com.mhalong.shophomework.model.CartListCollection;
import com.mhalong.shophomework.model.ProductListCollection;
import com.mhalong.shophomework.model.ProductListItem;

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
        setData();

    }

    private void setData() {
        List<ProductListItem> productList = new ArrayList<>();

        ProductListItem temp = new ProductListItem(1, "M9A3", "Beretta", "Semi auto pistol.", R.drawable.a01, 500);
        productList.add(temp);

        ProductListItem temp1 = new ProductListItem(2, "92FS", "Beretta", "US Military contract--check. Renewed US", R.drawable.a02, 190);
        productList.add(temp1);

        ProductListItem temp2 = new ProductListItem(3, "92FS Centenial", "Beretta", "It's first semi-automatic pistol.", R.drawable.a03, 1900);
        productList.add(temp2);

        ProductListItem temp3 = new ProductListItem(4, "92G", "Beretta", "Burnt Bronze Black Beretta 9mm", R.drawable.a04, 650);
        productList.add(temp3);

        ProductListItem temp4 = new ProductListItem(5, "96A1", "Beretta", "New for 2010 the Model 96A1", R.drawable.a05, 680);
        productList.add(temp4);

        ProductListItem temp5 = new ProductListItem(6, "M&P9 shield", "Smith&Wesson", "NEW IN THE BOX,BERETTA 92FS INOX,STAINLESS STEEL,9MM", R.drawable.a06, 450);
        productList.add(temp5);

        ProductListItem temp6 = new ProductListItem(7, "M&P22 compact", "Smith&Wesson", "Precision built to be the most accurate and reliable firearms", R.drawable.a07, 390);
        productList.add(temp6);

        ProductListItem temp7 = new ProductListItem(8, "SW22 victory ", "Smith&Wesson", "The SW22 VICTORY® is constructed on a single-action,", R.drawable.a08, 410);
        productList.add(temp7);

        ProductListItem temp8 = new ProductListItem(9, "Engraved-1911", "Smith&Wesson", "Engraved, Wooden Presentation Case", R.drawable.a09, 1220);
        productList.add(temp8);

        ProductListItem temp9 = new ProductListItem(10, "SW1911 Pro series", "Smith&Wesson", "Completing the line between main production", R.drawable.a10, 1230);
        productList.add(temp9);

        ProductListItem temp10 = new ProductListItem(11, "G17", "Glock", "Designed for professionals, the GLOCK 17", R.drawable.a11, 500);
        productList.add(temp10);

        ProductListItem temp11 = new ProductListItem(12, "G22", "Glock", "GLOCK introduced the G22 .40 in 1990", R.drawable.a12, 500);
        productList.add(temp11);

        ProductListItem temp12 = new ProductListItem(13, "G43", "Glock", "This auction is for a Glock G43 9mm pistol", R.drawable.a13, 100);
        productList.add(temp12);

        ProductListItem temp13 = new ProductListItem(14, "G36", "Glock", "Up for sale is a brand new Glock 36 Gen3 .45ACP Pistol.", R.drawable.a14, 540);
        productList.add(temp13);

        ProductListItem temp14 = new ProductListItem(15, "G41", "Glock", "The GLOCK 41 Gen4 is a practical/tactical .45 AUTO caliber pistol", R.drawable.a15, 670);
        productList.add(temp14);

        ProductListItem temp15 = new ProductListItem(16, "P2870", "Colt", "Single Action Army with Black Powder Frame", R.drawable.a16, 1600);
        productList.add(temp15);

        ProductListItem temp16 = new ProductListItem(17, "P2850", "Colt", "Single Action Army with Black Powder Frame", R.drawable.a17, 1600);
        productList.add(temp16);

        ProductListItem temp17 = new ProductListItem(18, "P1876", "Colt", "Revolver: Single Action", R.drawable.a18, 1800);
        productList.add(temp17);

        ProductListItem temp18 = new ProductListItem(19, "O6891", "Colt", "Pistol: Semi-Auto", R.drawable.a19, 600);
        productList.add(temp18);

        ProductListItem temp19 = new ProductListItem(20, "O4691", "Colt", "Pistol: Semi-Auto", R.drawable.a20, 850);
        productList.add(temp19);


        ProductListCollection.getInstance().setProductList(productList);
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
                        getSupportActionBar().setTitle("ค้นหา");
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
