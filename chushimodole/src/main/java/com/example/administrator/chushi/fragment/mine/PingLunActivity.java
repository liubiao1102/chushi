package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.ReturnSeccsess;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.example.administrator.chushi.view.CircleImageView;
import com.example.administrator.chushi.view.RatingBarView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

public class PingLunActivity extends BaseActivity implements View.OnClickListener {
    private RatingBarView ratingBarView;
    private LinearLayout back_layout;
    private CircleImageView agent_face;
    private TextView agent_name, agent_status, tv_fabiao;
    private EditText edt_content;
    private String face;
    private String name;
    private String status;
    private String id;
    private String userid;
    private String token;
    int mposition = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_lun);
        initView();
        initData();


    }


    private void initView() {
        face = getIntent().getStringExtra("face");
        name = getIntent().getStringExtra("name");
        status = getIntent().getStringExtra("status");
        id = getIntent().getStringExtra("id");

        agent_face = (CircleImageView) findViewById(R.id.agent_face);
        agent_name = (TextView) findViewById(R.id.agent_name);
        agent_status = (TextView) findViewById(R.id.agent_status);
        tv_fabiao = (TextView) findViewById(R.id.tv_fabiao);
        edt_content = (EditText) findViewById(R.id.edt_content);

        back_layout = (LinearLayout) findViewById(R.id.back_layout);
        back_layout.setOnClickListener(this);
        tv_fabiao.setOnClickListener(this);

        ratingBarView = (RatingBarView) findViewById(R.id.ratingBarView);
        ratingBarView.setRatingCount(5);//设置RatingBarView总数
        ratingBarView.setSelectedCount(1);//设置RatingBarView选中数
        ratingBarView.setSelectedIconResId(R.drawable.xingxing1);//设置RatingBarView选中的图片id
        ratingBarView.setNormalIconResId(R.drawable.xingxing2);//设置RatingBarView正常图片id
        ratingBarView.setClickable(true);//设置RatingBarView是否可点击
        ratingBarView.setChildPadding(4);//设置RatingBarView的子view的padding
        ratingBarView.setChildMargin(3);//设置RatingBarView的子view左右之间的margin
        ratingBarView.setChildDimension(20);//设置RatingBarView的子view的宽高尺寸
        ratingBarView.setOnRatingBarClickListener(new RatingBarView.RatingBarViewClickListener() {
            @Override
            public void onRatingBarClick(LinearLayout parent, View childView, int position) {
                mposition = position;
                Log.e("tag", String.valueOf(childView instanceof ImageView) + "," + position);
            }
        });


    }

    private void initData() {
        Glide.with(PingLunActivity.this).load(face).into(agent_face);
        agent_name.setText(name);
        agent_status.setText(status);

    }

    private void initNet() {
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
        OkGo.get(MyContants.BASEURL + "Order/order_pingjia/")
                .tag(this)
                .params("userid", userid)
                .params("token", token)
                .params("order_id", id)
                .params("peisong_star", mposition)
                .params("pinglun_content", edt_content.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ReturnSeccsess returnSeccsess = gson.fromJson(s, ReturnSeccsess.class);
                        if (returnSeccsess.getCode() == 200) {
                            Toast.makeText(PingLunActivity.this, "发表成功", Toast.LENGTH_SHORT).show();
                            EventMessage eventMessage = new EventMessage();
                            eventMessage.setMsg("refresh");
                            EventBus.getDefault().postSticky(eventMessage);
                            Intent intent = getIntent();
                            intent.putExtra("", "yiwancheng");
                            setResult(1, intent);
                            finish();
                        } else {
                            Toast.makeText(PingLunActivity.this, returnSeccsess.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(PingLunActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.tv_fabiao:
                if (edt_content.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入要评论的内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                initNet();
                break;
        }
    }
}
