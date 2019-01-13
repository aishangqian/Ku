package com.bawei.xff.weidu.contract;

import com.bawei.xff.weidu.entity.UserEntity;

import java.util.HashMap;

public interface LoginContract {
    public abstract class loginPresenter{
        public abstract void login(HashMap<String,String> params);
    }
    interface IloginModel{
        void login(HashMap<String,String> params, RequestCallback requestCallback);
    }
    interface IloginView{
        void mobileError(String msg);
        void pwdError(String msg);
        void failure(String msg);
        void success(UserEntity userEntity);
        void successMsg(String msg);
    }

    public interface RequestCallback {
        void failure(String msg);//服务器请求失败：断网，服务器宕机等等因素
        void successMsg(String msg);//请求成功，但数据不正确
        void success(UserEntity userEntity);//数据合法
        void success(String result);//数据合法
    }

}
