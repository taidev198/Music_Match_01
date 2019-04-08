package com.sunasterisk.musixmatch.data.source;

import com.sunasterisk.musixmatch.data.model.Track;

import java.util.List;

public interface TrackDataSource {
    interface Local {
        void getTracks(Callback<List<Track>> callback);
        void getTracks(Callback<List<Track>> callback, int id);
    }

    interface RemoteDataSource {
        void getTracksByAlbum(int albumID, int limit, LoadTrackCallback callback);
        void searchTrack(String searchKey, int limit, LoadTrackCallback callback);
    }

    interface LoadTrackCallback {
        void onSongsLoaded(List<Track> tracks);

        void onDataNotAvailable(Exception e);
    }
}
