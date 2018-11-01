package com.example.sande.walmart_onlineshopping.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sande.walmart_onlineshopping.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction().replace(R.id.id_loginActivity,
                new LoginFragment()).addToBackStack("null").commit();
    }
}
