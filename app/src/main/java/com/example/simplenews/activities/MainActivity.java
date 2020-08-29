package com.example.simplenews.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.viewpager2.widget.ViewPager2;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.simplenews.R;
import com.example.simplenews.adapters.NewsFragmentStatePagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.victor.loading.rotate.RotateLoading;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    RotateLoading rotateLoadingindicator;
    private NewsFragmentStatePagerAdapter viewpagerAdapter;
    private String[] tabTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Planting timber debug tree here because this joint refuses to work when planted in the application class
        Timber.plant(new Timber.DebugTree());
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.news_view_pager);
        viewpagerAdapter = new NewsFragmentStatePagerAdapter(this);
        viewPager.setAdapter(viewpagerAdapter);
        viewPager.setOffscreenPageLimit(7);

//        Setting the text of the tabLayout Titles
        tabTitles = new String[]{getString(R.string.tab_layout_general), getString(R.string.tab_layout_entertainment), getString(R.string.tab_layout_business), getString(R.string.tab_layout_health), getString(R.string.tab_layout_science), getString(R.string.tab_layout_sports), getString(R.string.tab_layout_technology)

        };


//Setting the text of the tablayout
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(tabTitles[position])).attach();


    }


    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }


}