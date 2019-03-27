package com.sunasterisk.musixmatch;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by superme198 on 25,March,2019
 * in Music_Match__1.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initComponent();
        initData();
    }

    protected abstract @LayoutRes int getLayoutResource();

    protected abstract void initComponent();

    protected abstract void initData();

}
