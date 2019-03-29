package com.sunasterisk.musixmatch.ui.playing.thumbnail;

import com.sunasterisk.musixmatch.data.model.Album;
import com.sunasterisk.musixmatch.data.repository.AlbumRepository;
import com.sunasterisk.musixmatch.data.source.Callback;

import java.util.List;

public class ThumbnailPresenter implements ThumbnailContract.Presenter {
    private AlbumRepository mRepository;
    private ThumbnailContract.View mView;

    public ThumbnailPresenter(AlbumRepository repository, ThumbnailContract.View view) {
        mRepository = repository;
        mView = view;
    }

    @Override
    public void getAlbums() {
        mRepository.getAlbums(new Callback<List<Album>>() {
            @Override
            public void getDataSuccess(List<Album> albums) {
                mView.showAlbums(albums);
            }

            @Override
            public void getDataFailure(Exception e) {
                mView.showError(e);
            }
        });
    }
}