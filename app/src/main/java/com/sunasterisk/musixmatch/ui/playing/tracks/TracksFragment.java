package com.sunasterisk.musixmatch.ui.playing.tracks;

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
        mRecyclerView = view.findViewById(R.id.recycler_tracks);
        mPresenter = new TracksPresenter(TrackRepository.getInstance(new TrackLocalDataSource(getActivity())), this);
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
    public void onTrackClickListener(Track track) {
    }
}
