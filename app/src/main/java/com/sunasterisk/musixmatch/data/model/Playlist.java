package com.sunasterisk.musixmatch.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by superme198 on 14,April,2019
 * in Music_Match__1.
 */
public class Playlist implements Parcelable {

    private int mPlaylistId;
    private int mOrderTrack;
    private String mPlaylistName;
    private int mNumSongs;

    public Playlist(Builder builder) {
        mNumSongs = builder.mNumSongs;
        mOrderTrack = builder.mOrderTrack;
        mPlaylistId = builder.mPlaylistId;
        mPlaylistName = builder.mPlaylistName;
    }

    protected Playlist(Parcel in) {
        mNumSongs = in.readInt();
        mOrderTrack = in.readInt();
        mPlaylistId = in.readInt();
        mPlaylistName = in.readString();
    }

    public static final Creator<Playlist> CREATOR = new Creator<Playlist>() {
        @Override
        public Playlist createFromParcel(Parcel in) {
            return new Playlist(in);
        }

        @Override
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public int getPlaylistId() {
        return mPlaylistId;
    }

    public void setPlaylistId(int mPlaylistId) {
        this.mPlaylistId = mPlaylistId;
    }

    public int getOrderTrack() {
        return mOrderTrack;
    }

    public void setOrderTrack(int mOrderTrack) {
        this.mOrderTrack = mOrderTrack;
    }

    public String getPlaylistName() {
        return mPlaylistName;
    }

    public void setPlaylistName(String mPlaylistName) {
        this.mPlaylistName = mPlaylistName;
    }

    public int getNumSongs() {
        return mNumSongs;
    }

    public void setNumSongs(int mNumSongs) {
        this.mNumSongs = mNumSongs;
    }

    public static class Builder {
        private int mPlaylistId;
        private int mOrderTrack;
        private String mPlaylistName;
        private int mNumSongs;

        public Builder() {

        }

        public Builder setPlaylistId(int mPlaylistId) {
            this.mPlaylistId = mPlaylistId;
            return this;
        }

        public Builder setOrderTrack(int mOrderTrack) {
            this.mOrderTrack = mOrderTrack;
            return this;
        }

        public Builder setPlaylistName(String mPlaylistName) {
            this.mPlaylistName = mPlaylistName;
            return this;
        }

        public Builder setNumSongs(int mNumSongs) {
            this.mNumSongs = mNumSongs;
            return this;
        }

        public Playlist build() {
            return new Playlist(this);
        }
    }
}
