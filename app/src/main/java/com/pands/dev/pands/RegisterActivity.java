package com.pands.dev.pands;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pands.dev.pands.product.ProductAdapter;
import com.pands.dev.pands.product.ProductParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;

public class RegisterActivity extends AppCompatActivity {

    public static String REG_STATUS = null;
    public static String NONCE_RETURN = null;
    public static String COMPLETED_QUERY = null;

    EditText etRegisterFirstName, etRegisterLastName, etRegisterEmail, etRegisterPassword;
    Button btnRegister;
    TextView tvRegisterHeaderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final Typeface RalewayLight = Typeface.createFromAsset(getAssets(), "Raleway-Regular.ttf");
        final Typeface RalewayBold = Typeface.createFromAsset(getAssets(), "Raleway-Bold.ttf");
        final Typeface PlayFairDisplayItalic = Typeface.createFromAsset(getAssets(), "PlayfairDisplay-Regular.otf");

        tvRegisterHeaderText = (TextView) findViewById(R.id.tvRegisterHeaderText);
        tvRegisterHeaderText.setTypeface(PlayFairDisplayItalic);

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



String start = "https://www.primpandstyle.com/api/user/register/?first_name=john&last_name=Stokes&email=johnStokes@crackpipes.com&nonce=8bdfeb4e16&display_name=John&notify=both&user_pass=YOUR-PASSWORD";


        String nonce = "https://www.primpandstyle.com/api/get_nonce/?controller=user&method=register";


        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new JSONAsync().execute();





            }
        });

    }

    class JSONAsync extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(RegisterActivity.this, null, "Registering...", true, false);
        }


        @Override
        protected Void doInBackground(Void... params) {

            try {

                URL url = new URL("https://www.primpandstyle.com/api/get_nonce/?json=get_nonce&controller=user&method=register");
//                URL url = new URL("https://www.primpandstyle.com/api/get_nonce/?controller=user&method=register");
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.connect();

                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
                JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

                REG_STATUS = rootobj.get("status").getAsString();
                Log.i("REG_STATUS", REG_STATUS);

                NONCE_RETURN = rootobj.get("nonce").getAsString();
                Log.i("NONCE_RETURN", NONCE_RETURN);

            } catch (IOException e){

                e.printStackTrace();

            }

            return null;

        }


        @Override
        protected void onPostExecute(Void result) {

            String firstName = etRegisterFirstName.getText().toString();
            String lastName = etRegisterLastName.getText().toString();
            String emailAddress = etRegisterEmail.getText().toString();
            String password = etRegisterPassword.getText().toString();

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

            COMPLETED_QUERY = base + "?" + username + "&" + first_name + "&" + last_name  + "&" + email + "&" + nonce + "&" + seconds + "&" + displayName + "&" + notify + "&" + userPassword;
            Log.i("output", COMPLETED_QUERY);


            if (REG_STATUS != null) {

                pd.dismiss();

            }
        }
    }
}
