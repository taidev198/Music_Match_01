package com.sunasterisk.musixmatch.ui.music;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sunasterisk.musixmatch.ui.music.albumstab.AlbumsTabFragment;
import com.sunasterisk.musixmatch.ui.music.artiststab.ArtistsTabFragment;
import com.sunasterisk.musixmatch.ui.music.trackstab.TracksTabFragment;

/**
 * Created by superme198 on 10,April,2019
 * in Music_Match__1.
 */
public class MusicAdapter extends FragmentPagerAdapter {

    private static final int PAGE_SIZE = 4;

    public MusicAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case MusicPagePosition.ALBUM:
                return AlbumsTabFragment.newInstance();
            case MusicPagePosition.ARTIST:
                return ArtistsTabFragment.newInstance();
            case MusicPagePosition.TRACK:
                return TracksTabFragment.newInstance();
            case MusicPagePosition.PLAYLIST:
                return TracksTabFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return PAGE_SIZE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case MusicPagePosition.ALBUM:
                return MusicPageTitle.ALBUM;
            case MusicPagePosition.ARTIST:
                return MusicPageTitle.ARTIST;
            case MusicPagePosition.TRACK:
                return MusicPageTitle.TRACK;
            case MusicPagePosition.PLAYLIST:
                return MusicPageTitle.PLAYLIST;
        }
        return "";
    }
}
