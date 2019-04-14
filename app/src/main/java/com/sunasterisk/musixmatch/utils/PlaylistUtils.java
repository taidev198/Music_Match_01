package com.sunasterisk.musixmatch.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.sunasterisk.musixmatch.data.model.Track;

/**
 * Created by superme198 on 14,April,2019
 * in Music_Match__1.
 */
public class PlaylistUtils {

    private Context mContext;
    private static ContentResolver mContentResolver;
    private static Cursor mCursor;
    private static PlaylistUtils sInstance;
    private static final String VOLUME_NAME = "external";
    private static final String URI_STRING = "content://media";
    private static final int INCREASE_INDEX = 1;
    private static final int BASE_INDEX = 0;
    private static final int ERROR_INDEX = -1;

    private PlaylistUtils(Context context) {
        mContext = context;
        mContentResolver = mContext.getContentResolver();
    }

    public static PlaylistUtils getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PlaylistUtils(context);
        }
        return sInstance;
    }

    public static Uri addSongToPlaylist(long playlistId, Track track) {
        if (playlistId == ERROR_INDEX)
            return null;
        // Find the greatest PLAY_ORDER in the playlist
        int base = getLargestPos(playlistId);
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Audio.Playlists.Members.PLAY_ORDER, base + 1);
        contentValues.put(MediaStore.Audio.Playlists.Members.AUDIO_ID, track.getTrackId());
        Uri uri = mContentResolver.insert(MediaStore.Audio.Playlists.Members.getContentUri(VOLUME_NAME, playlistId), contentValues);
        mContentResolver.notifyChange(Uri.parse(URI_STRING), null);
        return uri;
    }

    public static int getLargestPos(long playlistId) {

        Uri uri = MediaStore.Audio.Playlists.Members.getContentUri(VOLUME_NAME, playlistId);
        String[] projection = new String[]{MediaStore.Audio.Playlists.Members.PLAY_ORDER};
        mCursor = mContentResolver.query(uri, projection, null, null, null);
        int base = BASE_INDEX;
        if (mCursor.moveToLast())
            base = mCursor.getInt(BASE_INDEX) + INCREASE_INDEX;
        mCursor.close();
        return base;
    }
}
