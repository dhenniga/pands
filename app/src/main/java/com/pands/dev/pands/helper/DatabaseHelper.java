package com.pands.dev.pands.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "pands.db";
    public static final String TABLE_NAME = "LoggedInUser";

    private static final String CUSTOMER_ID = "customer_id";
    private static final String CUSTOMER_CREATED_AT = "customer_created_at";
    private static final String CUSTOMER_LAST_UPDATE = "customer_last_update";
    private static final String CUSTOMER_EMAIL = "customer_email";
    private static final String CUSTOMER_FIRST_NAME = "customer_first_name";
    private static final String CUSTOMER_LAST_NAME = "customer_last_name";
    private static final String CUSTOMER_USERNAME = "customer_username";
    private static final String CUSTOMER_LAST_ORDER_ID = "customer_last_order_id";
    private static final String CUSTOMER_LAST_ORDER_DATE = "customer_last_order_date";
//    private static final String CUSTOMER_ORDERS_COUNT = "customer_orders_count";
//    private static final String CUSTOMER_TOTAL_SPENT = "customer_total_spent";
    public static final String CUSTOMER_AVATAR_URL = "customer_avatar_url";
    private static final String BILLING_FIRST_NAME = "billing_first_name";
    private static final String BILLING_LAST_NAME = "billing_last_name";
    private static final String BILLING_COMPANY = "billing_company";
    private static final String BILLING_ADDRESS_1 = "billing_address_1";
    private static final String BILLING_ADDRESS_2 = "billing_address_2";
    private static final String BILLING_CITY = "billing_city";
    private static final String BILLING_STATE = "billing_state";
    private static final String BILLING_POSTCODE = "billing_postcode";
    private static final String BILLING_COUNTRY = "billing_country";
    private static final String BILLING_EMAIL = "billing_email";
    private static final String BILLING_PHONE = "billing_phone";
    private static final String SHIPPING_FIRST_NAME = "shipping_first_name";
    private static final String SHIPPING_LAST_NAME = "shipping_last_name";
    private static final String SHIPPING_COMPANY = "shipping_company";
    private static final String SHIPPING_ADDRESS_1 = "shipping_address_1";
    private static final String SHIPPING_ADDRESS_2 = "shipping_address_2";
    private static final String SHIPPING_CITY = "shipping_city";
    private static final String SHIPPING_STATE = "shipping_state";
    private static final String SHIPPING_POSTCODE = "shipping_postcode";
    private static final String SHIPPING_COUNTRY = "shipping_country";


    /**
     * @param context
     */
    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }


    /**
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE if EXISTS " + TABLE_NAME);

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                "customer_id integer, " +
                "customer_created_at text, " +
                "customer_last_update text, " +
                "customer_email text, " +
                "customer_first_name text, " +
                "customer_last_name text, " +
                "customer_username text, " +
                "customer_last_order_id integer, " +
                "customer_last_order_date text, " +
//                "customer_orders_count integer, " +
//                "customer_total_spent text, " +
                "customer_avatar_url text, " +
                "billing_first_name text, " +
                "billing_last_name text, " +
                "billing_company text, " +
                "billing_address_1 text, " +
                "billing_address_2 text, " +
                "billing_city text, " +
                "billing_state text, " +
                "billing_postcode text, " +
                "billing_country text, " +
                "billing_email text, " +
                "billing_phone text, " +
                "shipping_first_name text, " +
                "shipping_last_name text, " +
                "shipping_company text, " +
                "shipping_address_1 text, " +
                "shipping_address_2 text, " +
                "shipping_city text, " +
                "shipping_state text, " +
                "shipping_postcode text, " +
                "shipping_country text)");
    }


    /**
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE if EXISTS " + TABLE_NAME);
        this.onCreate(db);

    }



    public boolean insertData(
            int customer_id,
            String customer_created_at,
            String customer_last_update,
            String customer_email,
            String customer_first_name,
            String customer_last_name,
            String customer_username,
            int customer_last_order_id,
            String customer_last_order_date,
//            int customer_orders_count,
//            String customer_total_spent,
            String customer_avatar_url,
            String billing_first_name,
            String billing_last_name,
            String billing_company,
            String billing_address_1,
            String billing_address_2,
            String billing_city,
            String billing_state,
            String billing_postcode,
            String billing_country,
            String billing_email,
            String billing_phone,
            String shipping_first_name,
            String shipping_last_name,
            String shipping_company,
            String shipping_address_1,
            String shipping_address_2,
            String shipping_city,
            String shipping_state,
            String shipping_postcode,
            String shipping_country
    ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();

        contentValue.put(CUSTOMER_ID, customer_id);
        contentValue.put(CUSTOMER_CREATED_AT, customer_created_at);
        contentValue.put(CUSTOMER_LAST_UPDATE, customer_last_update);
        contentValue.put(CUSTOMER_EMAIL, customer_email);
        contentValue.put(CUSTOMER_FIRST_NAME, customer_first_name);
        contentValue.put(CUSTOMER_LAST_NAME, customer_last_name);
        contentValue.put(CUSTOMER_USERNAME, customer_username);
        contentValue.put(CUSTOMER_LAST_ORDER_ID, customer_last_order_id);
        contentValue.put(CUSTOMER_LAST_ORDER_DATE, customer_last_order_date);
//        contentValue.put(CUSTOMER_ORDERS_COUNT, customer_orders_count);
//        contentValue.put(CUSTOMER_TOTAL_SPENT, customer_total_spent);
        contentValue.put(CUSTOMER_AVATAR_URL, customer_avatar_url);
        contentValue.put(BILLING_FIRST_NAME, billing_first_name);
        contentValue.put(BILLING_LAST_NAME, billing_last_name);
        contentValue.put(BILLING_COMPANY, billing_company);
        contentValue.put(BILLING_ADDRESS_1, billing_address_1);
        contentValue.put(BILLING_ADDRESS_2, billing_address_2);
        contentValue.put(BILLING_CITY, billing_city);
        contentValue.put(BILLING_STATE, billing_state);
        contentValue.put(BILLING_POSTCODE, billing_postcode);
        contentValue.put(BILLING_COUNTRY, billing_country);
        contentValue.put(BILLING_EMAIL, billing_email);
        contentValue.put(BILLING_PHONE, billing_phone);
        contentValue.put(SHIPPING_FIRST_NAME, shipping_first_name);
        contentValue.put(SHIPPING_LAST_NAME, shipping_last_name);
        contentValue.put(SHIPPING_COMPANY, shipping_company);
        contentValue.put(SHIPPING_ADDRESS_1, shipping_address_1);
        contentValue.put(SHIPPING_ADDRESS_2, shipping_address_2);
        contentValue.put(SHIPPING_CITY, shipping_city);
        contentValue.put(SHIPPING_STATE, shipping_state);
        contentValue.put(SHIPPING_POSTCODE, shipping_postcode);
        contentValue.put(SHIPPING_COUNTRY, shipping_country);

        db.insert(TABLE_NAME, null, contentValue);

        return true;
    }



    /**
     *
     * @return
     */
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * from " + TABLE_NAME, null);
        return res;
    }


    /**
     *
     * @param customer_id
     * @param customer_created_at
     * @param customer_last_update
     * @param customer_email
     * @param customer_first_name
     * @param customer_last_name
     * @param customer_username
     * @param customer_last_order_id
     * @param customer_last_order_date
     * @param customer_avatar_url
     * @param billing_first_name
     * @param billing_last_name
     * @param billing_company
     * @param billing_address_1
     * @param billing_address_2
     * @param billing_city
     * @param billing_state
     * @param billing_postcode
     * @param billing_country
     * @param billing_email
     * @param billing_phone
     * @param shipping_first_name
     * @param shipping_last_name
     * @param shipping_company
     * @param shipping_address_1
     * @param shipping_address_2
     * @param shipping_city
     * @param shipping_state
     * @param shipping_postcode
     * @param shipping_country
     * @return
     */
    public boolean updateData(

            int customer_id,
            String customer_created_at,
            String customer_last_update,
            String customer_email,
            String customer_first_name,
            String customer_last_name,
            String customer_username,
            int customer_last_order_id,
            String customer_last_order_date,
//            int customer_orders_count,
//            String customer_total_spent,
            String customer_avatar_url,
            String billing_first_name,
            String billing_last_name,
            String billing_company,
            String billing_address_1,
            String billing_address_2,
            String billing_city,
            String billing_state,
            String billing_postcode,
            String billing_country,
            String billing_email,
            String billing_phone,
            String shipping_first_name,
            String shipping_last_name,
            String shipping_company,
            String shipping_address_1,
            String shipping_address_2,
            String shipping_city,
            String shipping_state,
            String shipping_postcode,
            String shipping_country


    ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();

        contentValue.put(CUSTOMER_ID, customer_id);
        contentValue.put(CUSTOMER_CREATED_AT, customer_created_at);
        contentValue.put(CUSTOMER_LAST_UPDATE, customer_last_update);
        contentValue.put(CUSTOMER_EMAIL, customer_email);
        contentValue.put(CUSTOMER_FIRST_NAME, customer_first_name);
        contentValue.put(CUSTOMER_LAST_NAME, customer_last_name);
        contentValue.put(CUSTOMER_USERNAME, customer_username);
        contentValue.put(CUSTOMER_LAST_ORDER_ID, customer_last_order_id);
        contentValue.put(CUSTOMER_LAST_ORDER_DATE, customer_last_order_date);
//        contentValue.put(CUSTOMER_ORDERS_COUNT, customer_orders_count);
//        contentValue.put(CUSTOMER_TOTAL_SPENT, customer_total_spent);
        contentValue.put(CUSTOMER_AVATAR_URL, customer_avatar_url);
        contentValue.put(BILLING_FIRST_NAME, billing_first_name);
        contentValue.put(BILLING_LAST_NAME, billing_last_name);
        contentValue.put(BILLING_COMPANY, billing_company);
        contentValue.put(BILLING_ADDRESS_1, billing_address_1);
        contentValue.put(BILLING_ADDRESS_2, billing_address_2);
        contentValue.put(BILLING_CITY, billing_city);
        contentValue.put(BILLING_STATE, billing_state);
        contentValue.put(BILLING_POSTCODE, billing_postcode);
        contentValue.put(BILLING_COUNTRY, billing_country);
        contentValue.put(BILLING_EMAIL, billing_email);
        contentValue.put(BILLING_PHONE, billing_phone);
        contentValue.put(SHIPPING_FIRST_NAME, shipping_first_name);
        contentValue.put(SHIPPING_LAST_NAME, shipping_last_name);
        contentValue.put(SHIPPING_COMPANY, shipping_company);
        contentValue.put(SHIPPING_ADDRESS_1, shipping_address_1);
        contentValue.put(SHIPPING_ADDRESS_2, shipping_address_2);
        contentValue.put(SHIPPING_CITY, shipping_city);
        contentValue.put(SHIPPING_STATE, shipping_state);
        contentValue.put(SHIPPING_POSTCODE, shipping_postcode);
        contentValue.put(SHIPPING_COUNTRY, shipping_country);


        db.update(TABLE_NAME, contentValue, "CUSTOMER_ID = ?", new String[]{((String.valueOf(customer_id)))});

        return true;
    }



    /**
     *
     * @param context
     */
    public void logOutUser(Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
        Log.d(TAG, "Deleted all user info from sqlite");
        context.deleteDatabase(DATABASE_NAME);
    }

}

