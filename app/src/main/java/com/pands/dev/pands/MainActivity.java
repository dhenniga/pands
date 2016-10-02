package com.pands.dev.pands;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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


    private AppCompatActivity activity = MainActivity.this;
    private List<ProductValue> productList;
    private RecyclerView rvProducts;
    public static int numberOfColumns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        new JSONAsync().execute();

    }


    private void initViews() {

        numberOfColumns = 1;
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

                    startActivity(intent);
                }
            }
        }));

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
            pd = ProgressDialog.show(MainActivity.this, null, "Loading Products...", true, false);
        }


        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = new JSONHelper().getJSONFromUrl();
            productList = new ProductParser().parse(jsonObject);
            return null;
        }


        @Override
            protected void onPostExecute (Void result){
                ProductAdapter productAdapter = new ProductAdapter(activity, productList);
                rvProducts.setAdapter(productAdapter);
                rvProducts.setSelected(false);
                pd.dismiss();
            }
        }
    }

