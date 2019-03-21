package com.sunasterisk.musixmatch.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.ui.main.MainActivity;

/**
 * Created by superme198 on 15,March,2019
 * in Music_Match_1.
 */
public class SplashActivity extends AppCompatActivity {
    public static final int TIME_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        goToHomeScreen();
    }

    private void goToHomeScreen() {
        //start to main
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }, TIME_DELAY);
    }
}
