package com.sunasterisk.musixmatch.data.repository;

import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.source.Callback;
import com.sunasterisk.musixmatch.data.source.TrackDataSource;

import java.util.List;

public class TrackRepository implements TrackDataSource.Local {
    private static TrackRepository sInstance;
    private TrackDataSource.Local mLocal;

    private TrackRepository(TrackDataSource.Local Local) {
        mLocal = Local;
    }

    public static TrackRepository getInstance(TrackDataSource.Local Local) {
        if (sInstance == null) {
            sInstance = new TrackRepository(Local);
        }
        return sInstance;
    }

    @Override
    public void getTracks(Callback<List<Track>> callback) {
        mLocal.getTracks(callback);
    }
}
