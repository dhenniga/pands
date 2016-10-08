package com.pands.dev.pands;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pands.dev.pands.helper.DatabaseHelper;
import com.pands.dev.pands.helper.SQLiteHandler;
import com.pands.dev.pands.helper.SessionManager;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    String userName, password, LOGIN_STATUS, NONCE_RETUR, tempAuthError;
    EditText etLoginUsername, etLoginPassword;
    Button btnLogin, btnRegister;
//    SessionManager session;
//    SQLiteHandler db;
    DatabaseHelper myDb;
    String response = null;



    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDb = new DatabaseHelper(this);


//
//        db = new SQLiteHandler(getApplicationContext());
//        session = new SessionManager(getApplicationContext());
//
//        if (session.isLoggedIn()) {
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }



        final Typeface RalewayLight = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
        final Typeface RalewayBold = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");
        final Typeface PlayFairDisplayItalic = Typeface.createFromAsset(getAssets(), "PlayfairDisplay-Regular.otf");

        etLoginUsername = (EditText) findViewById(R.id.etLoginUsername);
        etLoginUsername.setTypeface(RalewayLight);

        etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);
        etLoginPassword.setTypeface(RalewayLight);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setTypeface(RalewayBold);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setTypeface(RalewayBold);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new JSONAsync().execute();

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);

            }
        });


    }

//    public void AddData() {
//        btnLogin.setOnClickListener(
//                new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        myDb.insertData()
//                    }
//                }
//        );
//    }


    /**
     *
     */
    class JSONAsync extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            userName = etLoginUsername.getText().toString();
            password = etLoginPassword.getText().toString();

            pd = ProgressDialog.show(LoginActivity.this, null, "Logging in " + userName, true, false);
        }


        @Override
        protected Void doInBackground(Void... params) {

            try {
//
//                https://www.primpandstyle.com/wc-api/v3/customers/55?consumer_key=ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8&consumer_secret=cs_fe0ba2a0f443603553f9e30b0112644d03ff22ac

//                https://www.primpandstyle.com/api/auth/get_currentuserinfo/?cookie=dd|1476962779|AETWuwOphWyaHXrS8BpkouflfOH3EUy2K9OWoy0yHZX|3d996039bf6fb42dbe3570cd923a17e80b24ab868b763debd7f90016506e3a4c
//
//                https://www.primpandstyle.com/wc-api/v3/customers/55?consumer_key=ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8&consumer_secret=cs_fe0ba2a0f443603553f9e30b0112644d03ff22ac

                URL urlValidate = new URL("https://www.primpandstyle.com/api/auth/generate_auth_cookie/?username=" + userName + "&password=" + password);
                HttpURLConnection request = (HttpURLConnection) urlValidate.openConnection();
                request.connect();

                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonObject rootobj = root.getAsJsonObject();
                JsonObject jsonUser = rootobj.getAsJsonObject("user");

                LOGIN_STATUS = rootobj.get("status").getAsString();
                Log.i("LOGIN_STATUS", LOGIN_STATUS);
                request.disconnect();

                if (LOGIN_STATUS.equals("ok")) {

                    int userID = jsonUser.get("id").getAsInt();
                    Log.i("userID", ((String.valueOf(userID))));

                    String textUrlDetails = "https://www.primpandstyle.com/wc-api/v3/customers/" + userID + "?consumer_key=ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8&consumer_secret=cs_fe0ba2a0f443603553f9e30b0112644d03ff22ac";
                    Log.d("textUrlDetails", textUrlDetails);

                    URL urlDetails = new URL(textUrlDetails);
                    HttpURLConnection requestDetails = (HttpURLConnection) urlDetails.openConnection();
                    requestDetails.connect();

                    JsonParser jpDetails = new JsonParser();
                    JsonElement rootDetails = jpDetails.parse(new InputStreamReader((InputStream) requestDetails.getContent())); //Convert the input stream to a json element
                    JsonObject rootobjDetails = rootDetails.getAsJsonObject();
                    JsonObject customerDetails = rootobjDetails.getAsJsonObject("customer");
                    JsonObject CustomerBillingAddressDetails = customerDetails.getAsJsonObject("billing_address");
                    JsonObject CustomerShippingAddressDetails = customerDetails.getAsJsonObject("shipping_address");

                    String created_at = customerDetails.get("created_at").getAsString();
                    Log.i("created_at", created_at);

                    String last_update = customerDetails.get("last_update").getAsString();
                    Log.i("last_update", last_update);

                    String email = customerDetails.get("email").getAsString();
                    Log.i("email", email);

                    String first_name = customerDetails.get("first_name").getAsString();
                    Log.i("first_name", first_name);

                    String last_name = customerDetails.get("last_name").getAsString();
                    Log.i("last_name", last_name);

                    String username = customerDetails.get("username").getAsString();
                    Log.i("username", username);

                    String orders_count = customerDetails.get("orders_count").getAsString();
                    Log.i("orders_count", orders_count);

                    String total_spent = customerDetails.get("total_spent").getAsString();
                    Log.i("total_spent", total_spent);

                    String avatar_url = customerDetails.get("avatar_url").getAsString();
                    Log.i("avatar_url", avatar_url);



                    //  BILLING ADDRESS

                    String billing_first_name = CustomerBillingAddressDetails.get("first_name").getAsString();
                    Log.i("billing_first_name", billing_first_name);

                    String billing_last_name = CustomerBillingAddressDetails.get("last_name").getAsString();
                    Log.i("billing_last_name", billing_last_name);

                    String billing_company = CustomerBillingAddressDetails.get("company").getAsString();
                    Log.i("billing_company", billing_company);

                    String billing_address_1 = CustomerBillingAddressDetails.get("address_1").getAsString();
                    Log.i("billing_address_1", billing_address_1);

                    String billing_address_2 = CustomerBillingAddressDetails.get("address_2").getAsString();
                    Log.i("billing_address_2", billing_address_2);

                    String billing_city = CustomerBillingAddressDetails.get("city").getAsString();
                    Log.i("billing_city", billing_city);

                    String billing_state = CustomerBillingAddressDetails.get("state").getAsString();
                    Log.i("billing_state", billing_state);

                    String billing_postcode = CustomerBillingAddressDetails.get("postcode").getAsString();
                    Log.i("billing_postcode", billing_postcode);

                    String billing_country = CustomerBillingAddressDetails.get("country").getAsString();
                    Log.i("billing_country", billing_country);


                    String billing_email = CustomerBillingAddressDetails.get("email").getAsString();
                    Log.i("billing_email", billing_email);

                    String billing_phone = CustomerBillingAddressDetails.get("phone").getAsString();
                    Log.i("billing_phone", billing_phone);



                    //  SHIPPING ADDRESS

                    String shipping_first_name = CustomerShippingAddressDetails.get("first_name").getAsString();
                    Log.i("shipping_first_name", shipping_first_name);

                    String shipping_last_name = CustomerShippingAddressDetails.get("last_name").getAsString();
                    Log.i("shipping_last_name", shipping_last_name);

                    String shipping_company = CustomerShippingAddressDetails.get("company").getAsString();
                    Log.i("shipping_company", shipping_company);

                    String shipping_address_1 = CustomerShippingAddressDetails.get("address_1").getAsString();
                    Log.i("shipping_address_1", shipping_address_1);

                    String shipping_address_2 = CustomerShippingAddressDetails.get("address_2").getAsString();
                    Log.i("shipping_address_2", shipping_address_2);

                    String shipping_city = CustomerShippingAddressDetails.get("city").getAsString();
                    Log.i("shipping_city", shipping_city);

                    String shipping_state = CustomerShippingAddressDetails.get("state").getAsString();
                    Log.i("shipping_state", shipping_state);

                    String shipping_postcode = CustomerShippingAddressDetails.get("postcode").getAsString();
                    Log.i("shipping_postcode", shipping_postcode);

                    String shipping_country = CustomerShippingAddressDetails.get("country").getAsString();
                    Log.i("shipping_country", shipping_country);




                    String cookie = rootobj.get("cookie").getAsString();
                    Log.i("cookie", cookie);

                    String tempCookieName = rootobj.get("cookie_name").getAsString();
                    Log.i("cookie_name", tempCookieName);

                    String cookieAuth = "https://www.primpandstyle.com/api/auth/validate_auth_cookie/?cookie=" + cookie;
                    Log.i("cookieAuth", cookieAuth);

//                    myDb.insertData()

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), userName + " logged in", Toast.LENGTH_LONG).show();
                            }
                        });


                }

                if (LOGIN_STATUS.equals("error")) {

                    tempAuthError = rootobj.get("error").getAsString();
                    Log.i("Auth error", tempAuthError);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), tempAuthError, Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } catch (IOException e) {

                e.printStackTrace();

            }

            return null;

        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pd.dismiss();

        }
    }
}
