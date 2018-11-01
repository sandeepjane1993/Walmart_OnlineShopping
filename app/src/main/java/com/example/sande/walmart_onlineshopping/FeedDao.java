package com.example.sande.walmart_onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sande.walmart_onlineshopping.data.OrderData;

import java.util.ArrayList;
import java.util.List;

public class FeedDao {

    SQLiteDatabase db;
    DbHelper dbhelper;

    public FeedDao(Context context){
        dbhelper = new DbHelper(context);
    }
    public void openDb(){
        db = dbhelper.getWritableDatabase();
    }
    public void readDb(){
        db = dbhelper.getReadableDatabase();
    }
    public void closeDb()
    {
        db.close();
    }

    public void addToCart(String mobile, String pid, String pname, int quantity, String prize, String image)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.FeedEntry.MOBILE_NUM,mobile);
        contentValues.put(DbContract.FeedEntry.PRODUCT_ID,pid);
        contentValues.put(DbContract.FeedEntry.PRODUCT_NAME,pname);
        contentValues.put(DbContract.FeedEntry.PRODUCT_QTY,quantity);
        contentValues.put(DbContract.FeedEntry.PRODUCT_PRICE,prize);
        contentValues.put(DbContract.FeedEntry.PRODUCT_IMAGEURL,image);
        db.insert(DbContract.FeedEntry.TABLE_NAME,null,contentValues);

    }

    public List<OrderData> getToCart()
    {
        List<OrderData> list = new ArrayList<>();
        Cursor cursor = db.query(DbContract.FeedEntry.TABLE_NAME,null,null,null,null,
                null,null);
        if(cursor.moveToFirst())
        {
            do {
                {
                    OrderData orderData = new OrderData();
                    orderData.setPid(cursor.getString(2));
                    orderData.setPname(cursor.getString(3));
                    orderData.setQuantity(cursor.getInt(4));
                    orderData.setPrize(cursor.getString(5));
                    orderData.setImage(cursor.getString(6));
                    list.add(orderData);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  list;
    }

    public int checkCart(String pid, String mobile)
    {
        int quantity = 0;
        String sql = "SELECT quantity FROM " + DbContract.FeedEntry.TABLE_NAME + " WHERE pid =" + "\"" + pid + "\"" + "AND mobile =" + " " + mobile + " ";
        //String sql = "SELECT quantity FROM " + cartTable + " WHERE pid =" + " " + id + " " + "AND mobile =" + " " + mobile + " ";
        Cursor c = db.rawQuery(sql,null);
        c.moveToLast();
        quantity = c.getInt(0);
        return quantity;
    }
}
