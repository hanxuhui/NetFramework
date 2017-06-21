package com.hanxuhui.netframework.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.hanxuhui.netframework.R;
import com.hanxuhui.netframework.netdemo.NetDemoActivity;

/**
 * Created by hanxuhui on 2016/7/8.
 */
public class AppStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appstart);
        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(AppStartActivity.this, NetDemoActivity.class));
                finish();
            }
        }, 3000);
    }

}
