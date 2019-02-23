package com.example.administrator.chushi.fragment.mine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.view.NoCacheViewPager;

import java.util.ArrayList;
import java.util.List;

public class MineDingDanActivity extends BaseActivity {
    private ImageView tv_back;
    private NoCacheViewPager vp_look;
    private RadioGroup rg_look;
    private List<Fragment> mlist = new ArrayList<>();
    private FragmentManager fm;
    private RadioButton all_dingdan, rb_daifukuan, rb_daifahuo, rb_daishouhuo, rb_daipinglun;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_ding_dan);
        initView();
        initData();
    }


    private void initView() {
        tag = getIntent().getStringExtra("TAG");
        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        vp_look = (NoCacheViewPager) findViewById(R.id.vp_look);
        rg_look = (RadioGroup) findViewById(R.id.rg_look);
        all_dingdan = (RadioButton      ) findViewById(R.id.all_dingdan);
        rb_daifukuan = (RadioButton) findViewById(R.id.rb_daifukuan);
        rb_daifahuo = (RadioButton) findViewById(R.id.rb_daifahuo);
        rb_daishouhuo = (RadioButton) findViewById(R.id.rb_daishouhuo);
        rb_daipinglun = (RadioButton) findViewById(R.id.rb_daipinglun);
        vp_look.setOffscreenPageLimit(0);
        mlist.add(new AllDingDanFragment());
        mlist.add(new WaitFuKuanFragment());
        mlist.add(new WaitFaHuoFragment());
        mlist.add(new WaitShouHuoFragment());
        mlist.add(new WaitPingLunFragment());

    }

    private void initData() {
        vp_look.setOnPageChangeListener(new NoCacheViewPager.OnPageChangeListener() {
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
                    case R.id.rb_daishouhuo:
                        vp_look.setCurrentItem(3);
                        break;
                    case R.id.rb_daipinglun:
                        vp_look.setCurrentItem(4);
                        break;
                }
            }
        });


        fm = getSupportFragmentManager();
        vp_look.setAdapter(new MyAdapter(fm));
        if (!tag.equals("") && tag.equals("1")) {
            rg_look.check(R.id.rb_daifukuan);
            vp_look.setCurrentItem(1);
        } else if (!tag.equals("") && tag.equals("2")) {
            rg_look.check(R.id.rb_daifukuan);
            vp_look.setCurrentItem(2);
        } else if (!tag.equals("") && tag.equals("3")) {
            rg_look.check(R.id.rb_daifukuan);
            vp_look.setCurrentItem(3);
        }
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
