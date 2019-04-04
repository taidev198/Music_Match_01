package com.sunasterisk.musixmatch.utils;

public class StringUtils {
    public static String convertMilliSeconds(long milliSeconds) {
        String finalTimerString = "";
        String secondsString = "";
        String minutesSting = "";
        int hours = (int) (milliSeconds / (1000 * 60 * 60));
        int minutes = (int) (milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        if (hours > 0) {
            if (hours < 10)
                finalTimerString = "0" + hours + ":";
            else finalTimerString = hours + ":";
        }
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }
        if (minutes < 10) {
            minutesSting = "0" + minutes;
        } else {
            minutesSting = "" + minutes;
        }
        finalTimerString = finalTimerString + minutesSting + ":" + secondsString;
        return finalTimerString;
    }
}
