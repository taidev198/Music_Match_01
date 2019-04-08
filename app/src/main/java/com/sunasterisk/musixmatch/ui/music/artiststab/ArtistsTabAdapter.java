package com.sunasterisk.musixmatch.ui.music.artiststab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.view.ViewGroup;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Artist;
import com.sunasterisk.musixmatch.ui.base.BaseAdapter;
import com.sunasterisk.musixmatch.ui.base.BaseTrackViewHolder;
import com.sunasterisk.musixmatch.ui.base.OnRecyclerItemClickListener;

/**
 * Created by superme198 on 08,April,2019
 * in Music_Match__1.
 */
public class ArtistsTabAdapter extends BaseAdapter<Artist, OnRecyclerItemClickListener<Artist>, ArtistsTabAdapter.ArtistTabViewHolder> {

    public ArtistsTabAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ArtistTabViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = inflate(R.layout.item_track, viewGroup, false);
        return new ArtistTabViewHolder(mContext, v, mCallback);
    }

    public static class ArtistTabViewHolder extends BaseTrackViewHolder<Artist, OnRecyclerItemClickListener<Artist>> {

        private static final String TEXT_TRACKS = "Tracks";
        private static final String TEXT_TRACK = "Track";
        private static final String TEXT_ALBUMS = "Albums";
        private static final String TEXT_ALBUM = "Albums";
        private StringBuilder mSubTitle = new StringBuilder();

        public ArtistTabViewHolder(Context context, View itemView, OnRecyclerItemClickListener<Artist> mCallback) {
            super(context, itemView, mCallback);
            mTextTitle.setOnClickListener(this);
            mTextSubTitle.setOnClickListener(this);
            mOptionMore.setOnClickListener(this);
        }

        @Override
        public void bindData(Artist artist) {
            if (artist != null) {
                mItem = artist;
                mTextTitle.setText(artist.getArtistName());
                mSubTitle.append(artist.getNumberOfAlbums())
                        .append(" ");
                if (artist.getNumberOfAlbums() > 1) {
                    mSubTitle.append(TEXT_ALBUMS);
                } else {
                    mSubTitle.append(TEXT_ALBUM);
                }

                mSubTitle.append(" ")
                        .append(artist.getNumberOfTracks())
                        .append(" ");
                if (artist.getNumberOfTracks() > 1) {
                    mSubTitle.append(TEXT_TRACKS);
                } else {
                    mSubTitle.append(TEXT_TRACK);
                }
                mTextSubTitle.setText(mSubTitle.toString());
            }
        }

        @Override
        public void showOptionMenu() {
            PopupMenu popup = new PopupMenu(mContext, mOptionMore);
            popup.inflate(R.menu.options_menu_artists_tab);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.play:
                        return true;
                    case R.id.add_to_queue:
                        return true;
                    case R.id.add_to_playlist:
                        return true;
                    default:
                        return false;
                }
            });
            popup.show();

        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.item_track_card:
                    break;
                case R.id.button_more:
                    showOptionMenu();
                    break;
            }
            if (mCallback != null) {
                mCallback.onItemClicked(mItem);
            }
        }
    }

}
