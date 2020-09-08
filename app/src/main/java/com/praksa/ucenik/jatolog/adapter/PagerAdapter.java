package com.praksa.ucenik.jatolog.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.praksa.ucenik.jatolog.fragment.FragmentHome;
import com.praksa.ucenik.jatolog.fragment.FragmentTopRijeci;


public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentHome home = new FragmentHome();
                return home;
            case 1:
                FragmentTopRijeci topRijeci = new FragmentTopRijeci();
                return topRijeci;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}