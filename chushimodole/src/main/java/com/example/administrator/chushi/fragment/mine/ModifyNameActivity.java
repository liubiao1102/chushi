package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.ModifyNameBean;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.example.administrator.chushi.utils.SharedPreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

public class ModifyNameActivity extends BaseActivity implements View.OnClickListener {

    private EditText edt_name;
    private ImageView tv_back;
    private Button btn_sign;
    private String userid;
    private String token;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifyname);
        edt_name = (EditText) findViewById(R.id.edt_name);
        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        btn_sign = (Button) findViewById(R.id.btn_sign);
        btn_sign.setOnClickListener(this);
        edt_name.setHint("您的昵称");

        initData();
    }

    private void initNet() {
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);

        OkGo.get(MyContants.BASEURL+"User/update/")
                .tag(this)
                .params("userid",userid)
                .params("token",token)
                .params("username",edt_name.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ModifyNameBean mHomeFragmentBean = gson.fromJson(s, ModifyNameBean.class);
                        int code = mHomeFragmentBean.getCode();
                        if (code==200){
                            Intent intent = getIntent();
                            intent.putExtra("param", edt_name.getText().toString());
                            setResult(0, intent);
                            finish();
                            Toast.makeText(ModifyNameActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(ModifyNameActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }


    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                Intent intent = getIntent();
                intent.putExtra("param","");
                setResult(0, intent);
                finish();
                break;
            case R.id.btn_sign:
                if (edt_name.getText().toString().equals("")){
                    Toast.makeText(this, "请输入要修改的名字", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edt_name.getText().toString().length()>10){
                    Toast.makeText(this, "名字长度不可超过十位", Toast.LENGTH_SHORT).show();
                    return;
                }

                initNet();
                SharedPreferencesUtils.getInstace(ModifyNameActivity.this).setStringPreference("modifyname",edt_name.getText().toString());
                EventMessage eventMessage = new EventMessage();
                eventMessage.setMsg("modifyname");
                EventBus.getDefault().postSticky(eventMessage);
                edt_name.setClickable(false);
                edt_name.setFocusableInTouchMode(false);
                edt_name.setFocusable(false);


                break;
        }
    }
}
