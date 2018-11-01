package com.example.sande.walmart_onlineshopping.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.sande.walmart_onlineshopping.database.DbContract.*;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shoppingcart.db";
    private static final int DATABASE_VERSION = 1;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " CREATE TABLE " +
                FeedEntry.TABLE_NAME + " ( " +
                FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FeedEntry.MOBILE_NUM + " TEXT, " +
                FeedEntry.PRODUCT_ID + " TEXT, " +
                FeedEntry.PRODUCT_NAME + " TEXT, " +
                FeedEntry.PRODUCT_QTY +" INTEGER, " +
                FeedEntry.PRODUCT_PRICE + " TEXT, " +
                FeedEntry.PRODUCT_IMAGEURL + " TEXT " + " ) ";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
