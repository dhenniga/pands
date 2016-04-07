package com.pands.dev.pands.oauth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;
import java.math.BigInteger;

public class OAuth implements Serializable {

    private String oauth_base_url = "http://primpandstyle.com/wc-api/v3/";
    private String oauth_consumer_key = "6ck_962b3c0e86f61ebef52ddb90f5721dcc5d2c5fc8";
    private String oauth_consumer_secret = "cs_fe0ba2a0f443603553f9e30b0112644d03ff22ac";
    private String oauth_signature_method = "HMAC-SHA1";
    private String oauth_timestamp = "";
    private String oauth_nonce = "";
    private String oauth_signature = "";


    public OAuth(String oauth_consumer_key, String oauth_consumer_secret) {
        super();
        this.oauth_consumer_key = oauth_consumer_key;
        this.oauth_consumer_secret = oauth_consumer_secret;
    }

    public List<QParameter> getParams() {
        List<QParameter> parameters = new ArrayList<QParameter>();
        oauth_timestamp = this.generateTimeStamp();
        oauth_nonce = this.generateNonce();

        if(null!=oauth_nonce && !"".equals(oauth_nonce.trim()))
            parameters.add(new QParameter("oauth_nonce", oauth_nonce));
        if(null!=oauth_timestamp && !"".equals(oauth_timestamp.trim()))
            parameters.add(new QParameter("oauth_timestamp", oauth_timestamp));
        if(null!=oauth_consumer_key && !"".equals(oauth_consumer_key.trim()))
            parameters.add(new QParameter("oauth_consumer_key", oauth_consumer_key));
        if(null!=oauth_signature_method && !"".equals(oauth_signature_method.trim()))
            parameters.add(new QParameter("oauth_signature_method", oauth_signature_method));
        if(null!=oauth_signature && !"".equals(oauth_signature.trim()))
            parameters.add(new QParameter("oauth_signature", oauth_signature));

        return parameters;
    }


    public List<QParameter> getTokenParams() {
        List<QParameter> parameters = new ArrayList<>();

        oauth_timestamp = this.generateTimeStamp();
        oauth_nonce = this.generateNonce();
        //oauth_signature = com.pands.dev.pands.oauth.HmacSha1Signature.calculateRFC2104HMAC();

        parameters.add(new QParameter("oauth_base_url", oauth_base_url));
        parameters.add(new QParameter("oauth_nonce", oauth_nonce));
        parameters.add(new QParameter("oauth_timestamp", oauth_timestamp));
        parameters.add(new QParameter("oauth_consumer_key", oauth_consumer_key));
        parameters.add(new QParameter("oauth_signature_method", oauth_signature_method));
        parameters.add(new QParameter("oauth_signature", oauth_signature));
        return parameters;
    }


    /**
     * Generate the timestamp for the signature.
     *
     * @return
     */
    private String generateTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }


    /**
     * Generate the Nonce (32 alpha-numeric number)
     *
     * @return
     */
    public String generateNonce() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

}

