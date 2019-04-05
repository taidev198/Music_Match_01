package com.sunasterisk.musixmatch.utils;

import java.util.concurrent.TimeUnit;

public class StringUtils {
    public static String convertMilliSeconds(long milliSeconds) {
        String time = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds))
        );
        return time;
    }
}
