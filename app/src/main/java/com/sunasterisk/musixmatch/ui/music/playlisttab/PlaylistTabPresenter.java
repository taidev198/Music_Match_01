package com.sunasterisk.musixmatch.ui.music.playlisttab;

import com.sunasterisk.musixmatch.data.model.Playlist;
import com.sunasterisk.musixmatch.data.repository.PlaylistRepository;
import com.sunasterisk.musixmatch.data.source.Callback;

import java.util.List;

/**
 * Created by superme198 on 14,April,2019
 * in Music_Match__1.
 */
public class PlaylistTabPresenter implements PlaylistTabContract.Presenter {

    private PlaylistTabContract.View mView;
    private PlaylistRepository mRepository;

    public PlaylistTabPresenter(PlaylistRepository repository, PlaylistTabContract.View view){
        mRepository = repository;
        mView = view;
    }

    @Override
    public void getLocalPlaylist() {
        mRepository.getPlaylist(new Callback<List<Playlist>>() {
            @Override
            public void getDataSuccess(List<Playlist> data) {
                mView.showPlaylist(data);
            }

            @Override
            public void getDataFailure(Exception e) {
                mView.showError(e);
            }
        });
    }
}
