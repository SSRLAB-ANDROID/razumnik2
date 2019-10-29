package uk.technologylab.razumnik.Activities;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uk.technologylab.razumnik.Activities.Game.LettersMainGameActivity;
import uk.technologylab.razumnik.Activities.Game.NumbersMainGameActivity;
import uk.technologylab.razumnik.Activities.Theory.ColorsTheoryActivity;
import uk.technologylab.razumnik.Activities.Theory.LettersTheoryActivity;
import uk.technologylab.razumnik.Activities.Theory.MusicTheoryActivity;
import uk.technologylab.razumnik.Activities.Theory.NumbersTheoryActivity;
import uk.technologylab.razumnik.Adapters.ScreenSlidePagerAdapter;
import uk.technologylab.razumnik.Fragments.ScreenSliderFragment;
import uk.technologylab.razumnik.R;
import uk.technologylab.razumnik.Stuff.DoorMain;
import uk.technologylab.razumnik.Tools.Waiter;

public class DoorsActivity extends AppCompatActivity {

    private final int DOOR_TIME_SEC = 1;
    private int PAGE_NUMS;
    private DoorMain[] doorMains;
    private int topic;

    public static final int LETTERS_TOPIC = 0;
    public static final int NUMBERS_TOPIC = 1;
    public static final int COLORS_TOPIC = 2;
    public static final int MUSIC_TOPIC = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int a = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doors);
        topic = getSharedPreferences("sp", MODE_PRIVATE).getInt("topic", 0);
        ViewPager mPager = (ViewPager) findViewById(R.id.main_viewPager);

        setDoorsRoom();
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

    private void setDoorsRoom() {
        Class theoryClass = null;
        Class gameClass = null;
        if (topic == LETTERS_TOPIC) {
            theoryClass = LettersTheoryActivity.class;
            gameClass = LettersMainGameActivity.class;
        }
        if (topic == NUMBERS_TOPIC) {
            theoryClass = NumbersTheoryActivity.class;
            gameClass = NumbersMainGameActivity.class;
        }
        if (topic == COLORS_TOPIC) {
            theoryClass = ColorsTheoryActivity.class;
            gameClass = null;
        }
        if (topic == MUSIC_TOPIC) {
            theoryClass = MusicTheoryActivity.class;
            gameClass = null;
        }
        boolean availability = false;
        if (gameClass != null) {
            availability = true;
        }
        doorMains = new DoorMain[]{
                new DoorMain(getApplicationContext(), DoorMain.TYPE_DOOR, DoorMain.TYPE_ONE, true, theoryClass, "To study"),
                new DoorMain(getApplicationContext(), DoorMain.TYPE_DOOR, DoorMain.TYPE_ONE, availability, gameClass, "Play")
        };
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
