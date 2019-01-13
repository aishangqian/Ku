package com.bawei.xff.weidu;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.bawei.xff.weidu.fragment.Frag01;
import com.bawei.xff.weidu.fragment.Frag02;
import com.bawei.xff.weidu.fragment.Frag03;
import com.bawei.xff.weidu.fragment.Frag04;
import com.bawei.xff.weidu.fragment.Frag05;

public class ThreeActivity extends AppCompatActivity {

    private FrameLayout framelayout;
    private FragmentManager manager;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
         framelayout = findViewById(R.id.framelayout);
         rg = findViewById(R.id.rg);
        manager = getSupportFragmentManager();
         manager.beginTransaction().replace(R.id.framelayout,new Frag01()).commit();
         rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 switch (checkedId){
                     case R.id.rb_1:
                         manager.beginTransaction().replace(R.id.framelayout,new Frag01()).commit();
                         break;
                     case R.id.rb_2:
                         manager.beginTransaction().replace(R.id.framelayout,new Frag02()).commit();
                         break;
                     case R.id.rb_3:
                         manager.beginTransaction().replace(R.id.framelayout,new Frag03()).commit();
                         break;
                     case R.id.rb_4:
                         manager.beginTransaction().replace(R.id.framelayout,new Frag04()).commit();
                         break;
                     case R.id.rb_5:
                         manager.beginTransaction().replace(R.id.framelayout,new Frag05()).commit();
                         break;
                 }
             }
         });
    }

}
