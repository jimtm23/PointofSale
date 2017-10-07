package com.martinez.dyim.pointofsale;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SplashScreenActivity extends AppCompatActivity {

    private  static final long SPLASH_TIMEOUT = 2000;
    private boolean gone = false;
    private Button goButton;
    private static FirebaseDatabase database;
    private void initiateDataBase() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if(database == null) {
            database = FirebaseDatabase.getInstance();
            database.getInstance().setPersistenceEnabled(true);
        }
        goButton = (Button) findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!gone) go();
            }
        }, SPLASH_TIMEOUT);
    }

    private void go() {
        gone = true;
        Intent newActivity = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(newActivity);
        SplashScreenActivity.this.finish();
    }
}
