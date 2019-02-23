package com.example.administrator.chushi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.GuidePageBean;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.SpUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by lxk on 2017/6/30.
 */

public class GuidePageActivity extends BaseActivity {
    private ViewPager vp;
    private GuidePagerAdapter mGuidePagerAdapter;
    //    private int[] imgurls = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};
    private List<String> picUrls = new ArrayList<>();
    private List<GuidePageBean.DatasBean> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        vp = (ViewPager) findViewById(R.id.vp);
        go();
        //        if (mGuidePagerAdapter == null) {
        //            mGuidePagerAdapter = new GuidePagerAdapter();
        //        }
        //        vp.setAdapter(mGuidePagerAdapter);
    }

    private void go() {
        OkGo.get(MyContants.BASEURL + "Startpage/leadPage")
                .tag(GuidePageActivity.this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        GuidePageBean guidePageBean = new Gson().fromJson(s, GuidePageBean.class);
                        if (guidePageBean.getCode() == 101) {
                            Intent intent = new Intent(GuidePageActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            if (guidePageBean.getDatas() != null && guidePageBean.getDatas().size() > 0 && guidePageBean != null) {
                                datas = guidePageBean.getDatas();
                                if (mGuidePagerAdapter == null) {
                                    mGuidePagerAdapter = new GuidePagerAdapter();
                                }
                                vp.setAdapter(mGuidePagerAdapter);
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Log.e("请求失败", "失败原因：" + response);
                        Intent intent = new Intent(GuidePageActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    private class GuidePagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(GuidePageActivity.this, R.layout.item_guide, null);
            TextView tv_jinru = (TextView) view.findViewById(R.id.tv_jinru);
            if (position == datas.size() - 1) {
                tv_jinru.setVisibility(View.VISIBLE);
            } else {
                tv_jinru.setVisibility(View.GONE);
            }
            tv_jinru.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SpUtils.putBoolean(GuidePageActivity.this, "guide", true);
                    Intent intent = new Intent(GuidePageActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            ImageView iv_guide = (ImageView) view.findViewById(R.id.iv_guide);
            if (datas != null && datas.size() > 0) {
                Glide.with(GuidePageActivity.this).load(datas.get(position).getImg()).into(iv_guide);
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
