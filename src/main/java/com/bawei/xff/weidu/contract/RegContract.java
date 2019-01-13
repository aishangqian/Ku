package com.bawei.xff.weidu.contract;

import java.util.HashMap;

public interface RegContract {
    public abstract class IRegPresenter{
        public abstract void  reg(HashMap<String,String> params);
    }
    interface IRegModel{
        void reg(HashMap<String,String> params);
    }
    interface IRegView{
        void mobileError(String error);
        void success(String result);
        void failure(String msg);
    }
}
