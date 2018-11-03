package com.example.sande.walmart_onlineshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sande.walmart_onlineshopping.data.HomeDepartmentData;
import com.example.sande.walmart_onlineshopping.data.OrderData;
import com.example.sande.walmart_onlineshopping.database.FeedDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderSummary extends AppCompatActivity {

    private List<OrderData> myList;
    FeedDao feedDao;

    String itemNames;
    String itemIds;
    int itemQty;
    TextView tv_orderSummary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        myList = new ArrayList<>();
        feedDao = new FeedDao(this);
        feedDao.openDb();
        myList = feedDao.getToCart();
        tv_orderSummary = findViewById(R.id.tv_orderSummary_OS);

        String userId = getIntent().getStringExtra("userId");
        String fName = getIntent().getStringExtra("fName");
        String mobile = getIntent().getStringExtra("mobile");
        String apiKey = getIntent().getStringExtra("apiKey");
        String email = getIntent().getStringExtra("email");

        int subtotal =0;
        itemIds = "";
        itemNames ="";
        for(int i=0; i<myList.size();i++)
        {
            itemIds +=  myList.get(i).getPid() + ",";
            itemNames += myList.get(i).getPname()+ ",";
            itemQty += myList.get(i).getQuantity();
            subtotal = subtotal + (Integer.parseInt(myList.get(i).getPrize()) * myList.get(i).getQuantity());
        }
        int totPrice = (int) (subtotal + (subtotal * 0.08));
        String tp = "" + totPrice;
        String iQty = "" + itemQty;

        String url2 = "http://rjtmobile.com/aamir/e-commerce/android-app/orders.php?" +
                "?&item_id="+itemIds+
                "&item_names="+itemNames+
                "&item_quantity="+iQty+
                "&final_price="+tp+
                "&&api_key="+apiKey +
                "&user_id="+userId+
                "&user_name="+fName +
                "&billingadd="+"Chicago" +
                "&deliveryadd="+"Chicago"+
                "&mobile="+mobile +
                "&email="+email;

        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                 Toast.makeText(OrderSummary.this, "" + response, Toast.LENGTH_LONG).show();
                Log.i("checkResponse", "onResponse: " +response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Order confirmed");
                    for(int i=0;i< jsonArray.length();i++)
                    {
                        JSONObject mydata = jsonArray.getJSONObject(i);
                        String orderId = mydata.getString("orderid");
                        String orderStatus = mydata.getString("orderstatus");
                        String userName = mydata.getString("name");
                        String billing = mydata.getString("billingadd");
                        String delivery = mydata.getString("deliveryadd");
                        String mobile = mydata.getString("mobile");
                        String email = mydata.getString("email");
                        String itemId = mydata.getString("itemid");
                        String itemName = mydata.getString("itemname");
                        String itemQty = mydata.getString("itemquantity");
                        String totalPrice = mydata.getString("totalprice");
                        String paidPrice = mydata.getString("paidprice");
                        String placedOn = mydata.getString("placedon");

                        Log.i("final", "onResponse: " + mobile + email + itemName + itemId);

                        tv_orderSummary.setText("Order Id : " + orderId + "\n" +
                                "Order Status : " + orderStatus + "\n" +
                                "User Name : " + userName + "\n" +
                                "Billing Address : " + billing + "\n" +
                                "Delivery Address : " + delivery + "\n" +
                                "Mobile Number : " + mobile + "\n" +
                                "Email : " + email + "\n" +
                                "Item Id : " + itemId + "\n" +
                                "Item Name : " + itemName + "\n" +
                                "Item Qty : " + itemQty + "\n" +
                                "Total Price : " + totalPrice + "\n" +
                                "Paid Price : " + paidPrice + "\n" +
                                "Date & Time : " + placedOn);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                /*dialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();*/
            }
        })
        {

        };
        RequestQueue request5 = Volley.newRequestQueue(this);
        request5.add(request);

    }
}
