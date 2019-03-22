package com.sunasterisk.musixmatch.ui.playing;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sunasterisk.musixmatch.ui.playing.tracks.TracksFragment;

public class PlayingViewPagerAdapter extends FragmentStatePagerAdapter {
    public PlayingViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return TracksFragment.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
