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

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.TuiKuanListsBean;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MineShouHouActivity extends BaseActivity {
    private ImageView tv_back;
    private RecyclerView weishiyong_recycler;
    private AllDingDanItemAdapter allDingDanItemAdapter;
    private String userid;
    private String token;
    private List<TuiKuanListsBean.DataBean> data;
    private Boolean isevent = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_shou_hou);
        if (!isevent) {
            EventBus.getDefault().register(this);
            isevent = true;
        }
        initView();
        initData();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage messageEvent) {
        if (messageEvent.getMsg().equals("remove")){
            initData();
            allDingDanItemAdapter.notifyDataSetChanged();
        }
    }
    private void initData() {
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
        initNet();
    }

    private void initNet() {
        OkGo.get(MyContants.BASEURL + "Order/lists/")
                .tag(this)
                .params("userid", userid)
                .params("token", token)
                .params("tui", "1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        TuiKuanListsBean tuiKuanListsBean = gson.fromJson(s, TuiKuanListsBean.class);
                        int code = tuiKuanListsBean.getCode();
                        data = tuiKuanListsBean.getData();
                        if (code == 200) {
                            allDingDanItemAdapter = new AllDingDanItemAdapter(R.layout.mine_shouhou_item, data);
                            weishiyong_recycler.setLayoutManager(new LinearLayoutManager(MineShouHouActivity.this, LinearLayoutManager.VERTICAL, false));
                            weishiyong_recycler.setNestedScrollingEnabled(false);
                            weishiyong_recycler.setAdapter(allDingDanItemAdapter);
                            allDingDanItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent=new Intent(MineShouHouActivity.this,ShouHouXQActivity.class);
                                        intent.putExtra("id", data.get(position).getId());
                                    startActivity(intent);
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(MineShouHouActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initView() {
        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        weishiyong_recycler = (RecyclerView) findViewById(R.id.weishiyong_recycler);
    }

    class AllDingDanItemAdapter extends BaseQuickAdapter<TuiKuanListsBean.DataBean, BaseViewHolder> {

        public AllDingDanItemAdapter(@LayoutRes int layoutResId, @Nullable List<TuiKuanListsBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, TuiKuanListsBean.DataBean item) {

            Glide.with(MineShouHouActivity.this).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.name, item.getTitle());
            helper.setText(R.id.num, "商品数量：" + item.getProduct_num() + "/份");
            helper.setText(R.id.price, "¥" + item.getProduct_price());
            if (item.getTui_status().equals("3")){
                helper.setText(R.id.tv_status, "已完成");
            }else if (item.getTui_status().equals("4")){
                helper.setText(R.id.tv_status, "已拒绝");
            }else if (item.getTui_status().equals("5")){
                helper.setText(R.id.tv_status, "已取消");
            }else {
                helper.setText(R.id.tv_status, "退款中");
            }
        }
    }
}
