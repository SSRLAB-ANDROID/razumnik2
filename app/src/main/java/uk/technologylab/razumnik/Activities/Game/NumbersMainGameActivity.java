package uk.technologylab.razumnik.Activities.Game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import uk.technologylab.razumnik.R;

public class NumbersMainGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_main_game);
        ImageView draw_letters_iv = (ImageView) findViewById(R.id.draw_numbers_iv);
        GameDrawActivity.mode = GameDrawActivity.NUMBERS_MODE;
        draw_letters_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GameDrawActivity.class));
            }
        });
    }
}
