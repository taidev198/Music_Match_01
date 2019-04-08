package com.sunasterisk.musixmatch.ui.music.albumstab.albumdetails;

import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.ui.music.albumstab.AlbumsContract;

import java.util.List;

/**
 * Created by superme198 on 05,April,2019
 * in Music_Match__1.
 */
public interface AlbumDetailsContract extends AlbumsContract {

    interface View {
        void showTracksFromAlbum(List<Track> tracks);

        void showError(Exception e);
    }

    interface Presenter {
        void getTracksFollowAlbumID(List<Track> trackList, int id);

        void getLocalTracks();
    }
}
