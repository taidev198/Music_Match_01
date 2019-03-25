package com.sunasterisk.musixmatch.ui.search;


import android.content.Context;
import android.provider.SearchRecentSuggestions;

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

    public SearchPresenter(SearchContract.View view, TrackRepository trackRepository) {
        mTrackRepository = trackRepository;
        mView = view;
    }

    @Override
    public void loadSearchResult(String searchKey) {
        mTrackRepository.searchTrack(searchKey, 10, new TrackDataSource.LoadTrackCallback() {
            @Override
            public void onSongsLoaded(List<Track> tracks) {
                mView.showProgressBar(false);
                mView.showSearchResult(tracks);
                mView.showIntroSearch(false);
            }

            @Override
            public void onDataNotAvailable(Exception e) {
                mView.showProgressBar(false);
                mView.showError(e.getMessage());
            }
        });
    }

    @Override
    public void saveRecentSearch(Context context, String query) {
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(context,
                SuggestionProvider.AUTHORITY,
                SuggestionProvider.MODE_QUERY);
        suggestions.saveRecentQuery(query, null);
    }

    @Override
    public void onQueryTextSubmit(String query) {
        mView.showProgressBar(true);
        mView.showIntroSearch(false);
        loadSearchResult(query);
    }

    @Override
    public void start() {
        mView.showProgressBar(false);
        mView.showIntroSearch(true);
    }

}
