package com.example.administrator.chushi.fragment.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.chushi.CityNameInterface;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.EventMessageOne;
import com.example.administrator.chushi.utils.CityUtils;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.example.administrator.chushi.utils.SharedPreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.Call;
import okhttp3.Response;

public class BianJiAddressActivity extends BaseActivity implements View.OnClickListener {
    private ImageView tv_back;
    private TextView check_quyu, tv_save;
    private PopupWindow pop;
    private View view;
    private String idone;
    private String idtwo;
    private String idthree;
    private EditText edt_name, edt_phone, edt_xxaddress;
    private String userid;
    private String token;
    private String username;
    private String address;
    private String areaone;
    private String mobile;
    private String c_id;
    private String v_id;
    private String x_id;
    private String address_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bian_ji_address);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }


    private void initView() {
        username = getIntent().getStringExtra("username");
        address = getIntent().getStringExtra("address");
        areaone = getIntent().getStringExtra("area");
        mobile = getIntent().getStringExtra("mobile");
        c_id = getIntent().getStringExtra("c_id");
        v_id = getIntent().getStringExtra("v_id");
        x_id = getIntent().getStringExtra("x_id");
        address_id = getIntent().getStringExtra("address_id");
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_xxaddress = (EditText) findViewById(R.id.edt_xxaddress);
        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        check_quyu = (TextView) findViewById(R.id.check_quyu);
        tv_save = (TextView) findViewById(R.id.tv_save);
        check_quyu.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        if (username.equals("")) {
                check_quyu.setText("选择区域");
        } else {
            edt_name.setText(username);
            edt_phone.setText(mobile);
            edt_xxaddress.setText(address);
            check_quyu.setText("选择区域");
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessageOne messageEvent) {
        idone = messageEvent.getMsg();
        idtwo = messageEvent.getMsgone();
        idthree = messageEvent.getMsgtwo();
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_save:
                if (edt_name.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入您的用户名", Toast.LENGTH_SHORT).show();
                    return;
                } else if (edt_phone.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入您的联系方式", Toast.LENGTH_SHORT).show();
                    return;
                } else if (check_quyu.getText().toString().equals("选择区域")) {
                    Toast.makeText(this, "请选择您的区域", Toast.LENGTH_SHORT).show();
                    return;
                } else if (edt_xxaddress.getText().toString().equals("")) {
                    Toast.makeText(this, "请填写您的详细地址", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!MyUtils.isMobileNO(edt_phone.getText().toString())){
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if (username.equals("")) {
                        initNet();
                    } else {
                        initUpDate();
                    }

                }
                break;
            case R.id.check_quyu:
                InputMethodManager imm1 = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm1.hideSoftInputFromWindow(v.getWindowToken(), 0);//从控件所在的窗口中隐藏
                CityUtils.getInstance().getAllCity(this, new CityNameInterface() {
                    @Override
                    public void getCityName(String area) {
                        SharedPreferencesUtils.getInstace(BianJiAddressActivity.this).setStringPreference("area", area);
                        check_quyu.setText(area);
                    }
                });
                break;
        }
    }

    private void initUpDate() {
        OkGo.get(MyContants.BASEURL + "address/edit")
                .tag(this)
                .params("userid", userid)
                .params("token", token)
                .params("c_id", idone)
                .params("x_id", idtwo)
                .params("v_id", idthree)
                .params("username", edt_name.getText().toString())
                .params("mobile", edt_phone.getText().toString())
                .params("address", edt_xxaddress.getText().toString())
                .params("status", "1")
                .params("address_id", address_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        setResult(0, new Intent());
                        Toast.makeText(BianJiAddressActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(BianJiAddressActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });

    }

    private void initNet() {
        OkGo.get(MyContants.BASEURL + "Address/add")
                .tag(this)
                .params("userid", userid)
                .params("token", token)
                .params("c_id", idone)
                .params("x_id", idtwo)
                .params("v_id", idthree)
                .params("username", edt_name.getText().toString())
                .params("mobile", edt_phone.getText().toString())
                .params("address", edt_xxaddress.getText().toString())
                .params("status", "1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        Toast.makeText(BianJiAddressActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        setResult(0, new Intent());
                        finish();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(BianJiAddressActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(BianJiAddressActivity.this);
    }
}
