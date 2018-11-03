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
import com.example.sande.walmart_onlineshopping.adapters.ProductListAdaptor;
import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.data.ProductListData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductListFragment extends Fragment implements ProductListAdaptor.ClickListener {

    List<ProductListData> myList;
    ProductListAdaptor adapter;
    RecyclerView recyclerView;
    String url2 = "http://rjtmobile.com/ansari/shopingcart/androidapp/product_details.php";
    String[] pIdArray;
    String[] pNameArray;
    String[] pImageArray;
    String[] pPriceArray;
    String[] pDescriptionArray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_productlist,container,false);


        myList = new ArrayList<>();
        adapter = new ProductListAdaptor(myList,getContext());
        adapter.setClickListener(this);
        recyclerView = view.findViewById(R.id.recyclerView_productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // Toast.makeText(getActivity(), "" + response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("products");
                    pIdArray = new String[jsonArray.length()];
                    pNameArray = new String[jsonArray.length()];
                    pImageArray = new String[jsonArray.length()];
                    pPriceArray = new String[jsonArray.length()];
                    pDescriptionArray = new String[jsonArray.length()];
                    for(int i=0;i< jsonArray.length();i++)
                    {

                        JSONObject mydata = jsonArray.getJSONObject(i);
                        String myProductId = mydata.getString("id");
                        String myProductName = mydata.getString("pname");
                        String myProductQty = mydata.getString("quantity");
                        String myProductPrice = mydata.getString("prize");
                        String myProductDescription = mydata.getString("discription");
                        String myProductImage = mydata.getString("image");

                        pIdArray[i] = myProductId;
                        pNameArray[i] = myProductName;
                        pImageArray[i] = myProductImage;
                        pPriceArray[i] = myProductPrice;
                        pDescriptionArray[i] = myProductDescription;

                        ProductListData data = new ProductListData(myProductImage,myProductName,"$" + myProductPrice,"Free Shipping");
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
                Log.i("passdata", "getParams: hooooooooooooooooooooooooooooooooooo");

                Bundle b = getArguments();
                String cid =   b.getString("key2_cid");
                String scid =   b.getString("key2_scid");
                String apiKey =   b.getString("key2_apiKey");
                String userId =   b.getString("key2_userId");

                Log.i("passdata", "in fragment pl: cid = " + cid + "api = "+ apiKey+"usere_id = "+ userId);
                params.put("cid",cid);
                params.put("scid",scid);
                params.put("api_key", apiKey);
                params.put("user_id", userId);

                return params;
            }

        };
        RequestQueue request4 = Volley.newRequestQueue(getActivity());
        request4.add(request);

        return view;
    }

    @Override
    public void itemClicked(View view, int position) {


        Bundle b = getArguments();
        String cid =   b.getString("key2_cid");
        String userId =   b.getString("key2_userId");
        String fName =   b.getString("key2_fName");
        String lName =   b.getString("key2_lName");
        String email =   b.getString("key2_email");
        String mobile =   b.getString("key2_mobile");
        String apiKey =   b.getString("key2_apiKey");

        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key111",pNameArray[position]);
        bundle.putString("key222",pImageArray[position]);
        bundle.putString("key333",pPriceArray[position]);
        bundle.putString("key444",pDescriptionArray[position]);
        bundle.putString("key555",pIdArray[position]);
        bundle.putString("key3_userId",userId);
        bundle.putString("key3_fName",fName);
        bundle.putString("key3_lName",lName);
        bundle.putString("key3_email",email);
        bundle.putString("key3_mobile",mobile);
        bundle.putString("key3_apiKey",apiKey);

        productDetailFragment.setArguments(bundle);

        getFragmentManager().beginTransaction().
                replace(R.id.id_ProductBaseActivity, productDetailFragment).addToBackStack("null").commit();

    }
}
