package com.sunasterisk.musixmatch.ui.music;

import android.view.View;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.ui.base.BaseFragment;

public class FragmentMusic extends BaseFragment {
    public static FragmentMusic newInstance() {
        return new FragmentMusic();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_music;
    }

    @Override
    protected void initComponents(View view) {

    }

    @Override
    protected void initData() {

    }
}
