package com.example.sande.walmart_onlineshopping.ui.products;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sande.walmart_onlineshopping.database.FeedDao;
import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.ui.cart.CartActivity;
import com.example.sande.walmart_onlineshopping.ui.cart.CartFragment;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.squareup.picasso.Picasso;

public class ProductDetailFragment extends Fragment {


    /**
     * - Set to PayPalConfiguration.ENVIRONMENT_PRODUCTION to move real money.
     * <p>
     * - Set to PayPalConfiguration.ENVIRONMENT_SANDBOX to use your test credentials
     * from https://developer.paypal.com
     * <p>
     * - Set to PayPalConfiguration.ENVIRONMENT_NO_NETWORK to kick the tires
     * without communicating to PayPal's servers.
     */
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK;

    // note that these credentials will differ between live & sandbox environments.
    private static final String CONFIG_CLIENT_ID = "credentials from developer.paypal.com";

    private static final int REQUEST_CODE_PAYMENT = 1;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)
            .merchantName("Example Merchant")
            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));

    TextView tv_productName, tv_productPrice, tv_productDescription;
    ImageView iv_ProductImage;
    Button button_addToCart,button_goToCart;
    FeedDao feedDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        button_goToCart = view.findViewById(R.id.btn_goToCart);
        button_addToCart = view.findViewById(R.id.btn_addToCart_PD);
        tv_productName = view.findViewById(R.id.tv_productName_PD);
        tv_productPrice = view.findViewById(R.id.tv_price_PD);
        tv_productDescription = view.findViewById(R.id.tv_productDescription_PD);
        iv_ProductImage = view.findViewById(R.id.iv_pImage_PD);

        feedDao = new FeedDao(getActivity());
        feedDao.openDb();

        Bundle b = getArguments();
        String pName = b.getString("key111");
        String pImage = b.getString("key222");
        String pPrice = b.getString("key333");
        String pDescription = b.getString("key444");

        tv_productName.setText(pName);
        tv_productPrice.setText("$" + pPrice);
        tv_productDescription.setText(pDescription);
        Picasso.with(getContext()).load(pImage).into(iv_ProductImage);

        button_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = getArguments();
                String pName = b.getString("key111");
                String pImage = b.getString("key222");
                String pPrice = b.getString("key333");
                String pDescription = b.getString("key444");
                String pId = b.getString("key555");
                String userId = b.getString("key3_userId");
                String fName = b.getString("key3_fName");
                String lName = b.getString("key3_lName");
                String email = b.getString("key3_email");
                String mobile = b.getString("key3_mobile");
                String apiKey = b.getString("key3_apiKey");


                int quantity = feedDao.checkCart(pId, mobile);
                if (quantity >= 1) {
                    feedDao.updateCartQuantity(quantity + 1, pId, mobile);
                    Toast.makeText(getActivity(), "Item has been added to your cart", Toast.LENGTH_SHORT).show();
                }
                if (quantity == -1) {
                    feedDao.addToCart(mobile, pId, pName, 1, pPrice, pImage);
                    Toast.makeText(getActivity(), "Item has been added to your cart", Toast.LENGTH_SHORT).show();
                }



            }

        });

        button_goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = getArguments();
                String userId = b.getString("key3_userId");
                String fName = b.getString("key3_fName");
                String lName = b.getString("key3_lName");
                String email = b.getString("key3_email");
                String mobile = b.getString("key3_mobile");
                String apiKey = b.getString("key3_apiKey");

                Log.i("detaildata", "OS" + userId + "  " + apiKey);
                Intent i = new Intent(getActivity(),CartActivity.class);
                i.putExtra("userId",userId);
                i.putExtra("fName",fName);
                i.putExtra("email",email);
                i.putExtra("mobile",mobile);
                i.putExtra("apiKey",apiKey);
                Log.i("detaildata", "OS after" + userId + "  " + apiKey);
                startActivity(i);
            }
        });

        return view;
    }


}
