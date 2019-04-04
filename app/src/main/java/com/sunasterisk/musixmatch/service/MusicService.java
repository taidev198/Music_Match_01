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
    private static final String EXTRA_TRACK_PLAY = "EXTRA_TRACK_PLAY";
    private OnMediaPlayerChangeListener mOnMediaPlayerChangeListener;
    private List<Track> mTracks;
    private MediaPlayer mMediaPlayer;
    private Random mRandom;
    private int mPosition;
    private int mStateLoop;
    private Context mContext;
    private boolean isFirstPlayed;

    public static Intent getIntentService(
            Context context, List<Track> tracks, int position, boolean isPlay) {
        Intent intent = new Intent(context, MusicService.class);
        intent.putParcelableArrayListExtra(
                EXTRA_TRACK, (ArrayList<? extends Parcelable>) tracks);
        intent.putExtra(EXTRA_TRACK_PLAY, isPlay);
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
        isFirstPlayed = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPosition = intent.getIntExtra(EXTRA_TRACK_POSITION, 0);
        mTracks = intent.getParcelableArrayListExtra(EXTRA_TRACK);
        if (intent.getBooleanExtra(EXTRA_TRACK_PLAY, false)) {
            playNewTrack();
        } else {
            mOnMediaPlayerChangeListener.onTrackChange(getCurrentTrack());
        }
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
        if (mMediaPlayer != null && !isPlaying() && isFirstPlayed) {
            mOnMediaPlayerChangeListener.onMediaStateChange(true);
            start();
        } else if (mMediaPlayer != null && isPlaying() && isFirstPlayed) {
            mOnMediaPlayerChangeListener.onMediaStateChange(false);
            mMediaPlayer.pause();
        } else {
            play(getCurrentUri());
            mOnMediaPlayerChangeListener.onMediaStateChange(true);
            isFirstPlayed = true;
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
    }

    @Override
    public int getStateLoop() {
        return mStateLoop;
    }

    @Override
    public void start() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
        }
    }

    @Override
    public void play(String uriString) {
        release();
        Uri uri = Uri.parse(uriString);
        mMediaPlayer = MediaPlayer.create(mContext, uri);
        mMediaPlayer.setOnCompletionListener(this);
        mOnMediaPlayerChangeListener.onMediaStateChange(true);
        start();
    }

    @Override
    public void previous() {
        destroyMusic();
        if (mStateLoop == LoopMode.SHUFFLE_ON) {
            mPosition = mRandom.nextInt(mTracks.size() - 1);
        } else if (mPosition == 0) {
            mPosition = mTracks.size() - 1;
        } else {
            mPosition--;
        }
        playNewTrack();
    }

    @Override
    public void next() {
        destroyMusic();
        if (mStateLoop == LoopMode.SHUFFLE_ON) {
            mPosition = mRandom.nextInt(mTracks.size() - 1);
        } else if (mPosition == mTracks.size() - 1) {
            mPosition = 0;
        } else {
            mPosition++;
        }
        playNewTrack();
    }

    @Override
    public void loop() {
        mOnMediaPlayerChangeListener.onLoopStateChange(mStateLoop);
        switch (mStateLoop) {
            case LoopMode.LOOP_OFF:
                mStateLoop = LoopMode.LOOP_ONE;
                break;
            case LoopMode.LOOP_ONE:
                mStateLoop = LoopMode.LOOP_ALL;
                break;
            case LoopMode.LOOP_ALL:
                mStateLoop = LoopMode.SHUFFLE_ON;
                break;
            case LoopMode.SHUFFLE_ON:
                mStateLoop = LoopMode.LOOP_OFF;
            default:
                break;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        checkLoopMode();
    }

    private String getCurrentUri() {
        destroyMusic();
        return mTracks.get(mPosition).getData();
    }

    private void destroyMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void playNewTrack() {
        mOnMediaPlayerChangeListener.onTrackChange(getCurrentTrack());
        play(getCurrentUri());
    }

    private void checkLoopMode() {
        switch (mStateLoop) {
            case LoopMode.LOOP_OFF:
                if (mPosition < mTracks.size() - 1) {
                    mPosition++;
                    playNewTrack();
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
                if (mPosition == mTracks.size() - 1) {
                    mPosition = 0;
                } else {
                    mPosition++;
                }
                playNewTrack();
                break;
            case LoopMode.SHUFFLE_ON:
                if (mPosition < mTracks.size()) {
                    mPosition = mRandom.nextInt(mTracks.size() + 1);
                }
                playNewTrack();
                break;
        }
    }

    public MediaListener getMediaListener() {
        return this;
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}
