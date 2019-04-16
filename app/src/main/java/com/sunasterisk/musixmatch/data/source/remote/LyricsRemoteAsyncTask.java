package com.sunasterisk.musixmatch.data.source.remote;

import android.os.AsyncTask;

import com.sunasterisk.musixmatch.data.source.Callback;
import com.sunasterisk.musixmatch.utils.TrackLoaderUtils;

import org.json.JSONException;

import java.io.IOException;

public class LyricsRemoteAsyncTask extends AsyncTask<String, Void, String> {
    private String mTrackName;
    private String mArtistName;
    private Exception mException;
    private Callback<String> mCallback;

    public LyricsRemoteAsyncTask(String trackName, String artistName, Callback<String> callback) {
        mTrackName = trackName;
        mArtistName = artistName;
        mCallback = callback;
    }

    @Override
    protected String doInBackground(String... strings) {
        String lyrics = null;
        try {
            String jsonString = TrackLoaderUtils.getJSONFromAPI(strings[0]);
            lyrics = TrackLoaderUtils.getLyricsFromJSON(jsonString);
        } catch (IOException e) {
            mException = e;
        } catch (JSONException e) {
            mException = e;
        }
        return lyrics;
    }

    @Override
    protected void onPostExecute(String lyrics) {
        super.onPostExecute(lyrics);
        if (mCallback == null) {
            return;
        }
        if (mException == null) {
            mCallback.getDataSuccess(lyrics);
        } else {
            mCallback.getDataFailure(mException);
        }
    }
}
