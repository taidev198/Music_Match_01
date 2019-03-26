package com.sunasterisk.musixmatch.ui.playing.tracks;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Track;

import java.util.List;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder> {
    private List<Track> mTracks;
    private OnItemClickListener mCallback;

    public TracksAdapter(List<Track> tracks, OnItemClickListener callback) {
        mTracks = tracks;
        mCallback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_track, viewGroup, false);
        return new ViewHolder(itemView, mCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mTracks.get(i));
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextTrackName;
        private TextView mTextArtistName;
        private Track mTrack;
        private OnItemClickListener mCallback;

        public ViewHolder(@NonNull View itemView, OnItemClickListener callback) {
            super(itemView);
            mCallback = callback;
            mTextTrackName = itemView.findViewById(R.id.text_track_name);
            mTextArtistName = itemView.findViewById(R.id.text_artist_name);
            itemView.setOnClickListener(this);
        }

        void bindData(Track track) {
            if (track != null) {
                mTrack = track;
                mTextTrackName.setText(track.getTrackName());
                mTextArtistName.setText(track.getArtistName());
            }
        }

        @Override
        public void onClick(View v) {
            if (mCallback != null) {
                mCallback.onTrackClick(mTrack);
            }
        }
    }

    public interface OnItemClickListener {
        void onTrackClick(Track track);
    }
}
