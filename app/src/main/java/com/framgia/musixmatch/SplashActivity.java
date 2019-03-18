package com.framgia.musixmatch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by superme198 on 15,March,2019
 * in Music_Match_1.
 */
public class SplashActivity extends AppCompatActivity {
    public static final int TIME_DELAY = 2000;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //start to main
       mHandler =  new Handler();
       mHandler.postDelayed(()->{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        },TIME_DELAY);
    }
}
