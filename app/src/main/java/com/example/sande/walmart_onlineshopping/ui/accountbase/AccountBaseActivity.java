package com.example.sande.walmart_onlineshopping.ui.accountbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sande.walmart_onlineshopping.R;

public class AccountBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_base);

        String apiKey = getIntent().getStringExtra("apiKey");
        String userId = getIntent().getStringExtra("userId");
        String mobile = getIntent().getStringExtra("mobile");
        String fName = getIntent().getStringExtra("fName");
        String lName = getIntent().getStringExtra("lName");
        String email = getIntent().getStringExtra("email");

        AccountFragment accountFragment = new AccountFragment();
        Bundle bundle = new Bundle();
        bundle.putString("apiKey",apiKey);
        bundle.putString("userId",userId);
        bundle.putString("mobile",mobile);
        bundle.putString("fName",fName);
        bundle.putString("lName",lName);
        bundle.putString("email",email);
        accountFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.id_AccountBaseActivity,accountFragment).commit();
    }
}
