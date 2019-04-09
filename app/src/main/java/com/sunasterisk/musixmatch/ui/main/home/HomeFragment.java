package com.sunasterisk.musixmatch.ui.main.home;

import android.view.View;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.ui.base.BaseFragment;

public class HomeFragment extends BaseFragment {
    public static HomeFragment newInstance(){
        return new HomeFragment();
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initComponents(View view) {

    }

    @Override
    protected void initData() {

    }
}
