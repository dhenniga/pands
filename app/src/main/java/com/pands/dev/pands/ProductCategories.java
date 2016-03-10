package com.pands.dev.pands;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by David on 09/03/2016.
 */
public class ProductCategories {
    @SerializedName("product_categories")
    private List<Product> productCategoryList;

    public List<Product> getProductCategoryList() {
        return productCategoryList;
    }
}
