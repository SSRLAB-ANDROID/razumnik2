package by.ssrlab.razumnik_2_0.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import by.ssrlab.razumnik_2_0.Adapters.MyRecycleViewAdapter;
import by.ssrlab.razumnik_2_0.R;

public class TopicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);


        RecyclerView topics_list = (RecyclerView) findViewById(R.id.items_list);
        Integer data[] = new Integer[]{R.drawable.topic_1, R.drawable.topic_2, R.drawable.topic_3, R.drawable.topic_4};
        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter<>(data, R.layout.frame_item);
        adapter.setListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int pos) {
                int topic = -1;
                if (pos == 0) {
                    topic = DoorsActivity.LETTERS_TOPIC;
                }
                if (pos == 1) {
                    topic = DoorsActivity.NUMBERS_TOPIC;
                }
                if (pos == 2) {
                    topic = DoorsActivity.MUSIC_TOPIC;
                }
                if (pos == 3) {
                    topic = DoorsActivity.COLORS_TOPIC;
                }
                if (topic != -1) {
                    getSharedPreferences("sp", MODE_PRIVATE).edit().putInt("topic", topic).apply();
                    startActivity(new Intent(getApplicationContext(), DoorsActivity.class));
                }
            }
        });
        topics_list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        topics_list.setAdapter(adapter);
    }
}
