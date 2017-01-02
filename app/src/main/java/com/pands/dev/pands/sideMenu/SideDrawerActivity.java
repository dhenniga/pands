//package com.pands.dev.pands.sideMenu;
//
//import android.content.Context;
//import android.graphics.Point;
//import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
//import android.util.AttributeSet;
//import android.view.Display;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.RelativeLayout;
//import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
//import com.pands.dev.pands.JSONHelper;
//import com.pands.dev.pands.MainActivity;
//import com.pands.dev.pands.R;
//import com.pands.dev.pands.product.ProductValue;
//
//import org.json.JSONObject;
//
//import java.util.List;
//
//
//public class SideDrawerActivity extends FrameLayout {
//
//    public static RelativeLayout rlSideMenuContainer;
//    public static RecyclerViewHeader recyclerViewHeader;
//    private List<SideDrawerValue> sideDrawerList;
//    private RecyclerView rvSideDrawer;
//    private int width;
//
//    Display display = getWindowManager().getDefaultDisplay();
//    Point size = new Point();
//    display.getSize(size);
//    width = size.x;
//
//
//    public SideDrawerActivity(Context context) {
//        super(context);
//        init(context);
//    }
//
//    public SideDrawerActivity(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init(context);
//    }
//
//    public SideDrawerActivity(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        init(context);
//    }
//
//    private void init(final Context context) {
//
////        final View rootView = inflate(context, R.layout.menubar, this);
//
//        new JSONAsyncMenu.execute();
//
//
//
//
//    }
//
//    public void openSideMenu(View v1, View v2) {
//        v1.setX(width - width);
//        v2.setX(width - width);
//    }
//
//
//    class JSONAsyncMenu extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            JSONObject jsonObject = new JSONHelper().getJSONforDrawer();
//            sideDrawerList = new SideDrawerParser().parse(jsonObject);
//            return null;
//        }
//
//
//        @Override
//        protected void onPostExecute(Void result) {
//            SideDrawerAdapter sideDrawerAdapter = new SideDrawerAdapter(activity, sideDrawerList);
//            rvSideDrawer.setAdapter(sideDrawerAdapter);
//        }
//    }
//
//}
