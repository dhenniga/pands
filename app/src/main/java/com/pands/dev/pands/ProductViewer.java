package com.pands.dev.pands;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pands.dev.pands.product.ProductAdapter;
import com.pands.dev.pands.product.ProductParser;
import com.pands.dev.pands.product.ProductValue;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ProductViewer extends AppCompatActivity {

    private static final String TAG = ProductViewer.class.getSimpleName();

    private String EXTRA_FEATURED_SRC = "EXTRA_FEATURED_SRC";
    private String EXTRA_SHORT_DESCRIPTION = "EXTRA_SHORT_DESCRIPTION";
    private String EXTRA_TITLE = "EXTRA_TITLE";
    private String EXTRA_PRICE = "EXTRA_PRICE";
    private String EXTRA_CATEGORIES = "EXTRA_CATEGORIES";
    private String EXTRA_TAGS = "EXTRA_TAGS";
    private String EXTRA_IMAGES = "EXTRA_IMAGES";
    private String EXTRA_ON_SALE = "EXTRA_ON_SALE";
    private String EXTRA_ON_SALE_PRICE = "EXTRA_ON_SALE_PRICE";


    private AppCompatActivity activity = ProductViewer.this;
    private List<ProductValue> productList;
    private RecyclerView rvProductGallery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_viewer);




        Log.i(TAG, "start");

        Bundle extras = getIntent().getExtras();

        final Typeface RalewayExtraLight = Typeface.createFromAsset(activity.getAssets(), "Raleway-ExtraLight.otf");
        final Typeface RalewayBold = Typeface.createFromAsset(activity.getAssets(), "Raleway-Bold.otf");
        final Typeface PlayFairDisplayItalic = Typeface.createFromAsset(activity.getAssets(), "PlayfairDisplay-Italic.otf");

        TextView tvProductTitle = (TextView) findViewById(R.id.tvProductTitle);
        tvProductTitle.setTypeface(PlayFairDisplayItalic);

        TextView tvProductShortDescription = (TextView) findViewById(R.id.tvProductShortDescription);
        tvProductShortDescription.setTypeface(RalewayExtraLight);

        ImageView ivProductFeaturedSrc = (ImageView) findViewById(R.id.ivProductFeaturedSrc);

        TextView tvProductPrice = (TextView) findViewById(R.id.tvProductPrice);
        tvProductPrice.setTypeface(RalewayExtraLight);

        TextView tvProductCategoriesHeader = (TextView) findViewById(R.id.tvProductCategoriesHeader);
        tvProductCategoriesHeader.setTypeface(RalewayBold);

        TextView tvProductCategories = (TextView) findViewById(R.id.tvProductCategories);
        tvProductCategories.setTypeface(RalewayExtraLight);

        TextView tvProductTagsHeader = (TextView) findViewById(R.id.tvProductTagsHeader);
        tvProductTagsHeader.setTypeface(RalewayBold);

        TextView tvProductTags = (TextView) findViewById(R.id.tvProductTags);
        tvProductTags.setTypeface(RalewayExtraLight);

        TextView tvProductImagesHeader = (TextView) findViewById(R.id.tvProductImagesHeader);
        tvProductImagesHeader.setTypeface(RalewayBold);

        TextView tvProductImages = (TextView) findViewById(R.id.tvProductImages);
        tvProductImages.setTypeface(RalewayExtraLight);

        TextView tvProductSalePrice = (TextView) findViewById(R.id.tvProductSalePrice);
        tvProductSalePrice.setTypeface(RalewayExtraLight);

        TextView tvInvisibleSpacer = (TextView) findViewById(R.id.tvInvisibleSpacer);


        if (extras != null) {

            Log.d("DEXTRA_FEATURED_SRC", extras.getString(EXTRA_FEATURED_SRC));
            Log.d("DXTRA_SHORT_DESCRIPTION", extras.getString(EXTRA_SHORT_DESCRIPTION));
            Log.d("DEXTRA_TITLE", extras.getString(EXTRA_TITLE));
            Log.d("DEXTRA_PRICE", ((String.valueOf(extras.getInt(EXTRA_PRICE)))));
            Log.d("DEXTRA_CATEGORIES", extras.getString(EXTRA_CATEGORIES));
            Log.d("DEXTRA_TAGS", extras.getString(EXTRA_TAGS));
            Log.d("DEXTRA_IMAGES", extras.getString(EXTRA_IMAGES));
            Log.d("DEXTRA_ON_SALE", ((String.valueOf(extras.getBoolean(EXTRA_ON_SALE)))));
            Log.d("DEXTRA_ON_SALE_PRICE", ((String.valueOf(extras.getInt(EXTRA_ON_SALE_PRICE)))));

            tvProductTitle.setText(extras.getString(EXTRA_TITLE));

            Picasso.with(getApplicationContext()).load(extras.getString(EXTRA_FEATURED_SRC)).into(ivProductFeaturedSrc);

            String updated = stripHtml(extras.getString(EXTRA_SHORT_DESCRIPTION));
            tvProductShortDescription.setText(updated);

//            if (EXTRA_PRICE) {
                tvProductPrice.setText("€" + extras.getInt(EXTRA_PRICE));
//            } else { tvProductPrice.setVisibility(View.GONE);}

            if (EXTRA_CATEGORIES != null) {
                tvProductCategories.setText(extras.getString(EXTRA_CATEGORIES));
            } else { tvProductCategories.setVisibility(View.GONE); tvProductCategoriesHeader.setVisibility(View.GONE);}


            if (EXTRA_TAGS != null) {
                tvProductTags.setText(extras.getString(EXTRA_TAGS));
            } else { tvProductTags.setVisibility(View.GONE); tvProductTagsHeader.setVisibility(View.GONE);}

//            tvProductImages.setText(extras.getString(EXTRA_IMAGES));

//            List<String> items = Arrays.asList(extras.getString(EXTRA_IMAGES).split("\\s*,\\s*"));


            LinearLayout layout = (LinearLayout)findViewById(R.id.llImageContainerBottom);
            String[] items = extras.getString(EXTRA_IMAGES).split(",");

            for (final String item : items)
            {
                ImageView iv = new ImageView(this);
                Picasso.with(this).load(item).into(iv);
                layout.addView(iv);

            }




            boolean boolean2 = extras.getBoolean(EXTRA_ON_SALE);

            if (boolean2 != false) {

                tvProductSalePrice.setVisibility(View.VISIBLE);
                tvInvisibleSpacer.setVisibility(View.INVISIBLE);
                tvProductSalePrice.setText("€" + extras.getInt(EXTRA_ON_SALE_PRICE));
                tvProductSalePrice.setPaintFlags(tvProductSalePrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            }



        }

//        new JSONAsync.execute();

    }

    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }


    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
    }



//    class JSONAsync extends AsyncTask<Void, Void, Void> {
//        ProgressDialog pd;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            pd = ProgressDialog.show(ProductViewer.this, null, "Loading Product...", true, false);
//        }
//
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            JSONObject jsonObject = new JSONHelper().getJSONFromUrl();
//            productList = new ProductParser().parse(jsonObject);
//            return null;
//        }
//
//
//        @Override
//        protected void onPostExecute (Void result){
//            ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(), productList);
//            rvProductGallery.setAdapter(productAdapter);
//            rvProductGallery.setSelected(false);
//            pd.dismiss();
//        }
//    }
}
