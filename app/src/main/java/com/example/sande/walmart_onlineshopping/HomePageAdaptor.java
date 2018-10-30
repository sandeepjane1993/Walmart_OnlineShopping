package com.example.sande.walmart_onlineshopping;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HomePageAdaptor extends RecyclerView.Adapter<HomePageAdaptor.ViewHolder> {

    List<HomeDepartmentData> homeDepartmentDataList;

    public HomePageAdaptor(List<HomeDepartmentData> homeDepartmentDataList) {
        this.homeDepartmentDataList = homeDepartmentDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleitem_homedepartment_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        HomeDepartmentData obj = homeDepartmentDataList.get(position);
        viewHolder.tv.setText(obj.getName());
        viewHolder.iv.setImageResource(obj.getImage());
    }

    @Override
    public int getItemCount() {
        return homeDepartmentDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iv;
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_homeDepartment);
            tv = itemView.findViewById(R.id.tv_homeDepartment);
        }
    }
}
