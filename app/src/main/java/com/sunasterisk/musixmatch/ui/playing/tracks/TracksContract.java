package com.sunasterisk.musixmatch.ui.playing.tracks;

import com.sunasterisk.musixmatch.data.model.Track;

import java.util.List;

public interface TracksContract {
    interface Presenter {
        void getLocalTracks();
    }

    interface View {
        void showLocalTracks(List<Track> tracks);

        void showError(Exception e);
    }
}
