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
import com.example.administrator.chushi.utils.MyContants;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static com.taobao.accs.ACCSManager.mContext;

public class HelpXQActivity extends BaseActivity {
    private RecyclerView mine_title_recycler;
    private List<String> picList = new ArrayList<>();
    private AllDingDanAdapter allDingDanAdapter;
    private String class_id;
    private List<HelpBean.DataBean> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_xq);
        initView();
        initData();
    }
    private void initView() {
        class_id = getIntent().getStringExtra("class_id");
        ImageView tv_back= (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mine_title_recycler= (RecyclerView) findViewById(R.id.mine_title_recycler);
        initNet();
    }

        private void initNet() {
            OkGo.get(MyContants.BASEURL+"Bottom/lists/type/help/")
                    .tag(this)
                    .params("class_id",class_id)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Gson gson = new Gson();
                            HelpBean mHomeFragmentBean = gson.fromJson(s, HelpBean.class);
                            if (mHomeFragmentBean.getCode()==200){
                                data = mHomeFragmentBean.getData();

                            }else {
                                Toast.makeText(HelpXQActivity.this, "暂时没有数据", Toast.LENGTH_SHORT).show();
                            }
                            initData();
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                            Log.e("请求失败", "失败原因：" + response);
                        }
                    });
        }

    private void initData() {
            allDingDanAdapter = new AllDingDanAdapter(R.layout.mine_help_item_two, data);
        mine_title_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mine_title_recycler.setNestedScrollingEnabled(false);
        mine_title_recycler.setAdapter(allDingDanAdapter);
        allDingDanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(HelpXQActivity.this,HelpTowXQActivity.class);
                intent.putExtra("class_id",data.get(position).getId());
                startActivity(intent);
            }
        });
    }

    class AllDingDanAdapter extends BaseQuickAdapter<HelpBean.DataBean, BaseViewHolder> {

        public AllDingDanAdapter(@LayoutRes int layoutResId, @Nullable List<HelpBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HelpBean.DataBean item) {
            helper.setText(R.id.tv_help_one,item.getName());
        }
    }
}
