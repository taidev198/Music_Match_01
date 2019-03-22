package com.sunasterisk.musixmatch.ui.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sunasterisk.musixmatch.BaseActivity;
import com.sunasterisk.musixmatch.R;

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @Override
    protected void initComponent() {

    }

    @Override
    protected void initData() {

    }
}
