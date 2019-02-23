package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseFragment;
import com.example.administrator.chushi.bean.DingDanBean;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.ReturnSeccsess;
import com.example.administrator.chushi.fragment.order.JieSuanActivity;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
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

public class AllDingDanFragment extends BaseFragment {
    private RecyclerView mine_title_recycler;
    private List<String> picList = new ArrayList<>();
    private AllDingDanAdapter allDingDanAdapter;
    //    private AllDingDanItemAdapter allDingDanItemAdapter;
    private String userid;
    private String token;
    private List<DingDanBean.DataBean> data;
    private String order_id;
    private String sp_id;
    private Boolean isevent = false;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.activity_all_ding_dan_fragment, null);
        mine_title_recycler = (RecyclerView) view.findViewById(R.id.mine_title_recycler);
        if (!isevent) {
            EventBus.getDefault().register(this);
            isevent = true;
        }

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage messageEvent) {
        if (messageEvent.getMsg().equals("refresh")) {
//            if (data.size()==1){
//                data.remove(0);
//                data.clear();
//                allDingDanAdapter.remove(0);
//                allDingDanAdapter.notifyDataSetChanged();
//            }
//            allDingDanAdapter.notifyDataSetChanged();
//            mine_title_recycler.setAdapter(allDingDanAdapter);
            initData();
        }
    }

    @Override
    protected void initData() {
        initNet();

    }

    private void initNet() {
        userid = MyUtils.getUserid(mContext);
        token = MyUtils.getToken(mContext);
        OkGo.get(MyContants.BASEURL + "Order/lists/")
                .tag(this)
                .params("userid", userid)
                .params("token", token)
                .params("status", "5")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        DingDanBean mHomeFragmentBean = gson.fromJson(s, DingDanBean.class);
                        if (mHomeFragmentBean.getCode() == 200) {
                            data = mHomeFragmentBean.getData();
                            allDingDanAdapter = new AllDingDanAdapter(R.layout.mine_waitfukuan_item, data);
                            mine_title_recycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                            mine_title_recycler.setNestedScrollingEnabled(false);
                            mine_title_recycler.setAdapter(allDingDanAdapter);
                            allDingDanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    String id = data.get(position).getId();
                                    String status = data.get(position).getStatus();
                                    Intent intent = new Intent(mContext, DingDanXQActivity.class);
                                    intent.putExtra("id", id);
                                    if (status.equals("0")) {
                                        intent.putExtra("", "daifukuan");
                                    } else if (status.equals("1")) {
                                        intent.putExtra("", "daifahuo");
                                    } else if (status.equals("2")) {
                                        intent.putExtra("", "daishouhuo");
                                    } else if (status.equals("3")) {
                                        intent.putExtra("", "");
                                    } else if (status.equals("4")) {
                                        intent.putExtra("", "yiwancheng");
                                    }
                                    startActivity(intent);
                                }
                            });
                        } else {
                            mine_title_recycler.setVisibility(View.GONE);
                            Toast.makeText(mContext, "暂时没有订单", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    class AllDingDanAdapter extends BaseQuickAdapter<DingDanBean.DataBean, BaseViewHolder> {

        public AllDingDanAdapter(@LayoutRes int layoutResId, @Nullable List<DingDanBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final DingDanBean.DataBean item) {
            helper.setIsRecyclable(false);
            helper.setText(R.id.tv_peisong, item.getPeisong_name());
            helper.setText(R.id.tv_allnumber, "共" + item.getOrder_num() + "件商品");
            helper.setText(R.id.tv_allprice, "¥" + item.getOrder_price());
            Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_title, "商品名称：" + item.getTitle());
            helper.setText(R.id.tv_num, "商品数量：" + item.getProduct_num() + " 份/" + item.getUnit());
            helper.setText(R.id.tv_price, "¥" + item.getProduct_price());
            helper.setText(R.id.yunfei_price, "(含配送费¥" + item.getYun_price() + ")");


            //显示隐藏下面的按钮以及跳转不同页面
            LinearLayout gone_layout = helper.getView(R.id.gone_layout);
            final TextView left_gone = helper.getView(R.id.left_gone);
            final TextView right_gone = helper.getView(R.id.right_gone);

            String status = item.getStatus();
            if (status.equals("0")) {
                helper.setText(R.id.tv_status, "待付款");
                left_gone.setVisibility(View.VISIBLE);
                right_gone.setVisibility(View.VISIBLE);
                left_gone.setText("取消订单");
                right_gone.setText("付款");
            } else if (status.equals("1")) {
                helper.setText(R.id.tv_status, "待发货");
                left_gone.setVisibility(View.INVISIBLE);
                right_gone.setVisibility(View.VISIBLE);
                right_gone.setText("申请退款");
            } else if (status.equals("2")) {
                helper.setText(R.id.tv_status, "待收货");
                left_gone.setVisibility(View.INVISIBLE);
                right_gone.setVisibility(View.VISIBLE);
                right_gone.setText("确认收货");
            } else if (status.equals("3")) {
                helper.setText(R.id.tv_status, "待评论");
                left_gone.setVisibility(View.VISIBLE);
                right_gone.setVisibility(View.VISIBLE);
                left_gone.setText("退货退款");
                right_gone.setText("去评论");
            } else if (status.equals("4")) {
                helper.setText(R.id.tv_status, "已完成");
                left_gone.setVisibility(View.INVISIBLE);
                right_gone.setVisibility(View.VISIBLE);
                right_gone.setText("退货退款");
//                gone_layout.setVisibility(View.INVISIBLE);

            }
            if (item.getTui_status().equals("5")||item.getBuneng()==1) {
                if (left_gone.getText().equals("退货退款")){
                    left_gone.setVisibility(View.INVISIBLE);
                }
                if (right_gone.getText().equals("申请退款")||right_gone.getText().equals("退货退款")){
                    right_gone.setVisibility(View.INVISIBLE);
                }
            }
            left_gone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (left_gone.getText().toString().equals("取消订单")) {
                        order_id = item.getId();
                        initDelete();

                    } else if (left_gone.getText().toString().equals("退货退款")) {
                            Intent intent = new Intent(mContext, TuiKuanActivity.class);
                            intent.putExtra("tag", "2");
                            intent.putExtra("sp_id", item.getId());
                            intent.putExtra("name", item.getTitle());
                            intent.putExtra("num", item.getProduct_num());
                            intent.putExtra("price", item.getProduct_price());
                            intent.putExtra("pic", item.getPic());
                            startActivity(intent);

                    }
                }
            });
            right_gone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (right_gone.getText().toString().equals("付款")) {
                        Intent intent = new Intent(mContext, JieSuanActivity.class);
                        intent.putExtra("dingdan", "dingdan");
                        intent.putExtra("price", item.getOrder_price());
                        intent.putExtra("number", item.getNumber());
                        intent.putExtra("shixiao_time", item.getShixiao_time());
                        startActivity(intent);

                    } else if (right_gone.getText().toString().equals("申请退款")) {
                            sp_id = item.getId();
                            Intent intent = new Intent(mContext, TuiKuanActivity.class);
                            intent.putExtra("tag", "1");
                            intent.putExtra("sp_id", sp_id);
                            intent.putExtra("name", item.getTitle());
                            intent.putExtra("num", item.getProduct_num());
                            intent.putExtra("price", item.getProduct_price());
                            intent.putExtra("pic", item.getPic());
                            startActivity(intent);
                    } else if (right_gone.getText().toString().equals("退货退款")) {
                            sp_id = item.getId();
                            Intent intent = new Intent(mContext, TuiKuanActivity.class);
                            intent.putExtra("tag", "1");
                            intent.putExtra("sp_id", sp_id);
                            intent.putExtra("name", item.getTitle());
                            intent.putExtra("num", item.getProduct_num());
                            intent.putExtra("price", item.getProduct_price());
                            intent.putExtra("pic", item.getPic());
                            startActivity(intent);
                    }else if (right_gone.getText().toString().equals("确认收货")) {
                        String id = item.getId();
                        SureShouHuo(id);
                    } else if (right_gone.getText().toString().equals("去评论")) {
                        Intent intent = new Intent(mContext, DingDanXQActivity.class);
                        intent.putExtra("", "");
                        intent.putExtra("id", item.getId());
                        startActivity(intent);
                    }
                }
            });

        }
    }

    private void SureShouHuo(String order_id) {
        OkGo.get(MyContants.BASEURL + "Order/commitOrder/")
                .tag(this)
                .params("order_id", order_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ReturnSeccsess returnSeccsess = gson.fromJson(s, ReturnSeccsess.class);
                        if (returnSeccsess.getCode() == 200) {
                            Toast.makeText(mContext, "确认收货成功", Toast.LENGTH_SHORT).show();
                            EventMessage eventMessage = new EventMessage();
                            eventMessage.setMsg("refresh");
                            EventBus.getDefault().postSticky(eventMessage);
                            initNet();
                        } else {
                            Toast.makeText(mContext, returnSeccsess.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initDelete() {
        OkGo.get(MyContants.BASEURL + "Order/del/")
                .tag(this)
                .params("userid", userid)
                .params("token", token)
                .params("order_id", order_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ReturnSeccsess returnSeccsess = gson.fromJson(s, ReturnSeccsess.class);
                        if (returnSeccsess.getCode() == 200) {
                            EventMessage eventMessage = new EventMessage();
                            eventMessage.setMsg("refresh");
                            eventMessage.setMsg("refreshmine");
                            EventBus.getDefault().postSticky(eventMessage);
                            Toast.makeText(mContext, "取消成功", Toast.LENGTH_SHORT).show();
                            initNet();

                        } else {
                            Toast.makeText(mContext, returnSeccsess.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }
}
