package com.sunasterisk.musixmatch.ui.music.artiststab;

import com.sunasterisk.musixmatch.data.model.Artist;

import java.util.List;

/**
 * Created by superme198 on 08,April,2019
 * in Music_Match__1.
 */
public interface ArtistsContract {

    interface View {
        void showArtists(List<Artist> artists);

        void showError(Exception e);
    }

    interface Presenter {
        void getLocalArtists();
    }
}
