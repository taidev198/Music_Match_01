package com.sunasterisk.musixmatch.ui.music.artiststab;


import com.sunasterisk.musixmatch.data.model.Artist;
import com.sunasterisk.musixmatch.data.repository.ArtistRepository;
import com.sunasterisk.musixmatch.data.source.Callback;

import java.util.List;

/**
 * Created by superme198 on 08,April,2019
 * in Music_Match__1.
 */
public class ArtistsTabPresenter implements ArtistsContract.Presenter {

    private ArtistRepository mRepository;
    private ArtistsContract.View mView;

    public ArtistsTabPresenter(ArtistRepository repository, ArtistsContract.View view){
        mRepository = repository;
        mView = view;
    }

    @Override
    public void getLocalArtists() {
        mRepository.getArtists(new Callback<List<Artist>>() {
            @Override
            public void getDataSuccess(List<Artist> data) {
                mView.showArtists(data);
            }

            @Override
            public void getDataFailure(Exception e) {
                mView.showError(e);
            }
        });
    }
}
