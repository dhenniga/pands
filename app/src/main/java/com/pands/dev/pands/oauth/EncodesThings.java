package com.pands.dev.pands.oauth;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by dhen0003 on 10/03/16.
 */

public class EncodesThings {

    static String BASE_URL = "http://primpandstyle.com/wc-api/v3/";
    String oauth_nonce = "ac75648b7c646e66a26a39700744f670";
    String oauth_timestamp = "1457650747";
    String oauth_consumer_key = "ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8";
    String oauth_signature_method = "HMAC-SHA1";
    String oauth_signature = "bGcA0PBRfjN3HERS81iw7VKEV44%3D";



}
