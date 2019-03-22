package com.sunasterisk.musixmatch.ui.playing.tracks;

import android.content.Context;
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
    private LayoutInflater mInflater;
    private OnItemClickListener mCallback;

    public TracksAdapter(Context context, List<Track> tracks) {
        mTracks = tracks;
        mInflater = LayoutInflater.from(context);
    }

    public void setCallback(OnItemClickListener callback) {
        mCallback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.item_track, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(mTracks.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) {
                    mCallback.onTrackClickListener(mTracks.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextTrackName;
        private TextView mTextArtistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextTrackName = itemView.findViewById(R.id.text_track_name);
            mTextArtistName = itemView.findViewById(R.id.text_artist_name);
        }

        void bindData(Track track) {
            if (track != null) {
                mTextTrackName.setText(track.getTrackName());
                mTextArtistName.setText(track.getArtistName());
            }
        }
    }

    public interface OnItemClickListener {
        void onTrackClickListener(Track track);
    }
}
