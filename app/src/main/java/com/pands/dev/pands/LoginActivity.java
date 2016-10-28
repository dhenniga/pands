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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pands.dev.pands.helper.DatabaseHelper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    String userName, password, LOGIN_STATUS, tempAuthError;
    EditText etLoginUsername, etLoginPassword;
    Button btnLogin, btnRegister;
    String response = null;

    int customer_id, customer_last_order_id, customer_orders_count;
    String customer_created_at, customer_last_update, customer_email, customer_first_name, customer_last_name, customer_username, customer_last_order_date, billing_first_name, billing_last_name, billing_company, billing_address_1, billing_address_2, billing_city, billing_state, billing_postcode, billing_country, billing_email, billing_phone, shipping_first_name, shipping_last_name, shipping_company, shipping_address_1, shipping_address_2, shipping_city, shipping_state, shipping_postcode, shipping_country;
//    String customer_total_spent;
    public static String customer_avatar_url;


//    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//    private OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDb = new DatabaseHelper(this);
        myDb.logOutUser(getApplicationContext());

        final Typeface RalewayLight = Typeface.createFromAsset(getAssets(), "Raleway-Regular.otf");
        final Typeface RalewayBold = Typeface.createFromAsset(getAssets(), "Raleway-Bold.otf");

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
                    JsonElement rootDetails = jpDetails.parse(new InputStreamReader((InputStream) requestDetails.getContent()));
                    JsonObject rootobjDetails = rootDetails.getAsJsonObject();
                    JsonObject customerDetails = rootobjDetails.getAsJsonObject("customer");
                    JsonObject CustomerBillingAddressDetails = customerDetails.getAsJsonObject("billing_address");
                    JsonObject CustomerShippingAddressDetails = customerDetails.getAsJsonObject("shipping_address");


                    customer_id = customerDetails.get("id").getAsInt();
                    customer_created_at = customerDetails.get("created_at").getAsString();
                    customer_last_update = customerDetails.get("last_update").getAsString();
                    customer_email = customerDetails.get("email").getAsString();
                    customer_first_name = customerDetails.get("first_name").getAsString();
                    customer_last_name = customerDetails.get("last_name").getAsString();
                    customer_username = customerDetails.get("username").getAsString();

                    Boolean customer_last_order_id_boolean = customerDetails.get("last_order_id").isJsonNull();
                    if (!customer_last_order_id_boolean) {
                        customer_last_order_id = customerDetails.get("last_order_id").getAsInt();
                        Log.i("last_order_id", ((String.valueOf(customer_last_order_id))));
                    }

                    Boolean customer_last_order_date_boolean = customerDetails.get("last_order_date").isJsonNull();
                    if (!customer_last_order_date_boolean) {
                        customer_last_order_date = customerDetails.get("last_order_date").getAsString();
                        Log.i("last_order_date", customer_last_order_date);
                    }

//                    customer_orders_count = customerDetails.get("orders_count").getAsInt();
//                    customer_total_spent = customerDetails.get("total_spent").getAsBigDecimal();

                    Boolean customer_avatar_url_boolean = customerDetails.get("avatar_url").isJsonNull();
                    if (!customer_avatar_url_boolean) {
                        customer_avatar_url = customerDetails.get("avatar_url").getAsString();
                    }

                    //  BILLING ADDRESS

                    billing_first_name = CustomerBillingAddressDetails.get("first_name").getAsString();
                    billing_last_name = CustomerBillingAddressDetails.get("last_name").getAsString();
                    billing_company = CustomerBillingAddressDetails.get("company").getAsString();
                    billing_address_1 = CustomerBillingAddressDetails.get("address_1").getAsString();
                    billing_address_2 = CustomerBillingAddressDetails.get("address_2").getAsString();
                    billing_city = CustomerBillingAddressDetails.get("city").getAsString();
                    billing_state = CustomerBillingAddressDetails.get("state").getAsString();
                    billing_postcode = CustomerBillingAddressDetails.get("postcode").getAsString();
                    billing_country = CustomerBillingAddressDetails.get("country").getAsString();
                    billing_email = CustomerBillingAddressDetails.get("email").getAsString();
                    billing_phone = CustomerBillingAddressDetails.get("phone").getAsString();


                    //  SHIPPING ADDRESS

                    shipping_first_name = CustomerShippingAddressDetails.get("first_name").getAsString();
                    shipping_last_name = CustomerShippingAddressDetails.get("last_name").getAsString();
                    shipping_company = CustomerShippingAddressDetails.get("company").getAsString();
                    shipping_address_1 = CustomerShippingAddressDetails.get("address_1").getAsString();
                    shipping_address_2 = CustomerShippingAddressDetails.get("address_2").getAsString();
                    shipping_city = CustomerShippingAddressDetails.get("city").getAsString();
                    shipping_state = CustomerShippingAddressDetails.get("state").getAsString();
                    shipping_postcode = CustomerShippingAddressDetails.get("postcode").getAsString();
                    shipping_country = CustomerShippingAddressDetails.get("country").getAsString();

                    String cookie = rootobj.get("cookie").getAsString();
                    String tempCookieName = rootobj.get("cookie_name").getAsString();
                    String cookieAuth = "https://www.primpandstyle.com/api/auth/validate_auth_cookie/?cookie=" + cookie;

                    myDb.insertData(
                            customer_id,
                            customer_created_at,
                            customer_last_update,
                            customer_email,
                            customer_first_name,
                            customer_last_name,
                            customer_username,
                            customer_last_order_id,
                            customer_last_order_date,
//                            customer_orders_count,
//                            customer_total_spent,
                            customer_avatar_url,
                            billing_first_name,
                            billing_last_name,
                            billing_company,
                            billing_address_1,
                            billing_address_2,
                            billing_city,
                            billing_state,
                            billing_postcode,
                            billing_country,
                            billing_email,
                            billing_phone,
                            shipping_first_name,
                            shipping_last_name,
                            shipping_company,
                            shipping_address_1,
                            shipping_address_2,
                            shipping_city,
                            shipping_state,
                            shipping_postcode,
                            shipping_country
                    );

                    myDb.close();

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
