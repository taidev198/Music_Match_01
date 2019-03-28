package com.sunasterisk.musixmatch.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.sunasterisk.musixmatch.utils.Constants;
import com.sunasterisk.musixmatch.utils.TrackLoaderUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class Track implements Parcelable {
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

    public Track(JSONObject jsonObject) throws JSONException {
        mTrackId = jsonObject.getInt(Constants.JSonKey.TRACK_ID);
        mTrackName = jsonObject.getString(Constants.JSonKey.TRACK_NAME);
        mAlbumId = jsonObject.getInt(Constants.JSonKey.ALBUM_ID);
        mAlbumName = jsonObject.getString(Constants.JSonKey.ARTIST_NAME);
        mArtistName = jsonObject.getString(Constants.JSonKey.ARTIST_NAME);
    }

    protected Track(Parcel in) {
        mTrackId = in.readInt();
        mTrackName = in.readString();
        mAlbumId = in.readInt();
        mAlbumName = in.readString();
        mArtistId = in.readInt();
        mArtistName = in.readString();
        mData = in.readString();
        mDuration = in.readLong();
        mSize = in.readLong();
    }

    public static final Parcelable.Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mTrackId);
        parcel.writeString(mTrackName);
        parcel.writeInt(mAlbumId);
        parcel.writeString(mAlbumName);
        parcel.writeInt(mArtistId);
        parcel.writeString(mArtistName);
        parcel.writeString(mData);
        parcel.writeLong(mDuration);
        parcel.writeLong(mSize);
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
