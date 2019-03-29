package com.sunasterisk.musixmatch.ui.playing.thumbnail;

import com.sunasterisk.musixmatch.data.model.Album;

import java.util.List;

public interface ThumbnailContract {
    interface Presenter {
        void getAlbums();
    }

    interface View {
        void showAlbums(List<Album> albums);

        void showError(Exception e);
    }
}