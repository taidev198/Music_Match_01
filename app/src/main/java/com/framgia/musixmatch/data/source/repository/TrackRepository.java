package com.framgia.musixmatch.data.source.repository;

import com.framgia.musixmatch.data.model.Track;
import com.framgia.musixmatch.data.source.CallBack;
import com.framgia.musixmatch.data.source.TrackDataSource;
import com.framgia.musixmatch.data.source.local.TrackLocalDataSource;

import java.util.List;

public class TrackRepository implements TrackDataSource.LocalDataSource {
    private TrackLocalDataSource mTrackLocalDataSource;
    private static TrackRepository sInstance;

    private TrackRepository(TrackLocalDataSource trackLocalDataSource) {
        mTrackLocalDataSource = trackLocalDataSource;
    }

    public static TrackRepository getInstance(TrackLocalDataSource trackLocalDataSource) {
        if (sInstance == null){
            sInstance = new TrackRepository(trackLocalDataSource);
        }
        return sInstance;
    }

    @Override
    public void getTracks(CallBack<List<Track>> callBack) {
        mTrackLocalDataSource.getTracks(callBack);
    }
}
