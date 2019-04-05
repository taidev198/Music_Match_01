package com.sunasterisk.musixmatch.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcelable;

import com.sunasterisk.musixmatch.data.model.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusicService extends Service
        implements MediaListener, MediaPlayer.OnCompletionListener {
    private static final String EXTRA_TRACK = "EXTRA_TRACK";
    private static final String EXTRA_TRACK_POSITION = "EXTRA_TRACK_POSITION";
    private OnMediaPlayerChangeListener mOnMediaPlayerChangeListener;
    private List<Track> mTracks;
    private MediaPlayer mMediaPlayer;
    private Random mRandom;
    private int mPosition;
    private int mStateLoop;
    private Context mContext;

    public static Intent getIntentService(
            Context context, List<Track> tracks, int position) {
        Intent intent = new Intent(context, MusicService.class);
        intent.putParcelableArrayListExtra(
                EXTRA_TRACK, (ArrayList<? extends Parcelable>) tracks);
        intent.putExtra(EXTRA_TRACK_POSITION, position);
        return intent;
    }

    public void setOnMediaPlayerChangeListener(
            OnMediaPlayerChangeListener onMediaPlayerChangeListener) {
        mOnMediaPlayerChangeListener = onMediaPlayerChangeListener;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mRandom = new Random();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPosition = intent.getIntExtra(EXTRA_TRACK_POSITION, 0);
        mTracks = intent.getParcelableArrayListExtra(EXTRA_TRACK);
        return START_NOT_STICKY;
    }

    @Override
    public boolean isPlaying() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.isPlaying();
        }
        return false;
    }

    @Override
    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
    }

    @Override
    public void pause() {
        if (mMediaPlayer != null && isPlaying()) {
            mOnMediaPlayerChangeListener.onMediaStateChange(false);
            mMediaPlayer.pause();
        }
    }

    @Override
    public void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
    }

    @Override
    public void seek(long duration) {
        if (mMediaPlayer != null) {
            mMediaPlayer.seekTo((int) duration);
        }
    }

    @Override
    public int getCurrentDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override
    public int getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        }
        return 0;
    }

    @Override
    public Track getCurrentTrack() {
        return mTracks.get(mPosition);
    }

    @Override
    public void setStateLoop(int state) {
        mStateLoop = state;
        mOnMediaPlayerChangeListener.onLoopStateChange(state);
    }

    @Override
    public int getStateLoop() {
        return mStateLoop;
    }

    @Override
    public void start() {
        if (mMediaPlayer != null) {
            mOnMediaPlayerChangeListener.onMediaStateChange(true);
            mMediaPlayer.start();
        }
    }

    @Override
    public void play(String uriString) {
        release();
        Uri uri = Uri.parse(uriString);
        mMediaPlayer = MediaPlayer.create(mContext, uri);
        mMediaPlayer.setOnCompletionListener(this);
        mOnMediaPlayerChangeListener.onTrackChange(getCurrentTrack());
        mOnMediaPlayerChangeListener.onMediaStateChange(true);
        start();
    }

    @Override
    public void previous() {
        destroyMusic();
        decreasePosition();
        play(getCurrentUri());
    }

    @Override
    public void next() {
        destroyMusic();
        increasePosition();
        play(getCurrentUri());
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        onMediaPlayerComplete();
    }

    public MediaListener getMediaListener() {
        return this;
    }

    private String getCurrentUri() {
        return mTracks.get(mPosition).getData();
    }

    private void destroyMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    /**
     * <p> This method will check loop mode when current track has finished </p>
     */
    private void onMediaPlayerComplete() {
        switch (mStateLoop) {
            case LoopMode.LOOP_OFF:
                if (mPosition < mTracks.size() - 1) {
                    increasePosition();
                    play(getCurrentUri());
                } else {
                    mPosition = 0;
                    seek(0);
                    stop();
                    mOnMediaPlayerChangeListener.onMediaStateChange(false);
                    mOnMediaPlayerChangeListener.onTrackChange(getCurrentTrack());
                }
                break;
            case LoopMode.LOOP_ONE:
                play(getCurrentUri());
                break;
            case LoopMode.LOOP_ALL:
                increasePosition();
                play(getCurrentUri());
                break;
            case LoopMode.SHUFFLE_ON:
                randomPosition();
                play(getCurrentUri());
                break;
        }
    }

    private void increasePosition() {
        if (mPosition == mTracks.size() - 1) {
            mPosition = 0;
        } else {
            mPosition += 1;
        }
    }

    private void decreasePosition() {
        if (mPosition == 0) {
            mPosition = mTracks.size() - 1;
        } else {
            mPosition -= 1;
        }
    }

    private void randomPosition() {
        mPosition = mRandom.nextInt(mTracks.size() + 1);
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}
