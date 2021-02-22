package com.deepak.indianews;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class mainAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;

    public mainAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Headline();
            case 1:
                Business BusinessFragment = new Business();
                return BusinessFragment;
            case 2:
                Entertainment EntertainmentFragment = new Entertainment();
                return EntertainmentFragment;
            case 3:
                Health HealthFragment = new Health();
                return HealthFragment;

            case 4:
                Science scienceFragment = new Science();
                return scienceFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
