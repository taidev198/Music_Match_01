package com.sunasterisk.musixmatch.ui.music;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.ui.base.BaseFragment;

public class MusicFragment extends BaseFragment {

    private MusicAdapter mAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    public static MusicFragment newInstance() {
        return new MusicFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_music;
    }

    @Override
    protected void initComponents(View view) {
        mViewPager = view.findViewById(R.id.view_pager_music);
        mTabLayout = view.findViewById(R.id.tab_layout_music);
    }

    @Override
    protected void initData() {
        mAdapter = new MusicAdapter(getContext(), getFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
