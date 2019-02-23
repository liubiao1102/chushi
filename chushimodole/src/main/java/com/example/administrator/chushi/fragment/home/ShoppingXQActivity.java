package com.example.administrator.chushi.fragment.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.activity.LoginActivity;
import com.example.administrator.chushi.activity.MainActivity;
import com.example.administrator.chushi.base.UMShareActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.ShoppingXQBean;
import com.example.administrator.chushi.bean.ShouCangBean;
import com.example.administrator.chushi.db.LitePalHelper;
import com.example.administrator.chushi.db.ShopCarBean;
import com.example.administrator.chushi.utils.BannerUtils;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.example.administrator.chushi.utils.NetworkUtils;
import com.example.administrator.chushi.view.AmountView;
import com.example.administrator.chushi.view.MyScrollView;
import com.example.administrator.chushi.view.PullUpToLoadMore;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ShoppingXQActivity extends UMShareActivity implements View.OnClickListener {
    private List<String> mPicList = new ArrayList<>();
    private Banner banner;
    private ImageView iv_back;
    private AmountView mAmountView;
    private ImageView img_fengxiang;
    private PopupWindow pop;
    private View view;
    private ImageView add_shopcar, addgreen_img;
    private String id;
    private TextView tv_content, tv_price, tv_kucunshengyu, tv_name, tv_baozhiqi, tv_address, tv_number;
    private List<ShoppingXQBean.DataBeanX.DataBean.DetailBean> detailBeanList;
    private LinearLayout layout_one, layout_two, layout_three, layout_four, layout_five, layout_six, all_layout;
    private View view_one, view_two, view_three, view_four, view_five, view_six, view_all_huodong,di_view1,di_view2,di_view3;
    private ImageView img_one, img_two, img_three, img_four, img_five, img_six, img_xq_one, img_xq_two;
    private TextView tv_name_one, tv_name_two, tv_name_three, tv_name_four, tv_name_five, tv_name_six;
    private TextView tv_price_one, tv_price_two, tv_price_three, tv_price_four, tv_price_five, tv_price_six, tv_guige;
    private ImageView img_shoucang, addgreen_img_one, addgreen_img_two, addgreen_img_three, addgreen_img_four, addgreen_img_five, addgreen_img_six;
    private boolean isCheck = true;
    private List<ShoppingXQBean.DataBeanX.GuessBean> guess;
    private List<ShoppingXQBean.DataBeanX.DataBean.BannerBean> bannerBeanList;
    private String userid;
    private String token;
    private String is_collect;
    private TextView tv_gocar;
    private ShoppingXQBean mMHomeFragmentBean;
    private int currCount = 1;
    private TextView tv_share_qq;
    private TextView tv_share_weibo;
    private TextView tv_share_weixin;
    private TextView tv_share_frind;
    private ShoppingXQBean.DataBeanX.DataBean data;
    private String title;
    private String pic;
    private Bitmap bitmap;
    private ProgressDialog dialog;
    private NestedScrollView ysnowswebview;
    public RecyclerView mine_title_recycler;
    private AllDingDanAdapter allDingDanAdapter;
    private int mDistanceY;
    private int anInt;
    private String addpic;
    private TextView tv_kou_price, tv_manzeng, tv_manjian;
    private LinearLayout layout_all_huodong, layout_zhekou, layout_manzeng, layout_manjian;
    private MyScrollView MyScrollView;
    private PullUpToLoadMore ptlm;
    private TextView tv_tishi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_xq);
        initView();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initRecycle() {
        allDingDanAdapter = new AllDingDanAdapter(R.layout.shopp_xq_recycler, detailBeanList);
        mine_title_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mine_title_recycler.setNestedScrollingEnabled(false);
        mine_title_recycler.setAdapter(allDingDanAdapter);
    }

    class AllDingDanAdapter extends BaseQuickAdapter<ShoppingXQBean.DataBeanX.DataBean.DetailBean, BaseViewHolder> {

        public AllDingDanAdapter(@LayoutRes int layoutResId, @Nullable List<ShoppingXQBean.DataBeanX.DataBean.DetailBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ShoppingXQBean.DataBeanX.DataBean.DetailBean item) {
            ImageView img_shop_xq = (ImageView) helper.getView(R.id.img_shop_xq);
            WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            int width1 = display.getWidth();
//            Toast.makeText(ShoppingXQActivity.this,width1+"",Toast.LENGTH_SHORT).show();
            ViewGroup.LayoutParams layoutParams = img_shop_xq.getLayoutParams();
//            layoutParams.height = (int) (width1 * 0.6);
//            layoutParams.height = item.getHeight();
            layoutParams.height = width1*(item.getHeight()/item.getWidth());
            layoutParams.width = width1;
            img_shop_xq.setLayoutParams(layoutParams);
            Glide.with(ShoppingXQActivity.this).load(item.getPic()).into(img_shop_xq);

        }
    }

    private void initView() {
        id = getIntent().getStringExtra("id");
        layout_all_huodong = (LinearLayout) findViewById(R.id.layout_all_huodong);
        layout_zhekou = (LinearLayout) findViewById(R.id.layout_zhekou);
        layout_manzeng = (LinearLayout) findViewById(R.id.layout_manzeng);
        layout_manjian = (LinearLayout) findViewById(R.id.layout_manjian);

        tv_kou_price = (TextView) findViewById(R.id.tv_kou_price);
        tv_manzeng = (TextView) findViewById(R.id.tv_manzeng);
        tv_manjian = (TextView) findViewById(R.id.tv_manjian);
        view_all_huodong = (View) findViewById(R.id.view_all_huodong);
        tv_tishi = (TextView) findViewById(R.id.tv_tishi);
        ptlm = (PullUpToLoadMore) findViewById(R.id.ptlm);

        MyScrollView = (com.example.administrator.chushi.view.MyScrollView) findViewById(R.id.MyScrollView);
        mine_title_recycler = (RecyclerView) findViewById(R.id.mine_title_recycler);
        mine_title_recycler.setNestedScrollingEnabled(false);
        layout_one = (LinearLayout) findViewById(R.id.layout_one);
        layout_two = (LinearLayout) findViewById(R.id.layout_two);
        layout_three = (LinearLayout) findViewById(R.id.layout_three);
        layout_four = (LinearLayout) findViewById(R.id.layout_four);
        layout_five = (LinearLayout) findViewById(R.id.layout_five);
        layout_six = (LinearLayout) findViewById(R.id.layout_six);
        all_layout = (LinearLayout) findViewById(R.id.all_layout);
        view_one = findViewById(R.id.view_one);
        view_two = findViewById(R.id.view_two);
        view_three = findViewById(R.id.view_three);
        view_four = findViewById(R.id.view_four);
        view_five = findViewById(R.id.view_five);
        view_six = findViewById(R.id.view_six);
        di_view1 = findViewById(R.id.di_view1);
        di_view2 = findViewById(R.id.di_view2);
        di_view3 = findViewById(R.id.di_view3);

        layout_one.setOnClickListener(this);
        layout_two.setOnClickListener(this);
        layout_three.setOnClickListener(this);
        layout_four.setOnClickListener(this);
        layout_five.setOnClickListener(this);
        layout_six.setOnClickListener(this);


        img_one = (ImageView) findViewById(R.id.img_one);
        img_two = (ImageView) findViewById(R.id.img_two);
        img_three = (ImageView) findViewById(R.id.img_three);
        img_four = (ImageView) findViewById(R.id.img_four);
        img_five = (ImageView) findViewById(R.id.img_five);
        img_six = (ImageView) findViewById(R.id.img_six);

        addgreen_img_one = (ImageView) findViewById(R.id.addgreen_img_one);
        addgreen_img_two = (ImageView) findViewById(R.id.addgreen_img_two);
        addgreen_img_three = (ImageView) findViewById(R.id.addgreen_img_three);
        addgreen_img_four = (ImageView) findViewById(R.id.addgreen_img_four);
        addgreen_img_five = (ImageView) findViewById(R.id.addgreen_img_five);
        addgreen_img_six = (ImageView) findViewById(R.id.addgreen_img_six);
        addgreen_img_one.setOnClickListener(this);
        addgreen_img_two.setOnClickListener(this);
        addgreen_img_three.setOnClickListener(this);
        addgreen_img_four.setOnClickListener(this);
        addgreen_img_five.setOnClickListener(this);
        addgreen_img_six.setOnClickListener(this);

        tv_name_one = (TextView) findViewById(R.id.tv_name_one);
        tv_name_two = (TextView) findViewById(R.id.tv_name_two);
        tv_name_three = (TextView) findViewById(R.id.tv_name_three);
        tv_name_four = (TextView) findViewById(R.id.tv_name_four);
        tv_name_five = (TextView) findViewById(R.id.tv_name_five);
        tv_name_six = (TextView) findViewById(R.id.tv_name_six);

        tv_price_one = (TextView) findViewById(R.id.tv_price_one);
        tv_price_two = (TextView) findViewById(R.id.tv_price_two);
        tv_price_three = (TextView) findViewById(R.id.tv_price_three);
        tv_price_four = (TextView) findViewById(R.id.tv_price_four);
        tv_price_five = (TextView) findViewById(R.id.tv_price_five);
        tv_price_six = (TextView) findViewById(R.id.tv_price_six);

        img_shoucang = (ImageView) findViewById(R.id.img_shoucang);
        img_shoucang.setOnClickListener(this);
//        slidingMenu = (SlidingMenu) findViewById(R.id.expanded_menu);
        img_fengxiang = (ImageView) findViewById(R.id.img_fengxiang);
        img_fengxiang.setOnClickListener(this);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        mAmountView = (AmountView) findViewById(R.id.mAmountView);
        banner = (Banner) findViewById(R.id.banner);
        add_shopcar = (ImageView) findViewById(R.id.add_shopcar);
        add_shopcar.setOnClickListener(this);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_kucunshengyu = (TextView) findViewById(R.id.tv_kucunshengyu);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_baozhiqi = (TextView) findViewById(R.id.tv_baozhiqi);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_guige = (TextView) findViewById(R.id.tv_guige);

        tv_gocar = (TextView) findViewById(R.id.tv_gocar);
        tv_gocar.setOnClickListener(this);
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
        ptlm.setScrollListener(new PullUpToLoadMore.ScrollListener() {
            @Override
            public void onScrollToBottom() {
                mine_title_recycler.setNestedScrollingEnabled(false);
                tv_tishi.setText("向下滑动加载商品详情");

            }

            @Override
            public void onScrollToTop() {
                tv_tishi.setText("向上滑动加载图文详情");

            }
        });

        dialog = new ProgressDialog(ShoppingXQActivity.this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("正在加载...");
        dialog.show();
        if (NetworkUtils.isNetworkAvailable(this)) {
            if (userid.equals("") && token.equals("")) {
                initNet();
            } else {
                initNetOne();
            }
        } else {
            Toast.makeText(this, "请开启网络", Toast.LENGTH_SHORT).show();
        }

//        initScroll();

    }


    private void gotoXQ(String id) {
        Intent intent = new Intent(ShoppingXQActivity.this, ShoppingXQActivity.class);
        intent.putExtra("id", id + "");
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.layout_one:
                gotoXQ(guess.get(0).getId());
                break;
            case R.id.layout_two:
                gotoXQ(guess.get(1).getId());
                break;
            case R.id.layout_three:
                gotoXQ(guess.get(2).getId());
                break;
            case R.id.layout_four:
                gotoXQ(guess.get(3).getId());
                break;
            case R.id.layout_five:
                gotoXQ(guess.get(4).getId());
                break;
            case R.id.layout_six:
                gotoXQ(guess.get(5).getId());
                break;

            //6个写死的
            case R.id.addgreen_img_one:
                if (mMHomeFragmentBean.getData().getGuess() != null && mMHomeFragmentBean.getData().getGuess().size() > 0 && !mMHomeFragmentBean.getData().getGuess().get(0).getId().equals("")) {
                    huluwa(0);
                } else {
                    Toast.makeText(this, "此商品没有信息", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.addgreen_img_two:
                huluwa(1);
                break;
            case R.id.addgreen_img_three:
                huluwa(2);
                break;
            case R.id.addgreen_img_four:
                huluwa(3);
                break;
            case R.id.addgreen_img_five:
                huluwa(4);
                break;
            case R.id.addgreen_img_six:
                huluwa(5);
                break;
            case R.id.img_shoucang:
                userid = MyUtils.getUserid(this);
                token = MyUtils.getToken(this);
                if (userid.equals("") && token.equals("")) {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ShoppingXQActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    if (isCheck) {
                        img_shoucang.setImageResource(R.drawable.shoucang);
                        initShoucang();
                        isCheck = false;
                    } else {
                        img_shoucang.setImageResource(R.drawable.shoucangxq);
                        initShoucangClear();
                        isCheck = true;
                    }
                }

                break;

            case R.id.add_shopcar:
                Intent intent = new Intent(ShoppingXQActivity.this, MainActivity.class);
                intent.putExtra("checkshoppingcar", "checkshoppingcar");
                startActivity(intent);
                break;
            case R.id.img_fengxiang:
//                shareWebUrl("http://114.215.83.139/chushishare/fresh.html?id"+id, "aa",
//                        "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg",
//                        "aaa", ShoppingXQActivity.this);


                if (pop.isShowing()) {
                    pop.dismiss();
                } else {
                    pop.showAtLocation(view, Gravity.BOTTOM, 0, -560);//在父控件下方出来
                    //pop.showAsDropDown(view);
                }
                break;
            case R.id.tv_gocar:
                //                GreenUtils.insert(new GreenAllBean(
                //                        mMHomeFragmentBean.getData().getData().getId(),
                //                        mMHomeFragmentBean.getData().getData().getTitle(),
                //                        mMHomeFragmentBean.getData().getData().getYuan_price(),
                //                        mMHomeFragmentBean.getData().getData().getBanner().get(0).getPic(),
                //                        currCount,Integer.parseInt(mMHomeFragmentBean.getData().getData().getKucun())
                //                ));
                userid = MyUtils.getUserid(this);
                token = MyUtils.getToken(this);
                if (userid.equals("") && token.equals("")) {
                    Toast.makeText(ShoppingXQActivity.this, "登录后可添加到购物车", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(ShoppingXQActivity.this, LoginActivity.class);
                    startActivity(intent1);
                } else {
                    if (LitePalHelper.isInserted(mMHomeFragmentBean.getData().getData().getId())) {
                        ShopCarBean shopCarBean = LitePalHelper.
                                getInsertedOneBean(mMHomeFragmentBean.getData().getData().getId());
                        if (shopCarBean != null) {
                            LitePalHelper.updateCount(mMHomeFragmentBean.getData().getData().getId(), shopCarBean.getCount() + currCount);
                            Toast.makeText(this, "添加购物车成功", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (mMHomeFragmentBean.getData().getData().getBanner().size() > 0) {
                            addpic = mMHomeFragmentBean.getData().getData().getBanner().get(0).getPic();
                        } else {
                            addpic = "";
                            Toast.makeText(this, "商品没有图片", Toast.LENGTH_SHORT).show();
                        }
                        LitePalHelper.add(new ShopCarBean(mMHomeFragmentBean.getData().getData().getId(),
                                mMHomeFragmentBean.getData().getData().getTitle(),
                                Double.valueOf(mMHomeFragmentBean.getData().getData().getYuan_price()) - Double.valueOf(mMHomeFragmentBean.getData().getData().getKou_price()) + "",
                                addpic,
                                currCount, Integer.parseInt(mMHomeFragmentBean.getData().getData().getKucun())));
                    }
                }

                break;
            case R.id.tv_share_QQ:
                UMImage image = new UMImage(ShoppingXQActivity.this, "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");//网络图片
                UMImage thumb = new UMImage(this, pic + "");
                image.setThumb(thumb);
                UMWeb web = new UMWeb("http://114.215.83.139/chushishare/fresh.html?id=" + id);
                web.setTitle(title + "");//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription(title + "");//描述
                //注意在新浪平台，缩略图属于必传参数，否则会报错
                ShareAction shareAction = new ShareAction(ShoppingXQActivity.this);
                shareAction
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withText("hello")//分享内容
                        .withMedia(image)
                        .withMedia(web)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.tv_share_Weibo:

                UMImage imageone = new UMImage(ShoppingXQActivity.this, "");//网络图片
                UMImage thumbone = new UMImage(this, pic);
                imageone.setThumb(thumbone);
                UMWeb webone = new UMWeb("http://114.215.83.139/chushishare/fresh.html?id=" + id);
                webone.setTitle(title + "");//标题
                webone.setThumb(thumbone);  //缩略图
                webone.setDescription(title + "");//描述
                //注意在新浪平台，缩略图属于必传参数，否则会报错
                ShareAction shareActionone = new ShareAction(ShoppingXQActivity.this);
                shareActionone
                        .setPlatform(SHARE_MEDIA.SINA)//传入平台
                        .withText("hello")//分享内容
                        .withMedia(imageone)
                        .withMedia(webone)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.tv_share_Weixin:
                UMImage imagetwo = new UMImage(ShoppingXQActivity.this, "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");//网络图片
                UMImage thumbtwo = new UMImage(this, pic + "");
                imagetwo.setThumb(thumbtwo);
                UMWeb webtwo = new UMWeb("http://114.215.83.139/chushishare/fresh.html?id=" + id);
                webtwo.setTitle(title + "");//标题
                webtwo.setThumb(thumbtwo);  //缩略图
                webtwo.setDescription(title + "");//描述
                //注意在新浪平台，缩略图属于必传参数，否则会报错
                ShareAction shareActiontwo = new ShareAction(ShoppingXQActivity.this);
                shareActiontwo
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withText("hello")//分享内容
                        .withMedia(imagetwo)
                        .withMedia(webtwo)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.tv_share_Frind:
                UMImage imagethree = new UMImage(ShoppingXQActivity.this, "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");//网络图片
                UMImage thumbthree = new UMImage(this, pic + "");
                imagethree.setThumb(thumbthree);
                UMWeb webthree = new UMWeb("http://114.215.83.139/chushishare/fresh.html?id=" + id);
                webthree.setTitle(title + "");//标题
                webthree.setThumb(thumbthree);  //缩略图
                webthree.setDescription(title + "");//描述
                //注意在新浪平台，缩略图属于必传参数，否则会报错
                ShareAction shareActionthree = new ShareAction(ShoppingXQActivity.this);
                shareActionthree
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                        .withText("hello")//分享内容
                        .withMedia(imagethree)
                        .withMedia(webthree)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
        }
    }


    private void initNetOne() {
        OkGo.get(MyContants.BASEURL + "Product/detail")
                .tag(this)
                .params("id", id)
                .params("userid", userid)
                .execute(new StringCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        mMHomeFragmentBean = gson.fromJson(s, ShoppingXQBean.class);
                        detailBeanList = mMHomeFragmentBean.getData().getData().getDetail();
                        bannerBeanList = mMHomeFragmentBean.getData().getData().getBanner();
                        data = mMHomeFragmentBean.getData().getData();
                        title = data.getTitle();
                        if (bannerBeanList != null & bannerBeanList.size() > 0) {
                            pic = bannerBeanList.get(0).getPic();
                        }
                        is_collect = data.getIs_collect();
                        guess = mMHomeFragmentBean.getData().getGuess();
                        if (userid.equals("") && token.equals("")) {
//                            startActivity(new Intent(ShoppingXQActivity.this, LoginActivity.class));
                        } else {
                            if (is_collect.equals("1")) {
                                img_shoucang.setImageResource(R.drawable.shoucang);
                            } else {
                                img_shoucang.setImageResource(R.drawable.shoucangxq);
                            }

                        }
                        tv_content.setText(data.getContent());
                        tv_price.setText("￥" + (Double.valueOf(data.getYuan_price()) - Double.valueOf(data.getKou_price())) + "元/" + data.getUnit());

                        if (!data.getZhekou().equals("1") && mMHomeFragmentBean.getData().getManjian().equals("") && mMHomeFragmentBean.getData().getManzeng().equals("")) {
                            view_all_huodong.setVisibility(View.GONE);
                            layout_all_huodong.setVisibility(View.GONE);
                        } else {
                            layout_all_huodong.setVisibility(View.VISIBLE);
                            if (data.getZhekou().equals("1")) {
                                tv_kou_price.setText("￥" + data.getYuan_price() + "元/" + data.getUnit());
                                tv_kou_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
                            } else {
                                layout_zhekou.setVisibility(View.GONE);
                            }
                            if (!mMHomeFragmentBean.getData().getManzeng().equals("")) {
                                tv_manzeng.setText(mMHomeFragmentBean.getData().getManzeng());
                            } else {
                                layout_manzeng.setVisibility(View.GONE);
                            }
                            if (!mMHomeFragmentBean.getData().getManjian().equals("")) {
                                tv_manjian.setText(mMHomeFragmentBean.getData().getManjian());
                            } else {
                                layout_manjian.setVisibility(View.GONE);
                            }
                        }
                        tv_baozhiqi.setText(data.getBaozhiqi() + "天");
                        tv_kucunshengyu.setText("库存剩余：" + data.getKucun());
                        tv_name.setText(data.getTitle());
                        tv_address.setText(data.getAddress());
                        tv_number.setText("已售" + data.getSales() + "件");
                        tv_guige.setText(data.getGuige() + "");
                        dialog.dismiss();
                        initRecycle();
//                             if (detailBeanList.size() >= 2) {
//                                Glide.with(ShoppingXQActivity.this).load(detailBeanList.get(0).getPic()).into(img_xq_one);
//                                Glide.with(ShoppingXQActivity.this).load(detailBeanList.get(1).getPic()).into(img_xq_two);
//                            }else {
//                                 Glide.with(ShoppingXQActivity.this).load(detailBeanList.get(0).getPic()).into(img_xq_one);
//                                 img_xq_two.setVisibility(View.GONE);
//                             }


                        initData();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(ShoppingXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });


    }

    private void initNet() {
        OkGo.get(MyContants.BASEURL + "Product/detail")
                .tag(this)
                .params("id", id)
                .execute(new StringCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        mMHomeFragmentBean = gson.fromJson(s, ShoppingXQBean.class);
                        detailBeanList = mMHomeFragmentBean.getData().getData().getDetail();
                        bannerBeanList = mMHomeFragmentBean.getData().getData().getBanner();

                        data = mMHomeFragmentBean.getData().getData();
                        title = data.getTitle();
                        if (bannerBeanList != null && bannerBeanList.size() > 0) {
                            pic = bannerBeanList.get(0).getPic();
                        }
                        is_collect = data.getIs_collect();
                        guess = mMHomeFragmentBean.getData().getGuess();
                        if (userid.equals("") && token.equals("")) {
//                            startActivity(new Intent(ShoppingXQActivity.this, LoginActivity.class));
                        } else {
                            if (is_collect.equals("1")) {
                                img_shoucang.setImageResource(R.drawable.shoucang);
                            } else {
                                img_shoucang.setImageResource(R.drawable.shoucangxq);
                            }

                        }
                        tv_content.setText(data.getContent());
                        tv_price.setText("￥" + (Double.valueOf(data.getYuan_price()) - Double.valueOf(data.getKou_price())) + "元/" + data.getUnit());
                        if (!data.getZhekou().equals("1") && mMHomeFragmentBean.getData().getManjian().equals("") && mMHomeFragmentBean.getData().getManzeng().equals("")) {
                            view_all_huodong.setVisibility(View.GONE);
                            layout_all_huodong.setVisibility(View.GONE);
                        } else {
                            layout_all_huodong.setVisibility(View.VISIBLE);
                            if (data.getZhekou().equals("1")) {
                                tv_kou_price.setText("￥" + data.getYuan_price() + "元/" + data.getUnit());
                                tv_kou_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
                            } else {
                                layout_zhekou.setVisibility(View.GONE);
                            }
                            if (!mMHomeFragmentBean.getData().getManzeng().equals("")) {
                                tv_manzeng.setText(mMHomeFragmentBean.getData().getManzeng());
                            } else {
                                layout_manzeng.setVisibility(View.GONE);
                            }
                            if (!mMHomeFragmentBean.getData().getManjian().equals("")) {
                                tv_manjian.setText(mMHomeFragmentBean.getData().getManjian());
                            } else {
                                layout_manjian.setVisibility(View.GONE);
                            }
                        }
                        tv_baozhiqi.setText(data.getBaozhiqi() + "天");
                        tv_kucunshengyu.setText("库存剩余：" + data.getKucun());
                        tv_name.setText(data.getTitle());
                        tv_address.setText(data.getAddress());
                        tv_number.setText("已售" + data.getSales() + "件");
                        tv_guige.setText(data.getGuige() + "");
                        dialog.dismiss();
                        initRecycle();
//                            if (detailBeanList.size() >= 2) {
//                                Glide.with(ShoppingXQActivity.this).load(detailBeanList.get(0).getPic()).into(img_xq_one);
//                                Glide.with(ShoppingXQActivity.this).load(detailBeanList.get(1).getPic()).into(img_xq_two);
//                            }else {
//                                Glide.with(ShoppingXQActivity.this).load(detailBeanList.get(0).getPic()).into(img_xq_one);
//                                img_xq_two.setVisibility(View.GONE);
//                    }

                        initData();

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(ShoppingXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        //猜你喜欢
        if (guess != null && guess.size() > 0) {
            if (guess.size() == 0) {
                all_layout.setVisibility(View.GONE);
            } else if (guess.size() == 1) {
                Glide.with(ShoppingXQActivity.this).load(guess.get(0).getPic()).into(img_one);
                tv_name_one.setText(guess.get(0).getTitle());
                tv_price_one.setText("￥" + guess.get(0).getYuan_price());
                di_view1.setVisibility(View.INVISIBLE);
                di_view2.setVisibility(View.INVISIBLE);
                di_view3.setVisibility(View.INVISIBLE);
                layout_two.setVisibility(View.INVISIBLE);
                layout_three.setVisibility(View.INVISIBLE);
                layout_four.setVisibility(View.INVISIBLE);
                layout_five.setVisibility(View.INVISIBLE);
                layout_six.setVisibility(View.INVISIBLE);
                view_two.setVisibility(View.INVISIBLE);
                view_three.setVisibility(View.INVISIBLE);
                view_four.setVisibility(View.INVISIBLE);
                view_five.setVisibility(View.INVISIBLE);
                view_six.setVisibility(View.INVISIBLE);
            } else if (guess.size() == 2) {
                Glide.with(ShoppingXQActivity.this).load(guess.get(0).getPic()).into(img_one);
                tv_name_one.setText(guess.get(0).getTitle());
                tv_price_one.setText("￥" + guess.get(0).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(1).getPic()).into(img_two);
                tv_name_two.setText(guess.get(1).getTitle());
                tv_price_two.setText("￥" + guess.get(1).getYuan_price());
                di_view1.setVisibility(View.INVISIBLE);
                di_view2.setVisibility(View.INVISIBLE);
                di_view3.setVisibility(View.INVISIBLE);
                layout_three.setVisibility(View.INVISIBLE);
                layout_four.setVisibility(View.INVISIBLE);
                layout_five.setVisibility(View.INVISIBLE);
                layout_six.setVisibility(View.INVISIBLE);
                view_three.setVisibility(View.INVISIBLE);
                view_four.setVisibility(View.INVISIBLE);
                view_five.setVisibility(View.INVISIBLE);
                view_six.setVisibility(View.INVISIBLE);
            } else if (guess.size() == 3) {
                Glide.with(ShoppingXQActivity.this).load(guess.get(0).getPic()).into(img_one);
                tv_name_one.setText(guess.get(0).getTitle());
                tv_price_one.setText("￥" + guess.get(0).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(1).getPic()).into(img_two);
                tv_name_two.setText(guess.get(1).getTitle());
                tv_price_two.setText("￥" + guess.get(1).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(2).getPic()).into(img_three);
                tv_name_three.setText(guess.get(2).getTitle());
                tv_price_three.setText("￥" + guess.get(2).getYuan_price());

                di_view1.setVisibility(View.INVISIBLE);
                di_view2.setVisibility(View.INVISIBLE);
                di_view3.setVisibility(View.INVISIBLE);
                layout_four.setVisibility(View.INVISIBLE);
                layout_five.setVisibility(View.INVISIBLE);
                layout_six.setVisibility(View.INVISIBLE);
                view_four.setVisibility(View.INVISIBLE);
                view_five.setVisibility(View.INVISIBLE);
                view_six.setVisibility(View.INVISIBLE);
            } else if (guess.size() == 4) {
                Glide.with(ShoppingXQActivity.this).load(guess.get(0).getPic()).into(img_one);
                tv_name_one.setText(guess.get(0).getTitle());
                tv_price_one.setText("￥" + guess.get(0).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(1).getPic()).into(img_two);
                tv_name_two.setText(guess.get(1).getTitle());
                tv_price_two.setText("￥" + guess.get(1).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(2).getPic()).into(img_three);
                tv_name_three.setText(guess.get(2).getTitle());
                tv_price_three.setText("￥" + guess.get(2).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(3).getPic()).into(img_four);
                tv_name_four.setText(guess.get(3).getTitle());
                tv_price_four.setText("￥" + guess.get(3).getYuan_price());


                layout_five.setVisibility(View.INVISIBLE);
                layout_six.setVisibility(View.INVISIBLE);
                view_five.setVisibility(View.INVISIBLE);
                view_six.setVisibility(View.INVISIBLE);
            } else if (guess.size() == 5) {
                Glide.with(ShoppingXQActivity.this).load(guess.get(0).getPic()).into(img_one);
                tv_name_one.setText(guess.get(0).getTitle());
                tv_price_one.setText("￥" + guess.get(0).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(1).getPic()).into(img_two);
                tv_name_two.setText(guess.get(1).getTitle());
                tv_price_two.setText("￥" + guess.get(1).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(2).getPic()).into(img_three);
                tv_name_three.setText(guess.get(2).getTitle());
                tv_price_three.setText("￥" + guess.get(2).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(3).getPic()).into(img_four);
                tv_name_four.setText(guess.get(3).getTitle());
                tv_price_four.setText("￥" + guess.get(3).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(4).getPic()).into(img_five);
                tv_name_five.setText(guess.get(4).getTitle());
                tv_price_five.setText("￥" + guess.get(4).getYuan_price());

                layout_six.setVisibility(View.INVISIBLE);
                view_six.setVisibility(View.INVISIBLE);
            } else if (guess.size() == 6) {
                Glide.with(ShoppingXQActivity.this).load(guess.get(0).getPic()).into(img_one);
                tv_name_one.setText(guess.get(0).getTitle());
                tv_price_one.setText("￥" + guess.get(0).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(1).getPic()).into(img_two);
                tv_name_two.setText(guess.get(1).getTitle());
                tv_price_two.setText("￥" + guess.get(1).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(2).getPic()).into(img_three);
                tv_name_three.setText(guess.get(2).getTitle());
                tv_price_three.setText("￥" + guess.get(2).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(3).getPic()).into(img_four);
                tv_name_four.setText(guess.get(3).getTitle());
                tv_price_four.setText("￥" + guess.get(3).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(4).getPic()).into(img_five);
                tv_name_five.setText(guess.get(4).getTitle());
                tv_price_five.setText("￥" + guess.get(4).getYuan_price());

                Glide.with(ShoppingXQActivity.this).load(guess.get(5).getPic()).into(img_six);
                tv_name_six.setText(guess.get(5).getTitle());
                tv_price_six.setText("￥" + guess.get(5).getYuan_price());
            }
        }


        initPop();
        mAmountView.setGoods_storage(Integer.parseInt(mMHomeFragmentBean.getData().getData().getKucun()));
        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                currCount = amount;
            }
        });


        for (int i = 0; i < bannerBeanList.size(); i++) {
            mPicList.add(bannerBeanList.get(i).getPic());
        }
        BannerUtils.startBanner(banner, mPicList);
    }

    private void initPop() {
        WindowManager wm = this.getWindowManager();
        int height = wm.getDefaultDisplay().getHeight();
        view = getLayoutInflater().inflate(R.layout.dialog_choice, null);

        tv_share_qq = (TextView) view.findViewById(R.id.tv_share_QQ);
        tv_share_weibo = (TextView) view.findViewById(R.id.tv_share_Weibo);
        tv_share_weixin = (TextView) view.findViewById(R.id.tv_share_Weixin);
        tv_share_frind = (TextView) view.findViewById(R.id.tv_share_Frind);

        tv_share_qq.setOnClickListener(this);
        tv_share_weibo.setOnClickListener(this);
        tv_share_weixin.setOnClickListener(this);
        tv_share_frind.setOnClickListener(this);


        View viewById = view.findViewById(R.id.view);
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                height);
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);// 点击back退出pop
        pop.setAnimationStyle(R.style.add_new_style);
        pop.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明，点击back退出pop
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
    }

    private void huluwa(int i) {
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
        if (userid.equals("") && token.equals("")) {
            Toast.makeText(ShoppingXQActivity.this, "登录后可添加到购物车", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ShoppingXQActivity.this, LoginActivity.class));
        } else {
            if (LitePalHelper.isInserted(mMHomeFragmentBean.getData().getGuess().get(i).getId())) {
                ShopCarBean shopCarBean = LitePalHelper.
                        getInsertedOneBean(mMHomeFragmentBean.getData().getGuess().get(i).getId());
                if (shopCarBean != null) {
                    LitePalHelper.add(shopCarBean);
                }
            } else {
                LitePalHelper.add(new ShopCarBean(mMHomeFragmentBean.getData().getGuess().get(i).getId(),
                        mMHomeFragmentBean.getData().getGuess().get(i).getTitle(),
                        mMHomeFragmentBean.getData().getGuess().get(i).getYuan_price(),
                        mMHomeFragmentBean.getData().getGuess().get(i).getPic(),
                        1, Integer.parseInt(mMHomeFragmentBean.getData().getGuess().get(i).getKucun())));
            }
        }
    }


    private void initScusses() {
        OkGo.get(MyContants.BASEURL + "Activity/share/")
                .tag(this)
                .params("userid", MyUtils.getUserid(this))
                .params("product_id", id)
                .params("platform", "2")
                .params("type", "1")
                .params("share_platform", Tag)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        EventMessage eventMessage = new EventMessage();
                        eventMessage.setMsg("back");
                        EventBus.getDefault().postSticky(eventMessage);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(ShoppingXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private String Tag;
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ShoppingXQActivity.this, "成功", Toast.LENGTH_LONG).show();
            if (platform == SHARE_MEDIA.WEIXIN) {
                Tag = "2";
            } else if (platform == SHARE_MEDIA.QQ) {
                Tag = "3";
            } else if (platform == SHARE_MEDIA.WEIXIN_CIRCLE) {
                Tag = "4";
            } else if (platform == SHARE_MEDIA.SINA) {
                Tag = "1";
            }
            //成功了传一个商品信息
            initScusses();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {

            Toast.makeText(ShoppingXQActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {

        }
    };

    private void initShoucang() {
        OkGo.get(MyContants.BASEURL + "User/collect")
                .tag(this)
                .params("product_id", id)
                .params("userid", userid)
                .params("token", token)
                .params("platform", "2")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ShouCangBean mHomeFragmentBean = gson.fromJson(s, ShouCangBean.class);
                        if (mHomeFragmentBean.getCode() == 200) {
                            Toast.makeText(ShoppingXQActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(ShoppingXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initShoucangClear() {
        OkGo.get(MyContants.BASEURL + "User/del_collect")
                .tag(this)
                .params("product_id", id)
                .params("userid", userid)
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ShouCangBean mHomeFragmentBean = gson.fromJson(s, ShouCangBean.class);
                        if (mHomeFragmentBean.getCode() == 200) {
                            Toast.makeText(ShoppingXQActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(ShoppingXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

}
