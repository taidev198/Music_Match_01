package com.sunasterisk.musixmatch.data.source;

import com.sunasterisk.musixmatch.data.model.Playlist;
import com.sunasterisk.musixmatch.data.model.Track;

import java.util.List;

/**
 * Created by superme198 on 14,April,2019
 * in Music_Match__1.
 */
public interface PlaylistDataSource {

    interface Local {

        void getPlaylist(Callback<List<Playlist>> callback);
    }
}
