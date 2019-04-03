package com.sunasterisk.musixmatch.ui.playing.tracks.trackstab;

import android.view.View;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.repository.TrackRepository;
import com.sunasterisk.musixmatch.data.source.local.TrackLocalDataSource;
import com.sunasterisk.musixmatch.ui.base.OnRecyclerItemClickListener;
import com.sunasterisk.musixmatch.ui.playing.tracks.TracksFragment;

import java.util.List;

/**
 * Created by superme198 on 01,April,2019
 * in Music_Match__1.
 */
public class TracksTabFragment extends TracksFragment implements OnRecyclerItemClickListener<Track> {

    protected TracksTabAdapter mAdapter;
    @Override
    public int getLayoutResource() {
        return R.layout.fragment_tracks_tab;
    }

    @Override
    protected void initData() {
        mPresenter = new TracksTabPresenter(
                TrackRepository.getInstance(TrackLocalDataSource.getInstance(getActivity())),
                this);
        mPresenter.getLocalTracks();
    }

    @Override
    public void initComponents(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_tracks_tab);
    }

    @Override
    public void showLocalTracks(List<Track> tracks) {
        mAdapter = new TracksTabAdapter(getContext());
        mAdapter.setCallBack(this);
        mAdapter.setItems(tracks);
        mRecyclerView.setAdapter(mAdapter);

    }

}
