package com.pands.dev.pands;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.pands.dev.pands.product.ProductAdapter;
import com.pands.dev.pands.product.ProductParser;
import com.pands.dev.pands.product.ProductValue;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

public class ProductViewer extends AppCompatActivity {

    private static final String TAG = ProductViewer.class.getSimpleName();
    public static final String EXTRA_FEATURED_SRC = "EXTRA_FEATURED_SRC";
    public static final String EXTRA_SHORT_DESCRIPTION = "EXTRA_SHORT_DESCRIPTION";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_PRICE = "EXTRA_PRICE";
    public static final String EXTRA_CATEGORIES = "EXTRA_CATEGORIES";


    private AppCompatActivity activity = ProductViewer.this;
    private List<ProductValue> productList;
    private RecyclerView rvProductGallery;
    public int numberOfColumns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_viewer);

        final Typeface RalewayExtraLight = Typeface.createFromAsset(activity.getAssets(), "Raleway-ExtraLight.ttf");
        final Typeface PlayFairDisplayItalic = Typeface.createFromAsset(activity.getAssets(), "PlayfairDisplay-Italic.otf");

        TextView tvProductTitle = (TextView) findViewById(R.id.tvProductTitle);
        tvProductTitle.setTypeface(PlayFairDisplayItalic);

        TextView tvProductShortDescription = (TextView) findViewById(R.id.tvProductShortDescription);
        tvProductShortDescription.setTypeface(RalewayExtraLight);

        ImageView ivProductFeaturedSrc = (ImageView) findViewById(R.id.ivProductFeaturedSrc);

        TextView tvProductPrice = (TextView) findViewById(R.id.tvProductPrice);
        tvProductPrice.setTypeface(RalewayExtraLight);

        TextView tvProductCategories = (TextView) findViewById(R.id.tvProductCategories);
        tvProductCategories.setTypeface(RalewayExtraLight);

        initViews();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            tvProductTitle.setText(extras.getString(EXTRA_TITLE));

            Picasso.with(getApplicationContext()).load(extras.getString(EXTRA_FEATURED_SRC)).into(ivProductFeaturedSrc);

            String updated = stripHtml(extras.getString(EXTRA_SHORT_DESCRIPTION));
            tvProductShortDescription.setText(updated);

            tvProductPrice.setText("€" + extras.getString(EXTRA_PRICE));

            tvProductCategories.setText(extras.getString(EXTRA_CATEGORIES));

        }

//        new JSONAsync.execute();

    }

    public String stripHtml(String html) {
        return Html.fromHtml(html).toString();
    }


    private void initViews() {

        numberOfColumns = 1;
        rvProductGallery = (RecyclerView) findViewById(R.id.rvProductGallery);
        rvProductGallery.setLayoutManager(new GridLayoutManager(getApplicationContext(), numberOfColumns, GridLayoutManager.VERTICAL, false));

    }

    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
    }



    class JSONAsync extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(ProductViewer.this, null, "Loading Product...", true, false);
        }


        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = new JSONHelper().getJSONFromUrl();
            productList = new ProductParser().parse(jsonObject);
            return null;
        }


        @Override
        protected void onPostExecute (Void result){
            ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(), productList);
            rvProductGallery.setAdapter(productAdapter);
            rvProductGallery.setSelected(false);
            pd.dismiss();
        }
    }
}
