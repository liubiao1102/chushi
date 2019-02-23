package com.example.administrator.chushi.fragment.mine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.YouHuiJuanBean;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MineyhjActivity extends BaseActivity {
    private ImageView tv_back;
    private ViewPager vp_look;
    private RadioGroup rg_look;
    private List<Fragment> mlist = new ArrayList<>();
    private FragmentManager fm;
    private RadioButton all_dingdan,rb_daifukuan,rb_daifahuo,rb_daishouhuo,rb_daipinglun;
    private String userid;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mineyhj);
        initView();
        initData();
    }
    private void initView() {
        tv_back= (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        vp_look = (ViewPager)findViewById(R.id.vp_look);
        rg_look = (RadioGroup)findViewById(R.id.rg_look);
        all_dingdan= (RadioButton) findViewById(R.id.all_dingdan);
        rb_daifukuan= (RadioButton) findViewById(R.id.rb_daifukuan);
        rb_daifahuo= (RadioButton) findViewById(R.id.rb_daifahuo);
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
        initNet();
        mlist.add(new WeiShiYongFragment());
        mlist.add(new YiShiYongFragment());
        mlist.add(new YiGuoQiFragment());
    }
    private void initNet() {
        OkGo.get(MyContants.BASEURL + "User/ticket/")
                .tag(this)
                .params("userid", userid)
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();

                        YouHuiJuanBean mHomeFragmentBean = gson.fromJson(s, YouHuiJuanBean.class);
                        if (mHomeFragmentBean.getCode() == 200) {
                            List<YouHuiJuanBean.DataBean.YouxiaoBean> youxiao = mHomeFragmentBean.getData().getYouxiao();
                            List<YouHuiJuanBean.DataBean.WuxiaoBean> wuxiao = mHomeFragmentBean.getData().getWuxiao();
                            List<YouHuiJuanBean.DataBean.UsedBean> used = mHomeFragmentBean.getData().getUsed();
                            all_dingdan.setText("未使用("+youxiao.size()+")");
                            rb_daifukuan.setText("已使用("+used.size()+")");
                            rb_daifahuo.setText("已过期("+wuxiao.size()+")");
                        } else {
                            Toast.makeText(MineyhjActivity.this, "暂时没有优惠劵", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(MineyhjActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }
    private void initData() {
        vp_look.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) rg_look.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg_look.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.all_dingdan:
                        vp_look.setCurrentItem(0);
//                        vp_look.removeAllViews();
                        break;
                    case R.id.rb_daifukuan:
                        vp_look.setCurrentItem(1);
                        break;
                    case R.id.rb_daifahuo:
                        vp_look.setCurrentItem(2);
                        break;
                }
            }
        });
        fm = getSupportFragmentManager();
        vp_look.setAdapter(new MyAdapter(fm));
    }

    class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mlist.get(position);
        }

        @Override
        public int getCount() {
            return mlist.size();
        }
    }
}
