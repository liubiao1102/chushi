package com.example.administrator.chushi.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.chushi.MyApplication;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.ThreeLoginBean;
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

public class BindMobileActivity extends BaseActivity implements View.OnClickListener {
    private ImageView tv_back;
    private Button btn_login;
    private String username;
    private String userhead;
    private String uid;
    private String type;
    private EditText edt_phone,edt_code;
    private TextView tv_getcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_mobile);
        initView();
    }

    private void initView() {
        username = getIntent().getStringExtra("username");
        userhead = getIntent().getStringExtra("userhead");
        uid = getIntent().getStringExtra("uid");
        type = getIntent().getStringExtra("type");


        tv_back= (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        edt_phone= (EditText) findViewById(R.id.edt_phone);
        edt_code= (EditText) findViewById(R.id.edt_code);
        tv_getcode= (TextView) findViewById(R.id.tv_getcode);
        tv_getcode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_getcode:
                getCode();
                break;
            case R.id.btn_login:
                if (edt_phone.getText().toString().equals("")){
                    Toast.makeText(this, "请输入要绑定的手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                initLogin();
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
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Gson gson = new Gson();
                            Toast.makeText(BindMobileActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                            SendSmsTimerUtils.sendSms(tv_getcode, R.color.white, R.color.white);

                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            Toast.makeText(BindMobileActivity.this, "验证码获取失败,请检查网络或重试", Toast.LENGTH_SHORT).show();
                            Log.e("请求失败", "失败原因：" + response);
                        }
                    });

        }

    private void initLogin() {
        String url = MyContants.BASEURL + "Login/nt_login/";
        OkGo
                .get(url)
                .tag(this)
                .params("openid",uid)
                .params("type",type)
                .params("username", username)
                .params("face", userhead)
                .params("mobile", edt_phone.getText().toString())
                .params("code", edt_code.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            Gson gson = new Gson();
                            ThreeLoginBean threeLoginBean=gson.fromJson(s,ThreeLoginBean.class);
                            int code = threeLoginBean.getCode();
                            if (code==200){
                                SharedPreferencesUtils.getInstace(BindMobileActivity.this).setStringPreference("userid",threeLoginBean.getData().getId());
                                SharedPreferencesUtils.getInstace(BindMobileActivity.this).setStringPreference("token",threeLoginBean.getData().getToken());
                                Toast.makeText(MyApplication.getGloableContext(), "绑定成功", Toast.LENGTH_SHORT).show();
                                EventMessage eventMessage = new EventMessage();
                                eventMessage.setMsg("back");
                                EventBus.getDefault().postSticky(eventMessage);
                                finish();
                            }else {
                                Toast.makeText(BindMobileActivity.this, threeLoginBean.getMsg(), Toast.LENGTH_SHORT).show();
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
}
