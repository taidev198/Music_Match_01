package com.sunasterisk.musixmatch.ui.playing.tracks;

import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.repository.TrackRepository;
import com.sunasterisk.musixmatch.data.source.Callback;

import java.util.List;

public class TracksPresenter implements TracksContract.Presenter {
    private TrackRepository mRepository;
    private TracksContract.View mView;

    public TracksPresenter(TrackRepository repository, TracksContract.View view) {
        mRepository = repository;
        mView = view;
    }

    @Override
    public void getLocalTracks() {
        mRepository.getTracks(new Callback<List<Track>>() {
            @Override
            public void getDataSuccess(List<Track> tracks) {
                mView.showLocalTracks(tracks);
            }

            @Override
            public void getDataFailure(Exception e) {
                mView.showError(e);
            }
        });
    }
}
