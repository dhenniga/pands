package com.pands.dev.pands;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class MenuFunctions extends FrameLayout {
    private ImageButton menuButton, searchButton, memberButton, cartButton, pandsLogoButton;

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

    private void init(final Context context) {
        final View rootView = inflate(context, R.layout.menubar, this);

        menuButton = (ImageButton) rootView.findViewById(R.id.menu_button);
        searchButton = (ImageButton) rootView.findViewById(R.id.search_button);
        memberButton = (ImageButton) rootView.findViewById(R.id.member_button);
        cartButton = (ImageButton) rootView.findViewById(R.id.cart_button);
        pandsLogoButton = (ImageButton) rootView.findViewById(R.id.pands_logo);

        menuButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getBus().post(new FirstItemClicked());

            }
        });


        searchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getBus().post(new FirstItemClicked());
            }
        });

        memberButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getBus().post(new FirstItemClicked());
            }
        });

        cartButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getBus().post(new FirstItemClicked());
            }
        });

    }

}

