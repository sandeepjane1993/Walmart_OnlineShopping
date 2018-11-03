package com.example.sande.walmart_onlineshopping.ui.cart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sande.walmart_onlineshopping.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        String userId = getIntent().getStringExtra("userId");
        String fName = getIntent().getStringExtra("fName");
        String email = getIntent().getStringExtra("email");
        String mobile = getIntent().getStringExtra("mobile");
        String apiKey = getIntent().getStringExtra("apiKey");

        Log.i("cartActivity", "User Id in Card Activity" + userId + "  " + apiKey);
        CartFragment cartFragment = new CartFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId",userId);
        bundle.putString("fName",fName);
        bundle.putString("email",email);
        bundle.putString("mobile",mobile);
        bundle.putString("apiKey",apiKey);
        Log.i("cartActivity", "User Id in Card Activity" + userId + "  " + apiKey);
        cartFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.id_CartActivity, cartFragment).commit();
    }
}
