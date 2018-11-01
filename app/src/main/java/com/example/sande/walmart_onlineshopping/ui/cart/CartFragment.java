package com.example.sande.walmart_onlineshopping.ui.cart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sande.walmart_onlineshopping.database.FeedDao;
import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.adapters.CartAdapter;
import com.example.sande.walmart_onlineshopping.data.OrderData;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    List<OrderData> myList;
    CartAdapter adapter;
    RecyclerView recyclerView;
    FeedDao feedDao;
    TextView tv_subtotal, tv_taxes, tv_EstTotal,tv_topTotal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        tv_subtotal = view.findViewById(R.id.tv_subtotal_cart);
        tv_taxes = view.findViewById(R.id.tv_taxes_cart);
        tv_EstTotal = view.findViewById(R.id.tv_EstTotal_Cart);
        tv_topTotal = view.findViewById(R.id.tv_topTotal);

        myList = new ArrayList<>();
        feedDao = new FeedDao(getActivity());
        feedDao.openDb();
        recyclerView = view.findViewById(R.id.recyclerView_Cart);
        myList = feedDao.getToCart();
        //   Toast.makeText(getActivity(), "" + myList.get(0).getPname() + myList.get(1).getPname(), Toast.LENGTH_SHORT).show();
        adapter = new CartAdapter(myList, getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        displayTotal();
        return view;
    }

    public void displayTotal() {
        int subtotal = 0;
        for (int i = 0; i < myList.size(); i++) {
            subtotal = subtotal + (Integer.parseInt(myList.get(i).getPrize()) * myList.get(i).getQuantity());
        }

        int taxes = (int) (subtotal * 0.08);
        int estTotal = subtotal + taxes;
        tv_subtotal.setText("$" + subtotal);
        tv_taxes.setText("$" + taxes);
        tv_EstTotal.setText("$" + estTotal);
        tv_topTotal.setText("Est Total    $" + estTotal);

    }
}
