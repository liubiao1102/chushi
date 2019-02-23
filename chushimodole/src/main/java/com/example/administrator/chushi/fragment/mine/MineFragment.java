package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.activity.LoginActivity;
import com.example.administrator.chushi.base.BaseFragment;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.MineDataBean;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.example.administrator.chushi.utils.SharedPreferencesUtils;
import com.example.administrator.chushi.view.CircleImageView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/24.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout message_relayout;
    private LinearLayout myaddress_layout;
    private TextView mine_dingdan,tv_wait_fukuan,tv_wait_daifuhuo,tv_wait_daishouhuo,mine_tuikuan,tv_dengji,tv_name,tv_phone,tv_minegz,tv_minejjr,tv_msg_num;
    private LinearLayout mine_yhj_layout,mine_shouchang_layout,liulanlishi_layout,help_Center_layout,aboutUs_layout,feedback_layout;
    private CircleImageView mydata_img;
    private String userid;
    private String token;
    private Boolean isevent = false;
    private MineDataBean.DataBean data;
    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_mine, null);
        if (!isevent) {
            EventBus.getDefault().register(this);
            isevent = true;
        }
        tv_msg_num= (TextView) view.findViewById(R.id.tv_msg_num);
        message_relayout= (RelativeLayout) view.findViewById(R.id.message_relayout);
        message_relayout.setOnClickListener(this);
        myaddress_layout= (LinearLayout) view.findViewById(R.id.myaddress_layout);
        myaddress_layout.setOnClickListener(this);
        mine_dingdan= (TextView) view.findViewById(R.id.mine_dingdan);
        mine_dingdan.setOnClickListener(this);
        tv_wait_fukuan= (TextView) view.findViewById(R.id.tv_wait_fukuan);
        tv_wait_fukuan.setOnClickListener(this);
        tv_wait_daifuhuo= (TextView) view.findViewById(R.id.tv_wait_daifahuo);
        tv_wait_daifuhuo.setOnClickListener(this);
        tv_wait_daishouhuo= (TextView) view.findViewById(R.id.tv_wait_daishouhuo);
        tv_wait_daishouhuo.setOnClickListener(this);
        mine_tuikuan= (TextView) view.findViewById(R.id.mine_tuikuan);
        mine_tuikuan.setOnClickListener(this);
        mine_yhj_layout= (LinearLayout) view.findViewById(R.id.mine_yhj_layout);
        mine_yhj_layout.setOnClickListener(this);
        mine_shouchang_layout= (LinearLayout) view.findViewById(R.id.mine_shouchang_layout);
        mine_shouchang_layout.setOnClickListener(this);
        liulanlishi_layout= (LinearLayout) view.findViewById(R.id.liulanlishi_layout);
        liulanlishi_layout.setOnClickListener(this);
        help_Center_layout= (LinearLayout) view.findViewById(R.id.help_Center_layout);
        help_Center_layout.setOnClickListener(this);
        aboutUs_layout= (LinearLayout) view.findViewById(R.id.aboutUs_layout);
        aboutUs_layout.setOnClickListener(this);
        feedback_layout= (LinearLayout) view.findViewById(R.id.feedback_layout);
        feedback_layout.setOnClickListener(this);
        mydata_img= (CircleImageView) view.findViewById(R.id.mydata_img);
        mydata_img.setOnClickListener(this);
        tv_dengji= (TextView) view.findViewById(R.id.tv_dengji);
        tv_name= (TextView) view.findViewById(R.id.tv_name);
        tv_phone= (TextView) view.findViewById(R.id.tv_phone);
        tv_minegz= (TextView) view.findViewById(R.id.tv_minegz);
        tv_minejjr= (TextView) view.findViewById(R.id.tv_minejjr);
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage messageEvent) {
        if (messageEvent.getMsg().equals("modifyname")){
            String modifyname = SharedPreferencesUtils.getInstace(mContext).getStringPreference("modifyname", "");
            tv_name.setText(modifyname);
        }else if (messageEvent.getMsg().equals("face")){
            String face = SharedPreferencesUtils.getInstace(mContext).getStringPreference("face", "");
            Glide.with(mContext).load(face).into(mydata_img);
        }else if (messageEvent.getMsg().equals("back")){
           initData();
        }else if (messageEvent.getMsg().equals("refreshmine")){
            userid = MyUtils.getUserid(mContext);
            token = MyUtils.getToken(mContext);
            if (userid.equals("")&&token.equals("")){
                mydata_img.setImageResource(R.drawable.x);
                tv_dengji.setText("LV"+"0");
                tv_name.setText("未登录");
                tv_phone.setText("");
                tv_minegz.setText("0");
                tv_minejjr.setText("0");
            }else {
                initNetone();
            }
        }
    }
    @Override
    protected void initData() {
        userid = MyUtils.getUserid(mContext);
        token = MyUtils.getToken(mContext);
        if (userid.equals("")&&token.equals("")){
            mydata_img.setImageResource(R.drawable.x);
            tv_dengji.setText("LV"+"0");
            tv_name.setText("未登录");
            tv_phone.setText("");
            tv_minegz.setText("0");
            tv_minejjr.setText("0");
            tv_msg_num.setVisibility(View.GONE);
        }else {
            initNet();
        }

    }
    private void initNetone() {
        OkGo.get(MyContants.BASEURL+"User/detail/")
                .tag(this)
                .params("userid",userid)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        MineDataBean mHomeFragmentBean = gson.fromJson(s, MineDataBean.class);
                        data = mHomeFragmentBean.getData();
                        if (mHomeFragmentBean.getCode()==200){
                            tv_dengji.setText("LV"+ data.getLevel()+"");
                            tv_name.setText(data.getUsername()+"");
                            tv_phone.setText(data.getMobile()+"");
                            tv_minegz.setText(data.getPoint()+"");
                            tv_minejjr.setText(data.getTicket()+"");
                            if (data.getUnread().equals("0")){
                                tv_msg_num.setVisibility(View.GONE);
                            }else {
                                tv_msg_num.setVisibility(View.VISIBLE);
                                tv_msg_num.setText(data.getUnread()+"");
                            }
                        }else {

                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initNet() {
        OkGo.get(MyContants.BASEURL+"User/detail/")
                .tag(this)
                .params("userid",userid)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        MineDataBean mHomeFragmentBean = gson.fromJson(s, MineDataBean.class);
                        data = mHomeFragmentBean.getData();
                        if (mHomeFragmentBean.getCode()==200){
                            if (!data.getFace().equals("")){
                                if (Util.isOnMainThread()){
                                    Glide.with(mContext).load(data.getFace()+"").into(mydata_img);
                                }
                            }
                            tv_dengji.setText("LV"+ data.getLevel()+"");
                            tv_name.setText(data.getUsername()+"");
                            tv_phone.setText(data.getMobile()+"");
                            tv_minegz.setText(data.getPoint()+"");
                            tv_minejjr.setText(data.getTicket()+"");
                            if (data.getUnread().equals("0")){
                                tv_msg_num.setVisibility(View.GONE);
                            }else {
                                tv_msg_num.setVisibility(View.VISIBLE);
                                tv_msg_num.setText(data.getUnread()+"");
                            }
                        }else {

                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //消息中心
            case R.id.message_relayout:
                if (userid.equals("")&&token.equals("")){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }else {
                    startActivity(new Intent(mContext, MyMessageActivity.class));
                }
                break;
            //我的地址
            case R.id.myaddress_layout:
                if (userid.equals("")&&token.equals("")){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }else {
                    startActivity(new Intent(mContext, MyAddressActivity.class));
                }
                break;
            //我的订单
            case R.id.mine_dingdan:
                if (userid.equals("")&&token.equals("")){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }else {
                    Intent intent1=new Intent(mContext, MineDingDanActivity.class);
                    intent1.putExtra("TAG","");
                    startActivity(intent1);
                }

                break;
            //我的优惠劵
            case R.id.mine_yhj_layout:
                if (userid.equals("")&&token.equals("")){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }else {
                    Intent intent1=new Intent(mContext, MineyhjActivity.class);
                    intent1.putExtra("orderid","");
                    startActivity(intent1);
                }
                break;
            //我的收藏
            case R.id.mine_shouchang_layout:
                if (userid.equals("")&&token.equals("")){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }else {
                    startActivity(new Intent(mContext, MineShouChangActivity.class));
                }
                break;
            //浏览历史
            case R.id.liulanlishi_layout:
                if (userid.equals("")&&token.equals("")){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }else {
                    startActivity(new Intent(mContext, MineLiShiActivity.class));
                }
                break;
            //帮助中心
            case R.id.help_Center_layout:
                    startActivity(new Intent(mContext, MineHelpActivity.class));
                break;
            //代付款
            case R.id.tv_wait_fukuan:
                if (userid.equals("")&&token.equals("")){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }else {
                    Intent intent=new Intent(mContext, MineDingDanActivity.class);
                    intent.putExtra("TAG","1");
                    startActivity(intent);
                }

                break;
            //待发货
            case R.id.tv_wait_daifahuo:
                if (userid.equals("")&&token.equals("")){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }else {
                    Intent intent=new Intent(mContext, MineDingDanActivity.class);
                    intent.putExtra("TAG","2");
                    startActivity(intent);
                }
                break;
            //待收货
            case R.id.tv_wait_daishouhuo:
                if (userid.equals("")&&token.equals("")){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }else {
                    Intent intent3=new Intent(mContext, MineDingDanActivity.class);
                    intent3.putExtra("TAG","3");
                    startActivity(intent3);
                }

                break;
            //关于我们
            case R.id.aboutUs_layout:
                startActivity(new Intent(mContext,AboutUsActivity.class));
                break;
            //反馈
            case R.id.feedback_layout:
                if (userid.equals("")&&token.equals("")){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }else {
                    startActivity(new Intent(mContext,FeedBackActivity.class));
                }

                break;
            //退货退款
            case R.id.mine_tuikuan:
                if (userid.equals("")&&token.equals("")){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }else {
                    startActivity(new Intent(mContext,MineShouHouActivity.class));
                }
                break;
            case R.id.mydata_img:
                if (userid.equals("")&&token.equals("")){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }else {
                    startActivity(new Intent(mContext,MyDaTaActivity.class));
                }
                break;
        }
    }
}
