package com.bawei.xff.weidu.model;

import android.os.Handler;
import android.text.TextUtils;

import com.bawei.xff.weidu.api.UserApi;
import com.bawei.xff.weidu.contract.LoginContract;
import com.bawei.xff.weidu.entity.UserEntity;
import com.bawei.xff.weidu.utils.OkhttpCallback;
import com.bawei.xff.weidu.utils.OkhttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginModel implements LoginContract.IloginModel {
    Handler handler=new Handler();
    @Override
    public void login(HashMap<String, String> params, final LoginContract.RequestCallback requestCallback) {
        OkhttpUtils.getInstance().doPost(UserApi.USER_LOGIN, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        requestCallback.failure("网络错误");
                        return;
                    }
                });
            }

            @Override
            public void success(String result) {
                if (!TextUtils.isEmpty(result)) {
                    paresResult(result, requestCallback);
                }
            }
        });
    }

    private void paresResult(String result, final LoginContract.RequestCallback requestCallback) {
        final UserEntity userEntity = new Gson().fromJson(result, UserEntity.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                requestCallback.success(userEntity);
            }
        });
    }
}
