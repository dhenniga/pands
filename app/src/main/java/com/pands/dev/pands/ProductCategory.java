package com.pands.dev.pands;

/**
 * Created by David on 09/03/2016.
 */
public class ProductCategory {

    // https://www.primpandstyle.com/wc-api/v3/products/categories?filter[limit]=1&consumer_key=ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8&consumer_secret=cs_fe0ba2a0f443603553f9e30b0112644d03ff22ac

    int id;
    String name;
    String display;
    int parent;
    String description;
    int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
