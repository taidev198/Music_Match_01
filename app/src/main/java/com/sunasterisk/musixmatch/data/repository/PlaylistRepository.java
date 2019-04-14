package com.sunasterisk.musixmatch.data.repository;

import com.sunasterisk.musixmatch.data.model.Playlist;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.source.Callback;
import com.sunasterisk.musixmatch.data.source.PlaylistDataSource;

import java.util.List;

/**
 * Created by superme198 on 14,April,2019
 * in Music_Match__1.
 */
public class PlaylistRepository implements PlaylistDataSource.Local {

    private static PlaylistRepository sInstance;
    private PlaylistDataSource.Local mLocal;

    private PlaylistRepository(PlaylistDataSource.Local local) {
        mLocal = local;
    }

    public static PlaylistRepository getsInstance(PlaylistDataSource.Local local) {
        if (sInstance == null) {
            sInstance = new PlaylistRepository(local);
        }
        return sInstance;
    }

    @Override
    public void getPlaylistId(Callback<List<Playlist>> callback) {
        mLocal.getPlaylistId(callback);
    }

    @Override
    public long getPlaylistId(String name) {
        return mLocal.getPlaylistId(name);
    }

    @Override
    public long createPlaylist(String name) {
        return mLocal.createPlaylist(name);
    }

    @Override
    public void deletePlaylist(long id) {
        mLocal.deletePlaylist(id);
    }

    @Override
    public void removeTrackFromPlaylist(long playlistId, Track track) {
        mLocal.removeTrackFromPlaylist(playlistId, track);
    }

    @Override
    public void renamePlaylist(long id, String newName) {
        mLocal.renamePlaylist(id, newName);
    }
}
