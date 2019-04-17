package com.sunasterisk.musixmatch.data.source.local;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.BaseColumns;
import android.provider.MediaStore;

import com.sunasterisk.musixmatch.R;
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

    private static final String CONTENT_URI_NAME = "external";
    private static final String CONTENT_URI_MEDIA = "content://media";
    private static final long ERROR_INDEX = -1;
    private static final int FIRST_ELEMENT_INDEX = 0;
    private static PlaylistLocalDataSource sInstance;
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

    @Override
    public void getTrackFromRecentlyPlayed(Callback<List<Track>> callback) {

        AsyncTask<Void, Void, List<Track>> asyncTask = new AsyncTask<Void, Void, List<Track>>() {
            @Override
            protected List<Track> doInBackground(Void... voids) {
                long id = getPlaylistId(mContext.getString(R.string.text_recently_played));
                return getTracksFromPlaylist(id);
            }

            @Override
            protected void onPostExecute(List<Track> tracks) {
                super.onPostExecute(tracks);
                callback.getDataSuccess(tracks);
            }
        };
        asyncTask.execute();
    }

    @Override
    public void getTrackFromFavorite(Callback<List<Track>> callback) {

        AsyncTask<Void, Void, List<Track>> asyncTask = new AsyncTask<Void, Void, List<Track>>() {
            @Override
            protected List<Track> doInBackground(Void... voids) {
                long id = getPlaylistId(mContext.getString(R.string.text_your_favorite_songs));
                return getTracksFromPlaylist(id);
            }

            @Override
            protected void onPostExecute(List<Track> tracks) {
                super.onPostExecute(tracks);
                callback.getDataSuccess(tracks);
            }
        };
        asyncTask.execute();

    }

    private int countPlaylist(final long playlistId) {
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(
                    MediaStore.Audio.Playlists.Members.getContentUri(CONTENT_URI_MEDIA, playlistId),
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

        return 0;
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

    private long getPlaylistId(String name) {
        long id = ERROR_INDEX;
        Cursor c = mContentResolver.query(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Audio.Playlists._ID},
                MediaStore.Audio.Playlists.NAME + " = ?", new String[]{name}, null);
        if (c != null & c.moveToFirst()) {
            id = c.getLong(c.getColumnIndex(MediaStore.Audio.Playlists._ID));
            c.close();
        }

        return id;
    }

    private List<Track> getTracksFromPlaylist(long id) {
        List<Track> tracks = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(
                    MediaStore.Audio.Playlists.Members.getContentUri(CONTENT_URI_NAME, id),
                    null, null, null, null);
            int indexTrackId = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int indexTrackName = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int indexAlbumId = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            int indexAlbumName = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int indexArtistId = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID);
            int indexArtistName = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int indexData = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int indexDuration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int indexSize = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int albumId = cursor.getInt(indexAlbumId);
                int trackId = cursor.getInt(indexTrackId);
                String trackName = cursor.getString(indexTrackName);
                int artistId = cursor.getInt(indexArtistId);
                String artistName = cursor.getString(indexArtistName);
                String albumName = cursor.getString(indexAlbumName);
                String data = cursor.getString(indexData);
                long size = cursor.getLong(indexSize);
                long duration = cursor.getLong(indexDuration);
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
                cursor.moveToNext();
            }
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }

        return tracks;
    }
}
