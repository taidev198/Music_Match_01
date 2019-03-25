package com.sunasterisk.musixmatch.ui.search;

import android.content.Context;

import com.sunasterisk.musixmatch.BasePresenter;
import com.sunasterisk.musixmatch.BaseView;
import com.sunasterisk.musixmatch.data.model.Track;

import java.util.List;

/**
 * Created by superme198 on 21,March,2019
 * in Music_Match_1.
 */
public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void showIntroSearch(boolean isLoading);

        void showProgressBar(boolean isLoading);

        void showSearchResult(List<Track> tracks);

        void showError(String errMsg);

        void showSuccess(String msg);

    }

    interface Presenter extends BasePresenter {

        void loadSearchResult(String searchKey);

        void saveRecentSearch(Context context, String query);

        void onQueryTextSubmit(String query);

    }
}
