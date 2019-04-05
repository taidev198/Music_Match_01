package com.sunasterisk.musixmatch.ui.music.albumstab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Album;
import com.sunasterisk.musixmatch.ui.base.BaseAdapter;
import com.sunasterisk.musixmatch.ui.base.BaseTrackViewHolder;
import com.sunasterisk.musixmatch.ui.base.OnRecyclerItemClickListener;

/**
 * Created by superme198 on 05,April,2019
 * in Music_Match__1.
 */
public class AlbumsTabAdapter extends BaseAdapter<Album, OnRecyclerItemClickListener<Album>, AlbumsTabAdapter.AlbumsViewHolder> {

    public AlbumsTabAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public AlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflate(R.layout.album_item, viewGroup, false);
        return new AlbumsViewHolder(mContext, itemView, mCallback);
    }

    public static class AlbumsViewHolder extends BaseTrackViewHolder<Album, OnRecyclerItemClickListener<Album>> {

        protected ImageView mOptionMore;
        protected TextView mTextTitle;
        protected TextView mTextSubTitle;
        protected CardView mAlbumArt;

        public AlbumsViewHolder(Context context,
                                @NonNull View itemView, OnRecyclerItemClickListener<Album> callback) {
            super(context, itemView, callback);
            mAlbumArt = itemView.findViewById(R.id.cardview_album_art);
            mOptionMore = itemView.findViewById(R.id.button_more_album);
            mTextTitle = itemView.findViewById(R.id.text_title_album);
            mTextSubTitle = itemView.findViewById(R.id.text_subtitle_album);
            mOptionMore.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_more_album:
                    showOptionMenu();
                    break;
            }
        }

        @Override
        public void bindData(Album album) {
            if (album != null) {
                mItem = album;
                mTextTitle.setText(mItem.getAlbumName());
                mTextSubTitle.setText(mItem.getArtistName());
                mAlbumArt.setForeground(Drawable.createFromPath(album.getAlbumArt()));
            }
        }

        public void showOptionMenu() {
            PopupMenu popup = new PopupMenu(mContext, mOptionMore);
            popup.inflate(R.menu.options_menu_albums_tab);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.play:
                        return true;
                    case R.id.add_to_queue:
                        return true;
                    case R.id.add_to_playlist:
                        return true;
                    case R.id.edit_info:
                        return true;
                    default:
                        return false;
                }
            });
            popup.show();
        }
    }
}
