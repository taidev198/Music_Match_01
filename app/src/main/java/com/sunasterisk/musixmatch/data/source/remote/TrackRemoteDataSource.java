package com.sunasterisk.musixmatch.data.source.remote;


import android.net.Uri;

import com.sunasterisk.musixmatch.BuildConfig;
import com.sunasterisk.musixmatch.data.source.Callback;
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

    @Override
    public void getLyrics(String trackName, String artistName, Callback<String> callback) {
        getTrackLyrics(trackName, artistName, callback);
    }

    private void getSongsBySearchKey(String searchKey, int limit, TrackDataSource.LoadTrackCallback callBack) {
        Uri.Builder urlBuilder = new Uri.Builder();
        urlBuilder.scheme(Constants.SCHEME)
                .encodedAuthority(Constants.BASE_URL)
                .appendPath(Constants.VERSION)
                .appendPath(Methods.TRACK_SEARCH)
                .appendQueryParameter(Constants.API_KEY, BuildConfig.API_KEY)
                .appendQueryParameter(Constants.QUERY_TRACK, searchKey);
        System.out.println(urlBuilder);
        new SearchRemoteTrackFromAPIAsyncTask(searchKey, callBack).execute(urlBuilder.build().toString());
    }

    private void getSongsDataFromAPI(int albumID, int limit, TrackDataSource.LoadTrackCallback callBack) {
        Uri.Builder urlBuilder = new Uri.Builder();
        urlBuilder.scheme(Constants.SCHEME)
                .encodedAuthority(Constants.BASE_URL)
                .appendPath(Constants.VERSION)
                .appendPath(Methods.ALBUM_TRACKS_GET)
                .appendQueryParameter(Constants.API_KEY, BuildConfig.API_KEY)
                .appendQueryParameter(Constants.ALBUM_ID, String.valueOf(albumID));
        new SearchRemoteTrackFromAPIAsyncTask("", callBack).execute(urlBuilder.build().toString());
    }

    private void getTrackLyrics(String trackName, String artistName, Callback<String> callback) {
        Uri.Builder urlBuilder = new Uri.Builder();
        urlBuilder.scheme(Constants.SCHEME)
                .encodedAuthority(Constants.BASE_URL)
                .appendPath(Constants.VERSION)
                .appendPath(Methods.MATCHER_LYRICS_GET)
                .appendQueryParameter(Constants.API_KEY, BuildConfig.API_KEY)
                .appendQueryParameter(Constants.QUERY_TRACK, trackName)
                .appendQueryParameter(Constants.QUERY_ARTIST, artistName);
        new LyricsRemoteAsyncTask(trackName, artistName, callback).execute(urlBuilder.build().toString());
    }
}
