package com.pands.dev.pands;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pands.dev.pands.product.ProductAdapter;
import com.pands.dev.pands.product.ProductParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;

public class RegisterActivity extends Activity {

    public static String REG_STATUS = null;
    public static String FINAL_REG_STATUS = null;
    public static String NONCE_RETURN = null;
    public static String COMPLETED_QUERY = null;

    private EditText etRegisterFirstName, etRegisterLastName, etRegisterEmail, etRegisterPassword;
    private Button btnRegister;
    private TextView tvRegisterHeaderText;
    private WebView wvResponse;


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

        wvResponse = (WebView) findViewById(R.id.wvResponse);

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

            } catch (IOException e) {

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

            COMPLETED_QUERY = base + "?" + username + "&" + first_name + "&" + last_name + "&" + email + "&" + nonce + "&" + seconds + "&" + displayName + "&" + notify + "&" + userPassword;
            Log.i("output", COMPLETED_QUERY);


            if (REG_STATUS != null) {

                pd.dismiss();

                etRegisterFirstName.setText("");
                etRegisterLastName.setText("");
                etRegisterEmail.setText("");
                etRegisterPassword.setText("");


                wvResponse.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        wvResponse.loadUrl("javascript:window.HtmlViewer.showHTML" +
                                "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                    }
                });


//                wvResponse.loadUrl(COMPLETED_QUERY);

//                WebViewClient yourWebClient = new WebViewClient() {
//
//                    @Override
//                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                        return false;
//                    }
//
//                    @Override
//                    public void onPageFinished(WebView view, String url) {
////                        wvResponse.loadUrl(COMPLETED_QUERY);
//                    }
//                };








//                wvResponse.getSettings().setJavaScriptEnabled(true);
//                wvResponse.getSettings().setSupportZoom(true);
//                wvResponse.getSettings().setBuiltInZoomControls(true);
//                wvResponse.setWebViewClient(yourWebClient);
//                wvResponse.loadUrl(COMPLETED_QUERY);
//                wvResponse.addJavascriptInterface(new MyJavaScriptInterface(getApplicationContext()), "HtmlViewer");


                wvResponse.loadUrl(COMPLETED_QUERY);
                Toast.makeText(getApplicationContext(), wvResponse.toString(), Toast.LENGTH_SHORT).show();

            }
        }
    }


//    class MyJavaScriptInterface {
//
//        private Context ctx;
//
//        MyJavaScriptInterface(Context ctx) {
//            this.ctx = ctx;
//        }
//
//        @JavascriptInterface
//        public void showHTML(String html) {
//            Log.d("response" , html);
////            System.out.println(html);
//
//        }
//    }
}
