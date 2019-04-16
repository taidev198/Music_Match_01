package com.sunasterisk.musixmatch.ui.music.playlisttab;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Playlist;
import com.sunasterisk.musixmatch.data.repository.PlaylistRepository;
import com.sunasterisk.musixmatch.data.source.local.PlaylistLocalDataSource;
import com.sunasterisk.musixmatch.ui.base.BaseFragment;
import com.sunasterisk.musixmatch.ui.base.OnRecyclerItemClickListener;

import java.util.List;

/**
 * Created by superme198 on 14,April,2019
 * in Music_Match__1.
 */
public class PlaylistTabFragment extends BaseFragment implements PlaylistTabContract.View,
        OnRecyclerItemClickListener<Playlist> {

    private RecyclerView mRecyclerView;
    private PlaylistTabContract.Presenter mPresenter;
    private PlaylistTabAdapter mAdapter;

    public static PlaylistTabFragment newInstance() {
        return new PlaylistTabFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_tracks_tab;
    }

    @Override
    protected void initComponents(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_tracks_tab);
    }

    @Override
    protected void initData() {
        PlaylistRepository repository = PlaylistRepository.getsInstance(
                PlaylistLocalDataSource.getsInstance(getContext()));
        mPresenter = new PlaylistTabPresenter(repository, this);
        mPresenter.getLocalPlaylist();
    }

    @Override
    public void showPlaylist(List<Playlist> playlists) {
        mAdapter = new PlaylistTabAdapter(getContext());
        mAdapter.setCallBack(this);
        mAdapter.setItems(playlists);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClicked(Playlist item) {

    }

    @Override
    public void onItemClicked(long id) {

    }
}
