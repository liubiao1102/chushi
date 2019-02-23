package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.bean.LiShiLieBiaoBean;
import com.example.administrator.chushi.fragment.home.ShoppingXQActivity;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MineLiShiActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView tv_back;
    private RecyclerView weishiyong_recycler;
    private List<String> picList = new ArrayList<>();
    private AllDingDanItemAdapter allDingDanItemAdapter;
    private String userid;
    private String token;
    private List<LiShiLieBiaoBean.DataBean> data;
    private TextView tv_Delete;
    private LiShiLieBiaoBean mHomeFragmentBean;
    private StringBuilder stringBuilder;
    private boolean isEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_li_shi);
        initView();
    }

    private void initNet() {
        OkGo.get(MyContants.BASEURL + "User/browse/")
                .tag(this)
                .params("userid", userid)
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        mHomeFragmentBean = gson.fromJson(s, LiShiLieBiaoBean.class);
                        if (mHomeFragmentBean.getCode() == 200) {
                            data = mHomeFragmentBean.getData();
                            initData();
                        }else {
                            if (allDingDanItemAdapter!=null){
                                allDingDanItemAdapter.getData().clear();
                                allDingDanItemAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(MineLiShiActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initData() {
        allDingDanItemAdapter = new AllDingDanItemAdapter(R.layout.mine_liulanlishi_item, data);
        weishiyong_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        weishiyong_recycler.setNestedScrollingEnabled(false);
        weishiyong_recycler.setAdapter(allDingDanItemAdapter);
        allDingDanItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = data.get(position).getProduct_id();
                Intent intent=new Intent(MineLiShiActivity.this,ShoppingXQActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });
    }

    private void initView() {
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
//        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();

        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        weishiyong_recycler = (RecyclerView) findViewById(R.id.weishiyong_recycler);
        tv_Delete = (TextView) findViewById(R.id.tv_Delete);
        tv_Delete.setOnClickListener(this);
        initNet();
    }

    private List<String> collectIds = new ArrayList<>();

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_Delete:
                if (tv_Delete.getText().equals("删除")){
                    tv_Delete.setText("编辑");
                    isEdit=false;
                    delete();
                }else if (tv_Delete.getText().equals("编辑")){
                    tv_Delete.setText("删除");
                    isEdit=true;
                }
                allDingDanItemAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void delete() {
        int size = allDingDanItemAdapter.getData().size();
        if (collectIds.size() > 0) {
            collectIds.clear();
        }
        for (int i = 0; i < size; i++) {
            LiShiLieBiaoBean.DataBean dataBean = allDingDanItemAdapter.getData().get(i);
            if (dataBean.isCheck() == true) {
                String browse_id = dataBean.getBrowse_id();
                collectIds.add(browse_id);
            }
        }
        stringBuilder = new StringBuilder();
        for (String collectId : collectIds) {
            stringBuilder.append(collectId + ",");
        }
        OkGo.get(MyContants.BASEURL + "User/del_browse/")
                .tag(this)
                .params("userid", userid)
                .params("token", token)
                .params("browse_id", stringBuilder.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        //                        allDingDanItemAdapter.notifyDataSetChanged();
                        initNet();
                        Toast.makeText(MineLiShiActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(MineLiShiActivity.this, "请选择要删除的记录", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });

    }

    class AllDingDanItemAdapter extends BaseQuickAdapter<LiShiLieBiaoBean.DataBean, BaseViewHolder> {

        public AllDingDanItemAdapter(@LayoutRes int layoutResId, @Nullable List<LiShiLieBiaoBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final LiShiLieBiaoBean.DataBean item) {
            CheckBox check_ischeck = helper.getView(R.id.check_ischeck);
            if (isEdit){
                helper.setVisible(R.id.check_ischeck,true);
            }else {
                helper.setVisible(R.id.check_ischeck,false);
            }
            check_ischeck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setCheck(!item.isCheck());
                }
            });
            Glide.with(MineLiShiActivity.this).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_lishi_title, item.getTitle());
            helper.setText(R.id.tv_lishi_price, item.getYuan_price() + "元");

        }
    }

    public void refresh() {
        onCreate(null);
    }
}
