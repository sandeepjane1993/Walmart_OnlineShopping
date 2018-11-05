package com.example.sande.walmart_onlineshopping.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.ui.loginaccess.LoginActivity;

public class AccountFragment extends Fragment {

    Button btn_purchaseHistory, btn_topSellers, btn_signOut, btn_personalInfo, btn_resetPassword,btn_location,
            btn_termsOfUse,btn_feedback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_accountbase, container, false);

        btn_purchaseHistory = view.findViewById(R.id.btn_purchaseHistory);
        btn_signOut = view.findViewById(R.id.btn_signOut);
        btn_topSellers = view.findViewById(R.id.btn_topSellers);
        btn_personalInfo = view.findViewById(R.id.btn_personalInfo);
        btn_resetPassword = view.findViewById(R.id.btn_resetPassword);
        btn_location = view.findViewById(R.id.btn_location);
        btn_termsOfUse = view.findViewById(R.id.btn_termsofuse);
        btn_feedback = view.findViewById(R.id.btn_feedback_Account);

        btn_purchaseHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = getArguments();
                String apiKey = b.getString("apiKey");
                String userId = b.getString("userId");
                String mobile = b.getString("mobile");

                PurchaseHistoryFragment purchaseHistoryFragment = new PurchaseHistoryFragment();
                Bundle bundle = new Bundle();
                bundle.putString("apiKey", apiKey);
                bundle.putString("userId", userId);
                bundle.putString("mobile", mobile);
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

                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });
        btn_personalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = getArguments();
                String fName = b.getString("fName");
                String lName = b.getString("lName");
                String email = b.getString("email");
                String mobile = b.getString("mobile");

                PersonalInfoFragment personalInfoFragment = new PersonalInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("fName", fName);
                bundle.putString("lName", lName);
                bundle.putString("email", email);
                bundle.putString("mobile", mobile);
                personalInfoFragment.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.id_AccountBaseActivity, personalInfoFragment).addToBackStack("null").commit();
            }
        });
        btn_resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = getArguments();
                String mobile = b.getString("mobile");

                ResetPasswordFragment resetPasswordFragment = new ResetPasswordFragment();
                Bundle bundle = new Bundle();
                bundle.putString("mobile", mobile);
                resetPasswordFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.id_AccountBaseActivity, resetPasswordFragment).addToBackStack("null").commit();

            }
        });

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.id_AccountBaseActivity, new LocationFragment()).addToBackStack("null").commit();
            }
        });

        btn_termsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.id_AccountBaseActivity, new TermsofUseFragment()).addToBackStack("null").commit();
            }
        });
        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.id_AccountBaseActivity, new FeedbackFragment()).addToBackStack("null").commit();
            }
        });
        return view;
    }
}
