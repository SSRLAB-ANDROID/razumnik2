package by.ssrlab.razumnik.Activities.Theory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import by.ssrlab.razumnik.Adapters.MyRecycleViewAdapter;
import by.ssrlab.razumnik.R;
import by.ssrlab.razumnik.Tools.MyMediaPlayer;
import by.ssrlab.razumnik.Tools.ValueParser;

public class LettersTheoryActivity extends AppCompatActivity {

    MyMediaPlayer myMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters_theory);
        myMediaPlayer = new MyMediaPlayer(getApplicationContext());
        RecyclerView topics_list = (RecyclerView) findViewById(R.id.items_list);
        String data[] = ValueParser.parseValue(getApplicationContext(), R.string.letters);
        final String audio[] = ValueParser.parseValue(getApplicationContext(), R.string.letters_audio);


        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter<>(data, R.layout.frame_violet);
        adapter.setListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int pos) {
                myMediaPlayer.play(String.format("letters/%s.mp3", audio[pos]));
            }
        });
        topics_list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        topics_list.setAdapter(adapter);

    }
}
