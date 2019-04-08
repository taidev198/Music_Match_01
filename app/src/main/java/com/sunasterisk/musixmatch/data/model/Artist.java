package com.sunasterisk.musixmatch.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Artist implements Parcelable {
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

    protected Artist(Parcel in) {
        mArtistId = in.readInt();
        mArtistName = in.readString();
        mNumberOfAlbums = in.readInt();
        mNumberOfTracks = in.readInt();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mArtistId);
        parcel.writeString(mArtistName);
        parcel.writeInt(mNumberOfTracks);
        parcel.writeInt(mNumberOfAlbums);
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
