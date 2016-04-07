package com.pands.dev.pands.oauth;

/**
 * Created by dhen0003 on 11/03/16.
 */
public class OAuthParams {

    String oauth_version;
    String oauth_nonce;
    String oauth_timestamp;
    String oauth_signature;

    public String getOauth_version() {
        return oauth_version;
    }

    public void setOauth_version(String oauth_version) {
        this.oauth_version = oauth_version;
    }

    public String getOauth_nonce() {
        return oauth_nonce;
    }

    public void setOauth_nonce(String oauth_nonce) {
        this.oauth_nonce = oauth_nonce;
    }

    public String getOauth_timestamp() {
        return oauth_timestamp;
    }

    public void setOauth_timestamp(String oauth_timestamp) {
        this.oauth_timestamp = oauth_timestamp;
    }

    public String getOauth_signature() {
        return oauth_signature;
    }

    public void setOauth_signature(String oauth_signature) {
        this.oauth_signature = oauth_signature;
    }
}