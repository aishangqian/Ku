package com.bawei.xff.weidu.contract;

import com.bawei.xff.weidu.entity.ListEntity;
import com.bawei.xff.weidu.model.ListModel;

import java.util.HashMap;
import java.util.List;

public interface ListContract {
    interface IView{
        void success(List<ListEntity.ProductItemBean> list);
        void failure(String msg);
    }
    interface IModel{
        void getList(HashMap<String,String> params, ListModel.ModelCallback modelCallback);

    }
    public abstract class IPresenter{
        public abstract void getList(HashMap<String, String> params);

    }
}
