package com.example.sande.walmart_onlineshopping.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sande.walmart_onlineshopping.ui.payment.CheckoutActivity;
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
    Button btn_proceedToCheckout;
    double payment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        tv_subtotal = view.findViewById(R.id.tv_subtotal_cart);
        tv_taxes = view.findViewById(R.id.tv_taxes_cart);
        tv_EstTotal = view.findViewById(R.id.tv_EstTotal_Cart);
        tv_topTotal = view.findViewById(R.id.tv_topTotal);
        btn_proceedToCheckout = view.findViewById(R.id.btn_proceedToCheckout_Cart);

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
        adapter.setClickListener(new CartAdapter.ClickListener() {
            @Override
            public void addItem(int position) {

                feedDao.updateCartQuantity(myList.get(position).getQuantity()+1,myList.get(position).getPid(),myList.get(position).getMobile());

            }

            @Override
            public void deleteItem(int position) {
                feedDao.deleteItemCart(myList.get(position).getMobile(),myList.get(position).getPid());
            }
        });

        displayTotal();

        int subtotal = 0;
        for (int i = 0; i < myList.size(); i++) {
            subtotal = subtotal + (Integer.parseInt(myList.get(i).getPrize()) * myList.get(i).getQuantity());
        }

        int taxes = (int) (subtotal * 0.08);
        int estTotal = subtotal + taxes;
        payment = (double) estTotal;

        btn_proceedToCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = getArguments();
                String userId =   b.getString("userId");
                String fName =   b.getString("fName");
                String email =   b.getString("email");
                String mobile =   b.getString("mobile");
                String apiKey =   b.getString("apiKey");

                Log.i("cartFrag", "User Id in Card Frag" + userId + "  " + apiKey);
                Intent i = new Intent(getActivity(),CheckoutActivity.class);
                i.putExtra("userId",userId);
                i.putExtra("fName",fName);
                i.putExtra("email",email);
                i.putExtra("mobile",mobile);
                i.putExtra("apiKey",apiKey);
                i.putExtra("finalPrice",payment);
                Log.i("cartFrag", "User Id in Card Frag" + mobile + "  " + email);
                startActivity(i);
            }
        });
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
