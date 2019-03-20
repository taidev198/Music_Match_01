package com.framgia.musixmatch.data.source;

public interface CallBack<T> {
    void getDataSuccess(T data);
    void getDataFailure(Exception e);
}
