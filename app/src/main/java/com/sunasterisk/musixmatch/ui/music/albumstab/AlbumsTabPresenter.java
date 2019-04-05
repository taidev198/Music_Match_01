package com.sunasterisk.musixmatch.ui.music.albumstab;


import com.sunasterisk.musixmatch.data.model.Album;
import com.sunasterisk.musixmatch.data.repository.AlbumRepository;
import com.sunasterisk.musixmatch.data.source.Callback;

import java.util.List;

/**
 * Created by superme198 on 05,April,2019
 * in Music_Match__1.
 */
public class AlbumsTabPresenter implements AlbumsContract.Presenter {

    private AlbumRepository mRepository;
    private AlbumsContract.View mView;

    public AlbumsTabPresenter(AlbumRepository repository, AlbumsContract.View view) {
        mRepository = repository;
        mView = view;
    }

    @Override
    public void getLocalAlbums() {
        mRepository.getAlbums(new Callback<List<Album>>() {
            @Override
            public void getDataSuccess(List<Album> albums) {
                mView.showLocalAlbums(albums);
            }

            @Override
            public void getDataFailure(Exception e) {
                mView.showError(e);
            }
        });
    }
}
