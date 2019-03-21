package com.sunasterisk.musixmatch.data.model;

public class Artist {
    private int mArtistId;
    private String mArtistName;
    private int mNumberOfAlbums;
    private int mNumberOfTracks;

    public Artist(Builder builder) {
        mArtistId = builder.mArtistId;
        mArtistName = builder.mArtistName;
        mNumberOfAlbums = builder.mNumberOfAlbums;
        mNumberOfTracks = builder.mNumberOfTracks;
    }

    public int getArtistId() {
        return mArtistId;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public int getNumberOfAlbums() {
        return mNumberOfAlbums;
    }

    public int getNumberOfTracks() {
        return mNumberOfTracks;
    }

    public static class Builder {
        private int mArtistId;
        private String mArtistName;
        private int mNumberOfAlbums;
        private int mNumberOfTracks;

        public Builder() {
        }

        public Builder setArtistId(int artistId) {
            mArtistId = artistId;
            return this;
        }

        public Builder setArtistName(String artistName) {
            mArtistName = artistName;
            return this;
        }

        public Builder setNumberOfAlbums(int numberOfAlbums) {
            mNumberOfAlbums = numberOfAlbums;
            return this;
        }

        public Builder setNumberOfTracks(int numberOfTracks) {
            mNumberOfTracks = numberOfTracks;
            return this;
        }

        public Artist build() {
            return new Artist(this);
        }
    }
}
