package by.ssrlab.razumnik_2_0.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import by.ssrlab.razumnik_2_0.Adapters.ScreenSlidePagerAdapter;
import by.ssrlab.razumnik_2_0.Fragments.ScreenSliderFragment;
import by.ssrlab.razumnik_2_0.R;
import by.ssrlab.razumnik_2_0.Stuff.HouseMain;
import by.ssrlab.razumnik_2_0.Tools.Waiter;

public class MainActivity extends AppCompatActivity {

    private final int PAGE_NUMS = 3;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.main_viewPager);
        HouseMain[] houseMains = new HouseMain[]{
                new HouseMain(getApplicationContext(), HouseMain.TYPE_ONE, true, FirstHouseActivity.class),
                new HouseMain(getApplicationContext(), HouseMain.TYPE_TWO, false, null),
                new HouseMain(getApplicationContext(), HouseMain.TYPE_THREE, false, null)
        };
        ScreenSliderFragment[] fragments = toHouseAttach(houseMains);
        setFragmentsListeners(fragments);

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), fragments);
        mPager.setAdapter(mPagerAdapter);
    }


    private ScreenSliderFragment[] toHouseAttach(HouseMain[] houseMains) {
        ScreenSliderFragment[] fragments = new ScreenSliderFragment[PAGE_NUMS];
        for (int i = 0; i < houseMains.length; i++) {
            fragments[i] = new ScreenSliderFragment();
            fragments[i].setHouseMain(houseMains[i]);
        }
        return fragments;
    }

    private void setFragmentsListeners(ScreenSliderFragment[] fragments) {
        for (int i = 0; i < fragments.length; i++) {
            final ScreenSliderFragment fragment = fragments[i];
            fragment.setListener(new ScreenSliderFragment.OnClickListener() {
                @Override
                public void onClick() {
                    HouseMain houseMain = fragment.getHouseMain();
                    if (houseMain.isAvailability()) {
                        fragment.setImageViewImage(houseMain.getStateImage(true));
                        startActivity(new Intent(getApplicationContext(), houseMain.getMClass()));
                    }
                }
            });
        }
    }
}
