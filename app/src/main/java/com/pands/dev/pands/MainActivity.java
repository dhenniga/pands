package com.pands.dev.pands;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.pands.dev.pands.product.ProductAdapter;
import com.pands.dev.pands.product.ProductParser;
import com.pands.dev.pands.product.ProductValue;

import org.json.JSONObject;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppCompatActivity activity = MainActivity.this;
    private List<ProductValue> productList;
    private RecyclerView rvProducts;

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
        rvProducts = (RecyclerView) findViewById(R.id.rvProducts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rvProducts.setLayoutManager(layoutManager);
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

