package com.pands.dev.pands.sideMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SideDrawerParser {

    public List<SideDrawerValue> parse(JSONObject jsonObject) {

        List<SideDrawerValue> sideDrawerList = new ArrayList<>();

        SideDrawerValue sideDrawerValue;

        try {

            JSONArray postsArray = jsonObject.getJSONArray("product_categories");

            for (int i = 0; i < postsArray.length(); i++) {

                JSONObject posts = postsArray.getJSONObject(i);

                sideDrawerValue = new SideDrawerValue();

                int id = posts.getInt("id");
                sideDrawerValue.setId(id);

                String name = posts.getString("name");
                sideDrawerValue.setName(name);

                String slug = posts.getString("slug");
                sideDrawerValue.setSlug(slug);

                int parent = posts.getInt("parent");
                sideDrawerValue.setParent(parent);

                if (posts.getString("description") != null) {
                    String description = posts.getString("description");
                    sideDrawerValue.setDescription(description);
                }

                String display = posts.getString("display");
                sideDrawerValue.setDisplay(display);

                if (posts.getString("image") != null) {
                    String image = posts.getString("image");
                    sideDrawerValue.setImage(image);
                }

                int count = posts.getInt("count");
                sideDrawerValue.setCount(count);

                sideDrawerList.add(sideDrawerValue);

            }

        } catch (JSONException e) {

            e.printStackTrace();

        }

        return sideDrawerList;

    }
}