package com.sunasterisk.musixmatch.data.source;

public interface Callback<T> {
    void getDataSuccess(T data);

    void getDataFailure(Exception e);
}
