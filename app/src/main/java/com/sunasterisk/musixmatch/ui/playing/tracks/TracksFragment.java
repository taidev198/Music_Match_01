package com.sunasterisk.musixmatch.ui.playing.tracks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.repository.TrackRepository;
import com.sunasterisk.musixmatch.data.source.local.TrackLocalDataSource;
import com.sunasterisk.musixmatch.ui.base.BaseFragment;

import java.util.List;

public class TracksFragment extends BaseFragment implements TracksContract.View, TracksAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private TracksContract.Presenter mPresenter;
    private TracksAdapter mAdapter;
    private OnTrackClickListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnTrackClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnTrackClickListener");
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_tracks;
    }

    @Override
    public void initComponents(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_tracks);
        mPresenter = new TracksPresenter(TrackRepository.getInstance(TrackLocalDataSource.getInstance(getActivity())), this);
    }

    @Override
    protected void initData() {
        mPresenter.getLocalTracks();
    }

    @Override
    public void showLocalTracks(List<Track> tracks) {
        mAdapter = new TracksAdapter(tracks, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTrackClick(Track track) {
        mCallback.onPlayed(track);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
    }

    public interface OnTrackClickListener {
        void onPlayed(Track track);
    }
}
