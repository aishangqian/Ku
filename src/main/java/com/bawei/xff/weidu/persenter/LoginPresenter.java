package com.bawei.xff.weidu.persenter;

import com.bawei.xff.weidu.contract.LoginContract;
import com.bawei.xff.weidu.entity.UserEntity;
import com.bawei.xff.weidu.model.LoginModel;
import com.bawei.xff.weidu.utils.ValidatorUtil;

import java.util.HashMap;

public class LoginPresenter extends LoginContract.loginPresenter {
    LoginModel loginModel;
    LoginContract.IloginView iloginView;

    public LoginPresenter(LoginContract.IloginView iloginView) {
        loginModel=new LoginModel();
        this.iloginView = iloginView;
    }

    @Override
    public void login(HashMap<String, String> params) {
        String mobile = params.get("mobile");
        String password = params.get("password");
        if (!ValidatorUtil.isMobile(mobile)){
            if (iloginView!=null){
                iloginView.mobileError("请输入合法手机号");
            }
            return;
        }
        if (!ValidatorUtil.isPassword(password)){
            if (iloginView!=null){
                iloginView.pwdError("请输入合法密码");
            }
            return;
        }
            loginModel.login(params, new LoginContract.RequestCallback() {
                @Override
                public void failure(String msg) {
                    if (iloginView!=null)
                    iloginView.failure(msg);
                    return;
                }

                @Override
                public void successMsg(String msg) {
                    if (iloginView!=null)
                        iloginView.successMsg(msg);
                    return;
                }

                @Override
                public void success(UserEntity userEntity) {
                    if (iloginView!=null)
                    iloginView.success(userEntity);
                }

                @Override
                public void success(String result) {
                }
            });
    }
}
