package com.example.sande.walmart_onlineshopping.database;

import android.provider.BaseColumns;

public class DbContract {

    DbContract(){}

    public static final class FeedEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "cart";
        public static final String MOBILE_NUM = "mobile";
        public static final String PRODUCT_ID = "pid";
        public static final String PRODUCT_NAME = "pname";
        public static final String PRODUCT_QTY = "quantity";
        public static final String PRODUCT_PRICE = "prize";
        public static final String PRODUCT_IMAGEURL = "imageurl";

    }
}
