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


        String myCid = getIntent().getStringExtra("key_cid");
        String myUserId = getIntent().getStringExtra("key_userId");
        String myfName = getIntent().getStringExtra("key_fName");
        String mylName = getIntent().getStringExtra("key_lName");
        String myEmail = getIntent().getStringExtra("key_email");
        String myMobile = getIntent().getStringExtra("key_mobile");
        String myApiKey = getIntent().getStringExtra("key_apiKey");


        SubDepartmentFragment subDepartmentFragment = new SubDepartmentFragment();

        Bundle bundle = new Bundle();
        bundle.putString("key1_cid",myCid);
        bundle.putString("key1_userId",myUserId);
        bundle.putString("key1_fName",myfName);
        bundle.putString("key1_lName",mylName);
        bundle.putString("key1_email",myEmail);
        bundle.putString("key1_mobile",myMobile);
        bundle.putString("key1_apiKey",myApiKey);

        Log.i(TAG, "onCreate: cid = " + myCid +"userId " + myUserId + " api : " + myApiKey );

        subDepartmentFragment.setArguments(bundle);


        getSupportFragmentManager().beginTransaction().
                replace(R.id.id_ProductBaseActivity, subDepartmentFragment).commit();
    }
}
