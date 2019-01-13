package com.bawei.xff.weidu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.xff.weidu.R;
import com.bawei.xff.weidu.entity.ListEntity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends XRecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int one=0;
    private final int two=1;
    private final int three=2;
    private List<ListEntity.ProductItemBean> list;
    private Context context;
    public ProductAdapter(List<ListEntity.ProductItemBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=null;
        if (getItemViewType(i)==one){

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item1,viewGroup,false);

            OneVH oneVH = new OneVH(view);

            return oneVH;

        }else if (two==getItemViewType(i)){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item1,viewGroup,false);

            TwoVH twoVH = new TwoVH(view);

            return twoVH;

        }else{
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item1,viewGroup,false);

            ThreeVH threeVH = new ThreeVH(view);

            return threeVH;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof OneVH){//当前vieholder对象是oneviewholder类型
             ((OneVH) viewHolder).title1.setText(list.get(i).name);
            ((OneVH) viewHolder).rv1.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            ((OneVH) viewHolder).rv1.setAdapter(new ItemOneAdapter(context,list.get(i).commodityList));

        }else if (viewHolder instanceof TwoVH){
            ((TwoVH) viewHolder).title2.setText(list.get(i).name);
            ((TwoVH) viewHolder).rv2.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true));
            ((TwoVH) viewHolder).rv2.setAdapter(new ItemTwoAdapter(context,list.get(i).commodityList));
        }else{
            ((ThreeVH) viewHolder).title3.setText(list.get(i).name);
            ((ThreeVH) viewHolder).rv3.setLayoutManager(new GridLayoutManager(context,2));
            ((ThreeVH) viewHolder).rv3.setAdapter(new ItemThreeAdapter(context,list.get(i).commodityList));
        }
    }

    @Override
    public int getItemViewType(int position) {
        ListEntity.ProductItemBean productItemBean = list.get(position);
        if ("1002".equals(productItemBean.id)){
            return one;
        }else if ("1003".equals(productItemBean.id)){
            return two;
        }else {
            return three;
        }
    }

    @Override
    public int getItemCount() {
        return list ==null ?0:list.size();
    }

    private class OneVH extends RecyclerView.ViewHolder{
        private final RecyclerView rv1;
        private final TextView title1;
        public OneVH(@NonNull View itemView) {
            super(itemView);
            title1 = itemView.findViewById(R.id.text1);
            rv1 = itemView.findViewById(R.id.rv1);
        }
    }
    private class TwoVH extends RecyclerView.ViewHolder{
        private final RecyclerView rv2;
        private final TextView title2;
        public TwoVH(@NonNull View itemView) {
            super(itemView);
            title2 = itemView.findViewById(R.id.text1);
            rv2 = itemView.findViewById(R.id.rv1);
        }
    }
    private class ThreeVH extends RecyclerView.ViewHolder{
        private final RecyclerView rv3;
        private final TextView title3;
        public ThreeVH(@NonNull View itemView) {
            super(itemView);
            title3 = itemView.findViewById(R.id.text1);
            rv3 = itemView.findViewById(R.id.rv1);
        }
    }
}
