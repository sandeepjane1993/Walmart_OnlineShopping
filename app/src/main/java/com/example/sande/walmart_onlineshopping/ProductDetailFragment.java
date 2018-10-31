package com.example.sande.walmart_onlineshopping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProductDetailFragment extends Fragment {


    TextView tv_productName,tv_productPrice,tv_productDescription;
    ImageView iv_ProductImage;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail,container,false);

        tv_productName = view.findViewById(R.id.tv_productName_PD);
        tv_productPrice = view.findViewById(R.id.tv_price_PD);
        tv_productDescription = view.findViewById(R.id.tv_productDescription_PD);
        iv_ProductImage = view.findViewById(R.id.iv_pImage_PD);

        Bundle b = getArguments();
        String pName = b.getString("key111");
        String pImage = b.getString("key222");
        String pPrice = b.getString("key333");
        String pDescription = b.getString("key444");

        tv_productName.setText(pName);
        tv_productPrice.setText("$" + pPrice);
        tv_productDescription.setText(pDescription);
        Picasso.with(getContext()).load(pImage).into(iv_ProductImage);

        return view;
    }
}
