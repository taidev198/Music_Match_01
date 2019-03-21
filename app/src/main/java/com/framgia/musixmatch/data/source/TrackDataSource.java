package com.framgia.musixmatch.data.source;

import com.framgia.musixmatch.data.model.Track;

import java.util.List;

public interface TrackDataSource {
    interface Local {
        void getTracks(Callback<List<Track>> callback);
    }
}
