package com.sunasterisk.musixmatch.ui.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/**
 * Created by superme198 on 03,April,2019
 * in Music_Match__1.
 */
public abstract class BaseViewHolder<T, L extends BaseRecyclerListener> extends RecyclerView.ViewHolder implements View.OnClickListener{

    protected T mItem;
    protected L mCallback;
    protected Context mContext;

    public BaseViewHolder(Context context, @NonNull View itemView, L callback) {
        super(itemView);
        mContext = context;
        mCallback = callback;
        itemView.setOnClickListener(this);
    }

    @Override
    public abstract void onClick(View v);

    public abstract void bindData(T t);
}
