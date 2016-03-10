package com.pands.dev.pands;

import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void testJsonToProducts() {
        String jsonStr = "{\"products\":[{\"title\":\"Gunmetal Chain and Crystal Statement Necklace\",\"id\":1017,\"created_at\":\"2016-03-06T14:51:21Z\",\"updated_at\":\"2016-03-06T14:51:36Z\",\"type\":\"simple\",\"status\":\"publish\",\"downloadable\":false,\"virtual\":false,\"permalink\":\"https:\\/\\/primpandstyle.com\\/product\\/gunmetal-chain-and-crystal-statement-necklace\\/\",\"sku\":\"\",\"price\":\"30\",\"regular_price\":\"30\",\"sale_price\":null,\"price_html\":\"<span class=\\\"amount\\\">&euro;30.00<\\/span>\",\"taxable\":false,\"tax_status\":\"taxable\",\"tax_class\":\"\",\"managing_stock\":true,\"stock_quantity\":1,\"in_stock\":true,\"backorders_allowed\":false,\"backordered\":false,\"sold_individually\":false,\"purchaseable\":true,\"featured\":false,\"visible\":true,\"catalog_visibility\":\"visible\",\"on_sale\":false,\"product_url\":\"\",\"button_text\":\"\",\"weight\":null,\"dimensions\":{\"length\":\"\",\"width\":\"\",\"height\":\"\",\"unit\":\"cm\"},\"shipping_required\":true,\"shipping_taxable\":true,\"shipping_class\":\"\",\"shipping_class_id\":null,\"description\":\"\",\"short_description\":\"<p>Showstopping statement necklace, with gunmetal chain link strands accented with black and grey crystals. A really special piece.<\\/p>\\n\",\"reviews_allowed\":true,\"average_rating\":\"0.00\",\"rating_count\":0,\"related_ids\":[142,133,58,57,139],\"upsell_ids\":[],\"cross_sell_ids\":[],\"parent_id\":0,\"categories\":[\"Jewellry\",\"Necklace\"],\"tags\":[\"Shimmer\",\"Sparkle\",\"Statement\"],\"images\":[{\"id\":1015,\"created_at\":\"2016-03-06T14:47:04Z\",\"updated_at\":\"2016-03-06T14:47:04Z\",\"src\":\"https:\\/\\/primpandstyle.com\\/wp-content\\/uploads\\/2016\\/03\\/image_4322.jpg\",\"title\":\"image_4322\",\"alt\":\"\",\"position\":0},{\"id\":1016,\"created_at\":\"2016-03-06T14:47:06Z\",\"updated_at\":\"2016-03-06T14:47:06Z\",\"src\":\"https:\\/\\/primpandstyle.com\\/wp-content\\/uploads\\/2016\\/03\\/image_4322_2.jpg\",\"title\":\"image_4322_2\",\"alt\":\"\",\"position\":1}],\"featured_src\":\"https:\\/\\/primpandstyle.com\\/wp-content\\/uploads\\/2016\\/03\\/image_4322.jpg\",\"attributes\":[],\"downloads\":[],\"download_limit\":0,\"download_expiry\":0,\"download_type\":\"\",\"purchase_note\":\"\",\"total_sales\":0,\"variations\":[],\"parent\":[],\"grouped_products\":[],\"menu_order\":0},{\"title\":\"Tan Saffiano Leather Shopper\",\"id\":976,\"created_at\":\"2016-02-06T13:53:08Z\",\"updated_at\":\"2016-03-08T17:57:10Z\",\"type\":\"simple\",\"status\":\"publish\",\"downloadable\":false,\"virtual\":false,\"permalink\":\"https:\\/\\/primpandstyle.com\\/product\\/tan-saffiano-leather-shopper\\/\",\"sku\":\"\",\"price\":\"110\",\"regular_price\":\"110\",\"sale_price\":null,\"price_html\":\"<span class=\\\"amount\\\">&euro;110.00<\\/span>\",\"taxable\":false,\"tax_status\":\"taxable\",\"tax_class\":\"\",\"managing_stock\":true,\"stock_quantity\":1,\"in_stock\":true,\"backorders_allowed\":false,\"backordered\":false,\"sold_individually\":false,\"purchaseable\":true,\"featured\":false,\"visible\":true,\"catalog_visibility\":\"visible\",\"on_sale\":false,\"product_url\":\"\",\"button_text\":\"\",\"weight\":null,\"dimensions\":{\"length\":\"\",\"width\":\"\",\"height\":\"\",\"unit\":\"cm\"},\"shipping_required\":true,\"shipping_taxable\":true,\"shipping_class\":\"\",\"shipping_class_id\":null,\"description\":\"\",\"short_description\":\"<p>The ultimate tan shopper in Saffiano leather. This hardwearing leather is perfect for everyday.<\\/p>\\n<p>Cotton lined with internal pockets, gold zip detail and shoulder shoulder strap.<\\/p>\\n<p>Size: W 35\\/40cm H 30cm D 15cm<\\/p>\\n\",\"reviews_allowed\":true,\"average_rating\":\"0.00\",\"rating_count\":0,\"related_ids\":[343,943,818,268,817],\"upsell_ids\":[],\"cross_sell_ids\":[],\"parent_id\":0,\"categories\":[\"Accessories\",\"Handbags\"],\"tags\":[\"Leather\",\"Saffiano\",\"Shopper\",\"Tan\"],\"images\":[{\"id\":984,\"created_at\":\"2016-02-06T13:44:00Z\",\"updated_at\":\"2016-02-06T13:44:00Z\",\"src\":\"https:\\/\\/primpandstyle.com\\/wp-content\\/uploads\\/2016\\/02\\/image_4301.jpg\",\"title\":\"image_4301\",\"alt\":\"\",\"position\":0}],\"featured_src\":\"https:\\/\\/primpandstyle.com\\/wp-content\\/uploads\\/2016\\/02\\/image_4301.jpg\",\"attributes\":[],\"downloads\":[],\"download_limit\":0,\"download_expiry\":0,\"download_type\":\"\",\"purchase_note\":\"\",\"total_sales\":0,\"variations\":[],\"parent\":[],\"grouped_products\":[],\"menu_order\":0}]}";

        Gson gson = new Gson();

        Products product = gson.fromJson(jsonStr, Products.class);

        Assert.assertNotNull(product);

        List<Product> productsList = product.getProductList();

        Assert.assertNotNull(productsList);

        Assert.assertTrue(productsList.size() == 2);
    }
}