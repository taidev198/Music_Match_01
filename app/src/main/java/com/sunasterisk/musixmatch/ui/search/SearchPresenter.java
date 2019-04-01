package com.sunasterisk.musixmatch.ui.search;

import android.provider.SearchRecentSuggestions;

import com.sunasterisk.musixmatch.application.MusixMatchApplication;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.repository.TrackRepository;
import com.sunasterisk.musixmatch.data.source.TrackDataSource;

import java.util.List;

/**
 * Created by superme198 on 21,March,2019
 * in Music_Match_1.
 */
public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mView;
    private TrackRepository mTrackRepository;
    private static final int LIMITED_AMOUNT = 10;

    public SearchPresenter(SearchContract.View view, TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
        mView = view;
    }

    @Override
    public void loadSearchResult(String searchKey) {
        mTrackRepository.searchTrack(searchKey, LIMITED_AMOUNT, new TrackDataSource.LoadTrackCallback() {
            @Override
            public void onSongsLoaded(List<Track> tracks) {
                mView.hideProgressBar();
                mView.showSearchResult(tracks);
                mView.hideIntroSearch();
            }

            @Override
            public void onDataNotAvailable(Exception e) {
                mView.hideProgressBar();
                mView.showError(e.getMessage());
            }
        });
    }

    @Override
    public void saveRecentSearch(String query) {
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(MusixMatchApplication.getInstance(),
                SuggestionProvider.AUTHORITY,
                SuggestionProvider.MODE_QUERY);
        suggestions.saveRecentQuery(query, null);
    }

    @Override
    public void onQueryTextSubmit(String query) {
        mView.showProgressBar();
        mView.hideIntroSearch();
        mView.hideResultSearchGroup();
        loadSearchResult(query);
    }

    @Override
    public void start() {
        mView.hideProgressBar();
        mView.showIntroSearch();
        mView.hideResultSearchGroup();
    }

}
