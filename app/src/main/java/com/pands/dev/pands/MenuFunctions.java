package com.pands.dev.pands;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

/**
 * Created by David on 07/03/2016.
 */
public class MenuFunctions extends Activity implements View.OnClickListener {

    private ImageButton menuButton, searchButton, memberButton, cartButton, pandsLogoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menubar);

        menuButton = (ImageButton) findViewById(R.id.menu_button);
        searchButton = (ImageButton) findViewById(R.id.search_button);
        memberButton = (ImageButton) findViewById(R.id.member_button);
        cartButton = (ImageButton) findViewById(R.id.cart_button);
        pandsLogoButton = (ImageButton) findViewById(R.id.pands_logo);

    }


    @Override
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
                Intent intent = new Intent(getApplicationContext(), UserDetailsActivity.class);
                startActivity(intent);
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
}

