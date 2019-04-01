package com.sunasterisk.musixmatch.data.source.remote;


import android.net.Uri;

import com.sunasterisk.musixmatch.data.source.TrackDataSource;
import com.sunasterisk.musixmatch.utils.Constants;
import com.sunasterisk.musixmatch.utils.Methods;

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
        Uri.Builder urlBuilder = new Uri.Builder();
        urlBuilder.scheme("http")
                .encodedAuthority(Constants.BASE_URL)
                .appendPath(Constants.VERSION)
                .appendPath(Methods.TRACK_SEARCH)
                .appendQueryParameter("apikey", Constants.API_KEY)
                .appendQueryParameter(Constants.QUERY_TRACK, searchKey);
        new SearchRemoteTrackFromAPIAsyncTask(searchKey, callBack).execute(urlBuilder.build().toString());
    }

    private void getSongsDataFromAPI(int albumID, int limit, TrackDataSource.LoadTrackCallback callBack) {
        Uri.Builder urlBuilder = new Uri.Builder();
        urlBuilder.scheme("https")
                .encodedAuthority(Constants.BASE_URL)
                .appendPath(Constants.VERSION)
                .appendPath(Methods.ALBUM_TRACKS_GET)
                .appendQueryParameter("apikey", Constants.API_KEY)
                .appendQueryParameter(Constants.ALBUM_ID, String.valueOf(albumID));
        new SearchRemoteTrackFromAPIAsyncTask("", callBack).execute(urlBuilder.build().toString());
    }
}
