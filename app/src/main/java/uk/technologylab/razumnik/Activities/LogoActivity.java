package uk.technologylab.razumnik.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uk.technologylab.razumnik.R;
import uk.technologylab.razumnik.Tools.Waiter;

public class LogoActivity extends AppCompatActivity {

    private final int LOGO_TIME_SEC = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        new Waiter(new Waiter.OnWaitCompleteListener() {
            @Override
            public void OnWaitComplete() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }).execute(LOGO_TIME_SEC);
        // loadResources();
    }

}