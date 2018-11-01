package com.example.sande.walmart_onlineshopping.ui.cart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sande.walmart_onlineshopping.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportFragmentManager().beginTransaction().replace(R.id.id_CartActivity, new CartFragment()).commit();
    }
}
