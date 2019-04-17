package com.sunasterisk.musixmatch.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
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
public class PlaylistHelper {

    private static final String CONTENT_URI_NAME = "external";
    private static final String CONTENT_URI_MEDIA = "content://media";
    private static final int INCREMENT_NUMBER = 1;
    private static final int BASE_INDEX = 0;
    private static final int ERROR_INDEX = -1;
    private static final int SIZE = 1;
    private static PlaylistHelper sInstance;
    private Context mContext;
    private ContentResolver mContentResolver;

    private PlaylistHelper(Context context) {
        mContext = context;
        mContentResolver = mContext.getContentResolver();
    }

    public static PlaylistHelper getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PlaylistHelper(context);
        }
        return sInstance;
    }

    public Uri addSongToPlaylist(long playlistId, Track track) {
        if (playlistId < 0) {
            return null;
        }
        // Find the greatest PLAY_ORDER in the playlist
        int base = getLargestPos(playlistId);
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Audio.Playlists.Members.PLAY_ORDER, base + 1);
        contentValues.put(MediaStore.Audio.Playlists.Members.AUDIO_ID, track.getTrackId());
        Uri uri = mContentResolver.insert(MediaStore.Audio.Playlists.Members.getContentUri(CONTENT_URI_NAME, playlistId), contentValues);
        mContentResolver.notifyChange(Uri.parse(CONTENT_URI_MEDIA), null);
        return uri;
    }

    public int getLargestPos(long playlistId) {
        Uri uri = MediaStore.Audio.Playlists.Members.getContentUri(CONTENT_URI_NAME, playlistId);
        String[] projection = new String[]{MediaStore.Audio.Playlists.Members.PLAY_ORDER};
        Cursor cursor = mContentResolver.query(uri, projection, null, null, null);
        int base = BASE_INDEX;
        if (cursor.moveToLast()) {
            base = cursor.getInt(BASE_INDEX) + INCREMENT_NUMBER;
        }
        cursor.close();
        return base;
    }

    public long createPlaylist(String name) {
        long id = getPlaylistId(name);

        if (id == ERROR_INDEX) {
            // We need to create a new playlist.
            ContentValues values = new ContentValues(INCREMENT_NUMBER);
            values.put(MediaStore.Audio.Playlists.NAME, name);
            Uri uri = mContentResolver.insert(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, values);
            id = Long.parseLong(uri.getLastPathSegment());
        } else {
            // We are overwriting an existing playlist. Clear existing songs.
            Uri uri = MediaStore.Audio.Playlists.Members.getContentUri(CONTENT_URI_NAME, id);
            mContentResolver.delete(uri, null, null);
        }

        return id;
    }

    public long getPlaylistId(String name) {
        long id = ERROR_INDEX;
        Cursor cursor = mContentResolver.query(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Playlists._ID},
                MediaStore.Audio.Playlists.NAME + "=?",
                new String[]{name}, null);

        if (cursor != null) {
            if (cursor.moveToNext()) {
                id = cursor.getLong(BASE_INDEX);
            }
            cursor.close();
        }

        return id;
    }


    public void deletePlaylist(long id) {
        Uri uri = ContentUris.withAppendedId(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, id);
        mContentResolver.delete(uri, null, null);
    }

    public void removeTrackFromPlaylist(long playlistId, Track track) {
        mContentResolver.delete(
                MediaStore.Audio.Playlists.Members.getContentUri(CONTENT_URI_NAME, playlistId),
                MediaStore.Audio.Playlists.Members._ID + " = ? ", new String[]{String.valueOf(track.getTrackId())});
    }


    public void renamePlaylist(long id, String newName) {
        long existingId = getPlaylistId(newName);
        // We are already called the requested name; nothing to do.
        if (existingId == id) {
            return;
        }
        // There is already a playlist with this name. Kill it.
        if (existingId != ERROR_INDEX) {
            deletePlaylist(existingId);
        }

        ContentValues values = new ContentValues(SIZE);
        values.put(MediaStore.Audio.Playlists.NAME, newName);
        mContentResolver.update(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, values, "_id=" + id, null);
    }
}
