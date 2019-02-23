package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
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
import com.example.administrator.chushi.base.BaseFragment;
import com.example.administrator.chushi.bean.DingDanBean;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.ReturnSeccsess;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class WaitShouHuoFragment extends BaseFragment {
    private RecyclerView mine_title_recycler;
    private AllDingDanAdapter allDingDanAdapter;
    private String userid;
    private String token;
    private List<DingDanBean.DataBean> data;
    @Override
    protected View initView() {
        View view=View.inflate(mContext, R.layout.activity_wait_shou_huo_fragment,null);
        mine_title_recycler= (RecyclerView) view.findViewById(R.id.mine_title_recycler);
        return view;
    }

    @Override
    protected void initData() {
        initNet();
    }
    private void initNet() {
        userid = MyUtils.getUserid(mContext);
        token = MyUtils.getToken(mContext);
        OkGo.get(MyContants.BASEURL+"Order/lists/")
                .tag(this)
                .params("userid",userid)
                .params("token",token)
                .params("status","2")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        DingDanBean mHomeFragmentBean = gson.fromJson(s, DingDanBean.class);
                        if (mHomeFragmentBean.getCode()==200){
                            data = mHomeFragmentBean.getData();
                                allDingDanAdapter = new AllDingDanAdapter(R.layout.mine_waitshouhuo_item, data);
                            mine_title_recycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                            mine_title_recycler.setNestedScrollingEnabled(false);
                            mine_title_recycler.setAdapter(allDingDanAdapter);
                            allDingDanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    String id = data.get(position).getId();
                                    Intent intent=new Intent(mContext,DingDanXQActivity.class);
                                    intent.putExtra("","daishouhuo");
                                    intent.putExtra("id",id);
                                    startActivity(intent);
                                }
                            });
                        }else {
                            mine_title_recycler.setVisibility(View.GONE);
//                            Toast.makeText(mContext, "暂时没有订单", Toast.LENGTH_SHORT).show();
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
            helper.setText(R.id.tv_peisong,item.getPeisong_name());
            helper.setText(R.id.tv_title,"商品名称："+item.getTitle());
            helper.setText(R.id.tv_num,"商品数量："+item.getProduct_num()+" 份/"+item.getUnit());
            helper.setText(R.id.tv_price,"¥"+item.getProduct_price());
            helper.setText(R.id.tv_allnumber,"共"+item.getOrder_num()+"件商品");
            helper.setText(R.id.tv_allprice,"¥"+item.getOrder_price());
            helper.setText(R.id.yunfei_price,"(含配送费¥"+item.getYun_price()+")");
            Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_tupian));
            TextView tv_sure_queren = helper.getView(R.id.tv_sure_queren);
            tv_sure_queren.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SureShouHuo(item.getId());
                    Toast.makeText(mContext, "确认收货", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void SureShouHuo(String order_id) {
        OkGo.get(MyContants.BASEURL+"Order/commitOrder/")
                .tag(this)
                .params("order_id",order_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ReturnSeccsess returnSeccsess = gson.fromJson(s, ReturnSeccsess.class);
                        if (returnSeccsess.getCode()==200){
                            Toast.makeText(mContext, "确认收货成功", Toast.LENGTH_SHORT).show();
                            EventMessage eventMessage = new EventMessage();
                            eventMessage.setMsg("refresh");
                            EventBus.getDefault().postSticky(eventMessage);
                            initNet();
                        }else {
                            Toast.makeText(mContext, returnSeccsess.getMsg()+"", Toast.LENGTH_SHORT).show();
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
