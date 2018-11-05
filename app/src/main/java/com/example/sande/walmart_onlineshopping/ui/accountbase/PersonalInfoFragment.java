package com.example.sande.walmart_onlineshopping.ui.accountbase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sande.walmart_onlineshopping.R;

public class PersonalInfoFragment extends Fragment {

    TextView tv_personalInfo;
    EditText et_firstName,et_lastName,et_address,et_email;
    Button btn_updatePI;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);

        tv_personalInfo = view.findViewById(R.id.tv_PersonalInfo_PI);
        et_firstName = view.findViewById(R.id.et_firstName_PI);
        et_lastName = view.findViewById(R.id.et_lastName_PI);
        et_address = view.findViewById(R.id.et_address_PI);
        et_email = view.findViewById(R.id.et_email_PI);
        btn_updatePI = view.findViewById(R.id.btn_updateInfo_PI);

        Bundle b = getArguments();
        String fName =   b.getString("fName");
        String lName =   b.getString("lName");
        String email =   b.getString("email");
        final String mobile =   b.getString("mobile");

        tv_personalInfo.setText("First Name : " + fName + "\n" +
                "Last Name : " + lName + "\n" +
                "Address : " + "Chicago" + "\n" +
                "Email : " + email + "\n" +
                "Mobile : " + mobile);

        btn_updatePI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fn = et_firstName.getText().toString();
                String ln = et_lastName.getText().toString();
                String etAddress = et_address.getText().toString();
                String etEmail = et_email.getText().toString();

                String url = "http://rjtmobile.com/aamir/e-commerce/android-app/edit_profile.php?" +
                        "fname=" +fn+
                        "&lname=" +ln+
                        "&address=" +etAddress+
                        "& email="+etEmail+
                        "&mobile=" +mobile;

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), "" + response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                RequestQueue updateInfoRequest = Volley.newRequestQueue(getActivity());
                updateInfoRequest.add(request);
            }
        });

        return view;
    }
}
