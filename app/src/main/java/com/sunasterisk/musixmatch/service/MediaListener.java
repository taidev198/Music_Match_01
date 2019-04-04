package com.sunasterisk.musixmatch.service;

import com.sunasterisk.musixmatch.data.model.Track;

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
    void loop();
    int getDuration();
    Track getCurrentTrack();
    void setStateLoop(int state);
    int getStateLoop();
}
