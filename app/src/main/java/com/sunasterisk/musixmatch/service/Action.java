package com.sunasterisk.musixmatch.service;

import android.support.annotation.StringDef;

import static com.sunasterisk.musixmatch.service.Action.CLOSE;
import static com.sunasterisk.musixmatch.service.Action.NEXT;
import static com.sunasterisk.musixmatch.service.Action.TOGGLE_STATE;

@StringDef({TOGGLE_STATE, NEXT, CLOSE})
public @interface Action {
    String TOGGLE_STATE = "TOGGLE_STATE";
    String NEXT = "NEXT";
    String CLOSE = "CLOSE";
}
