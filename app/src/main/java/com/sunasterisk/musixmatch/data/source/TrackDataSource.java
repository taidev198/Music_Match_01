package com.sunasterisk.musixmatch.data.source;

import com.sunasterisk.musixmatch.data.model.Track;

import java.util.List;

public interface TrackDataSource {
    interface Local {
        void getTracks(Callback<List<Track>> callback);
    }
}
