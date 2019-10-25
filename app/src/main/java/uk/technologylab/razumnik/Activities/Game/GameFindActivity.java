package uk.technologylab.razumnik.Activities.Game;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import uk.technologylab.razumnik.R;
import uk.technologylab.razumnik.Tools.MyMediaPlayer;
import uk.technologylab.razumnik.Tools.ValueParser;

public class GameFindActivity extends AppCompatActivity {

    int mOffset = 0;
    String originalText = "";
    SparseBooleanArray letters;
    char symb = '-';
    TextView textView;
    LinearLayout letters_linLay;
    MyMediaPlayer myMediaPlayer;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_find);
        textView = (TextView) findViewById(R.id.tv);
        myMediaPlayer = new MyMediaPlayer(getApplicationContext());
        reset();

        Button btn = (Button) findViewById(R.id.button_again);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mOffset = textView.getOffsetForPosition(motionEvent.getX(), motionEvent.getY());

                    if (letters.indexOfKey(mOffset) >= 0) {
                        if (!letters.get(mOffset)) {
                            counter++;
                        }
                        letters.delete(mOffset);
                        letters.append(mOffset, true);
                    }

                    textView.setText(Html.fromHtml(getColoredText()));
                }
                return false;
            }
        });


    }

    private void reset() {
        counter = 0;
        final String titles[] = ValueParser.parseValue(getApplicationContext(), R.string.letters_find);

        if (letters_linLay != null) {
            letters_linLay.removeAllViews();
        } else {
            letters_linLay = (LinearLayout) findViewById(R.id.list_find);
        }


        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(30, 30, 30, 30);

        TextView testTextView = null;
        for (int i = 0; i < titles.length; i++) {
            final TextView tv = new TextView(getApplicationContext());
            if (titles[i].charAt(0) == symb) {
                tv.setTextColor(Color.GREEN);
            } else {
                tv.setTextColor(Color.WHITE);
            }
            tv.setText(titles[i]);
            tv.setLayoutParams(lparams);
            tv.setTextSize(28);
            tv.setGravity(Gravity.CENTER);
            letters_linLay.addView(tv);

            final int ind = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    symb = titles[ind].charAt(0);
                    reset();
                }
            });

            if (symb == '-') {
                testTextView = tv;
                symb = '_';
            }
        }

        // TODO: 10.05.2018 make counter

        mOffset = 0;
        originalText = textView.getText().toString();
        letters = new SparseBooleanArray();
        for (int i = 0; i < originalText.length(); i++) {
            if (Character.toLowerCase(originalText.charAt(i)) == symb || Character.toUpperCase(originalText.charAt(i)) == symb) {
                letters.append(i, false);
            }
        }
        textView.setText(Html.fromHtml(getColoredText()));
        if (testTextView != null) {
            testTextView.performClick();
        }
    }

    private String getColoredText() {

        String str = originalText;
        for (int i = letters.size() - 1; i >= 0; i--) {
            if (letters.get(letters.keyAt(i))) {
                int offset = letters.keyAt(i);
                str = String.format("%s<font color='#EE0000'>%s</font>%s", str.substring(0, offset), str.charAt(offset), str.substring(offset + 1));
            }
        }
        if (letters.size() != 0 && counter == letters.size()) {
           // myMediaPlayer = new MyMediaPlayer(getApplicationContext());
           // myMediaPlayer.play("numbers/sound_1.mp3");*/
            Toast.makeText(getApplicationContext(), "Добра!", Toast.LENGTH_SHORT).show();
       }
        return str;
    }

}
