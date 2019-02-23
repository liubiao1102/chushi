package com.example.administrator.chushi;

import android.app.Application;
import android.content.Context;

import com.example.administrator.chushi.utils.SpUtils;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.litepal.LitePalApplication;

/**
 * Created by Administrator on 2017/8/24.
 */

public class MyApplication extends LitePalApplication {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        initUMShare();
        initUMPush();
        instance = this;
    }

    private void initUMPush() {

//        Toast.makeText(getApplicationContext(), "Push", Toast.LENGTH_SHORT).show();
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
//                Toast.makeText(getContext(), "友盟推送注册成功", Toast.LENGTH_SHORT).show();

                System.out.println("友盟推送注册成功" + deviceToken);
                SpUtils.putString(getContext(), "UMPUSHID", deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
//                Toast.makeText(getContext(), "友盟推送注册失败", Toast.LENGTH_SHORT).show();
            }
        });

    }




    private void initUMShare() {
        Config.DEBUG = true;
        UMShareAPI.get(this);

        PlatformConfig.setWeixin("wxdbbb1928fdfa069d", "69ad4ae853a60921d709dd3125d75351");
        PlatformConfig.setQQZone("1106331431", "IzCFiE8hmnI9RIiZ");
        PlatformConfig.setSinaWeibo("4004384953", "11a1e6053a816fb1636a739cb67ce667", "https://sns.whalecloud.com/sina2/callback");
    }

    public static Application getInstance() {
        return instance;
    }

    public static Context getGloableContext() {
        return instance.getApplicationContext();
    }
}
