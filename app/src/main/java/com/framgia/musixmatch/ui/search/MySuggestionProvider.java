package com.framgia.musixmatch.ui.search;

import android.content.SearchRecentSuggestionsProvider;


/**
 * Created by superme198 on 20,March,2019
 * in Music_Match_1.
 */
public class MySuggestionProvider extends SearchRecentSuggestionsProvider {

    public final static String AUTHORITY = "com.framgia.musixmatch.ui.search.MySuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public MySuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
