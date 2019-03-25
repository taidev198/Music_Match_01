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

        void showSearchResult(List<Track> songs);

        void showError(String errMsg);

        void showSuccess(String msg);

    }

    interface Presenter extends BasePresenter {

        void loadSearchResult(String searchKey);

        void saveRecentSearch(String query, Context context);

        void onQueryTextSubmit(String query);

        String getSearchKey();

        void setSearchKey(String searchKey);

        void setAddSearchKey(boolean isAdding);

    }
}
