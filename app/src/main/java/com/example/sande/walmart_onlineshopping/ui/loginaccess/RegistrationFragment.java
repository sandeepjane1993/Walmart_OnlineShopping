package com.example.sande.walmart_onlineshopping.ui.loginaccess;

import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.example.sande.walmart_onlineshopping.ui.loginaccess.LoginFragment;

import java.util.HashMap;
import java.util.Map;

public class RegistrationFragment extends Fragment {


    EditText et_fname, et_lname, et_address, et_email, et_mobile, et_password;
    Button btn_create;
    String url2 = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reg.php";
    ProgressDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration,container,false);

        et_fname = view.findViewById(R.id.etext_fname);
        et_lname = view.findViewById(R.id.etext_lname);
        et_address = view.findViewById(R.id.etext_address);
        et_email = view.findViewById(R.id.etext_email);
        et_mobile = view.findViewById(R.id.etext_mobile);
        et_password = view.findViewById(R.id.etext_password);
        btn_create = view.findViewById(R.id.btn_create);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading ..... ");


        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

                if (et_password.getText().toString().length() < 6) {
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Password is too short", Toast.LENGTH_SHORT).show();

                } else if (et_mobile.getText().toString().length() < 10) {
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Mobile number should be 10 digits", Toast.LENGTH_SHORT).show();

                } else {
                    StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "" + response, Toast.LENGTH_SHORT).show();
                            if (response.equals("successfully registered")) {
                                getFragmentManager().beginTransaction().replace(R.id.id_loginActivity, new LoginFragment()).commit();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            dialog.dismiss();
                            Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();

                            String firstname = et_fname.getText().toString();
                            String lastname = et_lname.getText().toString();
                            String address = et_address.getText().toString();
                            String email = et_email.getText().toString();
                            String mobile = et_mobile.getText().toString();
                            String password = et_password.getText().toString();

                            params.put("fname", firstname);
                            params.put("lname", lastname);
                            params.put("address", address);
                            params.put("email", email);
                            params.put("mobile", mobile);
                            params.put("password", password);

                            return params;
                        }

                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

                    requestQueue.add(request);
                }
            }
        });

        return view;
    }
}
