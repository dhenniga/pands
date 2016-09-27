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

            JSONArray postsArray = new JSONArray(postList);

            for (int i = 0; i < postsArray.length(); i++) {

                JSONObject posts = postsArray.getJSONObject(i);
//                JSONObject post = posts.getJSONObject("");

                postValue = new PostValue();

                String id = posts.getString("id");
                String title = posts.getString("title");

                postValue.setID(id);
                postValue.setTitle(title);

                postList.add(postValue);
            }

        } catch (JSONException e) {

            e.printStackTrace();

        }

        return postList;

    }

}


//JSONArray jsonarray = new JSONArray(json);
//
//for (int i = 0; i < jsonarray.length(); i++) {
//
//        JSONObject jsonobj = jsonarray.getJSONObject(i);
//
//        System.out.println("categoryId : " + i + " = " + jsonobj.getString("categoryId"));
//        System.out.println("Title : " + i + " = " + jsonobj.getString("Title"));
//        System.out.println("songs : " + i + " = " + jsonobj.getString("songs"));
//        }