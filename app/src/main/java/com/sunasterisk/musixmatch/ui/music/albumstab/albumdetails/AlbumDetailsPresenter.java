package com.sunasterisk.musixmatch.ui.music.albumstab.albumdetails;

import com.sunasterisk.musixmatch.data.model.Album;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.repository.TrackRepository;
import com.sunasterisk.musixmatch.data.source.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by superme198 on 05,April,2019
 * in Music_Match__1.
 */
public class AlbumDetailsPresenter implements AlbumDetailsContract.Presenter {

    private TrackRepository mRepository;
    private AlbumDetailsContract.View mView;
    private Album mAlbum;

    public AlbumDetailsPresenter(TrackRepository repository, AlbumDetailsContract.View view, Album album) {
        mRepository = repository;
        mView = view;
        mAlbum = album;
    }

    @Override
    public void getLocalTracks() {
        mRepository.getTracks(mAlbum.getAlbumId(), new Callback<List<Track>>() {
            @Override
            public void getDataSuccess(List<Track> data) {
                mView.showTracksFromAlbum(data);
            }

            @Override
            public void getDataFailure(Exception e) {
                mView.showError(e);
            }
        });
    }

}
