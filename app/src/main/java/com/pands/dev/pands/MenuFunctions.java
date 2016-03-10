package com.pands.dev.pands;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by David on 07/03/2016.
 */
public class MenuFunctions extends Activity{

    private ImageButton menuButton = (ImageButton) findViewById(R.id.menu_button);
    private ImageButton searchButton = (ImageButton) findViewById(R.id.search_button);
    private ImageButton memberButton = (ImageButton) findViewById(R.id.member_button);
    private ImageButton cartButton = (ImageButton) findViewById(R.id.cart_button);
    private ImageButton pandsLogoButton = (ImageButton) findViewById(R.id.pands_logo);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menubar);
    }

    public View.OnClickListener MenuButtonInput = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.menu_button:
                    menuButton.setVisibility(View.INVISIBLE);
                    break;

                case R.id.search_button:
                    searchButton.setVisibility(View.INVISIBLE);
                    break;

                case R.id.member_button:
                    memberButton.setVisibility(View.INVISIBLE);
                    break;

                case R.id.cart_button:
                    cartButton.setVisibility(View.INVISIBLE);

                case R.id.pands_logo:
                    pandsLogoButton.setVisibility(View.INVISIBLE);
                    break;

                default:
                    break;
            }
        }
    };

}

