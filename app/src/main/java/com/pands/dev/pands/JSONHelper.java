package com.pands.dev.pands;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class JSONHelper {
//    private static final String URL_MAIN = "https://www.primpandstyle.com/wc-api/v3/products?consumer_key=ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8&consumer_secret=cs_fe0ba2a0f443603553f9e30b0112644d03ff22ac";


    private static final String URL_BASE = "https://www.primpandstyle.com/wc-api/v3/";
    private static final String URL_MAIN_1 = "products";
    private static final String URL_FILTER = "filter[limit]=90";
    private static final String URL_FILTER_BAGS = "filter[categories]=handbags";

    private static final String URL_CONSUMER_KEY = "consumer_key=ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8";
    private static final String URL_CONSUMER_SECRET = "consumer_secret=cs_fe0ba2a0f443603553f9e30b0112644d03ff22ac";

    private static final String QUERY = URL_BASE + URL_MAIN_1 + "?" + URL_FILTER_BAGS + "&" +URL_CONSUMER_KEY + "&" + URL_CONSUMER_SECRET;


    private static final String TAG = JSONHelper.class.getSimpleName();
    private JSONObject mJsonObject = null;
    private String json = "";

    public JSONObject getJSONFromUrl() {
        try {
            URL url = new URL(QUERY);
            URLConnection urlConnection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line).append("\n");
            }
            json = builder.toString();
            Log.i(TAG, json);
            in.close();
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            // Convert the JSON String from InputStream to a JSONObject
            mJsonObject = new JSONObject(json);
        } catch (JSONException e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return mJsonObject;
    }
}
