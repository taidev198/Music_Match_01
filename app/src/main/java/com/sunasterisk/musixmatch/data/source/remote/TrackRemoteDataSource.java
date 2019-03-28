package com.sunasterisk.musixmatch.data.source.remote;

import android.util.Log;

import com.sunasterisk.musixmatch.data.source.TrackDataSource;
import com.sunasterisk.musixmatch.utils.Constants;
import com.sunasterisk.musixmatch.utils.Methods;

import static android.content.ContentValues.TAG;

/**
 * Created by superme198 on 20,March,2019
 * in Music_Match_1.
 */
public class TrackRemoteDataSource implements TrackDataSource.RemoteDataSource {

    private static TrackRemoteDataSource sInstance;

    private TrackRemoteDataSource() {
    }

    public static TrackRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new TrackRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getTracksByAlbum(int albumID, int limit, TrackDataSource.LoadTrackCallback callBack) {
        getSongsDataFromAPI(albumID, limit, callBack);
    }

    @Override
    public void searchTrack(String searchKey, int limit, TrackDataSource.LoadTrackCallback callBack) {
        getSongsBySearchKey(searchKey, limit, callBack);
    }

    private void getSongsBySearchKey(String searchKey, int limit, TrackDataSource.LoadTrackCallback callBack) {
        String url = Constants.BASE_URL
                                    +Constants.VERSION
                                    + Constants.URL_DELIM
                                    +Methods.TRACK_SEARCH
                                    +"?"
                                    +Constants.API_KEY
                                    +"&"
                                    + Constants.QUERY_TRACK
                                    +"="
                +searchKey;
        new SearchRemoteTrackFromAPIAsyncTask(callBack, searchKey).execute(url);
    }

    private void getSongsDataFromAPI(int albumID, int limit, TrackDataSource.LoadTrackCallback callBack) {
        String url = Constants.BASE_URL
                                    +Constants.VERSION
                                    + Constants.URL_DELIM
                                    + Methods.ALBUM_TRACKS_GET
                                    +"?"
                                    +Constants.API_KEY
                                    +"&"
                                    + Constants.ALBUM_ID
                                    +"="
                                    +albumID;
        new SearchRemoteTrackFromAPIAsyncTask(callBack, "").execute(url);
    }
}
