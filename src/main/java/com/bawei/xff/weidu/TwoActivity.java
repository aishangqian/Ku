package com.bawei.xff.weidu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.xff.weidu.contract.RegContract;
import com.bawei.xff.weidu.persenter.RegPresenter;

import java.util.HashMap;

public class TwoActivity extends AppCompatActivity implements RegContract.IRegView,View.OnClickListener {

    private EditText phone,password;
    RegPresenter regPresenter=new RegPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
         phone = findViewById(R.id.reg_phone);
         password = findViewById(R.id.reg_password);
        findViewById(R.id.reg).setOnClickListener(this);
        findViewById(R.id.reg_login).setOnClickListener(this);
    }

    @Override
    public void mobileError(String error) {

    }

    @Override
    public void success(String result) {
        Intent intent=new Intent(TwoActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this,result,Toast.LENGTH_LONG).show();
    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_login:
                Intent intent=new Intent(TwoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.reg:
                HashMap<String,String> params=new HashMap<>();
                params.put("mobile",phone.getText().toString().trim());
                params.put("password",password.getText().toString().trim());
                regPresenter.reg(params);
                break;
        }
    }
}
