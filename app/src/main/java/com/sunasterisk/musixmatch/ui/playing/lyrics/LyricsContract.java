package com.sunasterisk.musixmatch.ui.playing.lyrics;

public interface LyricsContract {
    interface Presenter {
        void getLyrics(String trackName, String artistName);
    }

    interface View {
        void showLyrics(String lyrics);

        void showError(Exception e);
    }
}
