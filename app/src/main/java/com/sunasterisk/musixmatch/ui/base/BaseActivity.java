package com.sunasterisk.musixmatch.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initComponents();
        initData();
    }

    @LayoutRes
    protected abstract int getLayoutResource();

    protected abstract void initComponents();

    protected abstract void initData();
}
