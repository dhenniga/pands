package com.pands.dev.pands;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.pands.dev.pands.listener.RecyclerClickListener;
import com.pands.dev.pands.listener.RecyclerTouchListener;
import com.pands.dev.pands.product.ProductAdapter;
import com.pands.dev.pands.product.ProductParser;
import com.pands.dev.pands.product.ProductValue;
import com.pands.dev.pands.sideMenu.SideDrawerAdapter;
import com.pands.dev.pands.sideMenu.SideDrawerParser;
import com.pands.dev.pands.sideMenu.SideDrawerValue;
import org.json.JSONObject;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
    private static String EXTRA_SECTION_NAME = "Latest Products";

    private AppCompatActivity activity = MainActivity.this;
    private List<ProductValue> productList;
    private List<SideDrawerValue> sideDrawerList;
    private RecyclerView rvProducts;
    private RecyclerView rvSideDrawer;
    public static int numberOfColumns;

    public static RelativeLayout rlSideMenuContainer;
    public static RecyclerViewHeader recyclerViewHeader;
    private int width;

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
            EXTRA_SECTION_NAME = extras.getString("EXTRA_SECTION_NAME");
        }

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;

        Typeface RalewayRegular = Typeface.createFromAsset(getAssets(), "Raleway-Regular.otf");
        Typeface PlayfairDisplay_Regular = Typeface.createFromAsset(getAssets(), "PlayfairDisplay-Regular.otf");

        TextView tvSectionName = (TextView) findViewById(R.id.tvSectionName);
        tvSectionName.setText(EXTRA_SECTION_NAME);
        tvSectionName.setTypeface(RalewayRegular);

        TextView tvHeaderNotification = (TextView) findViewById(R.id.tvHeaderNotification);
        tvHeaderNotification.setTypeface(PlayfairDisplay_Regular);

        WebView wv = (WebView)findViewById(R.id.wvHeaderPromo);
        wv.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wv.loadUrl("https://www.primpandstyle.com/mobile/mobile_index.html");


        initViews();

        new JSONAsync().execute();

        new JSONAsyncMenu().execute();
    }

//    @Override
//    public void onBackPressed() {
//        if (cdl.mDrawer != null && cdl.mDrawer.isDrawerOpen()) {
//            cdl.mDrawer.closeDrawer();
//        } else {
//            super.onBackPressed();
//        }
//    }


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
        EXTRA_FILTER = "EXTRA_FILTER";
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume","OK");
    }


    private void initViews() {

        numberOfColumns = 2;

        rlSideMenuContainer = (RelativeLayout) findViewById(R.id.rlSideMenuContainer);

        View child = getLayoutInflater().inflate(R.layout.fragment_side_drawer, rlSideMenuContainer, false);
        rlSideMenuContainer.addView(child);

        rvSideDrawer = (RecyclerView) findViewById(R.id.rvSideDrawer);
        rvSideDrawer.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewHeader = (RecyclerViewHeader) findViewById(R.id.recyclerViewHeader);
        recyclerViewHeader.attachTo(rvSideDrawer);

//        rvSideDrawer.setX(width);
        rlSideMenuContainer.setX(width);
        recyclerViewHeader.setX(width);


//        RelativeLayout rlMainGalleryContainer = (RelativeLayout) findViewById(R.id.rlMainGalleryContainer);
//        rlMainGalleryContainer.setX(width - 220);


//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int width = size.x;
//        rlSideMenuContainer.setX(width);

        rvSideDrawer.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this, rvSideDrawer, new RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {

//                Display display = getWindowManager().getDefaultDisplay();
//                Point size = new Point();
//                display.getSize(size);
//                int width = size.x;
//                rlSideMenuContainer.setX(width - width);

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra(EXTRA_FILTER, "filter[category]=" + sideDrawerList.get(position).getSlug());
                intent.putExtra(EXTRA_SECTION_NAME, sideDrawerList.get(position).getName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        }));


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

    public void openSideMenu(View v1, View v2) {
        v1.setX(width - width);
        v2.setX(width - width);
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


    class JSONAsyncMenu extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = new JSONHelper().getJSONforDrawer();
            sideDrawerList = new SideDrawerParser().parse(jsonObject);
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            SideDrawerAdapter sideDrawerAdapter = new SideDrawerAdapter(activity, sideDrawerList);
            rvSideDrawer.setAdapter(sideDrawerAdapter);
        }
    }



}


