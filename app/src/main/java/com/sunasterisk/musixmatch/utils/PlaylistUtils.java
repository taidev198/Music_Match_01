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
public class PlaylistUtils {

    private static final String VOLUME_NAME = "external";
    private static final String URI_STRING = "content://media";
    private static final int INCREASE_INDEX = 1;
    private static final int BASE_INDEX = 0;
    private static final int ERROR_INDEX = -1;

    public static Uri addSongToPlaylist(Context context, long playlistId, Track track) {
        if (playlistId == ERROR_INDEX) {
            return null;
        }
        ContentResolver contentResolver = context.getContentResolver();
        // Find the greatest PLAY_ORDER in the playlist
        int base = getLargestPos(context, playlistId);
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Audio.Playlists.Members.PLAY_ORDER, base + 1);
        contentValues.put(MediaStore.Audio.Playlists.Members.AUDIO_ID, track.getTrackId());
        Uri uri = contentResolver.insert(MediaStore.Audio.Playlists.Members.getContentUri(VOLUME_NAME, playlistId), contentValues);
        contentResolver.notifyChange(Uri.parse(URI_STRING), null);
        return uri;
    }

    public static int getLargestPos(Context context, long playlistId) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = MediaStore.Audio.Playlists.Members.getContentUri(VOLUME_NAME, playlistId);
        String[] projection = new String[]{MediaStore.Audio.Playlists.Members.PLAY_ORDER};
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
        int base = BASE_INDEX;
        if (cursor.moveToLast()) {
            base = cursor.getInt(BASE_INDEX) + INCREASE_INDEX;
        }
        cursor.close();
        return base;
    }

    public static void removeSongFromPlaylist(Context context, String playlistName, Track track) {
        ContentResolver contentResolver = context.getContentResolver();
        long id = getPlaylistId(context, playlistName);
        contentResolver.delete(
                MediaStore.Audio.Playlists.Members.getContentUri(VOLUME_NAME, id),
                MediaStore.Audio.Playlists.Members._ID + " = ? ", new String[]{String.valueOf(track.getTrackId())});
    }

    public static long createPlaylist(Context context, String name) {
        ContentResolver contentResolver = context.getContentResolver();
        long id = getPlaylistId(context, name);

        if (id == ERROR_INDEX) {
            // We need to create a new playlist.
            ContentValues values = new ContentValues(INCREASE_INDEX);
            values.put(MediaStore.Audio.Playlists.NAME, name);
            Uri uri = contentResolver.insert(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, values);
            id = Long.parseLong(uri.getLastPathSegment());
        } else {
            // We are overwriting an existing playlist. Clear existing songs.
            Uri uri = MediaStore.Audio.Playlists.Members.getContentUri(VOLUME_NAME, id);
            contentResolver.delete(uri, null, null);
        }

        return id;
    }

    public static long getPlaylistId(Context context, String name) {
        ContentResolver contentResolver = context.getContentResolver();
        long id = ERROR_INDEX;
        Cursor cursor = contentResolver.query(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI,
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


    public static void deletePlaylist(Context context, long id) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = ContentUris.withAppendedId(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, id);
        contentResolver.delete(uri, null, null);
    }


    public static void removeTrackFromPlaylist(Context context, long playlistId, Track track) {
        ContentResolver contentResolver = context.getContentResolver();
        contentResolver.delete(
                MediaStore.Audio.Playlists.Members.getContentUri(VOLUME_NAME, playlistId),
                MediaStore.Audio.Playlists.Members._ID + " = ? ", new String[]{String.valueOf(track.getTrackId())});
    }


    public static void renamePlaylist(Context context, long id, String newName) {
        ContentResolver contentResolver = context.getContentResolver();
        long existingId = getPlaylistId(context, newName);
        // We are already called the requested name; nothing to do.
        if (existingId == id) {
            return;
        }
        // There is already a playlist with this name. Kill it.
        if (existingId != ERROR_INDEX) {
            deletePlaylist(context, existingId);
        }

        ContentValues values = new ContentValues(INCREASE_INDEX);
        values.put(MediaStore.Audio.Playlists.NAME, newName);
        contentResolver.update(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, values, "_id=" + id, null);
    }
}
