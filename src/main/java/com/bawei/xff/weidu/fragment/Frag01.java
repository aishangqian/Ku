package com.bawei.xff.weidu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.xff.weidu.MainActivity;
import com.bawei.xff.weidu.R;
import com.bawei.xff.weidu.adapter.ProductAdapter;
import com.bawei.xff.weidu.api.UserApi;
import com.bawei.xff.weidu.contract.ListContract;
import com.bawei.xff.weidu.entity.ImageEntity;
import com.bawei.xff.weidu.entity.ListEntity;
import com.bawei.xff.weidu.persenter.ListPresenter;
import com.bawei.xff.weidu.utils.OkhttpCallback;
import com.bawei.xff.weidu.utils.OkhttpUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Frag01 extends Fragment implements ListContract.IView {


    private XRecyclerView xr;
    private ListPresenter presenter;
    private XBanner mxbanner;
    List<ImageEntity.ResultBean> list;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.frag01,null);
         xr = view.findViewById(R.id.xr);
         mxbanner = view.findViewById(R.id.xbanner);
        HashMap<String, String> params = new HashMap<>();
         OkhttpUtils.getInstance().doGet(UserApi.Banner_lb, params, new OkhttpCallback() {
             @Override
             public void failure(String msg) {

             }

             @Override
             public void success(String result) {
                paresResult(result);
             }
         });
        presenter= new ListPresenter(this);
        presenter.getList(new HashMap<String, String>());
        return view;
    }

    private void paresResult(String result) {
        ImageEntity json = new Gson().fromJson(result, ImageEntity.class);
        final List<ImageEntity.ResultBean> result1 = json.result;
         List<String> imageList=new ArrayList<>();
        for (int i=0;i<result1.size();i++){
            imageList.add(result1.get(i).imageUrl);
        }
        mxbanner.setData(result1,null);
         mxbanner.loadImage(new XBanner.XBannerAdapter() {
             @Override
             public void loadBanner(XBanner banner, Object model, View view, int position) {
                 Glide.with(getActivity()).load(result1.get(position).imageUrl).into((ImageView) view);
             }
         });
         mxbanner.setPageTransformer(Transformer.Default);
    }


    @Override
    public void success(List<ListEntity.ProductItemBean> list) {
        ProductAdapter productAdapter = new ProductAdapter(list,getActivity());

        xr.setLayoutManager(new LinearLayoutManager(getActivity()));
        xr.setAdapter(productAdapter);
    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mxbanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mxbanner.stopAutoPlay();
    }
}
