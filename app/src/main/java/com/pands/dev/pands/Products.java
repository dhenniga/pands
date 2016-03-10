package com.pands.dev.pands;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by David on 09/03/2016.
 */
public class Products {
    @SerializedName("products")
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }
}
