package by.ssrlab.razumnik_2_0.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import by.ssrlab.razumnik_2_0.Adapters.MyRecycleViewAdapter;
import by.ssrlab.razumnik_2_0.R;

public class FirstHouseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_house);
        RecyclerView topics_list = (RecyclerView) findViewById(R.id.topics_list);
        //String data[] = new String[]{"1", "4", "4"};
        Integer data[] = new Integer[]{R.drawable.base_back, R.drawable.body_back, R.drawable.full_back};
        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter<>(data, R.layout.frame_item);
        adapter.setListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick() {
                startActivity(new Intent(getApplicationContext(), DoorsActivity.class));
            }
        });
        topics_list.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        topics_list.setAdapter(adapter);
    }
}
