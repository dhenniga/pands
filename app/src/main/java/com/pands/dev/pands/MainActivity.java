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
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.pands.dev.pands.listener.RecyclerClickListener;
import com.pands.dev.pands.listener.RecyclerTouchListener;
import com.pands.dev.pands.product.ProductAdapter;
import com.pands.dev.pands.product.ProductParser;
import com.pands.dev.pands.product.ProductValue;
import org.json.JSONObject;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Drawer.OnDrawerItemClickListener {

    private static final String EXTRA_FEATURED_SRC = "EXTRA_FEATURED_SRC";
    private static final String EXTRA_SHORT_DESCRIPTION = "EXTRA_SHORT_DESCRIPTION";
    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_PRICE = "EXTRA_PRICE";
    private static final String EXTRA_CATEGORIES = "EXTRA_CATEGORIES";
    private static final String EXTRA_TAGS = "EXTRA_TAGS";
    private static final String EXTRA_IMAGES = "EXTRA_IMAGES";
    private static final String EXTRA_ON_SALE = "EXTRA_ON_SALE";
    private static final String EXTRA_ON_SALE_PRICE = "EXTRA_ON_SALE_PRICE";
    private static final String EXTRA_STOCK_QUANTITY = "EXTRA_STOCK_QUANTITY";
    private static final String EXTRA_VISIBLE = "EXTRA_VISIBLE";

    private static String EXTRA_FILTER = "EXTRA_FILTER";

    private AppCompatActivity activity = MainActivity.this;
    private List<ProductValue> productList;
    private RecyclerView rvProducts;
    public static int numberOfColumns;

    private Drawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkStatusCheck(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            EXTRA_FILTER = extras.getString("EXTRA_FILTER");
        }

        CustomDrawerLayout cdl = new CustomDrawerLayout(getApplicationContext());

        mDrawer = new DrawerBuilder().withActivity(this)
                .withDrawerLayout(R.layout.material_drawer)
                .withFireOnInitialOnClick(true)
                .withSliderBackgroundColor(getResources().getColor(R.color.md_black_1000))
                .addDrawerItems(
                        cdl.item5,
                        new DividerDrawerItem(),
                        cdl.item2, cdl.item2_1, cdl.item2_2, cdl.item2_3, cdl.item2_4, cdl.item2_5,
                        new DividerDrawerItem(),
                        cdl.item3, cdl.item3_1,
                        new DividerDrawerItem(),
                        cdl.item4, cdl.item4_1, cdl.item4_2, cdl.item4_3

                )

                .build();

        initViews();

        new JSONAsync().execute();
    }

    @Override
    public void onBackPressed() {
        if (mDrawer != null && mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        App.getBus().register(this);
        mDrawer.closeDrawer();
        networkStatusCheck(getApplicationContext());

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        App.getBus().unregister(this);
        mDrawer.closeDrawer();
        EXTRA_FILTER = "EXTRA_FILTER";
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume","OK");
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Intent i = new Intent(MainActivity.this, MainActivity.class);
//        startActivity(i);
//        finish();
//    }




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

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

        if(drawerItem != null) {

            switch(((String.valueOf(drawerItem.getIdentifier())))) {

                case "14":
                    Intent last_chance_to_buy = new Intent(activity, MainActivity.class);
                    Log.d("position", ((String.valueOf(position))));
                    Toast.makeText(getApplicationContext(), "Loading Last Chance To Buy", Toast.LENGTH_SHORT).show();
                    last_chance_to_buy.putExtra(EXTRA_FILTER, "filter[category]=last-chance-to-buy");
                    startActivity(last_chance_to_buy);
                    break;

                case "2":
                    Intent accessories = new Intent(activity, MainActivity.class);
                    Log.d("position", ((String.valueOf(position))));
                    Toast.makeText(getApplicationContext(), "Loading Accessories", Toast.LENGTH_SHORT).show();
                    accessories.putExtra(EXTRA_FILTER, "filter[category]=accessories");
                    startActivity(accessories);
                    break;

                case "3":
                    Intent handbags = new Intent(activity, MainActivity.class);
                    Log.d("position", ((String.valueOf(position))));
                    Toast.makeText(getApplicationContext(), "Loading Handbags", Toast.LENGTH_SHORT).show();
                    handbags.putExtra(EXTRA_FILTER, "filter[category]=handbags");
                    startActivity(handbags);
                    break;

                case "4":
                    Intent wallets = new Intent(activity, MainActivity.class);
                    Log.d("position", ((String.valueOf(position))));
                    wallets.putExtra(EXTRA_FILTER, "filter[category]=wallets");
                    startActivity(wallets);
                    break;

                case "5":
                    Intent hats = new Intent(activity, MainActivity.class);
                    Log.d("position", ((String.valueOf(position))));
                    Toast.makeText(getApplicationContext(), "Loading Hats", Toast.LENGTH_SHORT).show();
                    hats.putExtra(EXTRA_FILTER, "filter[category]=hats");
                    startActivity(hats);
                    break;

                case "6":
                    Intent scarves = new Intent(activity, MainActivity.class);
                    Log.d("position", ((String.valueOf(position))));
                    Toast.makeText(getApplicationContext(), "Loading Scarves", Toast.LENGTH_SHORT).show();
                    scarves.putExtra(EXTRA_FILTER, "filter[category]=scarves");
                    startActivity(scarves);
                    break;

                case "7":
                    Intent sunglasses = new Intent(activity, MainActivity.class);
                    Log.d("position", ((String.valueOf(position))));
                    Toast.makeText(getApplicationContext(), "Loading Sunglasses", Toast.LENGTH_SHORT).show();
                    sunglasses.putExtra(EXTRA_FILTER, "filter[category]=sunglasses");
                    startActivity(sunglasses);
                    break;

                case "8":
                    Intent clothes = new Intent(activity, MainActivity.class);
                    Log.d("position", ((String.valueOf(position))));
                    Toast.makeText(getApplicationContext(), "Loading Clothes", Toast.LENGTH_SHORT).show();
                    clothes.putExtra(EXTRA_FILTER, "filter[category]=clothes");
                    startActivity(clothes);
                    break;

                case "9":
                    Intent outerwear = new Intent(activity, MainActivity.class);
                    Log.d("position", ((String.valueOf(position))));
                    Toast.makeText(getApplicationContext(), "Loading Outerwear", Toast.LENGTH_SHORT).show();
                    outerwear.putExtra(EXTRA_FILTER, "filter[category]=outerwear");
                    startActivity(outerwear);
                    break;

                case "10":
                    Intent jewellry = new Intent(getApplicationContext(), MainActivity.class);
                    Log.d("position", ((String.valueOf(position))));
                    Toast.makeText(getApplicationContext(), "Loading Jewellry", Toast.LENGTH_SHORT).show();
                    jewellry.putExtra(EXTRA_FILTER, "filter[category]=jewellry");
                    startActivity(jewellry);
                    break;

                case "11":
                    Intent bracelet = new Intent(activity, MainActivity.class);
                    Log.d("position", ((String.valueOf(position))));
                    Toast.makeText(getApplicationContext(), "Loading Bracelets", Toast.LENGTH_SHORT).show();
                    bracelet.putExtra(EXTRA_FILTER, "filter[category]=bracelet");
                    startActivity(bracelet);
                    break;

                case "12":
                    Intent earrings = new Intent(activity, MainActivity.class);
                    Log.d("position", ((String.valueOf(position))));
                    Toast.makeText(getApplicationContext(), "Loading Earrings", Toast.LENGTH_SHORT).show();
                    earrings.putExtra(EXTRA_FILTER, "filter[category]=earrings");
                    startActivity(earrings);
                    break;

                case "13":
                    Intent necklace = new Intent(activity, MainActivity.class);
                    Log.d("position", ((String.valueOf(position))));
                    Toast.makeText(getApplicationContext(), "Loading Necklace", Toast.LENGTH_SHORT).show();
                    necklace.putExtra(EXTRA_FILTER, "filter[category]=earrings");
                    startActivity(necklace);
                    break;
            }
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


