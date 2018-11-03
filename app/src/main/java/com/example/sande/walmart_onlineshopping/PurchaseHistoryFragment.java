package com.example.sande.walmart_onlineshopping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sande.walmart_onlineshopping.adapters.PurchaseHistoryAdapter;
import com.example.sande.walmart_onlineshopping.data.PurchaseHistoryData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PurchaseHistoryFragment extends Fragment {

    private List<PurchaseHistoryData> myList;
    PurchaseHistoryAdapter adapter;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchasehistory,container,false);

        myList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView_PurchaseHistory);
        adapter = new PurchaseHistoryAdapter(myList,getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Bundle b = getArguments();
        String apiKey = b.getString("apiKey");
        String userId = b.getString("userId");
        String mobile = b.getString("mobile");


        String url2 = "http://rjtmobile.com/aamir/e-commerce/android-app/order_history.php?" +
                "api_key=" + apiKey +
                "&user_id=" + userId +
                "&mobile=" +mobile;

        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Toast.makeText(getActivity(), "" + response, Toast.LENGTH_LONG).show();
                Log.i("checkResponse", "onResponse: " +response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Order history");
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

                        PurchaseHistoryData purchaseHistoryData = new PurchaseHistoryData(orderId,orderStatus,userName,billing,delivery,mobile,
                                email,itemId,itemName,itemQty,totalPrice,paidPrice,placedOn);
                        myList.add(purchaseHistoryData);

                    }
                    recyclerView.setAdapter(adapter);

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
        RequestQueue purchaseHistoryRequest = Volley.newRequestQueue(getActivity());
        purchaseHistoryRequest.add(request);
        return view;
    }
}
