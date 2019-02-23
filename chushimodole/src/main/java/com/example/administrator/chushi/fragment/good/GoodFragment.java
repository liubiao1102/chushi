package com.example.administrator.chushi.fragment.good;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.activity.SearchActivity;
import com.example.administrator.chushi.base.BaseFragment;
import com.example.administrator.chushi.bean.BannerBeanHome;
import com.example.administrator.chushi.bean.EventMessageCheck;
import com.example.administrator.chushi.bean.GoodFragmentBean;
import com.example.administrator.chushi.bean.GoodFragmentContentBean;
import com.example.administrator.chushi.bean.GoodFragmentLeftBean;
import com.example.administrator.chushi.bean.TitleBean;
import com.example.administrator.chushi.fragment.home.BannerXQActivity;
import com.example.administrator.chushi.utils.MyContants;
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

/**
 * Created by Administrator on 2017/8/24.
 */

public class GoodFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recycler_title;
    private RecyclerView recycler_content;
    private RecyclerView recycler_title_top;
    private TextView tv_search;
    private List<TitleBean> titleList = new ArrayList<>();
    private List<String> contentList = new ArrayList<>();
    private TitleAdapter mTitleAdapter;
    private TopTitleAdapter mTopTitleAdapter;
    private ContentAdapter mContentAdapter;
    private Boolean isevent = false;
    private String msg = "";
    private List<GoodFragmentBean.DataBean> data;
    private List<GoodFragmentLeftBean.DataBean> leftdata;
    private String pid;
    private List<GoodFragmentContentBean.DataBean> contentdata;
    private TitleAdapter titleAdapter;
    private String PIDTAG = "1";
    private String tid;
    private List<BannerBeanHome.DatasBean> datas;
    private String id;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_good, null);
        recycler_title = (RecyclerView) view.findViewById(R.id.recycler_title);
        recycler_content = (RecyclerView) view.findViewById(R.id.recycler_content);
        recycler_title_top = (RecyclerView) view.findViewById(R.id.recycler_title_top);
        tv_search = (TextView) view.findViewById(R.id.tv_search);
        tv_search.setOnClickListener(this);
        if (!isevent) {
            EventBus.getDefault().register(this);
            isevent = true;
        }
        return view;
    }

    @Override
    protected void initData() {
        initNet();
        initContent();

    }

    private void initNet() {
        //顶部活动
        OkGo.get(MyContants.BASEURL + "Product/activity_lists/")
                .tag(mContext)
                .params("show", "2")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        GoodFragmentBean mHomeFragmentBean = gson.fromJson(s, GoodFragmentBean.class);
                        data = mHomeFragmentBean.getData();

                        //左侧列表
                        initLeft();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initLeft() {
        OkGo.get(MyContants.BASEURL + "Product/type")
                .tag(mContext)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        GoodFragmentLeftBean mHomeFragmentBean = gson.fromJson(s, GoodFragmentLeftBean.class);
                        leftdata = mHomeFragmentBean.getData();
                        leftdata.get(0).setChecked(true);
                        initContent();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initContent() {
        OkGo.get(MyContants.BASEURL + "Product/type")
                .tag(mContext)
                .params("pid", PIDTAG)
                .params("level", "2")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        GoodFragmentContentBean mHomeFragmentBean = gson.fromJson(s, GoodFragmentContentBean.class);
                        contentdata = mHomeFragmentBean.getData();
                        initBanner();

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initBanner() {
        OkGo.get(MyContants.BASEURL + "Activity/lists/")
                .tag(GoodFragment.this)
                .params("show", "2")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        BannerBeanHome mHomeFragmentBean = gson.fromJson(s, BannerBeanHome.class);
                        int code = mHomeFragmentBean.getCode();
                        if (code == 200) {
                            datas = mHomeFragmentBean.getDatas();

                        } else {
                            Toast.makeText(mContext, "Banner图出错了", Toast.LENGTH_SHORT).show();

                        }
                        initAll();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initAll() {
        recycler_content.setLayoutManager(new GridLayoutManager(mContext, 2));
        recycler_title.setLayoutManager(new LinearLayoutManager(mContext));
        mTitleAdapter = new TitleAdapter(R.layout.good_title_item, leftdata);
        recycler_title.setAdapter(mTitleAdapter);


        mContentAdapter = new ContentAdapter(R.layout.good_content_item, contentdata);
        recycler_content.setAdapter(mContentAdapter);
        mContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {


            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String tid = contentdata.get(position).getId();
                Intent intent = new Intent(mContext, FenLeiXQActivity.class);
                intent.putExtra("tid", tid);
                intent.putExtra("name", contentdata.get(position).getName());
                intent.putExtra("tag", "tag");
                startActivity(intent);
            }
        });

        recycler_title_top.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mTopTitleAdapter = new TopTitleAdapter(R.layout.good_top_title_item, datas);
        recycler_title_top.setAdapter(mTopTitleAdapter);
        mTopTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, BannerXQActivity.class);
                intent.putExtra("huodongid", datas.get(position).getId());
                startActivity(intent);
            }
        });
    }

    class TitleAdapter extends BaseQuickAdapter<GoodFragmentLeftBean.DataBean, BaseViewHolder> {

        public TitleAdapter(@LayoutRes int layoutResId, @Nullable List<GoodFragmentLeftBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, GoodFragmentLeftBean.DataBean item) {
            pid = item.getId();
            helper.setText(R.id.rb_title, item.getName());
            helper.setChecked(R.id.rb_title, item.isChecked());
            if (item.isChecked()) {

            } else {

            }
            helper.getView(R.id.rb_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //直接在外层用adapter的点击事件就不管用，真是邪门
                    for (int i = 0; i < leftdata.size(); i++) {
                        if (helper.getAdapterPosition() == i) {
                            leftdata.get(i).setChecked(true);
                            PIDTAG = leftdata.get(i).getId();
                            initContent();
                            recycler_title.setAdapter(mTitleAdapter);
                        } else {
                            leftdata.get(i).setChecked(false);
                        }
                    }
                    mTitleAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessageCheck messageEvent) {
        int checkNum = messageEvent.getCheckNum();
        for (int i = 0; i < leftdata.size(); i++) {
            if (checkNum==i){
                leftdata.get(checkNum).setChecked(true);
            }else {
                leftdata.get(i).setChecked(false);
            }
        }

        PIDTAG = leftdata.get(checkNum).getId();
        initContent();
        if (mTitleAdapter != null) {
//            Toast.makeText(mContext, "aa", Toast.LENGTH_SHORT).show();
            mTitleAdapter.setNewData(leftdata);
            mTitleAdapter.notifyDataSetChanged();
        }
    }

    class TopTitleAdapter extends BaseQuickAdapter<BannerBeanHome.DatasBean, BaseViewHolder> {

        public TopTitleAdapter(@LayoutRes int layoutResId, @Nullable List<BannerBeanHome.DatasBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, BannerBeanHome.DatasBean item) {
            helper.setText(R.id.tv_good_top, item.getName());
            Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.img_good_top));
        }
    }

    class ContentAdapter extends BaseQuickAdapter<GoodFragmentContentBean.DataBean, BaseViewHolder> {

        public ContentAdapter(@LayoutRes int layoutResId, @Nullable List<GoodFragmentContentBean.DataBean> data) {
            super(layoutResId, data);
        }


        @Override
        protected void convert(BaseViewHolder helper, GoodFragmentContentBean.DataBean item) {
            helper.setText(R.id.tv_content_name, item.getName());
            Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_tupian));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(mContext);
    }
}
