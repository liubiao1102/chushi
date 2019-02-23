package com.example.administrator.chushi.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.chushi.MyApplication;
import com.example.administrator.chushi.activity.BindMobileActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.ReturnSeccsess;
import com.example.administrator.chushi.bean.ThreeLoginBean;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.example.administrator.chushi.utils.SharedPreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lxk on 2017/7/3.
 */

public class UMLoginActivity extends BaseActivity {

    private static Activity mContext;
    private String umpushid;

    /*
            * 授权中只是能拿到uid，openid，token这些授权信息，想获取用户名和用户资料，需要使用这个接口
            * 其中umAuthListener为授权回调，构建如下，其中授权成功会回调onComplete，取消授权回调onCancel，
            * 授权错误回调onError，对应的错误信息可以用过onError的Throwable参数来打印
            * */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected static void loginBySina(Activity context) {
        mContext = context;
        UMShareAPI.get(context).getPlatformInfo(context, SHARE_MEDIA.SINA, umAuthListener);
    }

    protected static void loginByWeiXin(Activity context) {
        mContext = context;
        UMShareAPI.get(context).getPlatformInfo(context, SHARE_MEDIA.WEIXIN, umAuthListener);
    }

    protected static void loginByQQ(Activity context) {
        mContext = context;
        UMShareAPI.get(context).getPlatformInfo(context, SHARE_MEDIA.QQ, umAuthListener);
    }

    private static UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
                        Toast.makeText(MyApplication.getGloableContext(), "授权开始回调", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            /*
        * 登录成功后，第三方平台会将用户资料传回， 全部会在Map data中返回 ，由于各个平台对于用户资料的标识不同，
        * 因此为了便于开发者使用，我们将一些常用的字段做了统一封装，开发者可以直接获取，
        * 不再需要对不同平台的不同字段名做转换，这里列出我们封装的字段及含义
        * */
            final String username = data.get("name");
            final String userhead = data.get("iconurl");
            final String uid = data.get("uid");
            Log.d("username",username+"---------------------------");
            Log.d("userhead",userhead+"---------------------------");
            Log.d("uid",uid+"---------------------------");
             String type = "";
            if (platform.equals(SHARE_MEDIA.QQ)) {
                type = "qq";
            } else if (platform.equals(SHARE_MEDIA.WEIXIN)) {
                type = "weixin";
            } else if (platform.equals(SHARE_MEDIA.SINA)) {
                type = "weibo";
            }
            String url = MyContants.BASEURL + "Login/nt_login/";
            final String finalType = type;
            OkGo
                    .get(url)
                    .tag(this)
                    .params("openid",uid)
                    .params("type",type)
                    .params("username", username)
                    .params("face", userhead)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            try {
                                Gson gson = new Gson();
                                ThreeLoginBean threeLoginBean=gson.fromJson(s,ThreeLoginBean.class);
                                int code = threeLoginBean.getCode();
                                if (code==200){
//                                    Intent intent=new Intent(mContext, MainActivity.class);
//                                    intent.putExtra("checkshoppingcar","login");
//                                    mContext.startActivity(intent);
                                    SharedPreferencesUtils.getInstace(mContext).setStringPreference("userid",threeLoginBean.getData().getId());
                                    SharedPreferencesUtils.getInstace(mContext).setStringPreference("token",threeLoginBean.getData().getToken());
                                    EventMessage eventMessage = new EventMessage();
                                    eventMessage.setMsg("back");
                                    EventBus.getDefault().postSticky(eventMessage);
                                    initPush();
                                    Toast.makeText(MyApplication.getGloableContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                                    mContext.finish();
                                }else if (code==103){
                                    Toast.makeText(mContext, threeLoginBean.getMsg()+"", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(mContext, BindMobileActivity.class);
                                    intent.putExtra("username",username);
                                    intent.putExtra("userhead",userhead);
                                    intent.putExtra("uid",uid);
                                    intent.putExtra("type", finalType);
                                    mContext.startActivity(intent);
                                    mContext.finish();
                                }else {
                                    Toast.makeText(mContext, threeLoginBean.getMsg()+"", Toast.LENGTH_SHORT).show();
                                }


                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            Log.e("请求失败", "失败原因：" + response);
                        }
                    });
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(MyApplication.getGloableContext(), "登陆失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MyApplication.getGloableContext(), "取消登录", Toast.LENGTH_SHORT).show();
        }
    };




    /*
    * 最后在登录所在的Activity里复写onActivityResult方法,注意不可在fragment中实现，如果在fragment中调用登录，
    * 在fragment依赖的Activity中实现，如果不实现onActivityResult方法，会导致登录或回调无法正常进行
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
    private static void initPush() {
       final String umpushid = SharedPreferencesUtils.getInstace(mContext).getStringPreference("UMPUSHID", "");
//        Log.d("Loginactivity----------------------------------------", umpushid);
        OkGo.get(MyContants.BASEURL+"Login/upush")
                .tag(mContext)
                .params("uid", MyUtils.getUserid(mContext))
                .params("device_token", umpushid +"")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ReturnSeccsess mHomeFragmentBean = gson.fromJson(s,ReturnSeccsess.class);
//                        Toast.makeText(mContext, umpushid +"",Toast.LENGTH_SHORT).show();

//                        if (mHomeFragmentBean.getCode()==200){
//                            Toast.makeText(mContext,"推送成功",Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(mContext,"推送失败",Toast.LENGTH_SHORT).show();
//                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }
}
