package com.example.administrator.chushi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.LauncherBean;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.SpUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/24.
 */

public class LancherActivity extends BaseActivity {
    private Handler mHandler = new Handler();
    private ImageView iv_launcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        iv_launcher = (ImageView) findViewById(R.id.iv_launcher);
        go();
        //        mHandler.postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                boolean guide = SpUtils.getBoolean(LancherActivity.this, "guide", false);
        //                if (!guide) {
        //                    Intent intent = new Intent(LancherActivity.this, GuidePageActivity.class);
        //                    startActivity(intent);
        //                    finish();
        //
        //                } else {
        //                    Intent intent = new Intent(LancherActivity.this, MainActivity.class);
        //                    startActivity(intent);
        //                    finish();
        //
        //                }
        //            }
        //        }, 1500);
    }

    private void go() {
        OkGo.get(MyContants.BASEURL + "Startpage/startPage")
                .tag(LancherActivity.this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        LauncherBean launcherBean = new Gson().fromJson(s, LauncherBean.class);
                        if (launcherBean != null && launcherBean.getDatas() != null && launcherBean.getDatas().getImg1() != null) {
                            String img3 = launcherBean.getDatas().getImg1();
                            Glide.with(LancherActivity.this).load(img3)
                                    .into(iv_launcher);
                        }

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                boolean guide = SpUtils.getBoolean(LancherActivity.this, "guide", false);
                                if (!guide) {
                                    Intent intent = new Intent(LancherActivity.this, GuidePageActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent = new Intent(LancherActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }, 1500);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Log.e("请求失败", "失败原因：" + response);
                        boolean guide = SpUtils.getBoolean(LancherActivity.this, "guide", false);
                        if (!guide) {
                            Intent intent = new Intent(LancherActivity.this, GuidePageActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(LancherActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}
