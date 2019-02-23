package com.example.administrator.chushi.fragment.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.MessageBean;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

public class MessageXQActivity extends BaseActivity implements View.OnClickListener {
    private ImageView tv_back;
    private String im_id;
    private String userid;
    private String token;
    private TextView message_title,message_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_xq);
        initView();
    }

    private void initView() {
        im_id = getIntent().getStringExtra("im_id");
        tv_back= (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        message_title= (TextView) findViewById(R.id.message_title);
        message_time= (TextView) findViewById(R.id.message_time);
        initNet();
    }

    private void initNet() {
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
        OkGo.get(MyContants.BASEURL+"User/im_detail/")
                .tag(this)
                .params("userid",userid)
                .params("token",token)
                .params("im_id",im_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        MessageBean mHomeFragmentBean = gson.fromJson(s, MessageBean.class);
                        MessageBean.DataBean data = mHomeFragmentBean.getData();
                        if (mHomeFragmentBean.getCode()==200){
                            message_title.setText(data.getContent());
                            message_time.setText(data.getAddtime());
                        }else {

                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(MessageXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventMessage eventMessage = new EventMessage();
        eventMessage.setMsg("refreshmine");
        EventBus.getDefault().postSticky(eventMessage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                EventMessage eventMessage = new EventMessage();
                eventMessage.setMsg("refreshmine");
                EventBus.getDefault().postSticky(eventMessage);
                finish();
                break;
        }
    }
}
