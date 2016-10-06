package com.pands.dev.pands;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RegisterActivity extends Activity {

    private static String REG_STATUS = null;
    private static String NONCE_RETURN = null;
    private static String COMPLETED_QUERY = null;
    private static String SUBMIT_STATUS = null;

    String firstName, lastName, emailAddress, password;


    String response = null;

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    private EditText etRegisterFirstName, etRegisterLastName, etRegisterEmail, etRegisterPassword;
    private Button btnRegister, btnRegisterClose, btnLoginFromRegister;
    private TextView tvRegisterHeaderText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final Typeface RalewayLight = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
        final Typeface RalewayBold = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");
        final Typeface PlayFairDisplayItalic = Typeface.createFromAsset(getAssets(), "PlayfairDisplay-Regular.otf");

        tvRegisterHeaderText = (TextView) findViewById(R.id.tvRegisterHeaderText);
        tvRegisterHeaderText.setTypeface(RalewayLight);

        etRegisterFirstName = (EditText) findViewById(R.id.etRegisterFirstName);
        etRegisterFirstName.setTypeface(RalewayLight);

        etRegisterLastName = (EditText) findViewById(R.id.etRegisterLastName);
        etRegisterLastName.setTypeface(RalewayLight);

        etRegisterEmail = (EditText) findViewById(R.id.etRegisterEmail);
        etRegisterEmail.setTypeface(RalewayLight);

        etRegisterPassword = (EditText) findViewById(R.id.etRegisterPassword);
        etRegisterPassword.setTypeface(RalewayLight);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setTypeface(RalewayBold);

        btnLoginFromRegister = (Button) findViewById(R.id.btnLoginFromRegister);
        btnLoginFromRegister.setTypeface(RalewayBold);

        btnRegisterClose = (Button) findViewById(R.id.btnRegisterClose);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new JSONAsync().execute();

            }
        });

        btnRegisterClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

       btnLoginFromRegister .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
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

            firstName = etRegisterFirstName.getText().toString();
            lastName = etRegisterLastName.getText().toString();
            emailAddress = etRegisterEmail.getText().toString();
            password = etRegisterPassword.getText().toString();

            pd = ProgressDialog.show(RegisterActivity.this, null, "Registering " + firstName + " " + lastName, true, false);
        }


        @Override
        protected Void doInBackground(Void... params) {

            try {

                URL url = new URL("https://www.primpandstyle.com/api/get_nonce/?controller=user&method=register");
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.connect();

                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
                JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

                REG_STATUS = rootobj.get("status").getAsString();
                Log.i("REG_STATUS", REG_STATUS);

                NONCE_RETURN = rootobj.get("nonce").getAsString();
                Log.i("NONCE_RETURN", NONCE_RETURN);

                String base = "https://www.primpandstyle.com/api/user/register/";
                String username = "username=" + firstName + lastName;
                String first_name = "first_name=" + firstName;
                String last_name = "last_name=" + lastName;
                String email = "email=" + emailAddress;
                String displayName = "display_name=" + firstName;
                String notify = "notify=both";
                String nonce = "nonce=" + NONCE_RETURN;
                String seconds = "seconds=2";
                String userPassword = "user_pass=" + password;

                COMPLETED_QUERY = base + "?" + username + "&" + first_name + "&" + last_name + "&" + email + "&" + nonce + "&" + seconds + "&" + displayName + "&" + notify + "&" + userPassword;
                Log.i("output", COMPLETED_QUERY);

            } catch (IOException e) {

                e.printStackTrace();

            }

            return null;

        }


        @Override
        protected void onPostExecute(Void result) {

            if (REG_STATUS != null) {

                new Thread(new Runnable() {
                    public void run() {
                        try {
                            String getResponse = doGetRequest(COMPLETED_QUERY);
                            response = getResponse;
                            Log.i("getResponse", getResponse);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        pd.dismiss();

//                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(intent);

                        try {

                            JsonParser jp = new JsonParser();
                            JsonElement root = jp.parse(response);
                            JsonObject rootobj = root.getAsJsonObject();
                            SUBMIT_STATUS = rootobj.get("status").getAsString();

                        } catch (Throwable t) {
                            Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                        }


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                            }
                        });

//                        etRegisterFirstName.setText("");
//                        etRegisterLastName.setText("");
//                        etRegisterEmail.setText("");
//                        etRegisterPassword.setText("");

                    }
                }).start();

            }
        }
    }



    /**
     *
     * @param url
     * @return
     * @throws IOException
     */
    String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
