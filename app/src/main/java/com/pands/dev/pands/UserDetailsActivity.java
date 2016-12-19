package com.pands.dev.pands;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.DrawerBuilder;
import com.pands.dev.pands.helper.DatabaseHelper;
import com.squareup.picasso.Picasso;

public class UserDetailsActivity extends FragmentActivity {

    DatabaseHelper myDb = new DatabaseHelper(this);

    ImageView ivCustomerProfileImage;

    TextView tvCustomerHeader, tvUserFirstName, tvUserLastName, tvUserName, tvUserEmail;
    EditText etUserFirstName, etUserLastName, etUserName, etUserEmail;

    TextView tvBillingHeader, tvBillingFirstName, tvBillingLastName, tvBillingCompany, tvBillingAddress1, tvBillingAddress2, tvBillingCity, tvBillingState, tvBillingPostcode, tvBillingCountry, tvBillingEmail, tvBillingPhone;
    EditText etBillingFirstName, etBillingLastName, etBillingCompany, etBillingAddress1, etBillingAddress2, etBillingCity, etBillingState, etBillingPostcode, etBillingCountry, etBillingEmail, etBillingPhone;

    TextView tvShippingHeader, tvShippingFirstName, tvShippingLastName, tvShippingCompany, tvShippingAddress1, tvShippingAddress2, tvShippingCity, tvShippingState, tvShippingPostcode, tvShippingCountry;
    EditText etShippingFirstName, etShippingLastName, etShippingCompany, etShippingAddress1, etShippingAddress2, etShippingCity, etShippingState, etShippingPostcode, etShippingCountry;

    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        final Typeface RalewayExtraLight = Typeface.createFromAsset(getAssets(), "Raleway-ExtraLight.otf");
        final Typeface RalewayBold = Typeface.createFromAsset(getAssets(), "Raleway-Bold.otf");
        final Typeface PlayFairDisplayItalic = Typeface.createFromAsset(getAssets(), "PlayfairDisplay-Italic.otf");

        btnLogout = (Button) findViewById(R.id.btnLogout);

        ivCustomerProfileImage = (ImageView) findViewById(R.id.ivCustomerProfileImage);

        // CUSTOMER DETAILS
        tvCustomerHeader = (TextView) findViewById(R.id.tvCustomerHeader);
        tvCustomerHeader.setTypeface(PlayFairDisplayItalic);
        tvUserFirstName = (TextView) findViewById(R.id.tvUserFirstName);
        tvUserFirstName.setTypeface(RalewayBold);
        tvUserLastName = (TextView) findViewById(R.id.tvUserLastName);
        tvUserLastName.setTypeface(RalewayBold);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvUserName.setTypeface(RalewayBold);
        tvUserEmail = (TextView) findViewById(R.id.tvUserEmail);
        tvUserEmail.setTypeface(RalewayBold);

        etUserFirstName = (EditText) findViewById(R.id.etUserFirstName);
        etUserFirstName.setTypeface(RalewayExtraLight);
        etUserLastName = (EditText) findViewById(R.id.etUserLastName);
        etUserLastName.setTypeface(RalewayExtraLight);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etUserName.setTypeface(RalewayExtraLight);
        etUserEmail = (EditText) findViewById(R.id.etUserEmail);
        etUserEmail.setTypeface(RalewayExtraLight);


        // BILLING DETAILS
        tvBillingHeader = (TextView) findViewById(R.id.tvBillingHeader);
        tvBillingHeader.setTypeface(PlayFairDisplayItalic);
        tvBillingFirstName = (TextView) findViewById(R.id.tvBillingFirstName);
        tvBillingFirstName.setTypeface(RalewayBold);
        tvBillingLastName = (TextView) findViewById(R.id.tvBillingLastName);
        tvBillingLastName.setTypeface(RalewayBold);
        tvBillingCompany = (TextView) findViewById(R.id.tvBillingCompany);
        tvBillingCompany.setTypeface(RalewayBold);
        tvBillingAddress1 = (TextView) findViewById(R.id.tvBillingAddress1);
        tvBillingAddress1.setTypeface(RalewayBold);
        tvBillingAddress2 = (TextView) findViewById(R.id.tvBillingAddress2);
        tvBillingAddress2.setTypeface(RalewayBold);
        tvBillingCity = (TextView) findViewById(R.id.tvBillingCity);
        tvBillingCity.setTypeface(RalewayBold);
        tvBillingState = (TextView) findViewById(R.id.tvBillingState);
        tvBillingState.setTypeface(RalewayBold);
        tvBillingPostcode = (TextView) findViewById(R.id.tvBillingPostcode);
        tvBillingPostcode.setTypeface(RalewayBold);
        tvBillingCountry = (TextView) findViewById(R.id.tvBillingCountry);
        tvBillingCountry.setTypeface(RalewayBold);
        tvBillingEmail = (TextView) findViewById(R.id.tvBillingEmail);
        tvBillingEmail.setTypeface(RalewayBold);
        tvBillingPhone = (TextView) findViewById(R.id.tvBillingPhone);
        tvBillingPhone.setTypeface(RalewayBold);

        etBillingFirstName = (EditText) findViewById(R.id.etBillingFirstName);
        etBillingFirstName.setTypeface(RalewayExtraLight);
        etBillingLastName = (EditText) findViewById(R.id.etBillingLastName);
        etBillingLastName.setTypeface(RalewayExtraLight);
        etBillingCompany = (EditText) findViewById(R.id.etBillingCompany);
        etBillingCompany.setTypeface(RalewayExtraLight);
        etBillingAddress1 = (EditText) findViewById(R.id.etBillingAddress1);
        etBillingAddress1.setTypeface(RalewayExtraLight);
        etBillingAddress2 = (EditText) findViewById(R.id.etBillingAddress2);
        etBillingAddress2.setTypeface(RalewayExtraLight);
        etBillingCity = (EditText) findViewById(R.id.etBillingCity);
        etBillingCity.setTypeface(RalewayExtraLight);
        etBillingState = (EditText) findViewById(R.id.etBillingState);
        etBillingState.setTypeface(RalewayExtraLight);
        etBillingPostcode = (EditText) findViewById(R.id.etBillingPostcode);
        etBillingPostcode.setTypeface(RalewayExtraLight);
        etBillingCountry = (EditText) findViewById(R.id.etBillingCountry);
        etBillingCountry.setTypeface(RalewayExtraLight);
        etBillingEmail = (EditText) findViewById(R.id.etBillingEmail);
        etBillingEmail.setTypeface(RalewayExtraLight);
        etBillingPhone = (EditText) findViewById(R.id.etBillingPhone);
        etBillingPhone.setTypeface(RalewayExtraLight);



        // SHIPPING DETAILS
        tvShippingHeader = (TextView) findViewById(R.id.tvShippingHeader);
        tvShippingHeader.setTypeface(PlayFairDisplayItalic);
        tvShippingFirstName = (TextView) findViewById(R.id.tvShippingFirstName);
        tvShippingFirstName.setTypeface(RalewayBold);
        tvShippingLastName = (TextView) findViewById(R.id.tvShippingLastName);
        tvShippingLastName.setTypeface(RalewayBold);
        tvShippingCompany = (TextView) findViewById(R.id.tvShippingCompany);
        tvShippingCompany.setTypeface(RalewayBold);
        tvShippingAddress1 = (TextView) findViewById(R.id.tvShippingAddress1);
        tvShippingAddress1.setTypeface(RalewayBold);
        tvShippingAddress2 = (TextView) findViewById(R.id.tvShippingAddress2);
        tvShippingAddress2.setTypeface(RalewayBold);
        tvShippingCity = (TextView) findViewById(R.id.tvShippingCity);
        tvShippingCity.setTypeface(RalewayBold);
        tvShippingState = (TextView) findViewById(R.id.tvShippingState);
        tvShippingState.setTypeface(RalewayBold);
        tvShippingPostcode = (TextView) findViewById(R.id.tvShippingPostcode);
        tvShippingPostcode.setTypeface(RalewayBold);
        tvShippingCountry = (TextView) findViewById(R.id.tvShippingCountry);
        tvShippingCountry.setTypeface(RalewayBold);

        etShippingFirstName = (EditText) findViewById(R.id.etShippingFirstName);
        etShippingFirstName.setTypeface(RalewayExtraLight);
        etShippingLastName = (EditText) findViewById(R.id.etShippingLastName);
        etShippingLastName.setTypeface(RalewayExtraLight);
        etShippingCompany = (EditText) findViewById(R.id.etShippingCompany);
        etShippingCompany.setTypeface(RalewayExtraLight);
        etShippingAddress1 = (EditText) findViewById(R.id.etShippingAddress1);
        etShippingAddress1.setTypeface(RalewayExtraLight);
        etShippingAddress2 = (EditText) findViewById(R.id.etShippingAddress2);
        etShippingAddress2.setTypeface(RalewayExtraLight);
        etShippingCity = (EditText) findViewById(R.id.etShippingCity);
        etShippingCity.setTypeface(RalewayExtraLight);
        etShippingState = (EditText) findViewById(R.id.etShippingState);
        etShippingState.setTypeface(RalewayExtraLight);
        etShippingPostcode = (EditText) findViewById(R.id.etShippingPostcode);
        etShippingPostcode.setTypeface(RalewayExtraLight);
        etShippingCountry = (EditText) findViewById(R.id.etShippingCountry);
        etShippingCountry.setTypeface(RalewayExtraLight);


        viewAll();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.logOutUser(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public String getAvatar() {

        Cursor res = myDb.getAllData();
        String output;

        if (res.getCount() == 0) {
            output = res.getString(9);
            return output;
        }

        return null;
    }


    public void viewAll() {

        Cursor res = myDb.getAllData();

        if (res.getCount() == 0) {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        while (res.moveToNext()) {
            
            etUserFirstName.setText(res.getString(4));
            etUserLastName.setText(res.getString(5));
            etUserName.setText(res.getString(6));
            etUserEmail.setText(res.getString(3));

            Picasso.with(getApplicationContext()).load(res.getString(9)).into(ivCustomerProfileImage);


            etBillingFirstName.setText(res.getString(10));
            etBillingLastName.setText(res.getString(11));
            etBillingCompany.setText(res.getString(12));
            etBillingAddress1.setText(res.getString(13));
            etBillingAddress2.setText(res.getString(14));
            etBillingCity.setText(res.getString(15));
            etBillingState.setText(res.getString(16));
            etBillingPostcode.setText(res.getString(17));
            etBillingCountry.setText(res.getString(18));
            etBillingEmail.setText(res.getString(19));
            etBillingPhone.setText(res.getString(20));

            etShippingFirstName.setText(res.getString(21));
            etShippingLastName.setText(res.getString(22));
            etShippingCompany.setText(res.getString(23));
            etShippingAddress1.setText(res.getString(24));
            etShippingAddress2.setText(res.getString(25));
            etShippingCity.setText(res.getString(26));
            etShippingState.setText(res.getString(27));
            etShippingPostcode.setText(res.getString(28));
            etShippingCountry.setText(res.getString(29));

        }
    }
}
