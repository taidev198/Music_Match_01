package com.sunasterisk.musixmatch.data.source;

import com.sunasterisk.musixmatch.data.model.Album;

import java.util.List;

public interface AlbumDataSource {
    interface Local {
        void getAlbums(Callback<List<Album>> callback);
    }
}
