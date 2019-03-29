package com.sunasterisk.musixmatch.ui.playing.thumbnail;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sunasterisk.musixmatch.R;
import com.sunasterisk.musixmatch.data.model.Album;
import com.sunasterisk.musixmatch.data.model.Track;
import com.sunasterisk.musixmatch.data.repository.AlbumRepository;
import com.sunasterisk.musixmatch.data.source.local.AlbumLocalDataSource;
import com.sunasterisk.musixmatch.ui.base.BaseFragment;

import java.util.List;

public class ThumbnailFragment extends BaseFragment implements ThumbnailContract.View {
    private ImageView mImageTrack;
    private ThumbnailContract.Presenter mPresenter;
    private OnGetAlbumsListener mCallback;
    private Track mTrack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnGetAlbumsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnGetAlbumsListener");
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_thumbnail;
    }

    @Override
    protected void initComponents(View view) {
        mImageTrack = view.findViewById(R.id.image_track);
    }

    @Override
    protected void initData() {
        mPresenter = new ThumbnailPresenter(AlbumRepository
                .getInstance(AlbumLocalDataSource
                        .getInstance(getActivity())), this);
        mPresenter.getAlbums();
    }

    public void setImageTrack(List<Album> albums) {
        for (Album album : albums) {
            if (album.getAlbumId() == mTrack.getAlbumId()) {
                mImageTrack.setImageDrawable(Drawable.createFromPath(album.getAlbumArt()));
            }
        }
    }

    @Override
    public void showAlbums(List<Album> albums) {
        mCallback.onGetAlbumsSuccess(albums);
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

    public void getTrack(Track track) {
        mTrack = track;
    }

    public interface OnGetAlbumsListener {
        void onGetAlbumsSuccess(List<Album> albums);
    }
}
