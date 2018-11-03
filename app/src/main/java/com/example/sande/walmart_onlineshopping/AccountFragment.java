package com.example.sande.walmart_onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sande.walmart_onlineshopping.ui.loginaccess.LoginActivity;

public class AccountFragment extends Fragment {

    Button btn_purchaseHistory,btn_topSellers,btn_signOut;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_accountbase,container,false);

        btn_purchaseHistory = view.findViewById(R.id.btn_purchaseHistory);
        btn_signOut = view.findViewById(R.id.btn_signOut);
        btn_topSellers = view.findViewById(R.id.btn_topSellers);
        btn_purchaseHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = getArguments();
                String apiKey = b.getString("apiKey");
                String userId = b.getString("userId");
                String mobile = b.getString("mobile");

                PurchaseHistoryFragment purchaseHistoryFragment = new PurchaseHistoryFragment();
                Bundle bundle = new Bundle();
                bundle.putString("apiKey",apiKey);
                bundle.putString("userId",userId);
                bundle.putString("mobile",mobile);
                purchaseHistoryFragment.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.id_AccountBaseActivity, purchaseHistoryFragment).addToBackStack("null").commit();
            }
        });
        btn_topSellers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.id_AccountBaseActivity, new TopSellersFragment()).addToBackStack("null").commit();
            }
        });
        btn_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),LoginActivity.class);
                startActivity(i);
            }
        });

        return  view;
    }
}
