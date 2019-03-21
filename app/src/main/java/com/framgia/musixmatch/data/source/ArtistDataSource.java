package com.framgia.musixmatch.data.source;

import com.framgia.musixmatch.data.model.Artist;

import java.util.List;

public interface ArtistDataSource {
    interface Local {
        void getArtists(Callback<List<Artist>> callback);
    }
}
