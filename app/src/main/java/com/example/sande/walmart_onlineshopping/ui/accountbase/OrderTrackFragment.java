package com.example.sande.walmart_onlineshopping.ui.accountbase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sande.walmart_onlineshopping.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderTrackFragment extends Fragment {

    TextView tv_shipmentMsg;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ordertrack,container,false);

        tv_shipmentMsg = view.findViewById(R.id.tv_shipmentMessage);
        Bundle b = getArguments();
        String apiKey = b.getString("apiKey");
        String userId = b.getString("userId");
        String orderId = b.getString("orderId");

        String url2 = "http://rjtmobile.com/aamir/e-commerce/android-app/shipment_track.php?" +
                "api_key=" + apiKey +
                "&user_id=" + userId +
                "&order_id=" + orderId;

        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Shipment track");

                    for (int i = 0; i<jsonArray.length(); i++)
                    {
                        JSONObject myData = jsonArray.getJSONObject(i);
                        String shipmentId = myData.getString("shipmentid");
                        String shipmentStatus = myData.getString("shipmentstatus");

                        switch (shipmentStatus)
                        {
                            case "1":
                                tv_shipmentMsg.setText("Order is confirmed and it will be dispatched soon");
                                break;
                            case "2":
                                tv_shipmentMsg.setText("Order is dispatched and it will be delivered soon");
                                break;
                            case "3":
                                tv_shipmentMsg.setText("Order is on the way. It will be delivered by 8:00 pm today");
                                break;
                            case "4":
                                tv_shipmentMsg.setText("Order has been delivered");
                                break;
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue orderTrackRequest = Volley.newRequestQueue(getActivity());
        orderTrackRequest.add(request);

        return view;

    }
}
