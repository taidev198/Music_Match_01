package com.sunasterisk.musixmatch.ui.playing.lyrics;

import com.sunasterisk.musixmatch.data.repository.TrackRepository;
import com.sunasterisk.musixmatch.data.source.Callback;

public class LyricsPresenter implements LyricsContract.Presenter {
    private TrackRepository mTrackRepository;
    private LyricsContract.View mView;

    public LyricsPresenter(TrackRepository trackRepository, LyricsContract.View view) {
        mTrackRepository = trackRepository;
        mView = view;
    }

    @Override
    public void getLyrics(String trackName, String artistName) {
        mTrackRepository.getLyrics(trackName, artistName, new Callback<String>() {
            @Override
            public void getDataSuccess(String lyrics) {
                mView.showLyrics(lyrics);
            }

            @Override
            public void getDataFailure(Exception e) {
                mView.showError(e);
            }
        });
    }
}
