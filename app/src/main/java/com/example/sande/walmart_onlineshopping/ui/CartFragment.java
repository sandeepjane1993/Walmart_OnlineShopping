package com.example.sande.walmart_onlineshopping.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sande.walmart_onlineshopping.FeedDao;
import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.adapters.CartAdapter;
import com.example.sande.walmart_onlineshopping.adapters.HomePageAdaptor;
import com.example.sande.walmart_onlineshopping.data.HomeDepartmentData;
import com.example.sande.walmart_onlineshopping.data.OrderData;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    List<OrderData> myList;
    CartAdapter adapter;
    RecyclerView recyclerView;
    FeedDao feedDao;


    Button btn_add_cart;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart,container,false);

        myList = new ArrayList<>();
        feedDao = new FeedDao(getActivity());
        feedDao.openDb();
        recyclerView = view.findViewById(R.id.recyclerView_Cart);
        myList = feedDao.getToCart();
     //   Toast.makeText(getActivity(), "" + myList.get(0).getPname() + myList.get(1).getPname(), Toast.LENGTH_SHORT).show();
        adapter = new CartAdapter(myList,getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        btn_add_cart = view.findViewById(R.id.btn_add_cart);
        return view;
    }
}
