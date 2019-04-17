package com.sunasterisk.musixmatch.ui.main.home;

import com.sunasterisk.musixmatch.data.model.Track;

import java.util.List;

/**
 * Created by superme198 on 17,April,2019
 * in Music_Match__1.
 */
public interface HomeContract {

    interface View {
        void showRecentlyPlayed(List<Track> tracks);

        void showFavourite(List<Track> tracks);

        void showError(Exception e);
    }

    interface Presenter {
        void getRecentlyPlayedTracks();

        void getFavouriteTracks();
    }
}
