package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseFragment;
import com.example.administrator.chushi.bean.CheckyhjBean;
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

public class WeiShiYongFragment extends BaseFragment {
    private RecyclerView weishiyong_recycler;
    private List<String> picList = new ArrayList<>();
    private String userid;
    private String token;
    private AllDingDanItemAdapter allDingDanItemAdapter;
    private List<YouHuiJuanBean.DataBean.YouxiaoBean> youxiao;
    private String orderid = "";
    private String check_activity = "";
    private String total_price, yun_price;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.activity_weishiyong_fragment, null);
        orderid = getActivity().getIntent().getStringExtra("orderid");
        total_price = getActivity().getIntent().getStringExtra("total_price");
        yun_price = getActivity().getIntent().getStringExtra("yun_price");
        check_activity = getActivity().getIntent().getStringExtra("check_activity");

        weishiyong_recycler = (RecyclerView) view.findViewById(R.id.weishiyong_recycler);
        userid = MyUtils.getUserid(mContext);
        token = MyUtils.getToken(mContext);
        return view;
    }

    @Override
    protected void initData() {
        initNet();

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
                            youxiao = mHomeFragmentBean.getData().getYouxiao();
                            initAdapter();
                        } else {
                            Toast.makeText(mContext, "暂时没有优惠劵", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initAdapter() {
        allDingDanItemAdapter = new AllDingDanItemAdapter(R.layout.mine_aweishiyong_item, youxiao);
        weishiyong_recycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        weishiyong_recycler.setNestedScrollingEnabled(false);
        weishiyong_recycler.setAdapter(allDingDanItemAdapter);
        allDingDanItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!orderid.equals("")) {
                    if (check_activity.equals("0")) {
                        initCheck(position);
                    } else if (!check_activity.equals("0")) {
                        initCheckOne(position);
                    }
                } else {

                }

            }
        });
    }

    private void initCheckOne(final int position) {
        OkGo.get(MyContants.BASEURL + "User/ex_ticket/")
                .tag(this)
                .params("user_id", userid)
                .params("ticket_id", youxiao.get(position).getId())
                .params("order_id", orderid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        CheckyhjBean checkyhjBean = gson.fromJson(s, CheckyhjBean.class);
                        if (checkyhjBean.getCode() == 200) {
                            if ((Double.valueOf(yun_price) + Double.valueOf(total_price)) >= Double.valueOf(youxiao.get(position).getRule_money())&&youxiao.get(position).getDiejia().equals("1")) {
                                Intent intent = getActivity().getIntent();
                                intent.putExtra("title", youxiao.get(position).getTitle());
                                intent.putExtra("money", youxiao.get(position).getMoney());
                                intent.putExtra("Ticket_user_id", youxiao.get(position).getTicket_user_id());
                                intent.putExtra("rule_money", youxiao.get(position).getRule_money());
                                intent.putExtra("ponit", checkyhjBean.getData().getPoint());
                                intent.putExtra("ponit_money", checkyhjBean.getData().getPoint_money());
                                intent.putExtra("yun_price", checkyhjBean.getData().getYun_price());
//                                Toast.makeText(mContext, youxiao.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                                getActivity().setResult(1, intent);
                                getActivity().finish();
                            } else {
                                Toast.makeText(mContext, "优惠劵不符合使用规则", Toast.LENGTH_SHORT).show();
                            }

                        } else {

                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initCheck(final int position) {
        OkGo.get(MyContants.BASEURL + "User/ex_ticket/")
                .tag(this)
                .params("user_id", userid)
                .params("ticket_id", youxiao.get(position).getId())
                .params("order_id", orderid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        CheckyhjBean checkyhjBean = gson.fromJson(s, CheckyhjBean.class);
                        if (checkyhjBean.getCode() == 200) {
                            if ((Double.valueOf(yun_price) + Double.valueOf(total_price)) >= Double.valueOf(youxiao.get(position).getRule_money())) {
                                Intent intent = getActivity().getIntent();
                                intent.putExtra("title", youxiao.get(position).getTitle());
                                intent.putExtra("money", youxiao.get(position).getMoney());
                                intent.putExtra("Ticket_user_id", youxiao.get(position).getTicket_user_id());
                                intent.putExtra("rule_money", youxiao.get(position).getRule_money());
                                intent.putExtra("ponit", checkyhjBean.getData().getPoint());
                                intent.putExtra("ponit_money", checkyhjBean.getData().getPoint_money());
                                intent.putExtra("yun_price", checkyhjBean.getData().getYun_price());
//                                Toast.makeText(mContext, youxiao.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                                getActivity().setResult(1, intent);
                                getActivity().finish();
                            } else {
                                Toast.makeText(mContext, "优惠劵不符合使用规则", Toast.LENGTH_SHORT).show();
                            }

                        } else {

                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    class AllDingDanItemAdapter extends BaseQuickAdapter<YouHuiJuanBean.DataBean.YouxiaoBean, BaseViewHolder> {

        public AllDingDanItemAdapter(@LayoutRes int layoutResId, @Nullable List<YouHuiJuanBean.DataBean.YouxiaoBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, YouHuiJuanBean.DataBean.YouxiaoBean item) {
                if (item.getDiejia().equals("1")){
                    helper.setText(R.id.tv_is_manjian,"可同满减/满赠活动一起使用");
                }else {
                    helper.setText(R.id.tv_is_manjian,"不参与满减/满赠活动");
                }
            helper.setText(R.id.tv_price, item.getMoney());
            helper.setText(R.id.tv_title, item.getTitle());
            helper.setText(R.id.tv_shengyu, "剩" + item.getShengyu() + "天");
            helper.setText(R.id.tv_manjian, item.getRule() + "可用");
            helper.setText(R.id.tv_time, "有效期至：" + item.getEndtime());

        }
    }
}
