package uk.technologylab.razumnik.Activities.Game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uk.technologylab.razumnik.R;
import uk.technologylab.razumnik.Tools.DrawingView;
import uk.technologylab.razumnik.Tools.ValueParser;

public class GameDrawActivity extends AppCompatActivity {

    DrawingView dv;
    int drawableInt;

    public static int LETTERS_MODE = 1;
    public static int NUMBERS_MODE = 2;
    public static int mode;
    public static RelativeLayout  mainLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_draw);
        if (mode != 1 && mode != 2) {
            mode = 0;
        }
        int r_1;
        int r_2;
        switch (mode) {
            case 1:
                r_1 = R.string.letters_draw_title;
                r_2 = R.string.letters_draw;
                drawableInt = R.drawable.a;
                break;
            case 2:
                r_1 = R.string.numbers_draw_title;
                r_2 = R.string.numbers_draw;
                drawableInt = R.drawable.one;
                break;
            default:
                r_1 = R.string.letters_draw_title;
                r_2 = R.string.letters_draw;
                drawableInt = R.drawable.a;
        }

        mainLay =(RelativeLayout) findViewById(R.id.main_layout);
        createCanvas();
        Button againButton = (Button) findViewById(R.id.button_again);
        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCanvas();
            }
        });




        String objects_titles[] = ValueParser.parseValue(getApplicationContext(), r_1);
        final String objects[] = ValueParser.parseValue(getApplicationContext(), r_2);

        LinearLayout letters_linLay = (LinearLayout) findViewById(R.id.list);

        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(30, 30, 30, 30);


        for (int i = 0; i < objects.length; i++) {
            TextView tv = new TextView(getApplicationContext());
            tv.setText(objects_titles[i]);
            tv.setLayoutParams(lparams);
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(28);
            tv.setGravity(Gravity.CENTER);
            letters_linLay.addView(tv);
            final int j = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDrawableInt(objects[j]);
                    createCanvas();
                }
            });
        }

    }


    public void setDrawableInt(String name) {
        drawableInt = getResources().getIdentifier(name, "drawable", getApplicationContext().getPackageName());
    }

    public void createCanvas() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int Height = displayMetrics.heightPixels;
        int Width = displayMetrics.widthPixels;
        int height = 600;
        int width = 600;
        float Density = displayMetrics.scaledDensity;
        int StrokeWidth=45; //brush size

            if (Density >= 1.6 && Density <= 3.0) {
                height = 630;
                width = 630;
                StrokeWidth=50;
            }
           else if (Density <= 1.5) {
            height = 300;
            width = 300;
            StrokeWidth=40;
        }
           else if(Density >= 3.0)
            {
                height = 810;
                width = 810;
                StrokeWidth=95;
            }
            else {
                height = 400;
                width = 400;
            }






        Bitmap superBitmap = BitmapFactory.decodeResource(getResources(), drawableInt);

        superBitmap = Bitmap.createScaledBitmap(
                superBitmap, width, height, true);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.wall_layout);
        if (dv != null) {
            layout.removeView(dv);
        }



        dv = new DrawingView(this, superBitmap, StrokeWidth);

        dv.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        dv.setBackgroundDrawable(getResources().getDrawable(drawableInt));

        RelativeLayout.LayoutParams layPar = new RelativeLayout.LayoutParams(height, width);
        layPar.addRule(RelativeLayout.CENTER_IN_PARENT);
        dv.setLayoutParams(layPar);
        dv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        layout.addView(dv);
    }


}
