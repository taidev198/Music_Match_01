package com.sunasterisk.musixmatch.data.source.local;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.source.Callback;
import com.sunasterisk.musixmatch.data.source.TrackDataSource;

import java.util.ArrayList;
import java.util.List;

public class TrackLocalDataSource implements TrackDataSource.Local {
    private Context mContext;
    private static TrackLocalDataSource sInstance;

    public static TrackLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new TrackLocalDataSource(context);
        }
        return sInstance;
    }

    private TrackLocalDataSource(Context context) {
        mContext = context;
    }

    @Override
    public void getTracks(Callback<List<Track>> callback) {
        AsyncTask<Void, Void, List<Track>> asyncTask = new AsyncTask<Void, Void, List<Track>>() {
            @Override
            protected List<Track> doInBackground(Void... voids) {
                MediaStore.Audio.Media audioMedia = new MediaStore.Audio.Media();
                ContentResolver resolver = mContext.getContentResolver();
                Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null, null, null, null);
                return getData(cursor, audioMedia);
            }

            @Override
            protected void onPostExecute(List<Track> tracks) {
                super.onPostExecute(tracks);
                if (!tracks.isEmpty()) {
                    callback.getDataSuccess(tracks);
                }
            }
        };
        asyncTask.execute();
    }

    @Override
    public void getTracks(Callback<List<Track>> callback, int id) {
        AsyncTask<Void, Void, List<Track>> asyncTask = new AsyncTask<Void, Void, List<Track>>() {
            @Override
            protected List<Track> doInBackground(Void... voids) {
                MediaStore.Audio.Media audioMedia = new MediaStore.Audio.Media();
                String selection = audioMedia.ALBUM_ID + " = ?";
                ContentResolver resolver = mContext.getContentResolver();
                String[] selectionArgs = new String[]{Integer.toString(id)};
                Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null, selection, selectionArgs, null);
                return getData(cursor, audioMedia);
            }

            @Override
            protected void onPostExecute(List<Track> tracks) {
                super.onPostExecute(tracks);
                if (!tracks.isEmpty()) {
                    callback.getDataSuccess(tracks);
                }
            }
        };
        asyncTask.execute();
    }

    public List<Track> getData(Cursor cursor, MediaStore.Audio.Media audioMedia) {
        List<Track> tracks = new ArrayList<>();
        int indexTrackId = cursor.getColumnIndex(audioMedia._ID);
        int indexTrackName = cursor.getColumnIndex(audioMedia.TITLE);
        int indexAlbumId = cursor.getColumnIndex(audioMedia.ALBUM_ID);
        int indexAlbumName = cursor.getColumnIndex(audioMedia.ALBUM);
        int indexArtistId = cursor.getColumnIndex(audioMedia.ARTIST_ID);
        int indexArtistName = cursor.getColumnIndex(audioMedia.ARTIST);
        int indexData = cursor.getColumnIndex(audioMedia.DATA);
        int indexDuration = cursor.getColumnIndex(audioMedia.DURATION);
        int indexSize = cursor.getColumnIndex(audioMedia.SIZE);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
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
        return tracks;
    }
}
