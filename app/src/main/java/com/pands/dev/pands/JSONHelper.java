package com.pands.dev.pands;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class JSONHelper {

    private static final String URL_BASE = "https://www.primpandstyle.com/wc-api/v3/";
    private static final String URL_MAIN_1 = "products";
    private static final String URL_FILTER_LIMIT_20 = "filter[limit]=20";
    private static final String URL_PAGE = "page=";
    private static final String URL_PRODUCT_CATEGORIES = "products/categories";
    private static final String URL_BASE_FIELDS = "fields=id,title,price,regular_price,visible,sale_price,stock_quantity,featured_src,short_description,on_sale,categories,tags,images";

    private static final String URL_CONSUMER_KEY = "consumer_key=ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8";
    private static final String URL_CONSUMER_SECRET = "consumer_secret=cs_fe0ba2a0f443603553f9e30b0112644d03ff22ac";

    private static final String TAG = JSONHelper.class.getSimpleName();
    private JSONObject mJsonObject = null;
    private String json = "";



    /**
     *
     * @param filter
     * @param page
     * @return
     */
    public JSONObject getJSONFromUrl(String filter, int page) {
        try {
            URL url = new URL(URL_BASE + URL_MAIN_1 + "?" + URL_BASE_FIELDS + "&" + filter + "&" + URL_FILTER_LIMIT_20 + "&" + URL_PAGE + page + "&" + URL_CONSUMER_KEY + "&" + URL_CONSUMER_SECRET);
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
            mJsonObject = new JSONObject(json);
        } catch (JSONException e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return mJsonObject;
    }



    /**
     *
     * @return
     */
    public JSONObject getJSONforDrawer() {
        try {
            URL url = new URL(URL_BASE + URL_PRODUCT_CATEGORIES + "?" + URL_CONSUMER_KEY + "&" + URL_CONSUMER_SECRET);
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
            mJsonObject = new JSONObject(json);
        } catch (JSONException e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return mJsonObject;
    }

}
