package com.example.sande.walmart_onlineshopping.ui.products;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sande.walmart_onlineshopping.account.AccountBaseActivity;
import com.example.sande.walmart_onlineshopping.adapters.HomePageAdaptor;
import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.data.HomeDepartmentData;
import com.example.sande.walmart_onlineshopping.ui.cart.CartActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,HomePageAdaptor.ClickListener {

    private static final String TAG = "passdata";
    int[] images = {R.drawable.pic_vf_food1, R.drawable.pic_vf_womenclothing, R.drawable.pic_vf_friday,
            R.drawable.pic_vf_haloween, R.drawable.pic_vf_food2, R.drawable.pic_vf_iphone, R.drawable.pic_vf_clothing};
    String[] department_name = {"food1", "women", "Black Friday", "Haloween", "food2", "i Phone", "Clothing"};

    List<HomeDepartmentData> myList;
    HomePageAdaptor adapter;
    RecyclerView recyclerView;
    ViewFlipper flipper;
    String[] cidArray;
    CardView cardView;
    String url2 ="http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php";


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_home:
                            return true;
                        case R.id.navigation_cart:

                            Intent cart = new Intent(HomePageActivity.this,CartActivity.class);
                            cart.putExtra("apiKey",getIntent().getStringExtra("API key"));
                            cart.putExtra("userId",getIntent().getStringExtra("user_id"));
                            cart.putExtra("mobile",getIntent().getStringExtra("mobile"));
                            cart.putExtra("fName",getIntent().getStringExtra("fName"));
                            cart.putExtra("lName",getIntent().getStringExtra("lName"));
                            cart.putExtra("email",getIntent().getStringExtra("email"));
                            startActivity(cart);
                            return true;
                        case R.id.navigation_Account:

                            Intent account = new Intent(HomePageActivity.this,AccountBaseActivity.class);
                            account.putExtra("apiKey",getIntent().getStringExtra("API key"));
                            account.putExtra("userId",getIntent().getStringExtra("user_id"));
                            account.putExtra("mobile",getIntent().getStringExtra("mobile"));
                            account.putExtra("fName",getIntent().getStringExtra("fName"));
                            account.putExtra("lName",getIntent().getStringExtra("lName"));
                            account.putExtra("email",getIntent().getStringExtra("email"));
                            startActivity(account);
                            return true;
                    }
                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        cardView = findViewById(R.id.cardView_location);
        myList = new ArrayList<>();
        adapter = new HomePageAdaptor(myList,getApplicationContext());
        adapter.setClickListener(this);
        flipper = findViewById(R.id.vFlipper);

        recyclerView = findViewById(R.id.recyclerView_HomePage);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(adapter);
        //create();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(HomePageActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(HomePageActivity.this, "Location enabled", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(HomePageActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
                }
            }
        });

        for (int i = 0; i < images.length; i++) {
            flipImage(images[i]);
        }

        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               // Toast.makeText(HomePageActivity.this, "" + response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("category");
                    cidArray = new String[jsonArray.length()];
                    for(int i=0;i< jsonArray.length();i++)
                    {
                        JSONObject mydata = jsonArray.getJSONObject(i);
                        String myId = mydata.getString("cid");
                        String myName = mydata.getString("cname");
                        String myImage = mydata.getString("cimagerl");
                        String myDescription = mydata.getString("cdiscription");

                        cidArray[i] = myId;

                        HomeDepartmentData data = new HomeDepartmentData(myImage,myName);
                        myList.add(data);

                    }
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                /*dialog.dismiss();
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();*/
            }
        })
        {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Log.i("wheeee", "getParams: hooooooooooooooooooooooooooooooooooo");

                String apiKey = getIntent().getStringExtra("API key");
                String userId = getIntent().getStringExtra("user_id");
                Log.i(TAG, "getParams: api " + apiKey +"user = " +userId);
                params.put("api_key", apiKey);
                params.put("user_id", userId);

                return params;
            }

        };
        RequestQueue request2 = Volley.newRequestQueue(this);
        request2.add(request);

    }


    private void flipImage(int i) {
        ImageView view = new ImageView(this);
        view.setBackgroundResource(i);
        flipper.addView(view);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);
        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void itemClicked(View view, int position) {

        String myUserId = getIntent().getStringExtra("user_id");
        String myfName = getIntent().getStringExtra("fName");
        String mylName = getIntent().getStringExtra("lName");
        String myEmail = getIntent().getStringExtra("email");
        String myMobile = getIntent().getStringExtra("mobile");
        String myApiKey = getIntent().getStringExtra("API key");

        Intent i = new Intent(HomePageActivity.this,ProductBaseActivity.class);
        i.putExtra("key_cid",cidArray[position]);
        i.putExtra("key_userId",myUserId);
        i.putExtra("key_fName",myfName);
        i.putExtra("key_lName",mylName);
        i.putExtra("key_email",myEmail);
        i.putExtra("key_mobile",myMobile);
        i.putExtra("key_apiKey",myApiKey);

        startActivity(i);
    }

}
