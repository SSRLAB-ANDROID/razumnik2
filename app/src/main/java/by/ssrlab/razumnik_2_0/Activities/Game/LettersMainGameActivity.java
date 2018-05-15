package by.ssrlab.razumnik_2_0.Activities.Game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import by.ssrlab.razumnik_2_0.R;

public class LettersMainGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters_main_game);
        ImageView draw_letters_iv = (ImageView) findViewById(R.id.draw_letters_iv);
        ImageView find_letters_iv = (ImageView) findViewById(R.id.find_letters_iv);
        GameDrawActivity.mode = GameDrawActivity.LETTERS_MODE;
        draw_letters_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GameDrawActivity.class));
            }
        });
        find_letters_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GameFindActivity.class));
            }
        });
    }
}
