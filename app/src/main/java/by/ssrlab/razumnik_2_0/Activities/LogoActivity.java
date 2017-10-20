package by.ssrlab.razumnik_2_0.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import by.ssrlab.razumnik_2_0.R;
import by.ssrlab.razumnik_2_0.Tools.Waiter;

public class LogoActivity extends AppCompatActivity {
    private final int LOGO_TIME_SEC = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        new LoadingTask().execute();
        // loadResources();

    }

    private class LoadingTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void[] params) {
            Waiter.waitInSec(LOGO_TIME_SEC);
            return null;
        }

        @Override
        protected void onPostExecute(Void o) {
            super.onPostExecute(o);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
