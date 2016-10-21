package com.pands.dev.pands;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.pands.dev.pands.product.ProductValue;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ProductViewer extends AppCompatActivity {

    private static final String TAG = ProductViewer.class.getSimpleName();

    String EXTRA_FEATURED_SRC = "EXTRA_FEATURED_SRC";
    String EXTRA_SHORT_DESCRIPTION = "EXTRA_SHORT_DESCRIPTION";
    String EXTRA_TITLE = "EXTRA_TITLE";
    String EXTRA_PRICE = "EXTRA_PRICE";
    String EXTRA_CATEGORIES = "EXTRA_CATEGORIES";
    String EXTRA_TAGS = "EXTRA_TAGS";
    String EXTRA_IMAGES = "EXTRA_IMAGES";
    String EXTRA_ON_SALE = "EXTRA_ON_SALE";
    String EXTRA_ON_SALE_PRICE = "EXTRA_ON_SALE_PRICE";
    String EXTRA_STOCK_QUANTITY = "EXTRA_STOCK_QUANTITY";
    String EXTRA_VISIBLE = "EXTRA_VISIBLE";


    private AppCompatActivity activity = ProductViewer.this;
    ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_viewer);




        Log.i(TAG, "start");

        final Bundle extras = getIntent().getExtras();

        final Typeface RalewayExtraLight = Typeface.createFromAsset(activity.getAssets(), "Raleway-ExtraLight.otf");
        final Typeface RalewayRegular = Typeface.createFromAsset(activity.getAssets(), "Raleway-Regular.otf");
        final Typeface RalewayBold = Typeface.createFromAsset(activity.getAssets(), "Raleway-Bold.otf");
        final Typeface PlayFairDisplayItalic = Typeface.createFromAsset(activity.getAssets(), "PlayfairDisplay-Italic.otf");

        TextView tvProductTitle = (TextView) findViewById(R.id.tvProductTitle);
        tvProductTitle.setTypeface(PlayFairDisplayItalic);

        TextView tvProductShortDescription = (TextView) findViewById(R.id.tvProductShortDescription);
        tvProductShortDescription.setTypeface(RalewayExtraLight);

        TextView tvProductPrice = (TextView) findViewById(R.id.tvProductPrice);
        tvProductPrice.setTypeface(RalewayExtraLight);

        TextView tvProductCategoriesHeader = (TextView) findViewById(R.id.tvProductCategoriesHeader);
        tvProductCategoriesHeader.setTypeface(RalewayBold);

        TextView tvProductCategories = (TextView) findViewById(R.id.tvProductCategories);
        tvProductCategories.setTypeface(RalewayExtraLight);

        TextView tvProductTagsHeader = (TextView) findViewById(R.id.tvProductTagsHeader);
        tvProductTagsHeader.setTypeface(RalewayBold);

        final TextView tvStockQuantity = (TextView) findViewById(R.id.tvStockQuantity);
        tvStockQuantity.setTypeface(RalewayExtraLight);

        TextView tvProductTags = (TextView) findViewById(R.id.tvProductTags);
        tvProductTags.setTypeface(RalewayExtraLight);

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
            Log.d("DEXTRA_STOCK_QUANTITY", ((String.valueOf(extras.getInt(EXTRA_STOCK_QUANTITY)))));
            Log.d("DEXTRA_STOCK_VISIBLE", ((String.valueOf(extras.getBoolean(EXTRA_VISIBLE)))));

            tvProductTitle.setText(extras.getString(EXTRA_TITLE));

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



            tvStockQuantity.setText("1");


            LinearLayout layout = (LinearLayout)findViewById(R.id.llImageContainerBottom);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            String[] items = extras.getString(EXTRA_IMAGES).split(", ");

            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);


            /**  QUICK IMAGE GALLERY  **/
            for (String item : items)
            {
                iv = new ImageView(this);
                iv.setMinimumWidth(metrics.widthPixels); iv.setMaxWidth(metrics.widthPixels);
                iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                Picasso.with(this).load(item).resize(metrics.widthPixels, metrics.widthPixels).centerInside().into(iv);
                layout.addView(iv);

            }


            /**  PRODUCT ON SALE  **/
            boolean boolean2 = extras.getBoolean(EXTRA_ON_SALE);

            if (boolean2 != false) {

                tvProductSalePrice.setVisibility(View.VISIBLE);
                tvInvisibleSpacer.setVisibility(View.INVISIBLE);
                tvProductSalePrice.setText("€" + extras.getInt(EXTRA_ON_SALE_PRICE));
                tvProductSalePrice.setPaintFlags(tvProductSalePrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            }
        }


        Button btnSubtractStock = ((Button)this.findViewById(R.id.btnSubtractStock));
        btnSubtractStock.setTypeface(RalewayExtraLight);
        Button btnAddStock = ((Button)this.findViewById(R.id.btnAddStock));
        btnAddStock.setTypeface(RalewayExtraLight);


        /**
         *
         */
        btnSubtractStock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                int val = Integer.parseInt( tvStockQuantity.getText().toString() );

                if (val <= extras.getInt(EXTRA_STOCK_QUANTITY) && val > 1) {

                    Log.d("Button","btnSubtractStock");
                    val--;
                    tvStockQuantity.setText(((String.valueOf(val))));
                }
            }
        });

        btnAddStock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                int val = Integer.parseInt( tvStockQuantity.getText().toString() );

                if (val < extras.getInt(EXTRA_STOCK_QUANTITY) && val >= 1) {

                    Log.d("Button","btnAddStock");
                    val++;
                    tvStockQuantity.setText(((String.valueOf(val))));
                }

            }
        });

    }



    /**
     *
     * @param html
     * @return
     */
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

}
