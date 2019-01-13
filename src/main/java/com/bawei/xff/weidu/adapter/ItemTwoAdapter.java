package com.bawei.xff.weidu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.xff.weidu.R;
import com.bawei.xff.weidu.entity.ListEntity;
import com.bumptech.glide.Glide;

import java.util.List;

public class ItemTwoAdapter extends RecyclerView.Adapter<ItemTwoAdapter.ViewHolder>{
    private Context context;
    private List<ListEntity.ProductItemBean.ProductItem> commodityList ;

    public ItemTwoAdapter(Context context,List<ListEntity.ProductItemBean.ProductItem> commodityList) {
        this.commodityList = commodityList;
        this.context =context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item2_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv.setText(commodityList.get(i).commodityName);
        viewHolder.price.setText("¥："+commodityList.get(i).price);
        Glide.with(context).load(commodityList.get(i).masterPic).into(viewHolder.iv);
    }

    @Override
    public int getItemCount() {
        return commodityList ==null ?0:commodityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv;
        private final TextView tv,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            price = itemView.findViewById(R.id.price);
        }
    }
}
