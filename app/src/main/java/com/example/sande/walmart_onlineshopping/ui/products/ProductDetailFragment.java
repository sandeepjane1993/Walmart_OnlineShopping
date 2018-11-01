package com.example.sande.walmart_onlineshopping.ui.products;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sande.walmart_onlineshopping.database.FeedDao;
import com.example.sande.walmart_onlineshopping.R;
import com.squareup.picasso.Picasso;

public class ProductDetailFragment extends Fragment {


    TextView tv_productName,tv_productPrice,tv_productDescription;
    ImageView iv_ProductImage;
    Button button_addToCart;
    FeedDao feedDao;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail,container,false);

        button_addToCart =view.findViewById(R.id.btn_addToCart_PD);
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


                    int quantity = feedDao.checkCart(pId, "1");
                    if (quantity >= 1) {
                        feedDao.updateCartQuantity(quantity + 1, pId, "1");
                        Toast.makeText(getActivity(), "Item has been added to your cart", Toast.LENGTH_SHORT).show();
                    } if(quantity == -1) {
                        feedDao.addToCart("1", pId, pName, 1, pPrice, pImage);
                        Toast.makeText(getActivity(), "Item has been added to your cart", Toast.LENGTH_SHORT).show();
                    }


            }

        });

        return view;
    }
}
