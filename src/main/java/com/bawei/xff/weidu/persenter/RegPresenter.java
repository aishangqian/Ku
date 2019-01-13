package com.bawei.xff.weidu.persenter;

import com.bawei.xff.weidu.contract.RegContract;
import com.bawei.xff.weidu.model.RegModel;
import com.bawei.xff.weidu.utils.ValidatorUtil;

import java.util.HashMap;

public class RegPresenter extends RegContract.IRegPresenter {
    RegModel regModel;
    RegContract.IRegView iRegView;

    public RegPresenter(RegContract.IRegView iRegView) {
        regModel=new RegModel();
        this.iRegView = iRegView;
    }

    @Override
    public void reg(HashMap<String, String> params) {
        String mobile = params.get("mobile");
        if (!ValidatorUtil.isMobile(mobile)){
            if (iRegView!=null){
                iRegView.mobileError("手机不合法");
            }
            return;
        }
        if (regModel!=null) {
            regModel.reg(params);
            regModel.setRegCallback(new RegModel.RegCallback() {
                @Override
                public void onFailure(String msg) {
                    iRegView.failure(msg);
                }

                @Override
                public void onResponse(String result) {
                    iRegView.success(result);
                }
            });
        }
    }

}
