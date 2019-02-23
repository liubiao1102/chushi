package com.example.administrator.chushi.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.base.BaseFragment;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.EventMessageCheck;
import com.example.administrator.chushi.fragment.good.GoodFragment;
import com.example.administrator.chushi.fragment.home.HomeFragment;
import com.example.administrator.chushi.fragment.mine.MineFragment;
import com.example.administrator.chushi.fragment.order.ShoppingCar2Fragment;
import com.example.administrator.chushi.utils.BaseDialog;
import com.example.administrator.chushi.utils.MyUtils;
import com.example.administrator.chushi.utils.NetworkUtils;
import com.example.administrator.chushi.utils.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private RadioGroup rgp;
    private int position;
    private List<BaseFragment> mBaseFragmentList = new ArrayList<>();
    private BaseFragment preFragment;
    private long preTime;
    private Boolean isevent = false;
    private String msg;
    private String checkshoppingcar = "";
    private ProgressDialog dialog;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                dialog.dismiss();
                EventMessage eventMessage = new EventMessage();
                eventMessage.setMsg("refreshmine");
                EventBus.getDefault().postSticky(eventMessage);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (NetworkUtils.isNetworkAvailable(this)) {
            if (getIntent() != null) {
                checkshoppingcar = getIntent().getStringExtra("checkshoppingcar");
            }
            rgp = (RadioGroup) findViewById(R.id.rgp);
            if (!isevent) {
                EventBus.getDefault().register(this);
                isevent = true;
            }
            initData();
            initListener();
        } else {
            Toast.makeText(this, "请开启网络", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);

    }

    private void initData() {
        if (mBaseFragmentList.size() <= 0) {
            mBaseFragmentList.add(new HomeFragment());
            mBaseFragmentList.add(new GoodFragment());
            mBaseFragmentList.add(new ShoppingCar2Fragment());
            mBaseFragmentList.add(new MineFragment());
        }
    }

    private void initListener() {

        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_good:
                        position = 1;
                        break;
                    case R.id.rb_order:
                        position = 2;
                        break;
                    case R.id.rb_mine:
                        position = 3;
                        dialog = new ProgressDialog(MainActivity.this);
                        dialog.setMessage("正在加载...");
                        dialog.show();
                        handler.sendEmptyMessageDelayed(1, 500);
                        break;
                    default:
                        position = 0;
                        break;
                }
                switchFragment(preFragment, mBaseFragmentList.get(position));
            }
        });
        rgp.check(R.id.rb_good);
        rgp.check(R.id.rb_home);
//        if (!checkshoppingcar.equals("")){
        if (checkshoppingcar != null) {
            if (checkshoppingcar.equals("checkshoppingcar")) {
                rgp.check(R.id.rb_order);
            }
        }

//            else if (checkshoppingcar.equals("login")){
//                rgp.check(R.id.rb_good);
//                rgp.check(R.id.rb_home);//默认选中首页
//            }
//        }else {
//            rgp.check(R.id.rb_good);
//            rgp.check(R.id.rb_home);
//        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessageCheck messageEvent) {
        if (messageEvent.getCheckNum() >= 0 && messageEvent.getCheckNum() < 100) {
            rgp.check(R.id.rb_good);
        } else {
            showcallDialog();
        }
    }

    private void showcallDialog() {
        MyUtils.cleanSharedPreference(MainActivity.this);//清除所有的sp数据

//                removeAllActivitys();
        SharedPreferencesUtils.getInstace(MainActivity.this).setStringPreference("userid", "");
        SharedPreferencesUtils.getInstace(MainActivity.this).setStringPreference("token", "");
//                SharedPreferencesUtils.getInstace(SettingsActivity.this).setStringPreference("logindata", "");
        String userid = SharedPreferencesUtils.getInstace(MainActivity.this).getStringPreference("userid", "");
        if (userid.equals("")) {
//                    startActivity(new Intent(MyDaTaActivity.this, MainActivity.class));
            EventMessage eventMessage = new EventMessage();
            eventMessage.setMsg("back");
            EventBus.getDefault().postSticky(eventMessage);

            EventMessage eventMessageone = new EventMessage();
            eventMessageone.setMsg("clearcar");
            EventBus.getDefault().postSticky(eventMessageone);
        } else {
            Toast.makeText(getApplicationContext(), "退出失败", Toast.LENGTH_SHORT).show();
        }


        BaseDialog.Builder builder = new BaseDialog.Builder(MainActivity.this);
        //设置dialogpadding
        //设置显示位置
        //设置动画
        //设置dialog的宽高
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final BaseDialog
                dialog = builder.setViewId(R.layout.item_callnumber)
                //设置dialogpadding
                .setPaddingdp(30, 0, 30, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.bottom_tab_style)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        Button btndismiss = dialog.getView(R.id.btn_dismiss);
        btndismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

    }


    private void switchFragment(BaseFragment from, BaseFragment to) {
        if (from == to) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //判断有没有被添加
        if (!to.isAdded()) {
            //to没有被添加
            //from隐藏
            if (from != null) {
                ft.hide(from);
            }
            //添加to
            if (to != null) {
                ft.add(R.id.fl_content, to).commit();//不要忘记commit
            }
        } else {
            //to已经被添加
            // from隐藏
            if (from != null) {
                ft.hide(from);
            }
            //显示to
            if (to != null) {
                ft.show(to).commit();//不要忘记commit
            }
        }
        preFragment = to;//将要显示的fragment当然就成为了下一次切换的preFragment
    }


    @Override
    public void onBackPressed() {
        if (checkshoppingcar != null && checkshoppingcar.equals("checkshoppingcar")) {
            finish();
        } else {
            if (System.currentTimeMillis() > preTime + 2000) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                preTime = System.currentTimeMillis();
            } else {

                super.onBackPressed();//相当于finish()
                realBack();//删除所有引用
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (checkshoppingcar != null && checkshoppingcar.equals("checkshoppingcar")) {

        } else {
//            if (Util.isOnMainThread()) {
//                Glide.with(MainActivity.this).pauseRequests();
//            }
        }

        EventBus.getDefault().unregister(MainActivity.this);

    }
}
