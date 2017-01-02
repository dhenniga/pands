package com.pands.dev.pands.menubar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.pands.dev.pands.App;
import com.pands.dev.pands.R;
import com.pands.dev.pands.helper.DatabaseHelper;
import java.io.File;

public class MenuFunctions extends FrameLayout {

    public ImageButton menuButton, searchButton, memberButton, cartButton, pandsLogoButton;
    SQLiteDatabase db;

    public MenuFunctions(Context context) {
        super(context);
        init(context);
    }

    public MenuFunctions(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MenuFunctions(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }



    /**
     *
     * @param context
     */
    private void init(final Context context) {

        final View rootView = inflate(context, R.layout.menubar, this);

        menuButton = (ImageButton) rootView.findViewById(R.id.btnMenu);
        searchButton = (ImageButton) rootView.findViewById(R.id.btnSearch);
        memberButton = (ImageButton) rootView.findViewById(R.id.btnMember);
        cartButton = (ImageButton) rootView.findViewById(R.id.btnCart);
        pandsLogoButton = (ImageButton) rootView.findViewById(R.id.pands_logo);

        memberButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        memberButton.setPadding(30,30,30,30);

        memberIconRefresh(context);

//        Picasso.with(context).load(loginActivity.customer_avatar_url).resize(70, 70).into(memberButton);

        menuButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getBus().post(new SideNavMenu(context));
            }
        });

        searchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getBus().post(new SearchMenu(context));
            }
        });

        memberButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getBus().post(new UserProfileMenu(context));
            }
        });

        cartButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getBus().post(new CartMenu(context));
            }
        });

        pandsLogoButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getBus().post(new PandSButton(context));
            }
        });

    }


    /**
     *
     * @param context
     */
    public void memberIconRefresh(Context context) {
        if (!doesDatabaseExist(context)) {
            memberButton.setImageResource(R.drawable.member_icon);
        } else {
             memberButton.setImageResource(R.drawable.pands_logo);
        }
    }



    /**
     *
     * @param context
     * @return
     */
    private static boolean doesDatabaseExist(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        File dbFile = context.getDatabasePath(databaseHelper.DATABASE_NAME);
        return dbFile.exists();
    }

}

