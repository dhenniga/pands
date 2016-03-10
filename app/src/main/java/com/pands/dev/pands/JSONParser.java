package com.pands.dev.pands;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    public List<PostValue> parse(JSONObject jsonObject) {
        List<PostValue> postList = new ArrayList<>();
        PostValue postValue;
        try {
            JSONArray postsArray = jsonObject.getJSONArray("");

            for (int i = 0; i < postsArray.length(); i++) {
                JSONObject posts = postsArray.getJSONObject(i);
                JSONObject post = posts.getJSONObject("images");

                postValue = new PostValue();

                String id = post.getString("id");

                postValue.setID(id);

                postList.add(postValue);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postList;
    }

}
