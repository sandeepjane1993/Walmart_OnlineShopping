package com.example.sande.walmart_onlineshopping.account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.adapters.HomePageAdaptor;
import com.example.sande.walmart_onlineshopping.adapters.TopSellersAdaptor;
import com.example.sande.walmart_onlineshopping.data.HomeDepartmentData;
import com.example.sande.walmart_onlineshopping.data.TopSellersData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopSellersFragment extends Fragment {


     private List<TopSellersData> myList;
    TopSellersAdaptor adapter;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topsellers,container,false);

        myList = new ArrayList<>();
        adapter = new TopSellersAdaptor(myList,getActivity());
        recyclerView = view.findViewById(R.id.recyclerView_topSellers);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        String url2 = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_top_sellers.php?";

        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Top sellers");

                    for (int i = 0; i<jsonArray.length(); i++)
                    {
                        JSONObject myData = jsonArray.getJSONObject(i);
                        String sellerName = myData.getString("sellername");
                        String sellerDeal = myData.getString("sellerdeal");
                        float sellerRating = Float.parseFloat(myData.getString("sellerrating"));
                        String sellerLogo = myData.getString("sellerlogo");

                        TopSellersData topSellersData = new TopSellersData(sellerName,sellerDeal,sellerRating,sellerLogo);
                        myList.add(topSellersData);
                    }
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue topSellerRequest = Volley.newRequestQueue(getActivity());
        topSellerRequest.add(request);

        return view;
    }
}
