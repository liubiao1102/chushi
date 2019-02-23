package com.example.administrator.chushi.base;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.chushi.bean.EventMessageCheck;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.utils.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lxk on 2017/6/10.
 */

public class BaseActivity extends AppCompatActivity {
    private static List<Activity> activityList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在这里判断是否token是否存在、是否过期之类的
        if (activityList != null)
            activityList.add(this);
        initPush();

    } private void initPush() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
        UmengMessageHandler umengMessageHandler = new UmengMessageHandler() {
            @Override
            public Notification getNotification(Context context, UMessage uMessage) {
                Toast.makeText(context, "收到", Toast.LENGTH_SHORT).show();
                Log.d("MyApplication", uMessage.text + "------------------------------------------");
                if (uMessage.text.equals("该用户已被停用")) {
                    EventMessageCheck eventMessageCheck=new EventMessageCheck();
                    eventMessageCheck.setCheckNum(101);
                    EventBus.getDefault().postSticky(eventMessageCheck);
                }

                return super.getNotification(context, uMessage);
            }
        };
        mPushAgent.setMessageHandler(umengMessageHandler);
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }



    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
//        overridePendingTransition(R.anim.start_activity_in, R.anim.start_activity_out);
    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(R.anim.finish_activity_in, R.anim.finish_activity_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (activityList != null)
            activityList.remove(this);
    }

    public static List<Activity> getAllActivitys() {
        return activityList;
    }

    public static void removeAllActivitys() {
        if (activityList != null && activityList.size() > 0) {
            for (int i = 0; i < activityList.size(); i++) {
                if (activityList.get(i) != null) {
                    activityList.get(i).finish();
                }
            }
            activityList.clear();
            //            activityList = null;
        }
    }

    public static void realBack() {
        if (activityList != null && activityList.size() > 0) {
            for (int i = 0; i < activityList.size(); i++) {
                if (activityList.get(i) != null) {
                    activityList.get(i).finish();
                }
            }
            activityList.clear();
            activityList = null;
        }
    }


}
