package com.sunasterisk.musixmatch.data.model;

public class Album {
    private int mAlbumId;
    private String mAlbumName;
    private int mArtistId;
    private String mArtistName;
    private int mNumberOfSongs;
    private String mAlbumArt;

    public Album(Builder builder) {
        mAlbumId = builder.mAlbumId;
        mAlbumName = builder.mAlbumName;
        mArtistId = builder.mArtistId;
        mArtistName = builder.mArtistName;
        mNumberOfSongs = builder.mNumberOfSongs;
        mAlbumArt = builder.mAlbumArt;
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

    public int getNumberOfSongs() {
        return mNumberOfSongs;
    }

    public String getAlbumArt() {
        return mAlbumArt;
    }

    public static class Builder {
        private int mAlbumId;
        private String mAlbumName;
        private int mArtistId;
        private String mArtistName;
        private int mNumberOfSongs;
        private String mAlbumArt;

        public Builder() {
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

        public Builder setNumberOfSongs(int numberOfSongs) {
            mNumberOfSongs = numberOfSongs;
            return this;
        }

        public Builder setAlbumArt(String albumArt) {
            mAlbumArt = albumArt;
            return this;
        }

        public Album build() {
            return new Album(this);
        }
    }
}
