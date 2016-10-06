package com.pands.dev.pands;

import android.app.ProgressDialog;
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
    SessionManager session;
    SQLiteHandler db;
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

                URL url = new URL("https://www.primpandstyle.com//api/auth/generate_auth_cookie/?username=" + userName + "&password=" + password);
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.connect();

                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
                JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

                LOGIN_STATUS = rootobj.get("status").getAsString();
                Log.i("LOGIN_STATUS", LOGIN_STATUS);

                if (LOGIN_STATUS.equals("ok")) {

                    String tempCookie = rootobj.get("cookie").getAsString();
                    Log.i("cookie", tempCookie);

                    String tempCookieName = rootobj.get("cookie_name").getAsString();
                    Log.i("cookie_name", tempCookieName);

                    String cookieAuth = "http://localhost/api/auth/validate_auth_cookie/?cookie=" + tempCookie;
                    Log.i("cookieAuth", cookieAuth);

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
