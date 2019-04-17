package com.sunasterisk.musixmatch.ui.main.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.ui.base.BaseAdapter;
import com.sunasterisk.musixmatch.ui.base.BaseViewHolder;
import com.sunasterisk.musixmatch.ui.base.OnRecyclerItemClickListener;

/**
 * Created by superme198 on 17,April,2019
 * in Music_Match__1.
 */
public class HomeAdapter extends BaseAdapter<Track, OnRecyclerItemClickListener<Track>, HomeAdapter.HomeViewHolder> {


    public HomeAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflate(R.layout.album_item, viewGroup, false);
        return new HomeViewHolder(mContext, v, mCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bindData(mItems.get(position));
    }

    public static class HomeViewHolder extends BaseViewHolder<Track, OnRecyclerItemClickListener<Track>> {

        private CardView mTrackCard;
        private TextView mTrackName;
        private TextView mTrackArtistName;
        private ImageView mButtonMore;

        public HomeViewHolder(Context context, @NonNull View itemView, OnRecyclerItemClickListener<Track> callback) {
            super(context, itemView, callback);
            itemView.setOnClickListener(this);
            mTrackName = itemView.findViewById(R.id.text_title_album);
            mTrackArtistName = itemView.findViewById(R.id.text_subtitle_album);
            mButtonMore = itemView.findViewById(R.id.button_more_album);
            mTrackCard = itemView.findViewById(R.id.cardview_album_art);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cardview_album_art:
                    break;
            }
        }

        @Override
        public void bindData(Track track) {
            if (track != null) {
                mItem = track;
                mTrackName.setText(mItem.getTrackName());
                mTrackArtistName.setText(mItem.getArtistName());
                hideButtonMore();
            }
        }

        private void hideButtonMore() {
            mButtonMore.setVisibility(View.GONE);
        }
    }

}
