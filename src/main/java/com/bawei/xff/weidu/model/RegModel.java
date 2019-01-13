package com.bawei.xff.weidu.model;

import android.os.Handler;

import com.bawei.xff.weidu.api.UserApi;
import com.bawei.xff.weidu.contract.RegContract;
import com.bawei.xff.weidu.utils.OkhttpCallback;
import com.bawei.xff.weidu.utils.OkhttpUtils;

import java.util.HashMap;

public class RegModel implements RegContract.IRegModel {
    Handler handler=new Handler();
    @Override
    public void reg(HashMap<String, String> params) {
        OkhttpUtils.getInstance().doPost(UserApi.USER_REG, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        regCallback.onFailure("网络错误");
                    }
                });
            }

            @Override
            public void success(final String result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        regCallback.onResponse(result);
                    }
                });
            }
        });
    }
    RegCallback regCallback;
    public interface RegCallback{
        void onFailure(String msg);
        void onResponse(String result);
    }
    public void setRegCallback(RegCallback regCallback) {
        this.regCallback = regCallback;
    }
}
