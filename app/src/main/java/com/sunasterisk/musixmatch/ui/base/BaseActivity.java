package com.sunasterisk.musixmatch.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sunasterisk.musixmatch.R;

public abstract class BaseActivity extends AppCompatActivity {

    private static FragmentManager sFragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sFragmentManager = getSupportFragmentManager();
        setContentView(getLayoutResource());
        initComponents();
        initData();
    }

    public static void addFragmentToActivity(@NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = sFragmentManager.beginTransaction();
        transaction.add(frameId, fragment)
                .commit();
    }

    public static void replaceFragment(@NonNull Fragment fragment) {
        FragmentTransaction transaction = sFragmentManager.beginTransaction();
        transaction.replace(R.id.frame_main, fragment)
                .addToBackStack(null)
                .commit();
    }

    @LayoutRes
    protected abstract int getLayoutResource();

    protected abstract void initComponents();

    protected abstract void initData();
}
