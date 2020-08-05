package com.example.simplenews.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.simplenews.fragments.BusinessNewsFragment;
import com.example.simplenews.fragments.EntertainmentNewsFragment;
import com.example.simplenews.fragments.GeneralNewsFragment;
import com.example.simplenews.fragments.HealthNewsFragment;
import com.example.simplenews.fragments.ScienceNewsFragment;
import com.example.simplenews.fragments.SportsNewsFragment;
import com.example.simplenews.fragments.TechnologyNewsFragment;

public class NewsFragmentStatePagerAdapter extends FragmentStateAdapter {

    private static final int NUM_OF_FRAGMENTS = 7;

    public NewsFragmentStatePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return GeneralNewsFragment.newInstance();
            case 1:
                return EntertainmentNewsFragment.newInstance();
            case 2:
                return BusinessNewsFragment.newInstance();
            case 3:
                return HealthNewsFragment.newInstance();
            case 4:
                return ScienceNewsFragment.newInstance();
            case 5:
                return SportsNewsFragment.newInstance();
            case 6:
                return TechnologyNewsFragment.newInstance();
            default:
                return null;

        }
    }

    @Override
    public int getItemCount() {
        return NUM_OF_FRAGMENTS;
    }
}
