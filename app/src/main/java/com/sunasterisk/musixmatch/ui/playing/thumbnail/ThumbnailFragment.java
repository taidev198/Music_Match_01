package com.sunasterisk.musixmatch.ui.playing.thumbnail;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Album;
import com.sunasterisk.musixmatch.data.repository.AlbumRepository;
import com.sunasterisk.musixmatch.data.source.local.AlbumLocalDataSource;
import com.sunasterisk.musixmatch.ui.base.BaseFragment;

import java.util.List;

public class ThumbnailFragment extends BaseFragment implements ThumbnailContract.View {
    private static ThumbnailFragment sInstance;
    private ImageView mImageTrack;
    private ThumbnailContract.Presenter mPresenter;
    private OnGetAlbums mCallback;

    public static ThumbnailFragment getInstance() {
        if (sInstance == null) {
            sInstance = new ThumbnailFragment();
        }
        return sInstance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnGetAlbums) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnGetAlbums");
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_thumbnail;
    }

    @Override
    protected void initComponents(View view) {
        mImageTrack = view.findViewById(R.id.image_track);
        mPresenter = new ThumbnailPresenter(AlbumRepository.getInstance(AlbumLocalDataSource.getInstance(getActivity())), this);
    }

    @Override
    protected void initData() {
        mCallback.onGetAlbumsSuccess(mPresenter.getAlbums());
    }

    public void setImageTrack(String albumArt) {
        mImageTrack.setImageDrawable(Drawable.createFromPath(albumArt));
    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCallback = null;
    }

    public interface OnGetAlbums {
        void onGetAlbumsSuccess(List<Album> albums);
    }
}
