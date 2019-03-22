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
    private static TracksFragment mInstance;
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
            throw new ClassCastException(context.toString() + " must implement OnTrackClickListener.");
        }
    }

    public static TracksFragment getInstance() {
        if (mInstance == null) {
            mInstance = new TracksFragment();
        }
        return mInstance;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_tracks;
    }

    @Override
    public void initComponents(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mPresenter = new TracksPresenter(TrackRepository.getInstance(new TrackLocalDataSource(getActivity())), this);

    }

    @Override
    protected void initData() {
        mPresenter.getLocalTracks();
    }

    @Override
    public void showLocalTracks(List<Track> tracks) {
        mAdapter = new TracksAdapter(getActivity(), tracks);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setCallback(this);
    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTrackClickListener(Track track) {
        mCallback.onTrackClickListener(track);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
    }

    public interface OnTrackClickListener {
        void onTrackClickListener(Track track);
    }
}
