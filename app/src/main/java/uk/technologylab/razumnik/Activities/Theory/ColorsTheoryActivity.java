package uk.technologylab.razumnik.Activities.Theory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import uk.technologylab.razumnik.Adapters.MyRecycleViewAdapter;
import uk.technologylab.razumnik.R;
import uk.technologylab.razumnik.Tools.MyMediaPlayer;
import uk.technologylab.razumnik.Tools.ValueParser;

public class ColorsTheoryActivity extends AppCompatActivity {


    MyMediaPlayer myMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        myMediaPlayer = new MyMediaPlayer(getApplicationContext());
        RecyclerView topics_list = (RecyclerView) findViewById(R.id.items_list);
        String[][] data = new String[3][];
        data[0] = ValueParser.parseValue(getApplicationContext(), R.string.colors);
        data[1] = ValueParser.parseValue(getApplicationContext(), R.string.colors_back);
        data[2] = ValueParser.parseValue(getApplicationContext(), R.string.colors_text);
        final String audio[] = ValueParser.parseValue(getApplicationContext(), R.string.colors_audio);

        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter<>(data, R.layout.frame_black);
        adapter.setListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int pos) {
                myMediaPlayer.play(String.format("colors/%s.mp3", audio[pos]));
            }
        });
        topics_list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        topics_list.setAdapter(adapter);
    }
}
