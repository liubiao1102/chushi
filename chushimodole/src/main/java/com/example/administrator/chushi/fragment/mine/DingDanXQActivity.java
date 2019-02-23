package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.DingDanXQBean;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.utils.BaseDialog;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.example.administrator.chushi.view.CircleImageView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class DingDanXQActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView mine_title_recycler;
    private List<String> picList = new ArrayList<>();
    private AllDingDanAdapter allDingDanAdapter;
    private AllDingDanItemAdapter allDingDanItemAdapter;
    private LinearLayout back_layout, layout_fukuan_time, layout_queren_time;
    private TextView tv_xq_pinglun, agent_name, tv_status, tv_shouhuo_name, tv_shouhuo_phone, tv_shouhuo_address, tv_jifen, tv_bianhao, tv_cj_time, tv_fk_time, tv_qr_time;
    private String stringExtra;
    private String id;
    private String userid;
    private String token;
    private DingDanXQBean.DataBean.AgentBean agent;
    private List<DingDanXQBean.DataBean.InfoBean> info;
    private DingDanXQBean.DataBean.OrderBean order;
    private CircleImageView agent_face;
    private LinearLayout agent_layout;
    private View agent_view;
    private TextView agent_phone,tv_phone;
    private TextView tv_pinglun;
    private TextView tv_zhuangtai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan_xq);
        initView();
    }


    private void initView() {
        stringExtra = getIntent().getStringExtra("");
        id = getIntent().getStringExtra("id");

        tv_xq_pinglun = (TextView) findViewById(R.id.tv_xq_pinglun);
        agent_name = (TextView) findViewById(R.id.agent_name);
        tv_status = (TextView) findViewById(R.id.tv_status);
        tv_shouhuo_name = (TextView) findViewById(R.id.tv_shouhuo_name);
        tv_shouhuo_phone = (TextView) findViewById(R.id.tv_shouhuo_phone);
        tv_shouhuo_address = (TextView) findViewById(R.id.tv_shouhuo_address);
        tv_jifen = (TextView) findViewById(R.id.tv_jifen);
        tv_bianhao = (TextView) findViewById(R.id.tv_bianhao);
        tv_cj_time = (TextView) findViewById(R.id.tv_cj_time);
        tv_fk_time = (TextView) findViewById(R.id.tv_fk_time);
        tv_qr_time = (TextView) findViewById(R.id.tv_qr_time);
        mine_title_recycler = (RecyclerView) findViewById(R.id.mine_title_recycler);
        back_layout = (LinearLayout) findViewById(R.id.back_layout);
        layout_fukuan_time = (LinearLayout) findViewById(R.id.layout_fukuan_time);
        layout_queren_time = (LinearLayout) findViewById(R.id.layout_queren_time);
        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        agent_face = (CircleImageView) findViewById(R.id.agent_face);
        agent_layout = (LinearLayout) findViewById(R.id.agent_layout);
        agent_view = (View) findViewById(R.id.agent_view);
        agent_phone = (TextView) findViewById(R.id.agent_phone);
        agent_phone.setOnClickListener(this);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_phone.setOnClickListener(this);
        tv_xq_pinglun.setOnClickListener(this);
        if (stringExtra.equals("daifukuan")) {
            agent_layout.setVisibility(View.GONE);
            agent_view.setVisibility(View.GONE);
            tv_xq_pinglun.setVisibility(View.GONE);
            layout_fukuan_time.setVisibility(View.GONE);
            layout_queren_time.setVisibility(View.GONE);
            tv_phone.setVisibility(View.GONE);
        } else if (stringExtra.equals("daifahuo")) {
            agent_layout.setVisibility(View.GONE);
            agent_view.setVisibility(View.GONE);
            tv_xq_pinglun.setVisibility(View.GONE);
            layout_queren_time.setVisibility(View.GONE);
            tv_phone.setVisibility(View.GONE);
        } else if (stringExtra.equals("daishouhuo")) {
            agent_phone.setVisibility(View.VISIBLE);
            tv_xq_pinglun.setVisibility(View.GONE);
            layout_queren_time.setVisibility(View.GONE);
        } else if (stringExtra.equals("yiwancheng")) {
            tv_xq_pinglun.setText("已评论");
            tv_status.setText("已完成");
        } else if (stringExtra.equals("")) {
            tv_status.setText("已完成");
        }


        initNet();
    }

    private void initNet() {
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
        OkGo.get(MyContants.BASEURL + "Order/detail/")
                .tag(this)
                .params("userid", userid)
                .params("token",token)
                .params("order_id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        DingDanXQBean mHomeFragmentBean = gson.fromJson(s, DingDanXQBean.class);
                        if (mHomeFragmentBean.getCode() == 200) {
                            //配送员
                            agent = mHomeFragmentBean.getData().getAgent();
                            //商品列表
                            info = mHomeFragmentBean.getData().getInfo();
                            //收货人
                            order = mHomeFragmentBean.getData().getOrder();
                            initData();
                        } else {
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(DingDanXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initData() {
        if (order!=null){
            if (order.getIs_pinglun().equals("1")){
                tv_xq_pinglun.setText("已评论");
            }else {
                tv_xq_pinglun.setText("评论");
            }
        }
        if (picList.size() == 0) {
            picList.add("");
        }
        int i=3;
       String str=order.getPrice().substring(0,order.getPrice().length()-i);
        agent_name.setText(agent.getName());
        Glide.with(DingDanXQActivity.this).load(agent.getFace()).into(agent_face);
        tv_shouhuo_name.setText(order.getShouhuo_name() + "");
        tv_shouhuo_phone.setText(order.getShouhuo_mobile() + "");
        tv_shouhuo_address.setText(order.getAddress() + "");
        tv_jifen.setText((Integer.parseInt(str)-Integer.parseInt(order.getYun_price().substring(0,order.getYun_price().length()-i)))+"点");
        tv_bianhao.setText(order.getNumber() + "");
        tv_cj_time.setText(order.getAddtime() + "");
        tv_fk_time.setText(order.getFukuan_time() + "");
        tv_qr_time.setText(order.getQueren_time() + "");


            allDingDanAdapter = new AllDingDanAdapter(R.layout.mine_all_item, picList);
        mine_title_recycler.setLayoutManager(new LinearLayoutManager(DingDanXQActivity.this, LinearLayoutManager.VERTICAL, false));
        mine_title_recycler.setNestedScrollingEnabled(false);
        mine_title_recycler.setAdapter(allDingDanAdapter);
    }
    private String Tag;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_xq_pinglun:
                if (tv_xq_pinglun.getText().toString().equals("评论")) {
                    Intent intent = new Intent(DingDanXQActivity.this, PingLunActivity.class);
                    intent.putExtra("face", agent.getFace());
                    intent.putExtra("name", agent.getName());
                    intent.putExtra("status", tv_status.getText().toString());
                    intent.putExtra("id",order.getId());
                    startActivityForResult(intent,0);
                } else {

                }
                break;
            case R.id.tv_phone:
                Tag=agent.getStore_mobile();
                showDialog(Gravity.CENTER, R.style.Alpah_aniamtion);
                break;
            case R.id.agent_phone:
                Tag=agent.getMobile();
                showDialog(Gravity.CENTER, R.style.Alpah_aniamtion);
                break;
        }
    }
    private void showDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_phone)
                //设置dialogpadding
                .setPaddingdp(10, 0, 10, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        TextView tv_content = dialog.getView(R.id.tv_content);
        tv_content.setText("确认拨打电话："+Tag);
        TextView tv_canel = dialog.getView(R.id.tv_canel);
        tv_canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭dialog
                dialog.close();
            }
        });
        TextView tv_yes = dialog.getView(R.id.tv_yes);
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + Tag));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                dialog.close();
            }
        });
    }

    class AllDingDanAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public AllDingDanAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView tv_zengsong = helper.getView(R.id.tv_zengsong);
            if (order!=null){
                if (order.getGift().equals("")){
                    tv_zengsong.setVisibility(View.GONE);
                }else {
                    tv_zengsong.setText("赠送:"+order.getGift());
                }
            }
            helper.setText(R.id.tv_peisong,info.get(0).getPeisong_name());
            tv_zhuangtai = helper.getView(R.id.tv_zhuangtai);

            if (stringExtra.equals("daifukuan")) {
                tv_zhuangtai.setText("待付款");
            } else if (stringExtra.equals("daifahuo")) {
                tv_zhuangtai.setText("待发货");
            } else if (stringExtra.equals("daishouhuo")) {
                tv_zhuangtai.setText("待收货");
            } else if (stringExtra.equals("")) {
                tv_zhuangtai.setText("待评论");
            } else if (stringExtra.equals("yiwancheng")) {
                tv_zhuangtai.setText("已完成");

            }

            TextView tv_all_price = helper.getView(R.id.tv_all_price);
            TextView tv_allnumber = helper.getView(R.id.tv_allnumber);
            tv_allnumber.setText("共" + order.getOrder_num() + "件商品");
            tv_all_price.setText("¥" + order.getPrice() + "");
            helper.setText(R.id.yunfei_price, "(含配送费¥" + order.getYun_price() + ")");


            RecyclerView all_dingdan_recycler = helper.getView(R.id.all_dingdan_recycler);
                allDingDanItemAdapter = new AllDingDanItemAdapter(R.layout.mine_waitxq_item, info);
            all_dingdan_recycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            all_dingdan_recycler.setNestedScrollingEnabled(false);
            all_dingdan_recycler.setAdapter(allDingDanItemAdapter);

        }
    }


    class AllDingDanItemAdapter extends BaseQuickAdapter<DingDanXQBean.DataBean.InfoBean, BaseViewHolder> {

        public AllDingDanItemAdapter(@LayoutRes int layoutResId, @Nullable List<DingDanXQBean.DataBean.InfoBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final DingDanXQBean.DataBean.InfoBean item) {
            Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_shoppname, "商品名称：" + item.getTitle());
            helper.setText(R.id.tv_shoppnum, "商品数量：" + item.getProduct_num() + " 份");
            helper.setText(R.id.tv_shop_price, "¥" + item.getProduct_price());
            final TextView tv_pinglun = helper.getView(R.id.tv_pinglun);
            if (stringExtra.equals("daifukuan")) {
                helper.getView(R.id.tv_pinglun).setVisibility(View.GONE);
            } else if (stringExtra.equals("daifahuo")) {
                helper.getView(R.id.tv_pinglun).setVisibility(View.GONE);
            } else if (stringExtra.equals("daishouhuo")) {
                helper.getView(R.id.tv_pinglun).setVisibility(View.GONE);
            } else if (stringExtra.equals("")) {

            } else if (stringExtra.equals("yiwancheng")) {
                helper.setText(R.id.tv_pinglun, "已评论");
            }
            if (order.getStatus().equals("4")){
                tv_zhuangtai.setText("已完成");
                tv_pinglun.setText("已评论");
            }


            for (int i = 0; i < info.size(); i++) {
                if (helper.getAdapterPosition()==i){
                    String is_pinglun = info.get(i).getIs_pinglun();
                    if (is_pinglun.equals("1")){
                        helper.setText(R.id.tv_pinglun, "已评论");
                    }else {
                        helper.setText(R.id.tv_pinglun, "评论");
                    }
                }
            }

         tv_pinglun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tv_pinglun.getText().toString().equals("评论")) {
                        Intent intent = new Intent(DingDanXQActivity.this, ShopPingJiaActivity.class);
                        intent.putExtra("shop_pic",item.getPic());
                        intent.putExtra("shop_name",item.getTitle());
                        intent.putExtra("shop_num",item.getProduct_num());
                        intent.putExtra("shop_price",item.getProduct_price());
                        intent.putExtra("product_id",item.getProduct_id());
                        intent.putExtra("order_id",id);
                        startActivityForResult(intent,0);
                    } else {

                    }
                }
            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==0){

        }else if (requestCode==0&&requestCode==1){

        }
        initView();
        EventMessage eventMessage = new EventMessage();
        eventMessage.setMsg("refresh");
        EventBus.getDefault().postSticky(eventMessage);
    }
}
