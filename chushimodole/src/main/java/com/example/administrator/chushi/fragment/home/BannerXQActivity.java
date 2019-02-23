package com.example.administrator.chushi.fragment.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
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
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.BannerxqBean;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class BannerXQActivity extends BaseActivity implements View.OnClickListener {
    private ImageView img_fenxiang;
    private LinearLayout back_layout;
    private PopupWindow pop;
    private View view;
    private TextView tv_share_qq;
    private TextView tv_share_weibo;
    private TextView tv_share_weixin;
    private TextView tv_share_frind;
    private String huodongid;
    private RecyclerView bannerxq_recycler;
    private AllDingDanAdapter allDingDanAdapter;
    private TextView banner_title;
    private String pic;
    private List<BannerxqBean.DatasBean.ListBean> pic1;
    private BannerxqBean bannerxqBean;
    private String pic2;
    private String msg;
    private String Tag;
    private ImageView img_banner_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_xq);
        initView();
        initData();
    }

    private void initView() {
        huodongid = getIntent().getStringExtra("huodongid");
        back_layout = (LinearLayout) findViewById(R.id.back_layout);
        back_layout.setOnClickListener(this);
        img_fenxiang = (ImageView) findViewById(R.id.img_fenxiang);
        img_fenxiang.setOnClickListener(this);
        bannerxq_recycler = (RecyclerView) findViewById(R.id.bannerxq_recycler);
        banner_title = (TextView) findViewById(R.id.banner_title);
        img_banner_top = (ImageView) findViewById(R.id.img_banner_top);
        initNet();


    }

    private void initNet() {
        OkGo.get(MyContants.BASEURL + "Activity/detail/")
                .tag(this)
                .params("activity_id", huodongid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        bannerxqBean = gson.fromJson(s, BannerxqBean.class);
                        if (bannerxqBean.getCode() == 200) {
                            pic1 = bannerxqBean.getDatas().getList();
                            pic2 = bannerxqBean.getDatas().getPic();
                            msg = bannerxqBean.getDatas().getName();
                            if (bannerxqBean.getDatas().getPic().equals("")){
                                img_banner_top.setVisibility(View.GONE);
                            }else {
                                Glide.with(BannerXQActivity.this).load(bannerxqBean.getDatas().getPic()).into(img_banner_top);

                            }
                            banner_title.setText(msg + "");
                            allDingDanAdapter = new AllDingDanAdapter(R.layout.banner_xq_item, pic1);
                            bannerxq_recycler.setLayoutManager(new LinearLayoutManager(BannerXQActivity.this, LinearLayoutManager.VERTICAL, false));
                            bannerxq_recycler.setNestedScrollingEnabled(false);
                            bannerxq_recycler.setAdapter(allDingDanAdapter);

                            allDingDanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    if (pic1.get(position).getProduct_id().equals("0")) {

                                    } else {
                                        Intent intent = new Intent(BannerXQActivity.this, ShoppingXQActivity.class);
                                        intent.putExtra("id", pic1.get(position).getProduct_id());
                                        startActivity(intent);
                                    }

                                }
                            });
                        } else {
                            Toast.makeText(BannerXQActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                        }
                        initData();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(BannerXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    class AllDingDanAdapter extends BaseQuickAdapter<BannerxqBean.DatasBean.ListBean, BaseViewHolder> {

        public AllDingDanAdapter(@LayoutRes int layoutResId, @Nullable List<BannerxqBean.DatasBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, BannerxqBean.DatasBean.ListBean item) {
            Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.img_banner_xq));
        }
    }

    private void initData() {
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

    private ProgressDialog dialog;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_fenxiang:
                if (pop.isShowing()) {
                    pop.dismiss();
                } else {
                    pop.showAtLocation(view, Gravity.BOTTOM, 0, -560);//在父控件下方出来
                    //pop.showAsDropDown(view);
                }
                break;
            case R.id.back_layout:
                finish();
                break;
            case R.id.tv_share_QQ:
                dialog = new ProgressDialog(this);
                dialog.setMessage("正在加载……");
                dialog.show();
                UMImage image = new UMImage(BannerXQActivity.this, "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");//网络图片
                UMImage thumb = new UMImage(this, "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");
                image.setThumb(thumb);
                UMWeb web = new UMWeb("http://114.215.83.139/chushishare/activity.html?id=" + huodongid);
                                     //http://114.215.83.139/chushishare/activity.html/id=16
                web.setTitle(msg);//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription(msg);//描述
                //注意在新浪平台，缩略图属于必传参数，否则会报错
                ShareAction shareAction = new ShareAction(BannerXQActivity.this);
                shareAction
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withText("hello")//分享内容
                        .withMedia(image)
                        .withMedia(web)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.tv_share_Weibo:
                dialog = new ProgressDialog(this);
                dialog.setMessage("正在加载……");
                dialog.show();
                UMImage imageone = new UMImage(BannerXQActivity.this, "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");//网络图片
                UMImage thumbone = new UMImage(this, pic2);
                imageone.setThumb(thumbone);
                UMWeb webone = new UMWeb("http://114.215.83.139/chushishare/activity.html?id=" + huodongid);
                webone.setTitle(msg);//标题
                webone.setThumb(thumbone);  //缩略图
                webone.setDescription(msg);//描述
                //注意在新浪平台，缩略图属于必传参数，否则会报错
                ShareAction shareActionone = new ShareAction(BannerXQActivity.this);
                shareActionone
                        .setPlatform(SHARE_MEDIA.SINA)//传入平台
                        .withText("hello")//分享内容
                        .withMedia(imageone)
                        .withMedia(webone)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.tv_share_Weixin:
                dialog = new ProgressDialog(this);
                dialog.setMessage("正在加载……");
                dialog.show();
                UMImage imagetwo = new UMImage(BannerXQActivity.this, "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");//网络图片
                UMImage thumbtwo = new UMImage(this, pic2);
                imagetwo.setThumb(thumbtwo);
                UMWeb webtwo = new UMWeb("http://114.215.83.139/chushishare/activity.html?id=" + huodongid);
                webtwo.setTitle(msg);//标题
                webtwo.setThumb(thumbtwo);  //缩略图
                webtwo.setDescription(msg);//描述
                //注意在新浪平台，缩略图属于必传参数，否则会报错
                ShareAction shareActiontwo = new ShareAction(BannerXQActivity.this);
                shareActiontwo
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withText("hello")//分享内容
                        .withMedia(imagetwo)
                        .withMedia(webtwo)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.tv_share_Frind:
                dialog = new ProgressDialog(this);
                dialog.setMessage("正在加载……");
                dialog.show();
                UMImage imagethree = new UMImage(BannerXQActivity.this, "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");//网络图片
                UMImage thumbthree = new UMImage(this, pic2);
                imagethree.setThumb(thumbthree);
                UMWeb webthree = new UMWeb("http://114.215.83.139/chushishare/activity.html?id=" + huodongid);
                webthree.setTitle(msg);//标题
                webthree.setThumb(thumbthree);  //缩略图
                webthree.setDescription(msg);//描述
                //注意在新浪平台，缩略图属于必传参数，否则会报错
                ShareAction shareActionthree = new ShareAction(BannerXQActivity.this);
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
            dialog.dismiss();
            Toast.makeText(BannerXQActivity.this, "成功了", Toast.LENGTH_LONG).show();
            if (platform == SHARE_MEDIA.WEIXIN) {
                Tag = "2";
            } else if (platform == SHARE_MEDIA.QQ) {
                Tag = "3";
            } else if (platform == SHARE_MEDIA.WEIXIN_CIRCLE) {
                Tag = "4";
            } else if (platform == SHARE_MEDIA.SINA) {
                Tag = "1";
            }
            initScusses();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            dialog.dismiss();

            Toast.makeText(BannerXQActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            dialog.dismiss();
            Toast.makeText(BannerXQActivity.this, "已取消分享", Toast.LENGTH_LONG).show();

        }
    };

    private void initScusses() {
        OkGo.get(MyContants.BASEURL + "Activity/share/")
                .tag(this)
                .params("userid", MyUtils.getUserid(this))
                .params("product_id", bannerxqBean.getDatas().getId())
                .params("platform", "2")
                .params("type", "2")
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
                        Toast.makeText(BannerXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }
}
