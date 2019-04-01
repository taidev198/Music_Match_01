package com.sunasterisk.musixmatch.ui.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Track;

import java.util.List;

/**
 * Created by superme198 on 21,March,2019
 * in Music_Match_1.
 */
public class ResultsSearchAdapter extends RecyclerView.Adapter<ResultsSearchAdapter.MyViewHolder> {
    private List<Track> mTracks;
    private Context mContext;

    public ResultsSearchAdapter(Context context, List<Track> tracks) {
        mContext = context;
        this.mTracks = tracks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.search_track_card, viewGroup, false);
        return new MyViewHolder(mContext, v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bindView(mTracks.get(i));
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitle;
        private TextView mSubtitle;
        private ImageView mImageMore;
        private Context mContext;

        public MyViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            mContext = context;
            mSubtitle = itemView.findViewById(R.id.text_subtitle_info);
            mTitle = itemView.findViewById(R.id.text_title_info);
            mImageMore = itemView.findViewById(R.id.image_more);
            mImageMore.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        /**
         * listen event when view clicked
         */
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.image_more : showOptionMenu(null);
                    break;
                case R.layout.search_track_card :
                    break;
            }
        }

        public void showOptionMenu(View anchor) {
            anchor = mImageMore;
            PopupMenu popup = new PopupMenu(this.mContext, anchor);
            popup.inflate(R.menu.options_menu_search);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.show_lyrics:
                        return true;
                    case R.id.go_to_artist:
                        return true;
                    default:
                        return false;
                }
            });
            popup.show();
        }

        public void bindView(Track track) {
            mTitle.setText(track.getTrackName());
            mSubtitle.setText(track.getAlbumName());
        }

    }
}
