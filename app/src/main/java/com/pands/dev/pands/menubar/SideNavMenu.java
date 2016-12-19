package com.pands.dev.pands.menubar;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mikepenz.materialdrawer.DrawerBuilder;

public class SideNavMenu extends Activity {

    public SideNavMenu(Context context) {

        Log.d("Header_menu", "SideNavMenu");
        Toast.makeText(context, "Header_menu: SideBarNav", Toast.LENGTH_SHORT).show();

//        new DrawerBuilder().withActivity(this).build();

    }

}