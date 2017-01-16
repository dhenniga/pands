package com.pands.dev.pands.sideMenu;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.pands.dev.pands.App;
import com.pands.dev.pands.JSONHelper;

import org.json.JSONObject;

import java.util.List;

import com.pands.dev.pands.MainActivity;
import com.pands.dev.pands.R;
import com.pands.dev.pands.listener.RecyclerClickListener;
import com.pands.dev.pands.listener.RecyclerTouchListener;
import com.pands.dev.pands.menubar.MenuFunctions;
import com.pands.dev.pands.menubar.SideNavMenu;

public class SideDrawerFragment extends Fragment {

    private static String EXTRA_FILTER = "EXTRA_FILTER";
    private static String EXTRA_SECTION_NAME = "Latest Products";
    private List<SideDrawerValue> sideDrawerList;
    private RecyclerView rvSideDrawer;

    public static RelativeLayout rlSideMenuContainer;
    public static RecyclerViewHeader recyclerViewHeader;

    public static int closedMenu;

    int pageNumber;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_side_drawer, parent, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Typeface RalewayRegular = Typeface.createFromAsset(getContext().getAssets(), "Raleway-Regular.otf");
        Typeface PlayfairDisplay_Regular = Typeface.createFromAsset(getContext().getAssets(), "PlayfairDisplay-Regular.otf");


        TextView tvHeaderNotification = (TextView) view.findViewById(R.id.tvHeaderNotification);
        tvHeaderNotification.setTypeface(PlayfairDisplay_Regular);

        final WebView wv = (WebView) view.findViewById(R.id.wvHeaderPromo);
        wv.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wv.loadUrl("https://www.primpandstyle.com/mobile/mobile_index.html");
        wv.setVerticalScrollBarEnabled(false);




        view.post(new Runnable() {
            @Override
            public void run() {
                closedMenu = 0 - wv.getWidth();
                rlSideMenuContainer.setX(closedMenu);
            }
        });

        rlSideMenuContainer = (RelativeLayout) view.findViewById(R.id.rlSideMenuContainer);

        rvSideDrawer = (RecyclerView) view.findViewById(R.id.rvSideDrawer);
        rvSideDrawer.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewHeader = (RecyclerViewHeader) view.findViewById(R.id.recyclerViewHeader);
        recyclerViewHeader.attachTo(rvSideDrawer);

        final App mApp = ((App)getActivity().getApplicationContext());
        pageNumber = mApp.getPageNumber();


        rvSideDrawer.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvSideDrawer, new RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {

                mApp.setPageNumber(1);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra(EXTRA_FILTER, "filter[category]=" + sideDrawerList.get(position).getSlug());
                intent.putExtra(EXTRA_SECTION_NAME, sideDrawerList.get(position).getName());
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            }
        }));

        try {
            new JSONAsyncMenu().execute();
        } catch (Exception e) {
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
            SideDrawerAdapter sideDrawerAdapter = new SideDrawerAdapter(getContext(), sideDrawerList);
            rvSideDrawer.setAdapter(sideDrawerAdapter);
        }
    }


}


