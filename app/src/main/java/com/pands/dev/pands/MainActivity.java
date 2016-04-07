package com.pands.dev.pands;

import android.content.pm.ActivityInfo;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pands.dev.pands.asynctasks.FetchProductsAsyncTask;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FetchProductsAsyncTask.ProductsRequestCallback {

    MenuFunctions menuFunctions;
    private Gson mGson;
    List<Product> productsList= new ArrayList<>();

    String list_20_products = "http://primpandstyle.com/wc-api/v3/";

    private List<Product> mItems;
    private ArrayAdapter<Product> mAdapter;

    //https://www.primpandstyle.com/wc-api/v3/products?filter[limit]=1&consumer_key=ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8&consumer_secret=cs_fe0ba2a0f443603553f9e30b0112644d03ff22ac
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

        mItems = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, mItems);
        ListView listViewTest = (ListView) findViewById(R.id.listViewTest);
        listViewTest.setAdapter(mAdapter);


        fetchProducts(list_20_products);
    }

    /**
     *
     * @return - Returns a list of products
     */
    public List<Product> fetchProducts(String url) {
        new FetchProductsAsyncTask(url, this).execute();


        return productsList;
    }

    @Override
    public void onSuccess(List<Product> products) {
        mItems.clear();
        mItems.addAll(products);

        mAdapter.notifyDataSetChanged(); // Observer - Observable pattern
    }

    @Override
    public void onError() {

    }
}
