package com.example.administrator.chushi.fragment.good;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.activity.LoginActivity;
import com.example.administrator.chushi.activity.SearchActivity;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.GoodContentItemBean;
import com.example.administrator.chushi.db.LitePalHelper;
import com.example.administrator.chushi.db.ShopCarBean;
import com.example.administrator.chushi.fragment.home.ShoppingXQActivity;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class FenLeiXQActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView tuijianXQ_recycler;
    private LinearLayout back_layout;
    private TuiJianAdapter mTuiJianAdapter;
    private ImageView tv_search;
    private boolean isSelect = true;
    private TextView tv_jiage, tv_xiaoliang, tv_haoping, tv_renqi;
    private ImageView img_jiage, img_xiaoliang, img_haoping, img_renqi;
    private String tid;
    private List<GoodContentItemBean.DataBean> data;
    private String OrderTag = "";
    private String name;
    private String userid;
    private String token;
    private TextView tv_title;
    private String tag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fen_lei_xq);
        initView();
    }

    private void initView() {
        userid = MyUtils.getUserid(FenLeiXQActivity.this);
        token = MyUtils.getToken(FenLeiXQActivity.this);
        tid = getIntent().getStringExtra("tid");
        tag = getIntent().getStringExtra("tag");

        name = getIntent().getStringExtra("name");
        tv_title = (TextView) findViewById(R.id.tv_title);
        back_layout = (LinearLayout) findViewById(R.id.back_layout);
        back_layout.setOnClickListener(this);
        tuijianXQ_recycler = (RecyclerView) findViewById(R.id.tuijianXQ_recycler);
        tv_search = (ImageView) findViewById(R.id.tv_search);
        tv_search.setOnClickListener(this);
        tv_jiage = (TextView) findViewById(R.id.tv_jiage);
        tv_xiaoliang = (TextView) findViewById(R.id.tv_xiaoliang);
        tv_haoping = (TextView) findViewById(R.id.tv_haoping);
        tv_renqi = (TextView) findViewById(R.id.tv_renqi);
        tv_jiage.setOnClickListener(this);
        tv_xiaoliang.setOnClickListener(this);
        tv_haoping.setOnClickListener(this);
        tv_renqi.setOnClickListener(this);
        img_jiage = (ImageView) findViewById(R.id.img_jiage);
        img_xiaoliang = (ImageView) findViewById(R.id.img_xiaoliang);
        img_haoping = (ImageView) findViewById(R.id.img_haoping);
        img_renqi = (ImageView) findViewById(R.id.img_renqi);
        tv_title.setText(name);
        initNet();

    }

    private void initNetpaixu() {
        OkGo.get(MyContants.BASEURL + "Product/lists")
                .tag(this)
                .params("tid", tid)
                .params("order", OrderTag)
                .params("title", name)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        GoodContentItemBean mHomeFragmentBean = gson.fromJson(s, GoodContentItemBean.class);
                        data = mHomeFragmentBean.getData();
                        initData();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(FenLeiXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initNet() {
        if (tag.equals("tag")) {
            OkGo.get(MyContants.BASEURL + "Product/lists")
                    .tag(this)
                    .params("tid", tid)
                    .params("type", 0)
                    .params("order", 0)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Gson gson = new Gson();
                            GoodContentItemBean mHomeFragmentBean = gson.fromJson(s, GoodContentItemBean.class);
                            data = mHomeFragmentBean.getData();
                            if (data != null && data.size() > 0) {
                                initData();
                            } else {
                                Toast.makeText(FenLeiXQActivity.this, "暂时没有相关的商品", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            Toast.makeText(FenLeiXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                            Log.e("请求失败", "失败原因：" + response);
                        }
                    });
        } else {
            OkGo.get(MyContants.BASEURL + "Product/lists")
                    .tag(this)
                    .params("title", name)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Gson gson = new Gson();
                            GoodContentItemBean mHomeFragmentBean = gson.fromJson(s, GoodContentItemBean.class);
                            data = mHomeFragmentBean.getData();
                            if (data.size() > 0) {
                                initData();
                            } else {
                                Toast.makeText(FenLeiXQActivity.this, "暂时没有相关的商品", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            Toast.makeText(FenLeiXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                            Log.e("请求失败", "失败原因：" + response);
                        }
                    });
        }

    }

    private void initData() {
        mTuiJianAdapter = new TuiJianAdapter(R.layout.fenleixq_item, data);
        tuijianXQ_recycler.setLayoutManager(new GridLayoutManager(this, 2));
        tuijianXQ_recycler.setNestedScrollingEnabled(false);
        tuijianXQ_recycler.setAdapter(mTuiJianAdapter);
        mTuiJianAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = data.get(position).getId();
                Intent intent = new Intent(FenLeiXQActivity.this, ShoppingXQActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.tv_search:
                startActivity(new Intent(FenLeiXQActivity.this, SearchActivity.class));
                finish();
                break;
            case R.id.tv_jiage:
                tv_jiage.setTextColor(getResources().getColor(R.color.chengse));
                tv_xiaoliang.setTextColor(getResources().getColor(R.color.font_black_5));
                tv_haoping.setTextColor(getResources().getColor(R.color.font_black_5));
                tv_renqi.setTextColor(getResources().getColor(R.color.font_black_5));
                img_xiaoliang.setImageResource(R.drawable.bommoren);
                img_haoping.setImageResource(R.drawable.bommoren);
                img_renqi.setImageResource(R.drawable.bommoren);
                if (isSelect) {
                    img_jiage.setImageResource(R.drawable.bomtotop);
                    OrderTag = "1";
                    initNetpaixu();
                    isSelect = false;
                } else {
                    img_jiage.setImageResource(R.drawable.toptobom);
                    OrderTag = "2";
                    initNetpaixu();
                    isSelect = true;
                }
                if (data != null && data.size() > 0) {
                    mTuiJianAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_xiaoliang:
                tv_jiage.setTextColor(getResources().getColor(R.color.font_black_5));
                tv_xiaoliang.setTextColor(getResources().getColor(R.color.chengse));
                tv_haoping.setTextColor(getResources().getColor(R.color.font_black_5));
                tv_renqi.setTextColor(getResources().getColor(R.color.font_black_5));
                img_jiage.setImageResource(R.drawable.bommoren);
                img_haoping.setImageResource(R.drawable.bommoren);
                img_renqi.setImageResource(R.drawable.bommoren);
                if (isSelect) {
                    img_xiaoliang.setImageResource(R.drawable.bomtotop);
                    OrderTag = "3";
                    initNetpaixu();

                    isSelect = false;
                } else {
                    img_xiaoliang.setImageResource(R.drawable.toptobom);
                    OrderTag = "4";
                    initNetpaixu();
                    isSelect = true;
                }
                if (data != null && data.size() > 0) {
                    mTuiJianAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_haoping:
                tv_jiage.setTextColor(getResources().getColor(R.color.font_black_5));
                tv_xiaoliang.setTextColor(getResources().getColor(R.color.font_black_5));
                tv_haoping.setTextColor(getResources().getColor(R.color.chengse));
                tv_renqi.setTextColor(getResources().getColor(R.color.font_black_5));
                img_jiage.setImageResource(R.drawable.bommoren);
                img_xiaoliang.setImageResource(R.drawable.bommoren);
                img_renqi.setImageResource(R.drawable.bommoren);
                if (isSelect) {
                    img_haoping.setImageResource(R.drawable.bomtotop);
                    OrderTag = "5";
                    initNetpaixu();
                    isSelect = false;
                } else {
                    img_haoping.setImageResource(R.drawable.toptobom);
                    OrderTag = "6";
                    initNetpaixu();
                    isSelect = true;
                }
                if (data != null && data.size() > 0) {
                    mTuiJianAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_renqi:
                tv_jiage.setTextColor(getResources().getColor(R.color.font_black_5));
                tv_xiaoliang.setTextColor(getResources().getColor(R.color.font_black_5));
                tv_haoping.setTextColor(getResources().getColor(R.color.font_black_5));
                tv_renqi.setTextColor(getResources().getColor(R.color.chengse));
                img_jiage.setImageResource(R.drawable.bommoren);
                img_haoping.setImageResource(R.drawable.bommoren);
                img_xiaoliang.setImageResource(R.drawable.bommoren);
                if (isSelect) {
                    img_renqi.setImageResource(R.drawable.bomtotop);
                    OrderTag = "7";
                    initNetpaixu();
                    isSelect = false;
                } else {
                    img_renqi.setImageResource(R.drawable.toptobom);
                    OrderTag = "8";
                    initNetpaixu();
                    isSelect = true;
                }
                if (data != null && data.size() > 0) {
                    mTuiJianAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    class TuiJianAdapter extends BaseQuickAdapter<GoodContentItemBean.DataBean, BaseViewHolder> {

        public TuiJianAdapter(@LayoutRes int layoutResId, @Nullable List<GoodContentItemBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final GoodContentItemBean.DataBean item) {
            helper.getView(R.id.add_shopp).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userid.equals("") && token.equals("")) {
                        Toast.makeText(mContext, "登录后可添加到购物车", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FenLeiXQActivity.this, LoginActivity.class));
                    } else {
                        if (LitePalHelper.isInserted(item.getId())) {
                            ShopCarBean shopCarBean = LitePalHelper.
                                    getInsertedOneBean(item.getId());
                            if (shopCarBean != null) {
                                LitePalHelper.add(shopCarBean);
                            }
                        } else {
                            LitePalHelper.add(new ShopCarBean(item.getId(),
                                    item.getTitle(),
                                   Double.valueOf(item.getYuan_price()) -Double.valueOf(item.getKou_price())+"",
                                    item.getPic(),
                                    1, Integer.parseInt(item.getKucun())));
                        }
                    }
                }
            });
            if (item.getKou_price().equals("0.00")){
                helper.setVisible(R.id.tv_kou_price,false);
                helper.getView(R.id.img_tehui).setVisibility(View.INVISIBLE);

            }else {
                helper.getView(R.id.img_tehui).setVisibility(View.VISIBLE);

                helper.setText(R.id.tv_kou_price,"省¥"+item.getKou_price());
            }
            helper.setText(R.id.tv_kou_price, "省¥" + item.getKou_price());
            helper.setText(R.id.tv_shop_title, item.getTitle());
            helper.setText(R.id.tv_price, "¥" + (Double.valueOf(item.getYuan_price()) -Double.valueOf(item.getKou_price()))  +"");
            Glide.with(FenLeiXQActivity.this).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_tupian));

        }
    }
}

