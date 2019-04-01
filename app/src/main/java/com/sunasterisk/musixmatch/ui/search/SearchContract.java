package com.sunasterisk.musixmatch.ui.search;


import com.sunasterisk.musixmatch.ui.base.BasePresenter;
import com.sunasterisk.musixmatch.ui.base.BaseView;
import com.sunasterisk.musixmatch.data.model.Track;

import java.util.List;

/**
 * Created by superme198 on 21,March,2019
 * in Music_Match_1.
 */
public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void showIntroSearch();

        void hideIntroSearch();

        void showResultSearchGroup();

        void hideResultSearchGroup();

        void showProgressBar();

        void hideProgressBar();

        void showSearchResult(List<Track> tracks);

        void showError(String errMsg);

        void showSuccess(String msg);

    }

    interface Presenter extends BasePresenter {

        void loadSearchResult(String searchKey);

        void saveRecentSearch(String query);

        void getTrackFromAPI(String query);

    }
}
