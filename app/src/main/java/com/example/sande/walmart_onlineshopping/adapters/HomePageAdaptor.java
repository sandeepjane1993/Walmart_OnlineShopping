package com.example.sande.walmart_onlineshopping.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sande.walmart_onlineshopping.R;
import com.example.sande.walmart_onlineshopping.data.HomeDepartmentData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomePageAdaptor extends RecyclerView.Adapter<HomePageAdaptor.ViewHolder> {

    private ClickListener clickListener;
    List<HomeDepartmentData> homeDepartmentDataList;
    Context ctx;

    public HomePageAdaptor(List<HomeDepartmentData> homeDepartmentDataList, Context ctx) {
        this.homeDepartmentDataList = homeDepartmentDataList;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleitem_homedepartment_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        HomeDepartmentData obj = homeDepartmentDataList.get(position);
        Picasso.with(ctx).load(obj.getImage()).into(viewHolder.iv);
        viewHolder.tv.setText(obj.getName());


    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return homeDepartmentDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView iv;
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            iv = itemView.findViewById(R.id.iv_homeDepartment);
            tv = itemView.findViewById(R.id.tv_homeDepartment);

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
