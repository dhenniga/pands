package com.pands.dev.pands.product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductParser {

    public List<ProductValue> parse(JSONObject jsonObject) {

        List<ProductValue> postList = new ArrayList<>();

        ProductValue productValue;

        try {

            JSONArray postsArray = jsonObject.getJSONArray("products");

            for (int i = 0; i < postsArray.length(); i++) {

                JSONObject posts = postsArray.getJSONObject(i);

                productValue = new ProductValue();

                int id = posts.getInt("price");
                String title = posts.getString("title");
                String featured_src = posts.getString("featured_src");

                productValue.setId(id);
                productValue.setTitle(title);
                productValue.setFeatured_src(featured_src);

                postList.add(productValue);
            }

        } catch (JSONException e) {

            e.printStackTrace();

        }

        return postList;

    }
}