package com.example.administrator.chushi.fragment.mine;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.ReturnSeccsess;
import com.example.administrator.chushi.bean.ShouHouXQBean;
import com.example.administrator.chushi.utils.BaseDialog;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ShouHouXQActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView mine_title_recycler;
    private List<String> picList = new ArrayList<>();
    private AllDingDanAdapter allDingDanAdapter;
    private String id;
    private TextView tv_status,tv_title,tv_leixing,tv_price,tv_yunfei,tv_number,tv_time,tv_jieguo,tv_huifu;
    private TextView edt_yuanying;
    private ShouHouXQBean.DatasBean.DataBean data;
    private List<ShouHouXQBean.DatasBean.InfoBean> info;
    private LinearLayout back_layout;
    private Button remove_tuikuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_hou_xq);

        initView();
        initData();
    }

    private void initView() {
        id = getIntent().getStringExtra("id");
        mine_title_recycler = (RecyclerView) findViewById(R.id.mine_title_recycler);
        tv_status= (TextView) findViewById(R.id.tv_status);
        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_leixing= (TextView) findViewById(R.id.tv_leixing);
        tv_price= (TextView) findViewById(R.id.tv_price);
        tv_yunfei= (TextView) findViewById(R.id.tv_yunfei);
        tv_number= (TextView) findViewById(R.id.tv_number);
        tv_time= (TextView) findViewById(R.id.tv_time);
        tv_jieguo= (TextView) findViewById(R.id.tv_jieguo);
        tv_huifu= (TextView) findViewById(R.id.tv_huifu);
        edt_yuanying= (TextView) findViewById(R.id.edt_yuanying);
        back_layout= (LinearLayout) findViewById(R.id.back_layout);
        remove_tuikuan= (Button) findViewById(R.id.remove_tuikuan);
        remove_tuikuan.setOnClickListener(this);
        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initNet();
    }

    private void initNet() {
      String  userid = MyUtils.getUserid(this);
     String   token = MyUtils.getToken(this);
        OkGo.get(MyContants.BASEURL + "Order/tuiDetail/")
                .tag(this)
                .params("order_id", id)
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ShouHouXQBean mShouHouXQBean = gson.fromJson(s, ShouHouXQBean.class);
                        if (mShouHouXQBean.getCode()==200){
                            data = mShouHouXQBean.getDatas().getData();
                            info = mShouHouXQBean.getDatas().getInfo();
                        }
                        initData();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(ShouHouXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initData() {
        if (data!=null){
           if (data.getTui_status().equals("3")){
                tv_status.setText("退款成功");
                tv_title.setText("您的退款申请已成功");
               tv_price.setText(data.getTui_price());
               tv_yunfei.setText("(扣"+data.getTui_yun_price()+"元运费)");
               tv_jieguo.setText("成功");
               tv_huifu.setText(data.getAgree_reason());
               remove_tuikuan.setVisibility(View.GONE);
           }else if (data.getTui_status().equals("4")){
                tv_status.setText("退款失败");
                tv_title.setText("您的申请已被商家拒绝！");
               tv_price.setText(data.getPrice());
               tv_yunfei.setVisibility(View.INVISIBLE);
               tv_jieguo.setText("失败");
               tv_huifu.setText(data.getRefuse_reason());
               remove_tuikuan.setVisibility(View.GONE);
           }else if (data.getTui_status().equals("5")){
               tv_status.setText("已取消退款");
               tv_title.setText("您已经取消了退款申请");
               tv_price.setText(data.getPrice());
               tv_yunfei.setVisibility(View.INVISIBLE);
               tv_jieguo.setText("");
               tv_huifu.setText("");
               remove_tuikuan.setVisibility(View.GONE);
           }else {
               remove_tuikuan.setVisibility(View.VISIBLE);
               tv_yunfei.setVisibility(View.INVISIBLE);
               tv_price.setText(data.getPrice());
               tv_status.setText("审核中");
               tv_title.setText("您的退款申请正在审核中，请耐心等待！");
               tv_jieguo.setText("");
               tv_huifu.setText("");
           }
            if (data.getTui_type().equals("1")){
                tv_leixing.setText("退款");
            }else {
                tv_leixing.setText("退货退款");
            }
            tv_number.setText(data.getNumber());
            tv_time.setText(data.getTui_time());
            edt_yuanying.setText(data.getTuikuanyuanyin());
        }


        allDingDanAdapter = new AllDingDanAdapter(R.layout.mine_shouhou_xq, info);
        mine_title_recycler.setLayoutManager(new LinearLayoutManager(ShouHouXQActivity.this, LinearLayoutManager.VERTICAL, false));
        mine_title_recycler.setNestedScrollingEnabled(false);
        mine_title_recycler.setAdapter(allDingDanAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.remove_tuikuan:
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
        TextView tv_canel = dialog.getView(R.id.tv_canel);
        TextView tv_content = dialog.getView(R.id.tv_content);
        tv_canel.setText("取消");
        tv_content.setText("退款退货操作只能进行一次，确认取消？");
        tv_canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭dialog
                dialog.close();
            }
        });
        TextView tv_yes = dialog.getView(R.id.tv_yes);
        tv_yes.setText("确认");
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initNetRemove();
                dialog.close();
            }
        });
    }
    private void initNetRemove() {
        String  userid = MyUtils.getUserid(this);
        String   token = MyUtils.getToken(this);
        OkGo.get(MyContants.BASEURL + "Order/tuiCancel/")
                .tag(this)
                .params("order_id", id)
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ReturnSeccsess mShouHouXQBean = gson.fromJson(s,ReturnSeccsess.class);
                        if (mShouHouXQBean.getCode()==200){
                            EventMessage eventMessage = new EventMessage();
                            eventMessage.setMsg("remove");
                            EventBus.getDefault().postSticky(eventMessage);
                            Toast.makeText(ShouHouXQActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(ShouHouXQActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    class AllDingDanAdapter extends BaseQuickAdapter<ShouHouXQBean.DatasBean.InfoBean, BaseViewHolder> {

        public AllDingDanAdapter(@LayoutRes int layoutResId, @Nullable List<ShouHouXQBean.DatasBean.InfoBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ShouHouXQBean.DatasBean.InfoBean item) {
            Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_title,"商品名称："+item.getTitle());
            helper.setText(R.id.tv_num,"商品数量："+item.getNum()+" 份");
            helper.setText(R.id.tv_price,"¥"+item.getPrice());
        }
    }
}
