package com.sunasterisk.musixmatch.ui.music.trackstab;

import com.sunasterisk.musixmatch.data.repository.TrackRepository;
import com.sunasterisk.musixmatch.ui.playing.tracks.TracksContract;
import com.sunasterisk.musixmatch.ui.playing.tracks.TracksPresenter;

/**
 * Created by superme198 on 01,April,2019
 * in Music_Match__1.
 */
public class TracksTabPresenter extends TracksPresenter {

    public TracksTabPresenter(TrackRepository repository, TracksContract.View view) {
        super(repository, view);
    }
}
