package com.example.administrator.chushi.fragment.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.activity.LoginActivity;
import com.example.administrator.chushi.activity.SearchActivity;
import com.example.administrator.chushi.base.BaseFragment;
import com.example.administrator.chushi.bean.BannerBeanHome;
import com.example.administrator.chushi.bean.EventMessageCheck;
import com.example.administrator.chushi.bean.HomeFragmentBean;
import com.example.administrator.chushi.utils.BannerUtils;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/24.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private TextView tv_search, tv_msg_number;
    private LinearLayout ll_msg;
    private Banner banner;
    private RecyclerView  recycler_tuijian, recycler_changxiao, recycler_comment,recycler_liebiao;
    private RelativeLayout rl_cuxiao, rl_changxiao, rl_comment, rl_top;
    private CuxiaoAdapter mCuxiaoAdapter;
    private ChangxiaoAdapter mChangxiaoAdapter;
    private CommentAdapter mCommentAdapter;
    private LiebiaoAdapter mLiebiaoAdapter;
    private NestedScrollView nestView;
    private int mDistanceY;
    String TAG = "MainActivity";
    int REQUEST_CODE = 1;
    private TextView tv_taocan_more,tv_shop_more;
    private List<String> mPicList = new ArrayList<>();
    private List<HomeFragmentBean.DataBean.ActivityBean> activitylist;
    private List<HomeFragmentBean.DataBean.TypeBean> type;
    private HomeFragmentBean.DataBean data;
    private List<HomeFragmentBean.DataBean.YytcBean> yytc;
    private List<HomeFragmentBean.DataBean.ThspBean> thsp;
    private List<HomeFragmentBean.DataBean.RemaiBean> remai;
    private String huodongid;
    private String userid;
    private String token;
    private List<BannerBeanHome.DatasBean> datas;
    private SwipeRefreshLayout swiperereshlayout ;
    private ProgressDialog dialog;
    private TextView tv_yytc_name,tv_thsp_name;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        getCameraPermission();
        ZXingLibrary.initDisplayOpinion(mContext);

        swiperereshlayout = (SwipeRefreshLayout)view. findViewById(R.id.swiperereshlayout);
        tv_search = (TextView) view.findViewById(R.id.tv_search);
        tv_yytc_name = (TextView) view.findViewById(R.id.tv_yytc_name);
        tv_thsp_name = (TextView) view.findViewById(R.id.tv_thsp_name);
        tv_search.setOnClickListener(this);
        ll_msg = (LinearLayout) view.findViewById(R.id.ll_msg);
        ll_msg.setOnClickListener(this);
        banner = (Banner) view.findViewById(R.id.banner);
        recycler_liebiao = (RecyclerView) view.findViewById(R.id.recycler_liebiao);
        recycler_tuijian = (RecyclerView) view.findViewById(R.id.recycler_tuijian);
        recycler_changxiao = (RecyclerView) view.findViewById(R.id.recycler_changxiao);
        recycler_comment = (RecyclerView) view.findViewById(R.id.recycler_comment);
        rl_cuxiao = (RelativeLayout) view.findViewById(R.id.rl_cuxiao);
        rl_cuxiao.setOnClickListener(this);
        rl_changxiao = (RelativeLayout) view.findViewById(R.id.rl_changxiao);
        rl_changxiao.setOnClickListener(this);
        rl_comment = (RelativeLayout) view.findViewById(R.id.rl_comment);
        rl_comment.setOnClickListener(this);
        rl_top = (RelativeLayout) view.findViewById(R.id.rl_top);
        nestView = (NestedScrollView) view.findViewById(R.id.nestView);
        tv_taocan_more = (TextView) view.findViewById(R.id.tv_taocan_more);
        tv_taocan_more.setOnClickListener(this);
        tv_shop_more = (TextView) view.findViewById(R.id.tv_shop_more);
        tv_shop_more.setOnClickListener(this);
        dialog = new ProgressDialog(mContext);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("正在加载...");
        dialog.show();
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width1 = display.getWidth();
//            Toast.makeText(ShoppingXQActivity.this,width1+"",Toast.LENGTH_SHORT).show();
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
//            layoutParams.height = (int) (width1 * 0.6);
//            layoutParams.height = item.getHeight();
        layoutParams.height = (width1/4)*3;
        layoutParams.width = width1;
        banner.setLayoutParams(layoutParams);
        return view;
    }


    //标题栏渐变
    @Override
    protected void initData() {
        //联网
        initNet();
    }



    private void initNet() {
        OkGo.get(MyContants.BASEURL+"Product/index")
                .tag(HomeFragment.this)
//                .params("","")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        HomeFragmentBean mHomeFragmentBean = gson.fromJson(s, HomeFragmentBean.class);
                        data = mHomeFragmentBean.getData();
                        //首页轮播
                        activitylist = data.getActivity();
                        //首页分类
                        type = data.getType();
                        //营养套餐
                        yytc = data.getYytc();
                        //特惠商品
                        thsp = data.getThsp();
                        //热卖推荐
                        remai = data.getRemai();
                        dialog.dismiss();
                        tv_yytc_name.setText(data.getYytc_name()+"");
                        tv_thsp_name.setText(data.getThsp_name()+"");
                        initAll();

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initBanner() {
        swiperereshlayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light);
        //给swipeRefreshLayout绑定刷新监听
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //设置2秒的时间来执行以下事件
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        initNet();
                        swiperereshlayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        if (activitylist!=null&&activitylist.size()>0){
            mPicList.clear();
            for (int i = 0; i < activitylist.size(); i++) {
                mPicList.add(activitylist.get(i).getPic()+"");
            }
        }
        BannerUtils.startBanner(banner, mPicList);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                huodongid = activitylist.get(position).getId();
                Intent intent=new Intent(mContext,BannerXQActivity.class);
                intent.putExtra("huodongid",huodongid);
                startActivity(intent);
            }
        });

    }

    private void initAll() {
        initBanner();
        nestView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //滑动的距离
                mDistanceY += scrollY - oldScrollY;
                //toolbar的高度
                int toolbarHeight = 300;//我写死的高度

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    rl_top.setBackgroundColor(Color.argb((int) alpha, 255, 167, 102));
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    rl_top.setBackgroundResource(R.color.chengse);
                }
            }
        });




        //首页列表
        if (mLiebiaoAdapter == null) {
            mLiebiaoAdapter = new LiebiaoAdapter(R.layout.home_liebiao_item, type);
        }
        recycler_liebiao.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        recycler_liebiao.setNestedScrollingEnabled(false);
        recycler_liebiao.setAdapter(mLiebiaoAdapter);
        mLiebiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    EventMessageCheck eventMessage = new EventMessageCheck();
                    eventMessage.setCheckNum(position);
                    EventBus.getDefault().postSticky(eventMessage);
            }
        });
        //营养套餐推荐
            mCuxiaoAdapter = new CuxiaoAdapter(R.layout.home_tuijian_item, yytc);
        recycler_tuijian.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        recycler_tuijian.setNestedScrollingEnabled(false);
        recycler_tuijian.setAdapter(mCuxiaoAdapter);
        mCuxiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = yytc.get(position).getId();
                Intent intent=new Intent(mContext,ShoppingXQActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });



        //特惠商品推荐
            mChangxiaoAdapter = new ChangxiaoAdapter(R.layout.home_changxiao_item, thsp);
        recycler_changxiao.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        recycler_changxiao.setNestedScrollingEnabled(false);
        recycler_changxiao.setAdapter(mChangxiaoAdapter);
        mChangxiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = thsp.get(position).getId();
                Intent intent=new Intent(mContext,ShoppingXQActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        //热卖商品推荐
            mCommentAdapter = new CommentAdapter(R.layout.home_comment_item, remai);
        recycler_comment.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_comment.setNestedScrollingEnabled(false);
        recycler_comment.setAdapter(mCommentAdapter);
        mCommentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = remai.get(position).getId();
                Intent intent=new Intent(mContext,ShoppingXQActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }


    public static boolean isCameraUseable() {

        boolean canUse =true;

        Camera mCamera =null;

        try{

            mCamera = Camera.open();

// setParameters 是针对魅族MX5。MX5通过Camera.open()拿到的Camera对象不为null

            Camera.Parameters mParameters = mCamera.getParameters();

            mCamera.setParameters(mParameters);

        }catch(Exception e) {

            canUse =false;

        }

        if(mCamera !=null) {

            mCamera.release();

        }
        return canUse;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_msg:
                userid = MyUtils.getUserid(mContext);
                token = MyUtils.getToken(mContext);
                if (userid.equals("")&&token.equals("")){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));

                }else {
                    if (isCameraUseable()){
                        Intent intent1 = new Intent(mContext, CaptureActivity.class);
                        startActivityForResult(intent1, REQUEST_CODE);
                    }else {
                        Toast.makeText(mContext, "开启权限后执行该操作", Toast.LENGTH_SHORT).show();
                    }

                }

                break;
            case R.id.rl_cuxiao:
                break;
            case R.id.rl_changxiao:
                break;
            case R.id.rl_comment:
                break;
            //营养套餐推荐更多
            case R.id.tv_taocan_more:
                Intent intenttaocan=new Intent(mContext,TuiJianXQActivity.class);
                intenttaocan.putExtra("tag","1");
                intenttaocan.putExtra("bannertag","3");
                startActivity(intenttaocan);
                break;
            //特惠商品推荐更多
            case R.id.tv_shop_more:
                Intent intentshop=new Intent(mContext,TuiJianXQActivity.class);
                intentshop.putExtra("tag","2");
                intentshop.putExtra("bannertag","4");
                startActivity(intentshop);
                break;
        }
    }


    class LiebiaoAdapter extends BaseQuickAdapter<HomeFragmentBean.DataBean.TypeBean, BaseViewHolder> {

        public LiebiaoAdapter(@LayoutRes int layoutResId, @Nullable List<HomeFragmentBean.DataBean.TypeBean> data) {
            super(layoutResId,data);
        }

        @Override
        protected void convert(BaseViewHolder helper,HomeFragmentBean.DataBean.TypeBean item) {
            ImageView img_liebiao = helper.getView(R.id.img_liebiao);
            TextView tv_liebiao = helper.getView(R.id.tv_liebiao);
            tv_liebiao.setText(item.getName());
            Glide.with(mContext).load(item.getPic()).into(img_liebiao);
        }
    }
    class CuxiaoAdapter extends BaseQuickAdapter<HomeFragmentBean.DataBean.YytcBean, BaseViewHolder> {

        public CuxiaoAdapter(@LayoutRes int layoutResId, @Nullable List<HomeFragmentBean.DataBean.YytcBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeFragmentBean.DataBean.YytcBean item) {
           helper.setText(R.id.tv_taocan_name,item.getTitle()+"/"+item.getUnit());
           helper.setText(R.id.tv_taocan_price,"¥"+item.getYuan_price());
            Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_tupian));
        }
    }

    class ChangxiaoAdapter extends BaseQuickAdapter<HomeFragmentBean.DataBean.ThspBean, BaseViewHolder> {

        public ChangxiaoAdapter(@LayoutRes int layoutResId, @Nullable List<HomeFragmentBean.DataBean.ThspBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeFragmentBean.DataBean.ThspBean item) {
            if (item.getKou_price().equals("0.00")){
                helper.setVisible(R.id.tv_tehui_sheng,false);
                helper.getView(R.id.img_tehui).setVisibility(View.INVISIBLE);

            }else {
                helper.getView(R.id.img_tehui).setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_tehui_sheng,"省¥"+item.getKou_price());
            }
            helper.setText(R.id.tv_tehui_title,item.getTitle());
            helper.setText(R.id.tv_tehui_price,"¥"+item.getPrice());
            Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_tupian));
        }
    }

    class CommentAdapter extends BaseQuickAdapter<HomeFragmentBean.DataBean.RemaiBean, BaseViewHolder> {

        public CommentAdapter(@LayoutRes int layoutResId, @Nullable List<HomeFragmentBean.DataBean.RemaiBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeFragmentBean.DataBean.RemaiBean item) {
            ImageView img_remai = (ImageView) helper.getView(R.id.img_remai);
            Glide.with(mContext).load(item.getPic()).into(img_remai);

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Log.e(TAG,"解析结果:" + result);
                    Intent intent=new Intent(mContext,WebActivity.class);
                    intent.putExtra("result",result);
                    startActivity(intent);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void getCameraPermission()
    {
        if (Build.VERSION.SDK_INT>22){
            if (ContextCompat.checkSelfPermission(mContext,
                    android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.CAMERA},2);
            }else {
                //说明已经获取到摄像头权限了 想干嘛干嘛
            }
        }else {
            //这个说明系统版本在6.0之下，不需要动态获取权限。
        }
    }

}
