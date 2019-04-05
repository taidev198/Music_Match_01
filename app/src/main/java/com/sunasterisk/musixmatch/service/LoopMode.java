package com.sunasterisk.musixmatch.service;

import android.support.annotation.IntDef;

import static com.sunasterisk.musixmatch.service.LoopMode.LOOP_ALL;
import static com.sunasterisk.musixmatch.service.LoopMode.LOOP_OFF;
import static com.sunasterisk.musixmatch.service.LoopMode.LOOP_ONE;
import static com.sunasterisk.musixmatch.service.LoopMode.SHUFFLE_ON;

@IntDef({LOOP_OFF, LOOP_ONE, LOOP_ALL, SHUFFLE_ON})
public @interface LoopMode {
    int LOOP_OFF = 0;
    int LOOP_ONE = 1;
    int LOOP_ALL = 2;
    int SHUFFLE_ON = 3;
}
