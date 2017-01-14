package com.pands.dev.pands.product;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

                String title = posts.getString("title");
                productValue.setTitle(title);

                int id = posts.getInt("id");
                productValue.setId(id);

                int price = posts.getInt("price");
                Log.i("price", ((String.valueOf(price))));
                productValue.setPrice(price);

                if (posts.getBoolean("on_sale")) {
                    int sale_price = posts.getInt("regular_price");
                    Log.i("sale_price", ((String.valueOf(sale_price))));
                    productValue.setSale_price(sale_price);
                }

                int stock_quantity = posts.getInt("stock_quantity");
                Log.i("stock_quantity", ((String.valueOf(stock_quantity))));
                productValue.setStock_quantity(stock_quantity);

                Boolean visible = posts.getBoolean("visible");
                Log.i("visible", ((String.valueOf(visible))));
                productValue.setVisible(visible);

                String featured_src = posts.getString("featured_src");
                productValue.setFeatured_src(featured_src);

                String short_description = posts.getString("short_description");
                productValue.setShort_description(short_description);

                Boolean on_sale = posts.getBoolean("on_sale");
                productValue.setOn_sale(on_sale);

                JSONArray categoriesArray = posts.getJSONArray("categories");
                ArrayList<String> categoriesList = new ArrayList<String>();
                for( int j=0; j<categoriesArray.length(); j++)
                {
                    String item = categoriesArray.getString(j);
                    categoriesList.add(item);
                }
                String categoriesProcessed = categoriesList.toString().replaceAll("\\[", "").replaceAll("\\]","");
                productValue.setCategories(categoriesProcessed);


                JSONArray tagsArray = posts.getJSONArray("tags");
                ArrayList<String> tagsList = new ArrayList<String>();
                for( int j=0; j<tagsArray.length(); j++)
                {
                    String item = tagsArray.getString(j);
                    tagsList.add(item);
                }
                String tagsProcessed = tagsList.toString().replaceAll("\\[", "").replaceAll("\\]","");
                productValue.setTags(tagsProcessed);



                JSONArray imagesArray = posts.getJSONArray("images");
                ArrayList<String> imageList = new ArrayList<String>();
                for( int j=0; j<imagesArray.length(); j++)
                {
                    JSONObject json_obj = imagesArray.getJSONObject(j);
                    String name = json_obj.getString("src");
                    imageList.add(name);
                }
                String imagesProcessed = imageList.toString().replaceAll("\\[", "").replaceAll("\\]","");
                productValue.setImages(imagesProcessed);


//                if (posts.getBoolean("visible") != false ) {
//                if (posts.getInt("stock_quantity") > 0) {
                    postList.add(productValue);
//                }

            }

        } catch (JSONException e) {

            e.printStackTrace();

        }

        return postList;

    }
}