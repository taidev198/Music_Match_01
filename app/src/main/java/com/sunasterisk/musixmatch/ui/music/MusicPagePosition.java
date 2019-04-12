package com.sunasterisk.musixmatch.ui.music;

import android.support.annotation.IntDef;

import static com.sunasterisk.musixmatch.ui.music.MusicPagePosition.ALBUM;
import static com.sunasterisk.musixmatch.ui.music.MusicPagePosition.ARTIST;
import static com.sunasterisk.musixmatch.ui.music.MusicPagePosition.PLAYLIST;
import static com.sunasterisk.musixmatch.ui.music.MusicPagePosition.TRACK;

/**
 * Created by superme198 on 10,April,2019
 * in Music_Match__1.
 */
@IntDef({ALBUM, ARTIST, TRACK, PLAYLIST})
public @interface MusicPagePosition {
    int ALBUM = 0;
    int ARTIST = 1;
    int TRACK = 2;
    int PLAYLIST = 3;
}
