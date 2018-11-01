package com.example.sande.walmart_onlineshopping.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.data.OrderData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    List<OrderData> orderDataList;
    Context ctx;

    public CartAdapter(List<OrderData> orderDataList, Context ctx) {
        this.orderDataList = orderDataList;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleitem_cart,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        OrderData orderData = orderDataList.get(position);
        Picasso.with(ctx).load(orderData.getImage()).into(viewHolder.iv_pImage_cart);
        viewHolder.tv_pName_cart.setText(orderData.getPname());
        viewHolder.tv_pPrice_cart.setText("$" + orderData.getPrize());
        viewHolder.tv_pQty_cart.setText("Qty - " + orderData.getQuantity());
    }

    @Override
    public int getItemCount() {
        return orderDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_pImage_cart;
        TextView tv_pName_cart, tv_pPrice_cart,tv_pQty_cart;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_pImage_cart = itemView.findViewById(R.id.iv_pImage_Cart);
            tv_pName_cart = itemView.findViewById(R.id.tv_pName_Cart);
            tv_pPrice_cart = itemView.findViewById(R.id.tv_pPrice_Cart);
            tv_pQty_cart = itemView.findViewById(R.id.tv_pQty_cart);

        }
    }
}
