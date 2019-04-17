package com.sunasterisk.musixmatch.ui.main.home;


import com.sunasterisk.musixmatch.data.model.Playlist;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.repository.PlaylistRepository;
import com.sunasterisk.musixmatch.data.source.Callback;

import java.util.List;

/**
 * Created by superme198 on 17,April,2019
 * in Music_Match__1.
 */
public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private PlaylistRepository mRepository;

    public HomePresenter(PlaylistRepository repository, HomeContract.View view) {
        mRepository = repository;
        mView = view;
    }

    @Override
    public void getRecentlyPlayedTracks() {
        mRepository.getTrackFromRecentlyPlayed(new Callback<List<Track>>() {
            @Override
            public void getDataSuccess(List<Track> data) {
                mView.showRecentlyPlayed(data);
            }

            @Override
            public void getDataFailure(Exception e) {
                mView.showError(e);
            }
        });
    }

    @Override
    public void getFavouriteTracks() {
        mRepository.getTrackFromFavorite(new Callback<List<Track>>() {
            @Override
            public void getDataSuccess(List<Track> data) {
                mView.showFavourite(data);
            }

            @Override
            public void getDataFailure(Exception e) {
                mView.showError(e);
            }
        });
    }

}
