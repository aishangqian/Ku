package com.bawei.xff.weidu.persenter;


import com.bawei.xff.weidu.contract.ListContract;
import com.bawei.xff.weidu.entity.ListEntity;
import com.bawei.xff.weidu.model.ListModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListPresenter extends ListContract.IPresenter {
    ListModel listModel;
    ListContract.IView iView;

    public ListPresenter(ListContract.IView iView) {
        listModel=new ListModel();
        this.iView = iView;
    }

    @Override
    public void getList(HashMap<String, String> params) {
        listModel.getList(params, new ListModel.ModelCallback() {
            @Override
            public void Success(String result) {
                ListEntity json1 = new Gson().fromJson(result, ListEntity.class);
                List<ListEntity.ProductItemBean> list = new ArrayList<>();

                list.addAll(json1.result.rxxp);//添加集合数据到当前集合内
                list.addAll(json1.result.pzsh);
                list.addAll(json1.result.mlss);

                iView.success(list);
            }

            @Override
            public void Failure(String error) {
                iView.failure(error);
            }
        });
    }
}
