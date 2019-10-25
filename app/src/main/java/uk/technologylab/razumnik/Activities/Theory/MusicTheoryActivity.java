package uk.technologylab.razumnik.Activities.Theory;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import uk.technologylab.razumnik.Adapters.PianoAdapter;
import uk.technologylab.razumnik.R;
import uk.technologylab.razumnik.Tools.MyMediaPlayer;
import uk.technologylab.razumnik.Tools.ValueParser;

public class MusicTheoryActivity extends AppCompatActivity {

    MyMediaPlayer myMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_theory);
        myMediaPlayer = new MyMediaPlayer(getApplicationContext());
        RecyclerView topics_list = (RecyclerView) findViewById(R.id.items_list);
        String data[] = ValueParser.parseValue(getApplicationContext(), R.string.music);
        final String audio[] = ValueParser.parseValue(getApplicationContext(), R.string.music_audio);



        RecyclerView whiteKeysRecView = (RecyclerView) findViewById(R.id.recViewWhite);
        PianoAdapter adapter_white = new PianoAdapter(R.layout.white_key, 0);
        whiteKeysRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        whiteKeysRecView.setAdapter(adapter_white);
        whiteKeysRecView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL) {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                // Do not draw the divider
            }
        });
        adapter_white.setListener(new PianoAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int i) {
                myMediaPlayer.play(String.format("music/%s.wav", audio[i]));
            }
        });


        RecyclerView blackKeysRecView = (RecyclerView) findViewById(R.id.recViewBlack);
        PianoAdapter adapter_black = new PianoAdapter(R.layout.black_key, 1);
        blackKeysRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        blackKeysRecView.setAdapter(adapter_black);
        blackKeysRecView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL) {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                // Do not draw the divider
            }
        });
        adapter_black.setListener(new PianoAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int i) {
                myMediaPlayer.play(String.format("music/%s.wav", audio[i]));
            }
        });

    }
}
