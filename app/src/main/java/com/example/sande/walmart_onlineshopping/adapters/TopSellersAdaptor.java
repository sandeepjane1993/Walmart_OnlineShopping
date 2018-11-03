package com.example.sande.walmart_onlineshopping.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.data.TopSellersData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopSellersAdaptor extends RecyclerView.Adapter<TopSellersAdaptor.ViewHolder> {

    List<TopSellersData> topSellersDataList;
    Context ctx;

    public TopSellersAdaptor(List<TopSellersData> topSellersDataList, Context ctx) {
        this.topSellersDataList = topSellersDataList;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleitem_topsellers,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        TopSellersData topSellersData = topSellersDataList.get(position);
        viewHolder.tv_sellerName.setText(topSellersData.getSellerName());
        viewHolder.tv_sellerDeal.setText(topSellersData.getSellerDeal());
        viewHolder.ratingBar.setRating(topSellersData.getSellerRating());
        Picasso.with(ctx).load(topSellersData.getSellerLogo()).into(viewHolder.iv_sellerLogo);
    }

    @Override
    public int getItemCount() {
        return topSellersDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_sellerLogo;
        TextView tv_sellerName,tv_sellerDeal;
        RatingBar ratingBar;
        public ViewHolder(View itemView) {
            super(itemView);

            iv_sellerLogo = itemView.findViewById(R.id.iv_singleitem_TS);
            tv_sellerName = itemView.findViewById(R.id.tv_sellerName_TS);
            tv_sellerDeal = itemView.findViewById(R.id.tv_sellerDeals_TS);
            ratingBar = itemView.findViewById(R.id.ratingBar_TS);


        }
    }
}
