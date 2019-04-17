package com.sunasterisk.musixmatch.ui.main.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.repository.PlaylistRepository;
import com.sunasterisk.musixmatch.data.source.local.PlaylistLocalDataSource;
import com.sunasterisk.musixmatch.ui.base.BaseFragment;
import com.sunasterisk.musixmatch.ui.base.OnRecyclerItemClickListener;

import java.util.List;

public class HomeFragment extends BaseFragment implements HomeContract.View, OnRecyclerItemClickListener<Track> {

    private RecyclerView mRecentlyPlayedRecyclerView;
    private RecyclerView mFavoriteRecyclerView;
    private HomeAdapter mRecentlyPlayedAdapter;
    private HomeAdapter mFavoriteAdapter;
    private HomeContract.Presenter mPresenter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initComponents(View view) {
        mRecentlyPlayedRecyclerView = view.findViewById(R.id.recycler_recently_played);
        mFavoriteRecyclerView = view.findViewById(R.id.recycler_favor_songs);
    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecentlyPlayedRecyclerView.setLayoutManager(layoutManager);
        mFavoriteRecyclerView.setLayoutManager(layoutManager1);
        PlaylistRepository repository = PlaylistRepository.getsInstance(PlaylistLocalDataSource.getsInstance(getContext()));
        mPresenter = new HomePresenter(repository, this);
        mPresenter.getFavouriteTracks();
        mPresenter.getRecentlyPlayedTracks();
    }

    @Override
    public void showRecentlyPlayed(List<Track> tracks) {

        mRecentlyPlayedAdapter = new HomeAdapter(getContext());
        mRecentlyPlayedAdapter.setItems(tracks);
        mRecentlyPlayedAdapter.setCallBack(this);
        mRecentlyPlayedRecyclerView.setAdapter(mRecentlyPlayedAdapter);
    }

    @Override
    public void showFavourite(List<Track> tracks) {
        mFavoriteAdapter = new HomeAdapter(getContext());
        mFavoriteAdapter.setItems(tracks);
        mFavoriteAdapter.setCallBack(this);
        mFavoriteRecyclerView.setAdapter(mFavoriteAdapter);
    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(Track item) {

    }

    @Override
    public void onItemClicked(long id) {

    }
}
