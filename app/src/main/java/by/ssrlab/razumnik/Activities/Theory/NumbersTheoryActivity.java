package by.ssrlab.razumnik.Activities.Theory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import by.ssrlab.razumnik.Adapters.MyRecycleViewAdapter;
import by.ssrlab.razumnik.R;
import by.ssrlab.razumnik.Tools.MyMediaPlayer;
import by.ssrlab.razumnik.Tools.ValueParser;

public class NumbersTheoryActivity extends AppCompatActivity {

    MyMediaPlayer myMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters_theory);
        myMediaPlayer = new MyMediaPlayer(getApplicationContext());
        RecyclerView topics_list = (RecyclerView) findViewById(R.id.items_list);
        String data[] = ValueParser.parseValue(getApplicationContext(), R.string.numbers);
        final String audio[] = ValueParser.parseValue(getApplicationContext(), R.string.numbers_audio);

        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter<>(data, R.layout.frame_orange);
        adapter.setListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int pos) {
                myMediaPlayer.play(String.format("numbers/%s.mp3", audio[pos]));
            }
        });
        topics_list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        topics_list.setAdapter(adapter);
    }
}
