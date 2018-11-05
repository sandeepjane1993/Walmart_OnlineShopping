package com.example.sande.walmart_onlineshopping.ui.accountbase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sande.walmart_onlineshopping.R;

public class ResetPasswordFragment extends Fragment {

    Button btn_resetPassword;
    EditText et_currentPassword,et_newPassword;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        btn_resetPassword = view.findViewById(R.id.btn_reset_RP);
        et_currentPassword = view.findViewById(R.id.et_currentPassword_RP);
        et_newPassword = view.findViewById(R.id.et_newPassword_RP);

        Bundle b = getArguments();
        final String mobile = b.getString("mobile");

        btn_resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cp = et_currentPassword.getText().toString();
                String np = et_newPassword.getText().toString();

                String url = " http://rjtmobile.com/aamir/e-commerce/android-app/shop_reset_pass.php?" +
                        "&mobile=" +mobile+
                        "&password=" +cp+
                        "&newpassword=" +np;

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), "password reset successfully" , Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                RequestQueue resetPasswordRequest = Volley.newRequestQueue(getActivity());
                resetPasswordRequest.add(request);
            }
        });

        return view;
    }
}
