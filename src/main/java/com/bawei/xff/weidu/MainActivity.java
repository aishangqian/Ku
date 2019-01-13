package com.bawei.xff.weidu;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.xff.weidu.contract.LoginContract;
import com.bawei.xff.weidu.entity.UserEntity;
import com.bawei.xff.weidu.persenter.LoginPresenter;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoginContract.IloginView,View.OnClickListener {

    private EditText phone,password;
    private LoginPresenter presenter;
    private HashMap<String, String> map;
    private CheckBox jzmm;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         phone = findViewById(R.id.login_phone);
         password = findViewById(R.id.login_password);
         findViewById(R.id.login).setOnClickListener(this);
         findViewById(R.id.login_reg).setOnClickListener(this);
         findViewById(R.id.xsmm).setOnClickListener(this);
         findViewById(R.id.qq).setOnClickListener(this);
         jzmm = findViewById(R.id.jzmm);
        presenter= new LoginPresenter(this);
         sp = getSharedPreferences("config", MODE_PRIVATE);
        if (sp.getBoolean("isRem", true)) {
            jzmm.setChecked(true);
            //通过sp在config中得到账号与密码并赋值给输入框上
            phone.setText(sp.getString("phone_v", ""));
            password.setText(sp.getString("password_v", ""));
        }
        jzmm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    jzmm.setChecked(true);
                }
            }
        });
    }

    @Override
    public void mobileError(String msg) {
        Toast.makeText(this,msg+"",Toast.LENGTH_LONG).show();
        return;
    }

    @Override
    public void pwdError(String msg) {
        Toast.makeText(this,msg+"",Toast.LENGTH_LONG).show();
        return;
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this,msg+"",Toast.LENGTH_LONG).show();
        return;
    }


    @Override
    public void success(UserEntity userEntity) {
        Toast.makeText(this,userEntity.getMsg()+"",Toast.LENGTH_LONG).show();
        if (userEntity.getMsg().equals("登录成功")){
            Intent intent=new Intent(MainActivity.this,ThreeActivity.class);
            startActivity(intent);
            finish();
        }
        return;
    }

    @Override
    public void successMsg(String msg) {
        Toast.makeText(this,msg+"",Toast.LENGTH_LONG).show();
        return;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_reg:
                Intent intent=new Intent(MainActivity.this,TwoActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login:
                map= new HashMap<>();
                map.put("mobile",phone.getText().toString().trim());
                map.put("password",password.getText().toString().trim());
                presenter.login(map);
                if (jzmm.isChecked()){
                     edit = sp.edit();
                    edit.putString("phone_v", phone.getText().toString().trim());
                    edit.putString("password_v", password.getText().toString().trim());
                    edit.putBoolean("isRem", true);
                    edit.commit();
                } else {
                    edit = sp.edit();
                    edit.putString("phone_v", "");
                    edit.putString("password_v", "");
                    edit.putBoolean("isRem", false);
                    edit.commit();
                }
                break;
            case R.id.qq:
                if(Build.VERSION.SDK_INT>=23){///QQ需要申请写入权限
                    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
                    ActivityCompat.requestPermissions(this,mPermissionList,123);
                }else{
                    UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
                }
                break;
            case R.id.xsmm:
                //显示密码
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {
            UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
        }
    }
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }
        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, ThreeActivity.class));
//             跳转
            finish();
            // 结束页面
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}

