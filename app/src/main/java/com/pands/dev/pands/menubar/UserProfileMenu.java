package com.pands.dev.pands.menubar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.pands.dev.pands.RegisterActivity;
import com.pands.dev.pands.UserDetailsActivity;

public class UserProfileMenu extends Activity{




    public UserProfileMenu(Context context) {

        Log.d("Header_menu", "UserProfileMenu");
//        Toast.makeText(context, "Header_menu: UserProfileMenu", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(context, UserDetailsActivity.class);
        context.startActivity(intent);

    }

}