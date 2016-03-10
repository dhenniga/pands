package com.pands.dev.pands.asynctasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.pands.dev.pands.MainActivity;
import com.pands.dev.pands.Product;
import com.pands.dev.pands.Products;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by dhen0003 on 10/03/16.
 */
public class FetchProductsAsyncTask extends AsyncTask<Void, Void, List<Product>> {

    private final String mUrl;
    private final ProductsRequestCallback mCallback;

    /**
     *
     * @param url
     * @param callback
     */
    public FetchProductsAsyncTask(String url, ProductsRequestCallback callback) {
        this.mUrl = url;
        this.mCallback = callback;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected List<Product> doInBackground(Void... params) {
        List<Product> productsList = new ArrayList<>();
        GetProduct getProduct = new GetProduct();
        Gson gson = new Gson();

        try {
            String response = getProduct.run(mUrl);
            String jsonStr = response;

            Products product = gson.fromJson(jsonStr, Products.class);
            productsList = product.getProductList();
        } catch (Exception e) {
            // something went wrong! omg!
            e.toString();

        }

        return productsList;
    }

    /**
     *
     * @param products
     */
    @Override
    protected void onPostExecute(List<Product> products) {
        super.onPostExecute(products);


        if (products != null && products.size() > 0) {
            mCallback.onSuccess(products);
        } else {
            mCallback.onError();
        }
    }

    public class GetProduct {
        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {

            client.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

    /**
     *
     */
    public interface ProductsRequestCallback {
        void onSuccess(List<Product> products);
        void onError();
    }
}
