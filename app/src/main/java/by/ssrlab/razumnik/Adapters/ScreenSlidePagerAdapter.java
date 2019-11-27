package by.ssrlab.razumnik.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

    private Fragment[] mFragments;

    public ScreenSlidePagerAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        mFragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
}
