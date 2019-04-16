package com.sunasterisk.musixmatch.data.source.local;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.sunasterisk.musixmatch.data.model.Playlist;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.source.Callback;
import com.sunasterisk.musixmatch.data.source.PlaylistDataSource;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by superme198 on 14,April,2019
 * in Music_Match__1.
 */
public class PlaylistLocalDataSource implements PlaylistDataSource.Local {

    private static PlaylistLocalDataSource sInstance;
    private static final String VOLUME_NAME = "external";
    private static final int ERROR_INDEX = -1;
    private static final int BASE_INDEX = 0;
    private static final int INCREASE_INDEX = 1;
    private Context mContext;
    private ContentResolver mContentResolver;

    private PlaylistLocalDataSource(Context context) {
        mContext = context;
        mContentResolver = mContext.getContentResolver();

    }

    public static PlaylistLocalDataSource getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PlaylistLocalDataSource(context);
        }
        return sInstance;
    }

    @Override
    public void getPlaylist(Callback<List<Playlist>> callback) {
        AsyncTask<Void, Void, List<Playlist>> asyncTask = new AsyncTask<Void, Void, List<Playlist>>() {
            @Override
            protected List<Playlist> doInBackground(Void... voids) {
                return getLocalPlaylist();
            }

            @Override
            protected void onPostExecute(List<Playlist> playlists) {
                super.onPostExecute(playlists);
                callback.getDataSuccess(playlists);
            }
        };
        asyncTask.execute();
    }

    private int countPlaylist(final long playlistId) {
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(
                    MediaStore.Audio.Playlists.Members.getContentUri(VOLUME_NAME, playlistId),
                    new String[]{
                            MediaStore.Audio.Playlists.Members.AUDIO_ID,
                    }, null, null,
                    MediaStore.Audio.Playlists.Members.DEFAULT_SORT_ORDER);

            if (cursor != null) {
                return cursor.getCount();
            }
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }

        return BASE_INDEX;
    }

    private List<Playlist> getLocalPlaylist() {
        ArrayList<Playlist> playlists = new ArrayList<>();
        Uri tempPlaylistURI = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
        Cursor playListCursor = mContentResolver.query(tempPlaylistURI,
                null, null, null, null);
        if (playListCursor == null) {
            return playlists;//don't have list on phone
        }
        playListCursor.moveToFirst();
        int indexPlaylistId = playListCursor.getColumnIndex(MediaStore.Audio.Playlists._ID);
        int indexPlaylistName = playListCursor.getColumnIndex(MediaStore.Audio.Playlists.NAME);
        while (!playListCursor.isAfterLast()) {
            int playlistId = playListCursor.getInt(indexPlaylistId);
            String playlistName = playListCursor.getString(indexPlaylistName);
            int numSongs = countPlaylist(playlistId);
            Playlist playList = new Playlist(playlistId, playlistName, numSongs);
            playlists.add(playList);
            playListCursor.moveToNext();
        }
        return playlists;
    }

}
