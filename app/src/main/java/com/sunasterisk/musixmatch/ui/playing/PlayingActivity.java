package com.sunasterisk.musixmatch.ui.playing;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Album;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.ui.base.BaseActivity;
import com.sunasterisk.musixmatch.ui.playing.thumbnail.ThumbnailFragment;
import com.sunasterisk.musixmatch.ui.playing.tracks.TracksFragment;
import com.sunasterisk.musixmatch.utils.widget.RepeatButtonView;

import java.util.List;

public class PlayingActivity extends BaseActivity implements TracksFragment.OnTrackClickListener, ThumbnailFragment.OnGetAlbums {
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
    private ViewPager mViewPager;
    private TabLayout mTabDots;
    private PlayingViewPagerAdapter mPagerAdapter;
    private List<Album> mAlbums;

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
        mViewPager.setCurrentItem(PlayingViewPagerAdapter.THUMB_NAIL);
        mTabDots.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onPlayed(Track track) {
        for (Album album : mAlbums) {
            if (track.getAlbumId() == album.getAlbumId()) {
                ThumbnailFragment.getInstance().setImageTrack(album.getAlbumArt());
            }
        }
    }

    @Override
    public void onGetAlbumsSuccess(List<Album> albums) {
        mAlbums = albums;
    }
}
