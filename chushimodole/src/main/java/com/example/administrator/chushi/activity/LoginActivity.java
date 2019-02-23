package com.example.administrator.chushi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.UMLoginActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.LoginBean;
import com.example.administrator.chushi.bean.ReturnSeccsess;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.example.administrator.chushi.utils.SendSmsTimerUtils;
import com.example.administrator.chushi.utils.SharedPreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/24.
 */

public class LoginActivity extends UMLoginActivity implements View.OnClickListener {
    private EditText edt_phone, edt_mima, edt_code;
    private Button btn_login;
    private TextView tv_forgetpsw, tv_zhuce,tv_xieyi,tv_getcode;
    private ImageView iv_weixin, iv_qq, iv_weibo,tv_back;
    private String loginTag;
    private String umpushid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginTag = getIntent().getStringExtra("LoginTag");
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_mima = (EditText) findViewById(R.id.edt_mima);
        edt_code = (EditText) findViewById(R.id.edt_code);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        tv_forgetpsw = (TextView) findViewById(R.id.tv_forgetpsw);
        tv_forgetpsw.setOnClickListener(this);
        tv_xieyi = (TextView) findViewById(R.id.tv_xieyi);
        tv_xieyi.setOnClickListener(this);
        iv_weixin = (ImageView) findViewById(R.id.iv_weixin);
        iv_weixin.setOnClickListener(this);
        iv_qq = (ImageView) findViewById(R.id.iv_qq);
        iv_qq.setOnClickListener(this);
        iv_weibo = (ImageView) findViewById(R.id.iv_weibo);
        iv_weibo.setOnClickListener(this);
        tv_getcode= (TextView) findViewById(R.id.tv_getcode);
        tv_getcode.setOnClickListener(this);
        tv_back= (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String mobile = SharedPreferencesUtils.getInstace(LoginActivity.this).getStringPreference("mobile", "");
        edt_phone.setText(mobile);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //获取验证码
            case R.id.tv_getcode:
                getCode();
                break;
            //登录
            case R.id.btn_login:
                if (edt_phone.getText().toString().equals("")){
                    Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!MyUtils.isMobileNO(edt_phone.getText().toString())){
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }else if (edt_code.getText().toString().equals("")){
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                }

                SharedPreferencesUtils.getInstace(LoginActivity.this).setStringPreference("mobile",edt_phone.getText().toString());
                initLogin();
                break;
            case R.id.tv_forgetpsw:
//                Intent intent3 = new Intent(this, ForgetpswActivity.class);
//                startActivity(intent3);
                break;
            case R.id.iv_weibo:
                loginBySina(this);
                break;
            case R.id.iv_weixin:
               loginByWeiXin(this);
                break;
            case R.id.iv_qq:
                loginByQQ(this);
                break;
            case R.id.tv_xieyi:
                startActivity(new Intent(LoginActivity.this,XieYiActivity.class));
                break;
        }
    }
    public void getCode() {
        if (TextUtils.isEmpty(edt_phone.getText().toString())) {
            Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (!MyUtils.isMobileNO(edt_phone.getText().toString())) {
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.get(MyContants.BASEURL+"Msg/sendSMS")
                .tag(this)
                .params("mobile",edt_phone.getText().toString())
                .params("type","3")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        Toast.makeText(LoginActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                        SendSmsTimerUtils.sendSms(tv_getcode, R.color.white, R.color.white);

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(LoginActivity.this, "验证码获取失败,请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });

    }
    private void initLogin() {
        OkGo.get(MyContants.BASEURL+"Login/login")
                .tag(this)
                .params("mobile",edt_phone.getText().toString())
                .params("code",edt_code.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        LoginBean mHomeFragmentBean = gson.fromJson(s, LoginBean.class);
                        LoginBean.DataBean data = mHomeFragmentBean.getData();

                        if (mHomeFragmentBean.getCode()==200){
                            SharedPreferencesUtils.getInstace(LoginActivity.this).setStringPreference("userid",data.getId());
                            SharedPreferencesUtils.getInstace(LoginActivity.this).setStringPreference("token",data.getToken());
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            initPush();
                            EventMessage eventMessage = new EventMessage();
                            eventMessage.setMsg("back");
                            EventBus.getDefault().postSticky(eventMessage);
//                            if (loginTag.equals("shoppxq")){
//
//                            }
//                            Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(intent1);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, mHomeFragmentBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(LoginActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });

    }

    private void initPush() {
      final String  umpushid = SharedPreferencesUtils.getInstace(this).getStringPreference("UMPUSHID", "");
//        Log.d("Loginactivity----------------------------------------", umpushid);
        OkGo.get(MyContants.BASEURL+"Login/upush")
                .tag(this)
                .params("uid",MyUtils.getUserid(this))
                .params("device_token", umpushid +"")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ReturnSeccsess mHomeFragmentBean = gson.fromJson(s,ReturnSeccsess.class);
//                        Toast.makeText(LoginActivity.this, umpushid +"",Toast.LENGTH_SHORT).show();

//                        if (mHomeFragmentBean.getCode()==200){
//                            Toast.makeText(LoginActivity.this,"推送成功",Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(LoginActivity.this,"推送失败",Toast.LENGTH_SHORT).show();
//
//                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(LoginActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }
}
