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

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    /*
     *TODO: Fix date not showing date from API response
     * TODO: Make sure there aren't excessive networking calls
     *  TODO: implement a network error handling (show a screen or something)
     *   TODO: implement in app webview where the user is taken after clickng on an article
     *    TODO: implement share button for articles
     *     TODO: implement cached news results (ROOM database)
     *      TODO: implement color prefrences and settings screen
     *       TODO: implement proper testing of app
     *        TODO: loading indicator is now showing fix this
     *
     * */
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private NewsFragmentStatePagerAdapter viewpagerAdapter;
    private String[] tabTitles = {"General", "Entertainment", "Business", "Health", "Science", "Sports", "Technology"};


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