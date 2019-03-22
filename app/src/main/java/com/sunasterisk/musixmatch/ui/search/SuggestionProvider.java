package com.sunasterisk.musixmatch.ui.search;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by superme198 on 20,March,2019
 * in Music_Match_1.
 */
public class SuggestionProvider extends SearchRecentSuggestionsProvider {

    public final static String AUTHORITY = "com.sunasterisk.musixmatch.ui.search.SuggestionProvider";
    public final static int MODE_QUERY = DATABASE_MODE_QUERIES;

    public SuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE_QUERY);
    }
}
