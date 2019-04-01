package com.sunasterisk.musixmatch.utils;

import com.sunasterisk.musixmatch.data.model.Track;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by superme198 on 21,March,2019
 * in Music_Match_1.
 */
public class TrackLoaderUtils {

    public static String getJSONFromAPI(String urlString) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(urlString);
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(Constants.METHOD_GET);
        httpURLConnection.setConnectTimeout(Constants.CONNECTION_TIMEOUT);
        httpURLConnection.setReadTimeout(Constants.READ_TIMEOUT);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        String jsonString = stringBuilder.toString();
        httpURLConnection.disconnect();
        return jsonString;
    }

    public static List<Track> getTracksFromJSON(String jsonString) throws JSONException {
        List<Track> tracks = new ArrayList<>();
        JSONObject root = new JSONObject(jsonString);
        JSONArray songsCollection = root.getJSONObject(Track.JSonKey.MESSAGE)
                                        .getJSONObject(Track.JSonKey.BODY)
                                        .getJSONArray(Track.JSonKey.TRACK_LIST);
        for (int i = 0; i < songsCollection.length(); i++) {
            JSONObject jsonObject = songsCollection.getJSONObject(i)
                                                   .getJSONObject(Track.JSonKey.TRACK);
            Track track = new Track(jsonObject);
            tracks.add(track);
        }
        return tracks;
    }
}
