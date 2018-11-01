package com.example.sande.walmart_onlineshopping.ui.products;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sande.walmart_onlineshopping.adapters.HomePageAdaptor;
import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.data.HomeDepartmentData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubDepartmentFragment extends Fragment implements HomePageAdaptor.ClickListener {

    private static final String TAG = "passdata";
    List<HomeDepartmentData> myList;
    HomePageAdaptor adapter;
    RecyclerView recyclerView;
    String[] scid;
    String url2 = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sub_department,container,false);


        myList = new ArrayList<>();
        adapter = new HomePageAdaptor(myList,getContext());
        adapter.setClickListener(this);
        recyclerView = view.findViewById(R.id.recyclerView_SubDepartment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               // Toast.makeText(getActivity(), "" + response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("subcategory");
                    scid = new String[jsonArray.length()];
                    for(int i=0;i< jsonArray.length();i++)
                    {

                        JSONObject mydata = jsonArray.getJSONObject(i);
                        String myId = mydata.getString("scid");
                        scid[i] = myId;
                        String myName = mydata.getString("scname");
                        String myImage = mydata.getString("scimageurl");
                        String myDescription = mydata.getString("scdiscription");

                        HomeDepartmentData data = new HomeDepartmentData(myImage,myName);
                        myList.add(data);


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

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Log.i("wheeee", "getParams: hooooooooooooooooooooooooooooooooooo");

                Bundle b = getArguments();
                String cid =   b.getString("key1");
                String apiKey =   b.getString("key2");
                String userId =   b.getString("key3");

                Log.i(TAG, "in fragment: cid = " + cid + "api = "+ apiKey+"usere_id = "+ userId);
                params.put("Id",cid);
                params.put("api_key", apiKey);
                params.put("user_id", userId);

                return params;
            }

        };
        RequestQueue request3 = Volley.newRequestQueue(getActivity());
        request3.add(request);

        return view;
    }


    @Override
    public void itemClicked(View view, int position) {
        Bundle b = getArguments();
        String cid =   b.getString("key1");
        String apiKey =   b.getString("key2");
        String userId =   b.getString("key3");

        ProductListFragment productListFragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key11",cid);
        bundle.putString("key22",scid[position]);
        bundle.putString("key33",apiKey);
        bundle.putString("key44",userId);
        Log.i(TAG, "final scid: " + scid[position]);
        productListFragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.id_ProductBaseActivity,productListFragment).addToBackStack("null").commit();
    }
}
