package com.pands.dev.pands.oauth;

/**
 * Created by dhen0003 on 10/03/16.
 */

public class EncodesThings {

    String oauth_consumer_key = "ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8";
    String oauth_timestamp = "";
    String oauth_nonce = "";
    String oauth_signature = "";
    String oauth_signature_method = "HMAC-SHA1";

    String seperator = "%26";

    String consumer_key = "oauth_consumer_key%3D"+ oauth_consumer_key;
    String timestamp = "oauth_timestamp%3D" + oauth_timestamp;
    String nonce = "oauth_nonce%3D" + oauth_nonce;
    String signature = "oauth_signature%3D" + oauth_signature;
    String signature_method = "oauth_signature_method%3D" + oauth_signature_method;



}
