package by.ssrlab.razumnik_2_0.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import by.ssrlab.razumnik_2_0.Adapters.MyRecycleViewAdapter;
import by.ssrlab.razumnik_2_0.R;
import by.ssrlab.razumnik_2_0.Tools.MyMediaPlayer;
import by.ssrlab.razumnik_2_0.Tools.ValueParser;

public class FirstHouseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_house);
        RecyclerView topics_list = (RecyclerView) findViewById(R.id.items_list);
        // TODO: 21.11.2017 separate to another class letters must be
        String data[] = ValueParser.parseValue(getApplicationContext(), R.string.letters);
        final String audio[] = ValueParser.parseValue(getApplicationContext(), R.string.letters_audio);

        //Integer data[] = new Integer[]{R.drawable.base_back, R.drawable.body_back, R.drawable.full_back};
        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter<>(data, R.layout.frame_alphabet);
        adapter.setListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int pos) {
                MyMediaPlayer myMediaPlayer = new MyMediaPlayer(getApplicationContext());
                myMediaPlayer.play(String.format("letters/%s.mp3", audio[pos]));
            }
        });
        topics_list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        topics_list.setAdapter(adapter);
    }
}
