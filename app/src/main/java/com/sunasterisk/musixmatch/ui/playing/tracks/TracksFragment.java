package com.sunasterisk.musixmatch.ui.playing.tracks;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.repository.TrackRepository;
import com.sunasterisk.musixmatch.data.source.local.TrackLocalDataSource;
import com.sunasterisk.musixmatch.ui.base.BaseFragment;
import com.sunasterisk.musixmatch.ui.base.OnRecyclerItemClickListener;
import com.sunasterisk.musixmatch.ui.playing.PlayingActivity;

import java.util.ArrayList;
import java.util.List;

public class TracksFragment extends BaseFragment implements TracksContract.View,
        OnRecyclerItemClickListener<Track> {
    protected RecyclerView mRecyclerView;
    protected TracksContract.Presenter mPresenter;
    protected TracksAdapter mAdapter;
    protected OnGetTracksListener mCallback;
    protected List<Track> mTracks;
    public static final String ARGUMENT_TRACKS = "ARGUMENT_TRACKS";

    public static TracksFragment newInstance(List<Track> tracks) {
        TracksFragment fragment = new TracksFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARGUMENT_TRACKS, (ArrayList<? extends Parcelable>) tracks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnGetTracksListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnTrackClickListener");
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_tracks;
    }

    @Override
    public void initComponents(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_tracks);
    }

    @Override
    protected void initData() {
        Bundle args = getArguments();
        mAdapter = new TracksAdapter(getContext());
        mAdapter.setCallBack(this);
        if (args != null) {
            mTracks = args.getParcelableArrayList(ARGUMENT_TRACKS);
            mAdapter.setItems(mTracks);
            mRecyclerView.setAdapter(mAdapter);
        }

    }

    @Override
    public void showLocalTracks(List<Track> tracks) {

    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClicked(Track item) {
        mCallback.onPlayed(item);
        mCallback.onGetTracksSuccess(mTracks);
    }

    @Override
    public void onItemClicked(Track item, List<Track> items) {
        mCallback.onPlayed(item);
    }

    public interface OnGetTracksListener {
        void onPlayed(Track track);

        void onGetTracksSuccess(List<Track> tracks);
    }
}
