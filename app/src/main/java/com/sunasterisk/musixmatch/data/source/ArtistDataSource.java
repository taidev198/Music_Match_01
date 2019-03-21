package com.sunasterisk.musixmatch.data.source;

import com.sunasterisk.musixmatch.data.model.Artist;

import java.util.List;

public interface ArtistDataSource {
    interface Local {
        void getArtists(Callback<List<Artist>> callback);
    }
}
