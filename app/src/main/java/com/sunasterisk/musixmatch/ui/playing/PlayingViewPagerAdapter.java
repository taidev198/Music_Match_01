package com.sunasterisk.musixmatch.ui.playing;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sunasterisk.musixmatch.ui.playing.thumbnail.ThumbnailFragment;
import com.sunasterisk.musixmatch.ui.playing.tracks.TracksFragment;

public class PlayingViewPagerAdapter extends FragmentPagerAdapter {
    public static final int TRACKS = 0;
    public static final int THUMB_NAIL = 1;
    private static final int[] TABS = {TRACKS, THUMB_NAIL};

    public PlayingViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case TRACKS:
                return new TracksFragment();
            case THUMB_NAIL:
                return new ThumbnailFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TABS.length;
    }
}
