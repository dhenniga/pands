package com.pands.dev.pands.menubar;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.pands.dev.pands.MainActivity;

public class SideNavMenu {

    public SideNavMenu(Context context) {

        Log.d("Header_menu", "SideNavMenu");
        Toast.makeText(context, "Header_menu: SideBarNav", Toast.LENGTH_SHORT).show();

        MainActivity mainActivity = new MainActivity();
        mainActivity.openSideMenu();

    }
}