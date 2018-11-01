package com.example.sande.walmart_onlineshopping.ui.products;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sande.walmart_onlineshopping.R;

public class ProductBaseActivity extends AppCompatActivity {

    private static final String TAG = "passdata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_base);


        String cid = getIntent().getStringExtra("key1");
        String apiKey = getIntent().getStringExtra("key2");
        String userId = getIntent().getStringExtra("key3");

        SubDepartmentFragment subDepartmentFragment = new SubDepartmentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key1",cid);
        bundle.putString("key2",apiKey);
        bundle.putString("key3",userId);
        Log.i(TAG, "on received: cid = " + cid + "api = "+ apiKey+"usere_id = "+ userId);
        subDepartmentFragment.setArguments(bundle);


        getSupportFragmentManager().beginTransaction().
                replace(R.id.id_ProductBaseActivity, subDepartmentFragment).commit();
    }
}
