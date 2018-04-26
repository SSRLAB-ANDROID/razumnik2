package by.ssrlab.razumnik_2_0.Activities.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

import by.ssrlab.razumnik_2_0.R;
import by.ssrlab.razumnik_2_0.Tools.DrawingView;
import by.ssrlab.razumnik_2_0.Tools.ValueParser;

public class LettersGameDrawActivity extends AppCompatActivity {

    DrawingView dv;
    int drawableInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters_game_draw);

        drawableInt = R.drawable.a;
        createCanvas();

        Button againButton = (Button) findViewById(R.id.button_again);
        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCanvas();
            }
        });

        String letters_titles[] = ValueParser.parseValue(getApplicationContext(), R.string.letters_draw_title);
        final String letters[] = ValueParser.parseValue(getApplicationContext(), R.string.letters_draw);

        LinearLayout letters_linLay = (LinearLayout) findViewById(R.id.letters_list);

        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(30,30,30,30);


        for (int i = 0; i < letters.length; i++) {
            TextView tv = new TextView(getApplicationContext());
            tv.setText(letters_titles[i]);
            tv.setLayoutParams(lparams);
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(28);
            tv.setGravity(Gravity.CENTER);
            letters_linLay.addView(tv);
            final int j = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDrawableInt(letters[j]);
                    createCanvas();
                }
            });
        }

    }


    public void setDrawableInt(String name) {
        drawableInt = getResources().getIdentifier(name, "drawable", getApplicationContext().getPackageName());
    }

    public void createCanvas() {
        final int height = 600;
        final int width = 600;

        Bitmap superBitmap = BitmapFactory.decodeResource(getResources(), drawableInt);

        superBitmap = Bitmap.createScaledBitmap(
                superBitmap, width, height, false);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.wall_layout);
        if (dv != null) {
            layout.removeView(dv);
        }

        dv = new DrawingView(this, superBitmap);

        dv.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        dv.setBackgroundDrawable(getResources().getDrawable(drawableInt));

        RelativeLayout.LayoutParams layPar = new RelativeLayout.LayoutParams(height, width);
        layPar.addRule(RelativeLayout.CENTER_IN_PARENT);
        dv.setLayoutParams(layPar);
        layout.addView(dv);
    }

}
