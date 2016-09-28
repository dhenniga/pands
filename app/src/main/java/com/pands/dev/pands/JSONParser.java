package com.pands.dev.pands;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    public List<ProductValue> parse(JSONObject jsonObject) {

        List<ProductValue> postList = new ArrayList<>();

        ProductValue productValue;

        try {

            JSONArray postsArray = jsonObject.getJSONArray("products");

            for (int i = 0; i < postsArray.length(); i++) {



                JSONObject posts = postsArray.getJSONObject(i);
//                JSONObject post = posts.getJSONObject("product_categories");

                productValue = new ProductValue();

                int id = posts.getInt("id");
                String title = posts.getString("permalink");

                productValue.setId(id);
                productValue.setTitle(title);

                postList.add(productValue);
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