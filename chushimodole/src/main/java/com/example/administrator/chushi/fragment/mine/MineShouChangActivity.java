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
import com.example.administrator.chushi.bean.ShouCangBean;
import com.example.administrator.chushi.bean.ShouCangLieBiaoBean;
import com.example.administrator.chushi.fragment.home.ShoppingXQActivity;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class MineShouChangActivity extends BaseActivity {
    private ImageView tv_back;
    private RecyclerView shoucang_recycler;
    private AllDingDanItemAdapter allDingDanItemAdapter;
    private String userid;
    private String token;
    private List<ShouCangLieBiaoBean.DataBean> data;
    private boolean isCheck;
    private String product_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_shou_chang);
        initView();

    }



    private void initView() {
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);

        tv_back= (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shoucang_recycler= (RecyclerView)findViewById(R.id.shoucang_recycler);
        initNet();
    }

    private void initNet() {
        OkGo.get(MyContants.BASEURL+"User/collect_lists/")
                .tag(this)
                .params("userid",userid)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ShouCangLieBiaoBean mHomeFragmentBean = gson.fromJson(s, ShouCangLieBiaoBean.class);
                        if (mHomeFragmentBean.getCode()==200){
                            data = mHomeFragmentBean.getData();
                            initData();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(MineShouChangActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });

    }

    private void initData() {
        if (allDingDanItemAdapter == null) {
            allDingDanItemAdapter = new AllDingDanItemAdapter(R.layout.mine_shouchang_item, data);
        }
        shoucang_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        shoucang_recycler.setNestedScrollingEnabled(false);
        shoucang_recycler.setAdapter(allDingDanItemAdapter);
        allDingDanItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = data.get(position).getProduct_id();
                Intent intent=new Intent(MineShouChangActivity.this,ShoppingXQActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });
    }

    class AllDingDanItemAdapter extends BaseQuickAdapter<ShouCangLieBiaoBean.DataBean, BaseViewHolder> {

        public AllDingDanItemAdapter(@LayoutRes int layoutResId, @Nullable List<ShouCangLieBiaoBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final ShouCangLieBiaoBean.DataBean item) {

            ImageView img_mine_shoucang = helper.getView(R.id.img_mine_shoucang);
            Glide.with(MineShouChangActivity.this).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_tupianshoucang));
            helper.setText(R.id.tv_title_shoucang,item.getTitle());
            helper.setText(R.id.tv_price_shoucang,"￥"+item.getYuan_price());
            img_mine_shoucang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < data.size(); i++) {
                        if (helper.getAdapterPosition() == i) {
                            product_id = allDingDanItemAdapter.getData().get(i).getProduct_id();
                            allDingDanItemAdapter.remove(i);
                            initShoucangClear();
                        }
                    }

                }
            });

        }
    }
    private void initShoucangClear() {
        OkGo.get(MyContants.BASEURL+"User/del_collect")
                .tag(this)
                .params("product_id",product_id)
                .params("userid",userid)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ShouCangBean mHomeFragmentBean = gson.fromJson(s, ShouCangBean.class);
                        if (mHomeFragmentBean.getCode()==200){
                            Toast.makeText(MineShouChangActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                            initNet();
                            shoucang_recycler.setAdapter(allDingDanItemAdapter);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(MineShouChangActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }
}
