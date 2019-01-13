package com.bawei.xff.weidu.model;

import com.bawei.xff.weidu.api.UserApi;
import com.bawei.xff.weidu.contract.ListContract;
import com.bawei.xff.weidu.utils.OkhttpCallback;
import com.bawei.xff.weidu.utils.OkhttpUtils;

import java.util.HashMap;

public class ListModel implements ListContract.IModel {

    @Override
    public void getList(HashMap<String, String> params, final ModelCallback modelCallback) {
        OkhttpUtils.getInstance().doGet(UserApi.Shang_pin,params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                modelCallback.Failure(msg);
            }

            @Override
            public void success(String result) {
                modelCallback.Success(result);
            }
        });
    }

    public interface ModelCallback {
        void Success(String result);
        void Failure(String error);
    }
}
