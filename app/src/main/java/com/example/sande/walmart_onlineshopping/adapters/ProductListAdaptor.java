package com.example.sande.walmart_onlineshopping.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.data.ProductListData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductListAdaptor extends RecyclerView.Adapter<ProductListAdaptor.ViewHolder> {

    private ClickListener clickListener;
    List<ProductListData> productListDataList;
    Context ctx;

    public ProductListAdaptor(List<ProductListData> productListDataList, Context ctx) {
        this.productListDataList = productListDataList;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleitem_productlist,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        ProductListData data = productListDataList.get(position);
        Picasso.with(ctx).load(data.getImage_pl()).into(viewHolder.iv);
        viewHolder.tv_pname.setText(data.getName_pl());
        viewHolder.tv_price.setText(data.getPrice());
        viewHolder.tv_shipping.setText("Free Shipping");
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return productListDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView iv;
        TextView tv_pname,tv_price,tv_shipping;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            iv = itemView.findViewById(R.id.imgView_PL);
            tv_pname = itemView.findViewById(R.id.tv_name_PL);
            tv_price = itemView.findViewById(R.id.tv_price_PL);
            tv_shipping = itemView.findViewById(R.id.tv_shippingDetails_Pl);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null)
            {
                clickListener.itemClicked(v,getAdapterPosition());
            }
        }
    }

    public interface ClickListener
    {
        public void itemClicked(View view, int position);
    }
}
