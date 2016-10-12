package com.pands.dev.pands.menubar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.pands.dev.pands.UserDetailsActivity;

public class UserProfileMenu extends Activity {

    public UserProfileMenu(Context context) {

        Log.d("Header_menu", "UserProfileMenu");
        Toast.makeText(context, "Header_menu: UserProfileMenu", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(context, UserDetailsActivity.class);
        startActivity(intent);

    }
}
