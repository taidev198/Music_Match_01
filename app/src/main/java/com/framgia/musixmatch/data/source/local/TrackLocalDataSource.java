package com.framgia.musixmatch.data.source.local;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;

import com.framgia.musixmatch.application.MusixMatchApplication;
import com.framgia.musixmatch.data.model.Track;
import com.framgia.musixmatch.data.source.CallBack;
import com.framgia.musixmatch.data.source.TrackDataSource;

import java.util.ArrayList;
import java.util.List;

public class TrackLocalDataSource implements TrackDataSource.LocalDataSource {
    @Override
    public void getTracks(CallBack<List<Track>> callBack) {
        List<Track> tracks = new ArrayList<>();
        ContentResolver resolver = MusixMatchApplication.getInstance().getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,null,null,null);
        cursor.moveToFirst();
        MediaStore.Audio.Media audioMedia = new MediaStore.Audio.Media();
        int indexId = cursor.getColumnIndex(audioMedia._ID);
        int indexData = cursor.getColumnIndex(audioMedia.DATA);
        int indexSize = cursor.getColumnIndex(audioMedia.SIZE);
        int indexDuration = cursor.getColumnIndex(audioMedia.DURATION);
        int indexTitle = cursor.getColumnIndex(audioMedia.TITLE);
        int indexArtistId = cursor.getColumnIndex(audioMedia.ARTIST_ID);
        int indexArtist = cursor.getColumnIndex(audioMedia.ARTIST);
        int indexAlbumId = cursor.getColumnIndex(audioMedia.ALBUM_ID);
        int indexAlbum = cursor.getColumnIndex(audioMedia.ALBUM);
        while (cursor.isAfterLast()==false){
            int id = cursor.getInt(indexId);
            String data = cursor.getString(indexData);
            long size = cursor.getLong(indexSize);
            long duration = cursor.getLong(indexDuration);
            String title = cursor.getString(indexTitle);
            int artistId = cursor.getInt(indexArtistId);
            String artist = cursor.getString(indexArtist);
            int albumId = cursor.getInt(indexAlbumId);
            String album = cursor.getString(indexAlbum);
            Track track = new Track(id,data,size,duration,title,artistId,artist,albumId,album, false);
            tracks.add(track);
            cursor.moveToNext();
        }
        callBack.getDataSuccess(tracks);
    }
}
