package com.sunasterisk.musixmatch.ui.playing.albums;

import com.sunasterisk.musixmatch.data.model.Album;

import java.util.List;

/**
 * Created by superme198 on 05,April,2019
 * in Music_Match__1.
 */
public interface AlbumsContract {

    interface Presenter {
        void getLocalAlbums();
    }

    interface View {
        void showLocalAlbums(List<Album> albums);

        void showError(Exception e);
    }
}
