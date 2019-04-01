package com.sunasterisk.musixmatch.data.source.remote;

import android.os.AsyncTask;

import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.source.TrackDataSource;
import com.sunasterisk.musixmatch.utils.TrackLoaderUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by superme198 on 21,March,2019
 * in Music_Match_1.
 */
public class SearchRemoteTrackFromAPIAsyncTask extends AsyncTask<String, Void, List<Track>> {
    private Exception mException;
    private String mSearchKey;
    private TrackDataSource.LoadTrackCallback mCallback;
    public SearchRemoteTrackFromAPIAsyncTask(String searchKey, TrackDataSource.LoadTrackCallback callback ){
        mCallback = callback;
        mSearchKey = searchKey;
    }

    @Override
    protected List<Track> doInBackground(String... strings) {
        List<Track> tracks = new ArrayList<>();
        try {
            String jsonString = TrackLoaderUtils.getJSONFromAPI(strings[0]);
            tracks = TrackLoaderUtils.getTracksFromJSON(jsonString);
        } catch (IOException e) {
            mException = e;
        } catch (JSONException e) {
            mException = e;
        }
        return tracks;
    }

    @Override
    protected void onPostExecute(List<Track> tracks) {
        super.onPostExecute(tracks);
        if (mCallback == null){
            return;
        }
        if (mException == null) {
            mCallback.onSongsLoaded(tracks);
        } else {
            mCallback.onDataNotAvailable(mException);
        }
    }
}
