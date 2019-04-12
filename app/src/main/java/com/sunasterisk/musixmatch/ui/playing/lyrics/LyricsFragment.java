package com.sunasterisk.musixmatch.ui.playing.lyrics;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.repository.TrackRepository;
import com.sunasterisk.musixmatch.data.source.local.TrackLocalDataSource;
import com.sunasterisk.musixmatch.data.source.remote.TrackRemoteDataSource;
import com.sunasterisk.musixmatch.ui.base.BaseFragment;

public class LyricsFragment extends BaseFragment implements LyricsContract.View, View.OnClickListener {
    private TextView mTextRefresh;
    private TextView mTextInternetConnection;
    private TextView mTextLyrics;
    private LyricsContract.Presenter mPresenter;
    private String mTrackName;
    private String mArtistName;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_lyrics;
    }

    @Override
    protected void initComponents(View view) {
        mTextRefresh = view.findViewById(R.id.text_refresh);
        mTextInternetConnection = view.findViewById(R.id.text_notify);
        mTextLyrics = view.findViewById(R.id.text_lyrics);
        mTextRefresh.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter = new LyricsPresenter(TrackRepository.getInstance(
                TrackLocalDataSource.getInstance(getActivity()),
                TrackRemoteDataSource.getInstance()),
                this);
        mPresenter.getLyrics(mTrackName, mArtistName);
    }

    @Override
    public void showLyrics(String lyrics) {
        if (isOnline()) {
            mTextLyrics.setText(lyrics);
            mTextInternetConnection.setVisibility(View.INVISIBLE);
            mTextRefresh.setVisibility(View.INVISIBLE);
        } else {
            mTextLyrics.setText("");
            mTextInternetConnection.setVisibility(View.VISIBLE);
            mTextRefresh.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(Exception e) {
        Log.e(getClass().getName(), e.getMessage());
        showLyrics(getActivity().getString(R.string.text_lyrics_not_found));
    }

    @Override
    public void onClick(View v) {
        mPresenter.getLyrics(mTrackName, mArtistName);
    }

    public void getLyrics(Track track) {
        mTrackName = track.getTrackName();
        mArtistName = track.getArtistName();
        mPresenter.getLyrics(mTrackName, mArtistName);
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }
}
