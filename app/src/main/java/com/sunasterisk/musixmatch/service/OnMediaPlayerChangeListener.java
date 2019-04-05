package com.sunasterisk.musixmatch.service;

import com.sunasterisk.musixmatch.data.model.Track;

public interface OnMediaPlayerChangeListener {
    void onMediaStateChange(boolean isPlaying);
    void onLoopStateChange(@LoopMode int state);
    void onTrackChange(Track track);
}
