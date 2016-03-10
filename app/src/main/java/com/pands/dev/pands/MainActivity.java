package com.pands.dev.pands;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MenuFunctions menuFunctions;
    private Gson mGson;
    List<Product> productsList= new ArrayList<Product>();

    String list_20_products = "https://www.primpandstyle.com/wc-api/v3/products?filter[limit]=1&consumer_key=ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8&consumer_secret=cs_fe0ba2a0f443603553f9e30b0112644d03ff22ac";

    //https://www.primpandstyle.com/wc-api/v3/products?filter[limit]=1&consumer_key=ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8&consumer_secret=cs_fe0ba2a0f443603553f9e30b0112644d03ff22ac";
    //https://www.primpandstyle.com/wc-api/v3/products/categories&consumer_key=ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8&consumer_secret=cs_fe0ba2a0f443603553f9e30b0112644d03ff22ac

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGson = new Gson();

    }

    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();

        ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(this,android.R.layout.simple_list_item_1,fetchProducts(list_20_products));
        ListView listViewTest = (ListView) findViewById(R.id.listViewTest);
        listViewTest.setAdapter(adapter);


//        fetchProducts(list_20_products);



    }

    public static class GetProduct {
        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {

            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

    /**
     *
     * @return - Returns a list of products
     */
    public List<Product> fetchProducts(String JSONInputString) {
        List<Product> productsList = new ArrayList<>();

        GetProduct getProduct = new GetProduct();

        try {
            String response = getProduct.run(JSONInputString);
            String jsonStr = response;

            Products product = mGson.fromJson(jsonStr, Products.class);
            productsList = product.getProductList();
        } catch (Exception e) {
            // something went wrong! omg!
        }

        return productsList;
    }

}
