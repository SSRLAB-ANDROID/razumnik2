package uk.technologylab.razumnik.Activities;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uk.technologylab.razumnik.Adapters.ScreenSlidePagerAdapter;
import uk.technologylab.razumnik.Fragments.ScreenSliderFragment;
import uk.technologylab.razumnik.R;
import uk.technologylab.razumnik.Stuff.DoorMain;
import uk.technologylab.razumnik.Tools.Waiter;

public class MainActivity extends AppCompatActivity {

    private final int DOOR_TIME_SEC = 1;
    private int PAGE_NUMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager mPager = (ViewPager) findViewById(R.id.main_viewPager);
        DoorMain[] doorMains = new DoorMain[]{
                new DoorMain(getApplicationContext(), DoorMain.TYPE_HOUSE, DoorMain.TYPE_ONE, true, TopicsActivity.class),
                new DoorMain(getApplicationContext(), DoorMain.TYPE_HOUSE, DoorMain.TYPE_TWO, false, null),
                new DoorMain(getApplicationContext(), DoorMain.TYPE_HOUSE, DoorMain.TYPE_THREE, false, null)
        };
        PAGE_NUMS = doorMains.length;
        ScreenSliderFragment[] fragments = toHouseAttach(doorMains);
        setFragmentsListeners(fragments);

        PagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), fragments);
        mPager.setAdapter(mPagerAdapter);
    }


    private ScreenSliderFragment[] toHouseAttach(DoorMain[] doorMains) {
        ScreenSliderFragment[] fragments = new ScreenSliderFragment[PAGE_NUMS];
        for (int i = 0; i < doorMains.length; i++) {
            fragments[i] = new ScreenSliderFragment();
            fragments[i].setDoorMain(doorMains[i]);
        }
        return fragments;
    }

    private void setFragmentsListeners(ScreenSliderFragment[] fragments) {
        for (final ScreenSliderFragment fragment : fragments
                ) {
            fragment.setListener(new ScreenSliderFragment.OnClickListener() {
                @Override
                public void onClick() {
                    final DoorMain doorMain = fragment.getDoorMain();
                    if (doorMain.isAvailability()) {
                        fragment.setClickable(false);
                        fragment.setImageViewImage(doorMain.getStateImage(true));
                        new Waiter(new Waiter.OnWaitCompleteListener() {
                            @Override
                            public void OnWaitComplete() {
                                fragment.setClickable(true);
                                startActivity(new Intent(getApplicationContext(), doorMain.getMClass()));
                            }
                        }).execute(DOOR_TIME_SEC);

                    }
                }
            });
        }
    }
}