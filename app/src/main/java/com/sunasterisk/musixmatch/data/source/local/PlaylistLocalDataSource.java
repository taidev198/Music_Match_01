package com.sunasterisk.musixmatch.data.source.local;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.BaseColumns;
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
    private Context mContext;
    private ContentResolver mContentResolver;
    private Cursor mCursor;
    private static final String VOLUME_NAME = "external";
    private static final int ERORR_INDEX = -1;
    private static final int BASE_INDEX = 0;
    private static final int INCREASE_INDEX = 1;

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
    public void getPlaylistId(Callback<List<Playlist>> callback) {
        AsyncTask<Void, Void, List<Playlist>> asyncTask = new AsyncTask<Void, Void, List<Playlist>>() {
            @Override
            protected List<Playlist> doInBackground(Void... voids) {
                return getData();
            }

            @Override
            protected void onPostExecute(List<Playlist> playlists) {
                super.onPostExecute(playlists);
                callback.getDataSuccess(playlists);
            }
        };
        asyncTask.execute();
    }

    @Override
    public long createPlaylist(String name) {
        long id = getPlaylistId(name);

        if (id == ERORR_INDEX) {
            // We need to create a new playlist.
            ContentValues values = new ContentValues(INCREASE_INDEX);
            values.put(MediaStore.Audio.Playlists.NAME, name);
            Uri uri = mContentResolver.insert(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, values);
            id = Long.parseLong(uri.getLastPathSegment());
        } else {
            // We are overwriting an existing playlist. Clear existing songs.
            Uri uri = MediaStore.Audio.Playlists.Members.getContentUri(VOLUME_NAME, id);
            mContentResolver.delete(uri, null, null);
        }

        return id;
    }

    @Override
    public long getPlaylistId(String name) {
        long id = ERORR_INDEX;
        mCursor = mContentResolver.query(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Playlists._ID},
                MediaStore.Audio.Playlists.NAME + "=?",
                new String[]{name}, null);

        if (mCursor != null) {
            if (mCursor.moveToNext())
                id = mCursor.getLong(BASE_INDEX);
            mCursor.close();
        }

        return id;
    }

    @Override
    public void deletePlaylist(long id) {
        Uri uri = ContentUris.withAppendedId(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, id);
        mContentResolver.delete(uri, null, null);
    }

    @Override
    public void removeTrackFromPlaylist(long playlistId, Track track) {
        mContentResolver.delete(
                MediaStore.Audio.Playlists.Members.getContentUri(VOLUME_NAME, playlistId),
                MediaStore.Audio.Playlists.Members._ID + " = ? ", new String[]{String.valueOf(track.getTrackId())});
    }

    @Override
    public void renamePlaylist(long id, String newName) {
        long existingId = getPlaylistId(newName);
        // We are already called the requested name; nothing to do.
        if (existingId == id)
            return;
        // There is already a playlist with this name. Kill it.
        if (existingId != ERORR_INDEX)
            deletePlaylist(existingId);

        ContentValues values = new ContentValues(INCREASE_INDEX);
        values.put(MediaStore.Audio.Playlists.NAME, newName);
        mContentResolver.update(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, values, "_id=" + id, null);
    }

    public int countPlaylist(final long playlistId) {
        try {
            mCursor = mContentResolver.query(
                    MediaStore.Audio.Playlists.Members.getContentUri(VOLUME_NAME, playlistId),
                    new String[]{
                            MediaStore.Audio.Playlists.Members.AUDIO_ID,
                    }, null, null,
                    MediaStore.Audio.Playlists.Members.DEFAULT_SORT_ORDER);

            if (mCursor != null) {
                return mCursor.getCount();
            }
        } finally {
            if (mCursor != null) {
                mCursor.close();
                mCursor = null;
            }
        }

        return BASE_INDEX;
    }

    public List<Track> getTracksFromPlaylist(long playlistId) {
        List<Track> tracks = new ArrayList<>();
        try {
            mCursor = mContentResolver.query(
                    MediaStore.Audio.Playlists.Members.getContentUri(VOLUME_NAME, playlistId),
                    null, null, null, null);
            int indexTrackId = mCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int indexTrackName = mCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int indexAlbumId = mCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            int indexAlbumName = mCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int indexArtistId = mCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID);
            int indexArtistName = mCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int indexData = mCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int indexDuration = mCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int indexSize = mCursor.getColumnIndex(MediaStore.Audio.Media.SIZE);
            mCursor.moveToFirst();
            while (mCursor.isAfterLast() == false) {
                int albumId = mCursor.getInt(indexAlbumId);
                int trackId = mCursor.getInt(indexTrackId);
                String trackName = mCursor.getString(indexTrackName);
                int artistId = mCursor.getInt(indexArtistId);
                String artistName = mCursor.getString(indexArtistName);
                String albumName = mCursor.getString(indexAlbumName);
                String data = mCursor.getString(indexData);
                long size = mCursor.getLong(indexSize);
                long duration = mCursor.getLong(indexDuration);
                Track track = new Track.Builder()
                        .setTrackId(trackId)
                        .setTrackName(trackName)
                        .setAlbumId(albumId)
                        .setAlbumName(albumName)
                        .setArtistId(artistId)
                        .setArtistName(artistName)
                        .setData(data)
                        .setSize(size)
                        .setDuration(duration)
                        .build();
                tracks.add(track);
                System.out.println(trackId);
                mCursor.moveToNext();
            }
        } finally {
            if (mCursor != null) {
                mCursor.close();
                mCursor = null;
            }
        }

        return tracks;
    }

    public List<Playlist> getData() {
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
        while (playListCursor.isAfterLast() == false) {
            int playlistId = playListCursor.getInt(indexPlaylistId);
            String playlistName = playListCursor.getString(indexPlaylistName);
            int numSongs = countPlaylist(playlistId);
            Playlist playList = new Playlist.Builder()
                    .setNumSongs(numSongs)
                    .setPlaylistId(playlistId)
                    .setPlaylistName(playlistName)
                    .build();
            playlists.add(playList);
            playListCursor.moveToNext();
        }
        return playlists;
    }

}
