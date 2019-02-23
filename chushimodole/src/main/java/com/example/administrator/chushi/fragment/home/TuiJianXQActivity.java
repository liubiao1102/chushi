package com.example.administrator.chushi.fragment.home;

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
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.BannerBeanHome;
import com.example.administrator.chushi.bean.GoodContentItemBean;
import com.example.administrator.chushi.db.LitePalHelper;
import com.example.administrator.chushi.db.ShopCarBean;
import com.example.administrator.chushi.utils.BannerUtils;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class TuiJianXQActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout back_layout;
    private RecyclerView tuijianXQ_recycler;
    private Banner banner;
    private TuiJianAdapter mTuiJianAdapter;
    private List<String> mPicList = new ArrayList<>();
    private boolean isSelect = true;
    private TextView tv_jiage, tv_xiaoliang, tv_haoping, tv_renqi;
    private ImageView img_jiage, img_xiaoliang, img_haoping, img_renqi;
    private String tag = "0";
    private String bannertag = "3";
    private List<GoodContentItemBean.DataBean> data;
    private String OrderTag = "";
    private List<BannerBeanHome.DatasBean> datas;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui_jian_xq);
        initView();

    }

    private void initView() {
        tag = getIntent().getStringExtra("tag");
        bannertag = getIntent().getStringExtra("bannertag");
        banner = (Banner) findViewById(R.id.banner);
        back_layout = (LinearLayout) findViewById(R.id.back_layout);
        back_layout.setOnClickListener(this);
        tuijianXQ_recycler = (RecyclerView) findViewById(R.id.tuijianXQ_recycler);
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
        tv_title = (TextView) findViewById(R.id.tv_title);
        if (tag.equals("1")) {
            tv_title.setText("营养套餐推荐");
        } else if (tag.equals("2")) {
            tv_title.setText("特惠商品推荐");
        }
        initNet();

    }

    private void initNetBanner() {
        OkGo.get(MyContants.BASEURL + "Activity/lists/")
                .tag(this)
                .params("show", bannertag)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        BannerBeanHome mHomeFragmentBean = gson.fromJson(s, BannerBeanHome.class);
                        int code = mHomeFragmentBean.getCode();
                        if (code == 200) {

                            datas = mHomeFragmentBean.getDatas();
                            if (datas != null && datas.size() > 0) {
                                for (int i = 0; i < datas.size(); i++) {
                                    mPicList.add(datas.get(i).getPic());
                                }
                            }
                        } else {
                            Toast.makeText(TuiJianXQActivity.this, "Banner图出错了", Toast.LENGTH_SHORT).show();

                        }

                        BannerUtils.startBanner(banner, mPicList);
                        banner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                String huodongid = datas.get(position).getId();
                                Intent intent = new Intent(TuiJianXQActivity.this, BannerXQActivity.class);
                                intent.putExtra("huodongid", huodongid);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(TuiJianXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initNetpaixu() {
        OkGo.get(MyContants.BASEURL + "Product/lists")
                .tag(this)
                .params("order", OrderTag)
                .params("type", tag)
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
                        Toast.makeText(TuiJianXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initNet() {
        OkGo.get(MyContants.BASEURL + "Product/lists")
                .tag(this)
                .params("type", tag)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        GoodContentItemBean mHomeFragmentBean = gson.fromJson(s, GoodContentItemBean.class);
                        data = mHomeFragmentBean.getData();
                        initNetBanner();
                        initData();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(TuiJianXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
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
                mTuiJianAdapter.notifyDataSetChanged();
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
                mTuiJianAdapter.notifyDataSetChanged();
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
                mTuiJianAdapter.notifyDataSetChanged();
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
                mTuiJianAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void initData() {


        mTuiJianAdapter = new TuiJianAdapter(R.layout.tuijianxq_item, data);
        tuijianXQ_recycler.setLayoutManager(new GridLayoutManager(this, 2));
        tuijianXQ_recycler.setNestedScrollingEnabled(false);
        tuijianXQ_recycler.setAdapter(mTuiJianAdapter);
        mTuiJianAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = data.get(position).getId();
                Intent intent = new Intent(TuiJianXQActivity.this, ShoppingXQActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    class TuiJianAdapter extends BaseQuickAdapter<GoodContentItemBean.DataBean, BaseViewHolder> {

        public TuiJianAdapter(@LayoutRes int layoutResId, @Nullable List<GoodContentItemBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final GoodContentItemBean.DataBean item) {
            ImageView img_add = helper.getView(R.id.img_add);
            if (item.getKou_price().equals("0.00")){
                helper.setVisible(R.id.tv_kou_price,false);
                helper.getView(R.id.img_tehui).setVisibility(View.INVISIBLE);

            }else {
                helper.getView(R.id.img_tehui).setVisibility(View.VISIBLE);

                helper.setText(R.id.tv_kou_price,"省¥"+item.getKou_price());
            }            helper.setText(R.id.tv_shop_title, item.getTitle());
            helper.setText(R.id.tv_price,"¥"+ item.getPrice());
            Glide.with(TuiJianXQActivity.this).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_tupian));
            img_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userid = MyUtils.getUserid(TuiJianXQActivity.this);
                    String token = MyUtils.getToken(TuiJianXQActivity.this);
                    if (userid.equals("") && token.equals("")) {
                        Toast.makeText(TuiJianXQActivity.this, "登录后可添加到购物车", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(TuiJianXQActivity.this, LoginActivity.class);
                        startActivity(intent1);
                    } else {
                        if (LitePalHelper.isInserted(item.getId())) {
                            ShopCarBean shopCarBean = LitePalHelper.
                                    getInsertedOneBean(item.getId());
                            if (shopCarBean != null) {
                                LitePalHelper.updateCount(item.getId(), shopCarBean.getCount() + 1);
                                Toast.makeText(TuiJianXQActivity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            LitePalHelper.add(new ShopCarBean(item.getId(),
                                    item.getTitle(),
                                    Double.valueOf(item.getYuan_price())-Double.valueOf(item.getKou_price())+"",
                                    item.getPic(),
                                    1, Integer.parseInt(item.getKucun())));
                        }
                    }
                }
            });
        }
    }
        }