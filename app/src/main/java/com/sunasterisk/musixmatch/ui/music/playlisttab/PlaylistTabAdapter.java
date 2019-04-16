package com.sunasterisk.musixmatch.ui.music.playlisttab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Playlist;
import com.sunasterisk.musixmatch.ui.base.BaseAdapter;
import com.sunasterisk.musixmatch.ui.base.BaseTrackViewHolder;
import com.sunasterisk.musixmatch.ui.base.OnRecyclerItemClickListener;
import com.sunasterisk.musixmatch.utils.PlaylistUtils;

/**
 * Created by superme198 on 14,April,2019
 * in Music_Match__1.
 */
public class PlaylistTabAdapter extends BaseAdapter<Playlist,
        OnRecyclerItemClickListener<Playlist>,
        PlaylistTabAdapter.PlaylistViewHolder> {

    public PlaylistTabAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflate(R.layout.item_track, viewGroup, false);
        return new PlaylistViewHolder(mContext, v, mCallback);
    }

    static class PlaylistViewHolder extends BaseTrackViewHolder<Playlist, OnRecyclerItemClickListener<Playlist>> {

        private CardView mPlaylistArt;
        private ImageView mTrackArt;
        private String mSubTitle;

        public PlaylistViewHolder(Context context,
                                  @NonNull View itemView, OnRecyclerItemClickListener<Playlist> callback) {
            super(context, itemView, callback);
            itemView.setOnClickListener(this);
            mPlaylistArt = itemView.findViewById(R.id.card_view_logo_track);
            mTrackArt = itemView.findViewById(R.id.image_track_art);
        }

        @Override
        public void showOptionMenu() {
            PopupMenu popup = new PopupMenu(mContext, mOptionMore);
            popup.inflate(R.menu.options_menu_playlist_tab);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.play:
                        return true;
                    case R.id.add_to_queue:
                        return true;
                    case R.id.delete:
                        PlaylistUtils.deletePlaylist(mContext, mItem.getId());
                        return true;
                    case R.id.rename_playlist:
                        PlaylistUtils.renamePlaylist(mContext, mItem.getId(), "");
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
        }

        @Override
        public void bindData(Playlist playlist) {
            if (playlist != null) {
                mTextTitle.setText(playlist.getName());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(getNumberAlbumTrackStr(playlist.getSongsCount()));
                mSubTitle = String.format(stringBuilder.toString(), playlist.getSongsCount());
                mTextSubTitle.setText(mSubTitle);
                showPlaylistArt();
                hideTrackArt();
            }
        }

        public void showPlaylistArt() {
            mPlaylistArt.setVisibility(View.VISIBLE);
            mPlaylistArt.setForeground((mContext.getDrawable(R.drawable.ic_playlist)));
        }

        public void hideTrackArt() {
            mTrackArt.setVisibility(View.GONE);
        }

        private String getNumberAlbumTrackStr(int count) {
            return count > 1 ? mContext.getResources().getString(R.string.text_tracks) :
                    mContext.getResources().getString(R.string.text_track);
        }
    }
}
