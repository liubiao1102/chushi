package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.MessagenoxqBean;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MyMessageActivity extends BaseActivity implements View.OnClickListener {
    private ImageView tv_back;
    private RecyclerView messgae_recycler;
    private List<String> picList = new ArrayList<>();
    private MyMessageAdapter MyMessageAdapter;
    private String userid;
    private String token;
    private List<MessagenoxqBean.DataBean> data;
    private Boolean isevent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        if (!isevent) {
            EventBus.getDefault().register(this);
            isevent = true;
        }
        initView();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage messageEvent) {
     if (messageEvent.getMsg().equals("refreshmine")){
            initNet();
         MyMessageAdapter.notifyDataSetChanged();
        }
    }
    private void initView() {
        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        messgae_recycler = (RecyclerView) findViewById(R.id.messgae_recycler);
        initNet();
    }

    private void initNet() {
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
        OkGo.get(MyContants.BASEURL+"User/im_lists/")
                .tag(this)
                .params("userid",userid)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        MessagenoxqBean mHomeFragmentBean = gson.fromJson(s, MessagenoxqBean.class);
                         data = mHomeFragmentBean.getData();
                        initData();
                        if (mHomeFragmentBean.getCode()==200){

                        }else {
                            Toast.makeText(MyMessageActivity.this, "您暂时还没有消息", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(MyMessageActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });

    }

    private void initData() {
            MyMessageAdapter = new MyMessageAdapter(R.layout.message_item, data);
        messgae_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        messgae_recycler.setAdapter(MyMessageAdapter);
        MyMessageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(MyMessageActivity.this, MessageXQActivity.class);
                intent.putExtra("im_id",data.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }
    }

    class MyMessageAdapter extends BaseQuickAdapter<MessagenoxqBean.DataBean, BaseViewHolder> {

        public MyMessageAdapter(@LayoutRes int layoutResId, @Nullable List<MessagenoxqBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MessagenoxqBean.DataBean item) {
            ImageView img_xiaohongdian = helper.getView(R.id.img_xiaohongdian);
            if (item.getStatus().equals("0")){
                img_xiaohongdian.setVisibility(View.VISIBLE);
            }else {
                img_xiaohongdian.setVisibility(View.INVISIBLE);
            }
            helper.setText(R.id.message_title,item.getContent());
            helper.setText(R.id.message_time, item.getAddtime());
        }
    }
}
