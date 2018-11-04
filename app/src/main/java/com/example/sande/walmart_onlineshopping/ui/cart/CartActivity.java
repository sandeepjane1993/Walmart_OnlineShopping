package com.example.sande.walmart_onlineshopping.ui.cart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.ui.products.HomePageActivity;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbar_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.homeButton_AB:


                String userId = getIntent().getStringExtra("userId");
                String fName = getIntent().getStringExtra("fName");
                String email = getIntent().getStringExtra("email");
                String mobile = getIntent().getStringExtra("mobile");
                String apiKey = getIntent().getStringExtra("apiKey");
                String lName = getIntent().getStringExtra("lName");

                Intent intent = new Intent(this,HomePageActivity.class);
                intent.putExtra("user_id",userId);
                intent.putExtra("fName",fName);
                intent.putExtra("lName",lName);
                intent.putExtra("email",email);
                intent.putExtra("mobile",mobile);
                intent.putExtra("API key",apiKey);

                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
