package com.sunasterisk.musixmatch.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Album implements Parcelable {
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

    protected Album(Parcel in) {
        mAlbumId = in.readInt();
        mAlbumName = in.readString();
        mArtistId = in.readInt();
        mArtistName = in.readString();
        mNumberOfSongs = in.readInt();
        mAlbumArt = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mAlbumId);
        parcel.writeString(mAlbumName);
        parcel.writeInt(mArtistId);
        parcel.writeString(mArtistName);
        parcel.writeString(mAlbumArt);
        parcel.writeInt(mNumberOfSongs);
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
