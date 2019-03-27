package com.sunasterisk.musixmatch.ui.playing.thumbnail;

import com.sunasterisk.musixmatch.data.model.Album;

import java.util.List;

public interface ThumbnailContract {
    interface Presenter {
        List<Album> getAlbums();
    }

    interface View {
        void showError(Exception e);
    }
}