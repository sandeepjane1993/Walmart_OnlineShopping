package com.example.sande.walmart_onlineshopping.ui.loginaccess;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.example.sande.walmart_onlineshopping.ui.products.HomePageActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {

    EditText editText_mobile,editText_password;
    String url2 = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php";
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);
        editText_mobile = view.findViewById(R.id.editText_mobile);
        editText_password = view.findViewById(R.id.editText_password);
        TextView tv_createAccount = view.findViewById(R.id.tv_createAccount);
        Button btn_SignIn = view.findViewById(R.id.btn_signIn);

        tv_createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.id_loginActivity,new RegistrationFragment()).addToBackStack("null").commit();
            }
        });


        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading ..... ");
        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.show();
                StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dialog.dismiss();
                       //Toast.makeText(getActivity(), "" + response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject mydata = jsonArray.getJSONObject(0);

                            String mymsg = mydata.getString("msg");
                            String id = mydata.getString("id");
                            String myFirstName =  mydata.getString("firstname");
                            String myLastName =  mydata.getString("lastname");
                            String myEmail =  mydata.getString("email");
                            String myMobile =  mydata.getString("mobile");
                            String myApiKey = mydata.getString("appapikey ");

                                if (mymsg.equals("success"))
                                {

                                    Intent intent = new Intent(getContext(),HomePageActivity.class);
                                    intent.putExtra("user_id",id);
                                    intent.putExtra("fName",myFirstName);
                                    intent.putExtra("lName",myLastName);
                                    intent.putExtra("email",myEmail);
                                    intent.putExtra("mobile",myMobile);
                                    intent.putExtra("API key",myApiKey);

                                    startActivity(intent);

                                }


                        } catch (JSONException e) {
                            Toast.makeText(getActivity(), "wrong details", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        dialog.dismiss();
                        Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
                    }
                })
                {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        Log.i("wheeee", "getParams: hooooooooooooooooooooooooooooooooooo");

                        String mob = editText_mobile.getText().toString();
                         String pass = editText_password.getText().toString();

                        params.put("mobile", mob);
                        params.put("password", pass);
                        return params;
                    }

                };
                RequestQueue request1 = Volley.newRequestQueue(getActivity());
                request1.add(request);

            }
        });
        return view;

    }
}
