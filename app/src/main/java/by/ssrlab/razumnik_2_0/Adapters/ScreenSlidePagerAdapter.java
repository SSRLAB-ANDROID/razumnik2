package by.ssrlab.razumnik_2_0.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Mihal on 20.10.2017.
 */

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
