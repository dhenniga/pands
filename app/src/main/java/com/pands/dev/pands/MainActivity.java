package com.pands.dev.pands;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.pands.dev.pands.listener.RecyclerClickListener;
import com.pands.dev.pands.listener.RecyclerTouchListener;
import com.pands.dev.pands.product.ProductAdapter;
import com.pands.dev.pands.product.ProductParser;
import com.pands.dev.pands.product.ProductValue;
import org.json.JSONObject;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_FEATURED_SRC = "EXTRA_FEATURED_SRC";
    public static final String EXTRA_SHORT_DESCRIPTION = "EXTRA_SHORT_DESCRIPTION";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_PRICE = "EXTRA_PRICE";
    public static final String EXTRA_CATEGORIES = "EXTRA_CATEGORIES";
    public static final String EXTRA_TAGS = "EXTRA_TAGS";
    public static final String EXTRA_IMAGES = "EXTRA_IMAGES";
    public static final String EXTRA_ON_SALE = "EXTRA_ON_SALE";
    public static final String EXTRA_ON_SALE_PRICE = "EXTRA_ON_SALE_PRICE";
    public static final String EXTRA_STOCK_QUANTITY = "EXTRA_STOCK_QUANTITY";
    public static final String EXTRA_VISIBLE = "EXTRA_VISIBLE";

    private AppCompatActivity activity = MainActivity.this;
    private List<ProductValue> productList;
    private RecyclerView rvProducts;
    public static int numberOfColumns;

    String EXTRA_FILTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        networkStatusCheck(getApplicationContext());

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            EXTRA_FILTER = extras.getString("EXTRA_FILTER");
        }

        initViews();

        new JSONAsync().execute();

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        App.getBus().register(this);

        networkStatusCheck(getApplicationContext());
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        App.getBus().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume","OK");
    }


    private void initViews() {

        numberOfColumns = 2;
        rvProducts = (RecyclerView) findViewById(R.id.rvProducts);
        rvProducts.setLayoutManager(new GridLayoutManager(getApplicationContext(), numberOfColumns, GridLayoutManager.VERTICAL, false));

        rvProducts.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this, rvProducts, new RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {

                if (productList != null) {

                    Log.i("recyclerActivity", ((String.valueOf(productList.get(position).getId()))));

                    Intent intent = new Intent(getApplicationContext(), ProductViewer.class);

                    intent.putExtra(EXTRA_FEATURED_SRC, productList.get(position).getFeatured_src());
                    intent.putExtra(EXTRA_SHORT_DESCRIPTION, productList.get(position).getShort_description());
                    intent.putExtra(EXTRA_TITLE, productList.get(position).getTitle());
                    intent.putExtra(EXTRA_PRICE, productList.get(position).getPrice());
                    intent.putExtra(EXTRA_CATEGORIES, productList.get(position).getCategories());
                    intent.putExtra(EXTRA_TAGS, productList.get(position).getTags());
                    intent.putExtra(EXTRA_IMAGES, productList.get(position).getImages());
                    intent.putExtra(EXTRA_ON_SALE, productList.get(position).getOn_sale());
                    intent.putExtra(EXTRA_ON_SALE_PRICE, productList.get(position).getSale_price());
                    intent.putExtra(EXTRA_STOCK_QUANTITY, productList.get(position).getStock_quantity());
                    intent.putExtra(EXTRA_VISIBLE, productList.get(position).getVisible());

                    startActivity(intent);

                }
            }
        }));

    }

    private void networkStatusCheck(Context context) {

        Boolean networkAvailabilityBoolean = isNetworkAvailable(context);

        if (networkAvailabilityBoolean) {
            String networkAvailability = ((String.valueOf(isNetworkAvailable(context))));
            Log.i("Network Available", networkAvailability);
        } else {
            Intent intent = new Intent(context, Network_error.class);
            startActivity(intent);
        }
    }


    public static boolean isNetworkAvailable(Context context) {
        int[] networkTypes = {ConnectivityManager.TYPE_MOBILE,
                ConnectivityManager.TYPE_WIFI};
        try {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            for (int networkType : networkTypes) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null &&
                        activeNetworkInfo.getType() == networkType)
                    return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }



    class JSONAsync extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(MainActivity.this, null, "Loading Products...", true, false);

        }


        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = new JSONHelper().getJSONFromUrl(EXTRA_FILTER);
            System.out.println("Hello World" + EXTRA_FILTER);
            productList = new ProductParser().parse(jsonObject);
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            ProductAdapter productAdapter = new ProductAdapter(activity, productList);
            rvProducts.setAdapter(productAdapter);
            rvProducts.setSelected(false);
            pd.dismiss();
        }
    }
}


