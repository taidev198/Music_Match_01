package com.sunasterisk.musixmatch.ui.music;

import android.support.annotation.StringDef;

import static com.sunasterisk.musixmatch.ui.music.MusicPageTitle.ALBUM;
import static com.sunasterisk.musixmatch.ui.music.MusicPageTitle.ARTIST;
import static com.sunasterisk.musixmatch.ui.music.MusicPageTitle.PLAYLIST;
import static com.sunasterisk.musixmatch.ui.music.MusicPageTitle.TRACK;

/**
 * Created by superme198 on 10,April,2019
 * in Music_Match__1.
 */
@StringDef({ALBUM, ARTIST, TRACK, PLAYLIST})
public @interface MusicPageTitle {
    String ALBUM = "ALBUM";
    String ARTIST = "ARTIST";
    String TRACK = "TRACK";
    String PLAYLIST = "PLAYLIST";

}
