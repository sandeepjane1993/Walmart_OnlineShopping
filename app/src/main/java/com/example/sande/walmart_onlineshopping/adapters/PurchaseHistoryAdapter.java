package com.example.sande.walmart_onlineshopping.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.data.PurchaseHistoryData;

import java.util.List;

public class PurchaseHistoryAdapter extends RecyclerView.Adapter<PurchaseHistoryAdapter.ViewHolder> {

    private ClickListener clickListener;
    List<PurchaseHistoryData> purchaseHistoryDataList;
    Context ctx;

    public PurchaseHistoryAdapter(List<PurchaseHistoryData> purchaseHistoryDataList, Context ctx) {
        this.purchaseHistoryDataList = purchaseHistoryDataList;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleitem_purchasehistory,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        PurchaseHistoryData data = purchaseHistoryDataList.get(position);
        viewHolder.tv_PurchaseHistory.setText("Order Id : " + data.getOrderId() + "\n" +
                "Order Status : " + data.getOrderStatus() + "\n" +
                "User Name : " + data.getUserName() + "\n" +
                "Billing Address : " + data.getBilling() + "\n" +
                "Delivery Address : " + data.getMailing() + "\n" +
                "Mobile Number : " + data.getMobile() + "\n" +
                "Email : " + data.getEmail() + "\n" +
                "Item Id : " + data.getItemId() + "\n" +
                "Item Name : " + data.getItemName() + "\n" +
                "Item Qty : " + data.getItemQuantity() + "\n" +
                "Total Price : " + data.getTotalPrice() + "\n" +
                "Paid Price : " + data.getPaidPrice() + "\n" +
                "Date & Time : " + data.getPlacedOn());
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return purchaseHistoryDataList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_PurchaseHistory;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_PurchaseHistory = itemView.findViewById(R.id.tv_singleItem_PH);
        }

        @Override
        public void onClick(View v) {

            if(clickListener != null)
            {
                clickListener.itemClicked(v,getAdapterPosition());
            }
        }
    }
    public interface ClickListener{

        public void itemClicked(View view,int position);
    }
}
