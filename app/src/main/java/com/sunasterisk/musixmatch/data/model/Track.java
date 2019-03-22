package com.sunasterisk.musixmatch.data.model;

public class Track {
    private int mTrackId;
    private String mTrackName;
    private int mAlbumId;
    private String mAlbumName;
    private int mArtistId;
    private String mArtistName;
    private String mData;
    private long mDuration;
    private long mSize;

    public Track(Builder builder) {
        mTrackId = builder.mTrackId;
        mTrackName = builder.mTrackName;
        mAlbumId = builder.mAlbumId;
        mAlbumName = builder.mAlbumName;
        mArtistId = builder.mArtistId;
        mArtistName = builder.mArtistName;
        mData = builder.mData;
        mDuration = builder.mDuration;
        mSize = builder.mSize;
    }

    public int getTrackId() {
        return mTrackId;
    }

    public String getTrackName() {
        return mTrackName;
    }

    public int getAlbumId() {
        return mAlbumId;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public int getArtistId() {
        return mArtistId;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getData() {
        return mData;
    }

    public long getDuration() {
        return mDuration;
    }

    public long getSize() {
        return mSize;
    }

    public static class Builder {
        private int mTrackId;
        private String mTrackName;
        private int mAlbumId;
        private String mAlbumName;
        private int mArtistId;
        private String mArtistName;
        private String mData;
        private long mDuration;
        private long mSize;

        public Builder() {
        }

        public Builder setTrackId(int trackId) {
            mTrackId = trackId;
            return this;
        }

        public Builder setTrackName(String trackName) {
            mTrackName = trackName;
            return this;
        }

        public Builder setAlbumId(int albumId) {
            mAlbumId = albumId;
            return this;
        }

        public Builder setAlbumName(String albumName) {
            mAlbumName = albumName;
            return this;
        }

        public Builder setArtistId(int artistId) {
            mArtistId = artistId;
            return this;
        }

        public Builder setArtistName(String artistName) {
            mArtistName = artistName;
            return this;
        }

        public Builder setData(String data) {
            mData = data;
            return this;
        }

        public Builder setDuration(long duration) {
            mDuration = duration;
            return this;
        }

        public Builder setSize(long size) {
            mSize = size;
            return this;
        }

        public Track build() {
            return new Track(this);
        }
    }
}
