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


//                String title = posts.getString("title");
//                int id = posts.getInt("id");
//                String created_at = posts.getString("created_at");
//                String updated_at = posts.getString("updated_at");
//                String type = posts.getString("type");
//                String status = posts.getString("status");
//                Boolean downloadable = posts.getBoolean("downloadable");
//                Boolean virtual = posts.getBoolean("virtual");
//                String permalink = posts.getString("permalink");
//                String sku = posts.getString("sku");
//                int price = posts.getInt("price");
//                int regular_price = posts.getInt("regular_price");
//                int sale_price = posts.getInt("sale_price");
//                String price_html = posts.getString("price_html");
//                Boolean taxable = posts.getBoolean("taxable");
//                String tax_status = posts.getString("tax_status");
//                String tax_class = posts.getString("tax_class");
//                Boolean managing_stock = posts.getBoolean("managing_stock");
//                int stock_quantity = posts.getInt("stock_quantity");
//                Boolean in_stock = posts.getBoolean("in_stock");
//                Boolean backorders_allowed = posts.getBoolean("backorders_allowed");
//                Boolean backordered = posts.getBoolean("backordered");
//                Boolean sold_individually = posts.getBoolean("sold_individually");
//                Boolean purchaseable = posts.getBoolean("purchaseable");
//                Boolean featured = posts.getBoolean("featured");
//                Boolean visible = posts.getBoolean("visible");
//                String catalog_visibility = posts.getString("catalog_visibility");
//                Boolean on_sale = posts.getBoolean("on_sale");
//                String product_url = posts.getString("product_url");
//                String button_text = posts.getString("button_text");
//                String weight = posts.getString("weight");
//                Boolean shipping_required = posts.getBoolean("shipping_required");
//                Boolean shipping_taxable = posts.getBoolean("shipping_taxable");
//                String shipping_class = posts.getString("shipping_class");
////                int shipping_class_id = posts.getInt("shipping_class_id");
//                String description = posts.getString("description");
//                String short_description = posts.getString("short_description");
//                Boolean reviews_allowed = posts.getBoolean("reviews_allowed");
//                Double average_rating = posts.getDouble("average_rating");
//                int rating_count = posts.getInt("rating_count");
//                String featured_src = posts.getString("featured_src");
//                int download_limit = posts.getInt("download_limit");
//                String download_expiry = posts.getString("download_expiry");
//                String download_type = posts.getString("download_type");
//                String purchase_note = posts.getString("purchase_note");
//                int total_sales = posts.getInt("total_sales");
//                int menu_order = posts.getInt("menu_order");
//
//
//                productValue.setTitle(title);
//                productValue.setId(id);
//                productValue.setCreated_at(created_at);
//                productValue.setUpdated_at(updated_at);
//                productValue.setType(type);
//                productValue.setStatus(status);
//                productValue.setDownloadable(downloadable);
//                productValue.setVirtual(virtual);
//                productValue.setPermalink(permalink);
//                productValue.setSku(sku);
//                productValue.setPrice(price);
//                productValue.setRegular_price(regular_price);
////              int sale_price = posts.getInt("sale_price");
//                productValue.setPrice_html(price_html);
//                productValue.setTax_status(tax_status);
//                productValue.setTax_class(tax_class);
//                productValue.setManaging_stock(managing_stock);
//                productValue.setStock_quantity(stock_quantity);
//                productValue.setIn_stock(in_stock);
//                productValue.setBackorders_allowed(backorders_allowed);
//                productValue.setBackordered(backordered);
//                productValue.setSold_individually(sold_individually);
//                productValue.setPurchaseable(purchaseable);
//                productValue.setFeatured(featured);
//                productValue.setVisible(visible);
//                productValue.setCatalog_visibility(catalog_visibility);
//                productValue.setOn_sale(on_sale);
//                productValue.setProduct_url(product_url);
//                productValue.setButton_text(button_text);
//                productValue.setWeight(weight);
//                productValue.setShipping_required(shipping_required);
//                productValue.setShipping_taxable(shipping_taxable);
//                productValue.setShipping_class(shipping_class);
////                int shipping_class_id = posts.getInt("shipping_class_id");
//                productValue.setDescription(description);
//                productValue.setShort_description(short_description);
//                productValue.setReviews_allowed(reviews_allowed);
//                productValue.setAverage_rating(average_rating);
//                productValue.setRating_count(rating_count);
//                productValue.setFeatured_src(featured_src);
//                productValue.setDownload_limit(download_limit);
//                productValue.setDownload_expiry(download_expiry);
//                productValue.setDownload_type(download_type);
//                productValue.setPurchase_note(purchase_note);
//                productValue.setTotal_sales(total_sales);
//                productValue.setMenu_order(menu_order);


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


                if (posts.getBoolean("visible") != false) {
                    postList.add(productValue);
                }

            }

        } catch (JSONException e) {

            e.printStackTrace();

        }

        return postList;

    }
}