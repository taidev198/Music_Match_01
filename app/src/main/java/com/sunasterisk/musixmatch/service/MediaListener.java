package com.sunasterisk.musixmatch.service;

import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.ui.playing.tracks.TracksFragment;

import java.util.List;

public interface MediaListener {
    boolean isPlaying();

    void stop();

    void pause();

    void release();

    void seek(long duration);

    int getCurrentDuration();

    void start();

    void play(String uriString);

    void previous();

    void next();

    int getDuration();

    Track getCurrentTrack();

    void setStateLoop(int state);

    int getStateLoop();

    void setTracks(List<Track> tracks);

    void setPosition(int pos);
}
