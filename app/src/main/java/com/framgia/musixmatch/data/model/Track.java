package com.framgia.musixmatch.data.model;

import java.util.List;

public class Track {
    private int mId;
    private String mData;
    private long mSize;
    private long mDuration;
    private String mTrackName;
    private int mArtistId;
    private String mArtist;
    private int mAlbumId;
    private String mAlbumName;
    private int mTrackMbID;
    private boolean mHasLyrics;
    private int mCommonTrackID;
    private List<Object> mTrackNameTranslationList;
    private int mLyricsID;

    public Track(int id, String data, long size, long duration, String trackName, int artistId, String artistName, int albumID,
             String albumName, int trackMbID, boolean hasLyrics,  int lyricsID, int commontrackID, List<Object> trackNameTranslationList) {
        mId = id;
        mData = data;
        mSize = size;
        mDuration = duration;
        mTrackName = trackName;
        mArtistId = artistId;
        mArtist = artistName;
        mAlbumId = albumID;
        mAlbumName = albumName;
        mTrackMbID = trackMbID;
        mHasLyrics = hasLyrics;
        mLyricsID = lyricsID;
        mCommonTrackID = commontrackID;
        mTrackNameTranslationList = trackNameTranslationList;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmData() {
        return mData;
    }

    public void setmData(String mData) {
        this.mData = mData;
    }

    public long getmSize() {
        return mSize;
    }

    public void setmSize(long mSize) {
        this.mSize = mSize;
    }

    public long getmDuration() {
        return mDuration;
    }

    public void setmDuration(long mDuration) {
        this.mDuration = mDuration;
    }

    public String getmTrackName() {
        return mTrackName;
    }

    public void setmTrackName(String mTrackName) {
        this.mTrackName = mTrackName;
    }

    public int getmArtistId() {
        return mArtistId;
    }

    public void setmArtistId(int mArtistId) {
        this.mArtistId = mArtistId;
    }

    public String getmArtist() {
        return mArtist;
    }

    public void setmArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    public int getmAlbumId() {
        return mAlbumId;
    }

    public void setmAlbumId(int mAlbumId) {
        this.mAlbumId = mAlbumId;
    }

    public int getmTrackMbID() {
        return mTrackMbID;
    }

    public void setmTrackMbID(int mTrackMbID) {
        this.mTrackMbID = mTrackMbID;
    }

    public int getmCommonTrackID() {
        return mCommonTrackID;
    }

    public void setmCommonTrackID(int mCommonTrackID) {
        this.mCommonTrackID = mCommonTrackID;
    }

    public List<Object> getmTrackNameTranslationList() {
        return mTrackNameTranslationList;
    }

    public void setmTrackNameTranslationList(List<Object> mTrackNameTranslationList) {
        this.mTrackNameTranslationList = mTrackNameTranslationList;
    }

    public String getmAlbumName() {
        return mAlbumName;
    }

    public void setmAlbumName(String mAlbumName) {
        this.mAlbumName = mAlbumName;
    }

    public boolean ismHasLyrics() {
        return mHasLyrics;
    }

    public void setmHasLyrics(boolean mHasLyrics) {
        this.mHasLyrics = mHasLyrics;
    }

    public int getmLyricsID() {
        return mLyricsID;
    }

    public void setmLyricsID(int mLyricsID) {
        this.mLyricsID = mLyricsID;
    }
}
