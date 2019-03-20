package com.framgia.musixmatch.data.source;

import com.framgia.musixmatch.data.model.Track;

import java.util.List;

public interface TrackDataSource {
    interface LoadTrackCallback {
        void onSongsLoaded(List<Track> songs);

        void onDataNotAvailable(Exception e);
    }

    interface LocalDataSource {
        void getTracks(CallBack<List<Track>> callback);
    }

    interface RemoteDataSource {
        void getTracksByAlbum(int albumID, int limit, CallBack<List<Track>> callback);
        void searchTrack(String searchKey, int limit, CallBack<List<Track>> callback);
    }
}
