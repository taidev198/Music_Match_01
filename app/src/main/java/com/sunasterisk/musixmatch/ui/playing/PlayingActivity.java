package com.sunasterisk.musixmatch.ui.playing;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.ui.base.BaseActivity;
import com.sunasterisk.musixmatch.ui.playing.tracks.TracksFragment;
import com.sunasterisk.musixmatch.utils.Constants;
import com.sunasterisk.musixmatch.utils.widget.RepeatButtonView;

public class PlayingActivity extends BaseActivity implements TracksFragment.OnTrackClickListener {
    private TextView mTextTrackName;
    private TextView mTextArtistName;
    private TextView mTextCurrentTime;
    private TextView mTextTotalTime;
    private SeekBar mSeekBarProgressTime;
    private RepeatButtonView mButtonRepeat;
    private ImageButton mButtonBack;
    private ImageButton mButtonPlay;
    private ImageButton mButtonNext;
    private ImageButton mButtonFavorite;
    private PlayingViewPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabDots;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_playing;
    }

    @Override
    protected void initComponents() {
        mTextTrackName = findViewById(R.id.text_title_song);
        mTextArtistName = findViewById(R.id.text_artist_name);
        mTextCurrentTime = findViewById(R.id.text_current_time);
        mTextTotalTime = findViewById(R.id.text_total_time);
        mSeekBarProgressTime = findViewById(R.id.seek_bar_progress_time);
        mButtonRepeat = findViewById(R.id.button_repeat);
        mButtonBack = findViewById(R.id.button_back);
        mButtonPlay = findViewById(R.id.button_play);
        mButtonNext = findViewById(R.id.button_next);
        mButtonFavorite = findViewById(R.id.button_favorite);
        mViewPager = findViewById(R.id.view_pager);
        mTabDots = findViewById(R.id.tab_dots);
        mPagerAdapter = new PlayingViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(1);
        mTabDots.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onTrackClickListener(Track track) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleTrackKey.ARGUMENT_TRACK, track);
    }

}
