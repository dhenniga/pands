package com.pands.dev.pands.menubar;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

public class UserProfileMenu extends Activity {

    public UserProfileMenu(Context context) {

        Log.d("Header_menu", "UserProfileMenu");
        Toast.makeText(context, "Header_menu: UserProfileMenu", Toast.LENGTH_SHORT).show();

    }
}
