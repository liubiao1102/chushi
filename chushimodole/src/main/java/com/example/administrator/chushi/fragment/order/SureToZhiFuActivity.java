package com.example.administrator.chushi.fragment.order;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.GoPayBean;
import com.example.administrator.chushi.bean.OrderBean;
import com.example.administrator.chushi.fragment.mine.CheckAddressActivity;
import com.example.administrator.chushi.fragment.mine.MineyhjActivity;
import com.example.administrator.chushi.fragment.mine.WebGuiZeActivity;
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

public class SureToZhiFuActivity extends BaseActivity implements View.OnClickListener {
    private ImageView tv_back;
    private LinearLayout checkaddress_layout, checkyhj_layout, ll_peisongtime,layout_zengsong;
    private TextView to_jiesuan, tv_name, tv_phone, tv_address,
            tv_youhuanquan, tv_total_price, tv_youzhekou, tv_zhekou,
            tv_yunfei, tv_zuizhong_price, tv_jifen, tv_peisong_time;
    private EditText edt_beizhu;
    private Switch id_switch;
    private OrderBean.DataEntity mData;
    private String zuizhongPrice, mDay, mTime;
    private String c_id;
    private String v_id;
    private String x_id;
    private String cTag, vTag, xTag;
    private String title="";
    private String money="";
    private String ticket_user_id="";
    private String Tagticket_user_id;
    private String ponit;
    private String username="";
    private String mobile;
    private String area;
    private String address;
    private String ponit_money;
    private  String Tagponit,Tagponitmoney;
    private OrderBean.DataEntity.GiftBean gift;
    private TextView tv_zengsong,tv_youhuiguize;
    private String gift_tag="";
    private String yun_price;
    private ProgressDialog dialog;
    private ArrayList<String> days;
    private ArrayList<ArrayList<String>> times;
    private String peisongtime_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_to_zhi_fu);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        initData();
    }

    private void initView() {
        tv_zengsong= (TextView) findViewById(R.id.tv_zengsong);
        tv_youhuiguize= (TextView) findViewById(R.id.tv_youhuiguize);
        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        checkaddress_layout = (LinearLayout) findViewById(R.id.checkaddress_layout);
        layout_zengsong = (LinearLayout) findViewById(R.id.layout_zengsong);
        checkaddress_layout.setOnClickListener(this);
        checkyhj_layout = (LinearLayout) findViewById(R.id.checkyhj_layout);
        checkyhj_layout.setOnClickListener(this);
        to_jiesuan = (TextView) findViewById(R.id.to_jiesuan);
        to_jiesuan.setOnClickListener(this);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_youhuanquan = (TextView) findViewById(R.id.tv_youhuanquan);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        tv_youzhekou = (TextView) findViewById(R.id.tv_youzhekou);
        tv_zhekou = (TextView) findViewById(R.id.tv_zhekou);
        edt_beizhu = (EditText) findViewById(R.id.edt_beizhu);
        tv_jifen = (TextView) findViewById(R.id.tv_jifen);
        id_switch = (Switch) findViewById(R.id.id_switch);
        tv_yunfei = (TextView) findViewById(R.id.tv_yunfei);
        tv_zuizhong_price = (TextView) findViewById(R.id.tv_zuizhong_price);
        tv_peisong_time = (TextView) findViewById(R.id.tv_peisong_time);
        ll_peisongtime = (LinearLayout) findViewById(R.id.ll_peisongtime);
        ll_peisongtime.setOnClickListener(this);
        tv_youhuiguize.setOnClickListener(this);
        dialog = new ProgressDialog(SureToZhiFuActivity.this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("正在加载...");
        dialog.show();
    }

    private void initData() {
        String product_id = getIntent().getStringExtra("product_id");
        final String num = getIntent().getStringExtra("num");
        if (TextUtils.isEmpty(product_id) || TextUtils.isEmpty(num)) {
            return;
        }
        //        Toast.makeText(this, "商品"+product_id, Toast.LENGTH_SHORT).show();
        //        Toast.makeText(this, "数量"+num, Toast.LENGTH_SHORT).show()
        OkGo.post(MyContants.BASEURL + "Order/add")
                .tag(this)
                .params("userid", MyUtils.getUserid(SureToZhiFuActivity.this))
                .params("token", MyUtils.getToken(SureToZhiFuActivity.this))
                .params("product_id", product_id)
                .params("num", num)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        OrderBean orderBean = gson.fromJson(s, OrderBean.class);
                        mData = orderBean.getData();
                        //赠送的礼物
                        gift = mData.getGift();
                        if (gift.getOrder_gift().equals("")&&gift.getPro_gift().equals("")){
                            gift_tag="";
                            layout_zengsong.setVisibility(View.GONE);
                        }else if (gift.getOrder_gift().equals("")){
                            gift_tag=gift.getPro_gift();
                            tv_zengsong.setText("赠送:"+gift.getPro_gift());
                        }else if (gift.getPro_gift().equals("")){
                            gift_tag=gift.getOrder_gift();
                            tv_zengsong.setText("赠送:"+gift.getOrder_gift());
                        }else {
                            gift_tag=gift.getOrder_gift()+","+gift.getPro_gift();
                            tv_zengsong.setText("赠送:"+gift.getOrder_gift()+","+gift.getPro_gift());
                        }
                        String c_id = mData.getAddress().getC_id();
                        String v_id = mData.getAddress().getV_id();
                        String x_id = mData.getAddress().getX_id();
                        cTag = c_id;
                        vTag = v_id;
                        xTag = x_id;
                        if (username.equals("")){
                            if (mData.getAddress().getAddress()==null){
                                tv_name.setText("");
                                tv_phone.setText("");
                                tv_address.setText("添加收货地址");
                            }else {
                                tv_name.setText(mData.getAddress().getUsername());
                                tv_phone.setText(mData.getAddress().getMobile());
                                tv_address.setText(mData.getAddress().getArea() + " " + mData.getAddress().getAddress());
                            }

                        }else {
                            tv_name.setText(username);
                            tv_phone.setText(mobile);
                            tv_address.setText(area + address);
                        }
                        if (title.equals("")) {
                        if (!mData.getTicked().getMoney().equals("")) {
                                tv_youhuanquan.setText(mData.getTicked().getTitle() + " 减" + mData.getTicked().getMoney() + "元");
                        } else {
                            tv_youhuanquan.setText("暂无可使用优惠券");
                        }} else {
                        tv_youhuanquan.setText(title + " 减" + money + "元");
                        }
                        tv_total_price.setText("￥" + mData.getPrice().getTotal_price() + "元");
                        if (title.equals("")){
                            tv_youzhekou.setText("￥" + mData.getPrice().getYouhui_price() + "元");
                        }else {
                            tv_youzhekou.setText("￥" + money + "元");
                        }

                        tv_zhekou.setText("￥" + mData.getPrice().getKou_price() + "元");
                        if (title.equals("")){
                            tv_jifen.setText("可用" + mData.getPrice().getPoint() + "积分" + "抵用" + mData.getPrice().getPoint_price() + "元");
                        }else {
                            tv_jifen.setText("可用" + ponit + "积分" + "抵用" +ponit_money + "元");
                        }
                        if (title.equals("")){
                            tv_yunfei.setText("￥" + mData.getPrice().getYun_price() + "元");
                        }else {
                            tv_yunfei.setText("￥" + yun_price + "元");
                        }
                        if (mData.getPeisong_time() != null && mData.getPeisong_time().size() > 0) {
                            mDay = mData.getPeisong_time().get(0).getDay();
                            mTime = mData.getPeisong_time().get(0).getTime().get(0).getStart_time() + "~"
                                    + mData.getPeisong_time().get(0).getTime().get(0).getEnd_time();
                            tv_peisong_time.setText(mData.getPeisong_time().get(0).getDay()
                                    + " " + mData.getPeisong_time().get(0).getTime().get(0).getStart_time() + "~"
                                    + mData.getPeisong_time().get(0).getTime().get(0).getEnd_time());
                        }
                        if (title.equals("")){
                            Tagponit=mData.getPrice().getPoint();
                            Tagponitmoney=mData.getPrice().getPoint_price();
                        }else {
                            Tagponit=ponit;
                            Tagponitmoney=ponit_money;
                        }
                        id_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                zuizhongPrice = "";
                                if (isChecked) {
                                    zuizhongPrice = mData.getPrice().getOrder_price();
                                    if (title.equals("")){
                                        double aDouble = Double.valueOf(mData.getPrice().getOrder_price());
                                        String format = String.format("%.1f", aDouble);
                                        tv_zuizhong_price.setText("￥" + format+ "元");
                                    }else {
                                        double v = Double.valueOf(mData.getPrice().getTotal_price()) + Double.valueOf(mData.getPrice().getYun_price()) - Integer.parseInt(money) - Double.valueOf(ponit_money)-Double.valueOf(mData.getPrice().getKou_price());
                                        String format = String.format("%.1f", v);
                                        zuizhongPrice=format;
                                        tv_zuizhong_price.setText("￥" + format + "元");
                                    }
                                } else {
                                    double v1 = Double.valueOf(mData.getPrice().getOrder_price())
                                            + Double.valueOf(mData.getPrice().getPoint_price());
                                    if (title.equals("")){
                                        String format = String.format("%.1f", v1);
                                        zuizhongPrice=format;
                                        tv_zuizhong_price.setText("￥" + format+ "元");
                                    }else {
                                        //总额+运费-优惠劵-折扣
                                        double v = Double.valueOf(mData.getPrice().getTotal_price()) + (Double.valueOf(mData.getPrice().getYun_price()) - Integer.parseInt(money)-Double.valueOf(mData.getPrice().getKou_price()));
                                        String format = String.format("%.1f", v);
                                        zuizhongPrice=format;
                                        tv_zuizhong_price.setText("￥" +format+ "元");
                                    }
                                }
                            }
                        });
                        if (title.equals("")){
                            id_switch.setChecked(true);
                        }else {
                            id_switch.setChecked(false);
                        }
                        if (!title.equals("")){
                            //总额+运费-优惠劵
                            double v = Double.valueOf(mData.getPrice().getTotal_price()) + (Double.valueOf(mData.getPrice().getYun_price()) - Integer.parseInt(money)-Double.valueOf(mData.getPrice().getKou_price()));
                            String format = String.format("%.1f", v);
                            zuizhongPrice=format;
                            tv_zuizhong_price.setText("￥" +format+ "元");
                        }
                        dialog.dismiss();

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(SureToZhiFuActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_youhuiguize:
                startActivity(new Intent(SureToZhiFuActivity.this, WebGuiZeActivity.class));
                break;
            case R.id.checkaddress_layout:
                Intent intent = new Intent(SureToZhiFuActivity.this, CheckAddressActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.checkyhj_layout:
                if (tv_youhuanquan.getText().equals("暂无可使用优惠券")){

                }else {
                    String total_price = mData.getPrice().getTotal_price();
                    String yun_price = mData.getPrice().getYun_price();
                    Intent intent1 = new Intent(SureToZhiFuActivity.this, MineyhjActivity.class);
                    intent1.putExtra("orderid", mData.getPrice().getOrder_id());
                    intent1.putExtra("total_price",total_price);
                    intent1.putExtra("yun_price",yun_price);
                    intent1.putExtra("check_activity",mData.getCheck_activity()+"");
                    startActivityForResult(intent1, 1);
                }

                break;
            case R.id.to_jiesuan:
                if (username.equals("")){

                }else {
                    cTag = c_id;
                    vTag = v_id;
                    xTag = x_id;
                }
                if (ticket_user_id.equals("")) {
                    Tagticket_user_id = mData.getTicked().getTicket_user_id();
                } else {
                    Tagticket_user_id = ticket_user_id;
                }
                if (tv_address.getText().equals("添加收货地址")){
                    Toast.makeText(this, "请添加收货地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<OrderBean.DataEntity.PeisongTimeEntity> list
                        = (ArrayList<OrderBean.DataEntity.PeisongTimeEntity>) mData.getPeisong_time();
               List<String> days = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    days.add(list.get(i).getDay());

                }
                List<List<String>> times = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    ArrayList<String> everyTime = new ArrayList<>();
                    for (int i1 = 0; i1 < list.get(i).getTime().size(); i1++) {
                        String start_time = list.get(i).getTime().get(i1).getStart_time();
                        String end_time = list.get(i).getTime().get(i1).getEnd_time();
                        everyTime.add(start_time + "~" + end_time);
                    }
                    times.add(everyTime);
                }
                if (days != null && days.size() > 0 && times != null && times.size() > 0) {
                    if (peisongtime_id.equals("")){
                        peisongtime_id = list.get(0).getTime().get(0).getId();
                    }
                    quzhifu();
                }else {
                    Toast.makeText(this, "暂时没有配送时间", Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.ll_peisongtime:
                go();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 0) {
            username = data.getStringExtra("username");
            mobile = data.getStringExtra("mobile");
            area = data.getStringExtra("area");
            address = data.getStringExtra("address");


            username = data.getStringExtra("username");
            tv_name.setText(data.getStringExtra("username"));
            tv_phone.setText(data.getStringExtra("mobile"));
            tv_address.setText(data.getStringExtra("area") + data.getStringExtra("address"));
            c_id = data.getStringExtra("c_id");
            v_id = data.getStringExtra("v_id");
            x_id = data.getStringExtra("x_id");
        } else if (requestCode == 1 && resultCode == 1) {
            title = data.getStringExtra("title");
            money = data.getStringExtra("money");
            ticket_user_id = data.getStringExtra("Ticket_user_id");
            ponit = data.getStringExtra("ponit");
            ponit_money = data.getStringExtra("ponit_money");
            yun_price = data.getStringExtra("yun_price");

        }
        initData();

    }

    private void quzhifu() {
        OkGo.post(MyContants.BASEURL + "Order/update")
                .tag(this)
                .params("userid", MyUtils.getUserid(SureToZhiFuActivity.this))
                .params("token", MyUtils.getToken(SureToZhiFuActivity.this))
                .params("order_id", mData.getPrice().getOrder_id())
                .params("shouhuo_name", tv_name.getText().toString())
                .params("shouhuo_mobile", tv_phone.getText().toString())
                .params("address", tv_address.getText().toString())
                .params("c_id", cTag)
                .params("x_id", xTag)
                .params("v_id", vTag)
                .params("ticket_id", Tagticket_user_id)
                .params("point", id_switch.isChecked()?Tagponit:"")
                .params("point_price", id_switch.isChecked()?Tagponitmoney:"")
                .params("youhui_price", mData.getPrice().getYouhui_price())
                .params("kou_price", mData.getPrice().getKou_price())
                .params("price", zuizhongPrice)
                .params("order_beizhu", edt_beizhu.getText().toString())
                .params("peisong_day", mDay)
                .params("peisong_time", mTime)
                .params("peisong_time_id", peisongtime_id)
                .params("gift", gift_tag)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        GoPayBean goPayBean = gson.fromJson(s, GoPayBean.class);

                        if (goPayBean.getCode() == 200) {

                            Intent intent = new Intent(SureToZhiFuActivity.this, JieSuanActivity.class);
                            intent.putExtra("data", goPayBean.getData());
                            intent.putExtra("dingdan", "");
                            startActivity(intent);
                            finish();
                            EventMessage eventMessage = new EventMessage();
                            eventMessage.setMsg("deletecar");
                            EventBus.getDefault().postSticky(eventMessage);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(SureToZhiFuActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void go() {
        final ArrayList<OrderBean.DataEntity.PeisongTimeEntity> list
                = (ArrayList<OrderBean.DataEntity.PeisongTimeEntity>) mData.getPeisong_time();
        days = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            days.add(list.get(i).getDay());

        }
        times = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> everyTime = new ArrayList<>();
            for (int i1 = 0; i1 < list.get(i).getTime().size(); i1++) {
                String start_time = list.get(i).getTime().get(i1).getStart_time();
                String end_time = list.get(i).getTime().get(i1).getEnd_time();
                everyTime.add(start_time + "~" + end_time);
            }
            times.add(everyTime);
        }
        if (days != null && days.size() > 0 && times != null && times.size() > 0) {
            //三级联动效果
            OptionsPickerView optionsPickerView = new OptionsPickerView(this);
            optionsPickerView.setPicker(days, times, true);
            //        optionsPickerView.setTitle(context.getString(R.string.action_settings));
            //设置是否有滚动效果
            optionsPickerView.setCyclic(false, false, false);
            optionsPickerView.show();
            optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    peisongtime_id = list.get(options1).getTime().get(option2).getId();
                    mDay = days.get(options1);
                    mTime = times.get(options1).get(option2);
                    tv_peisong_time.setText(mDay + " " + mTime);
                }
            });
        } else {
            Toast.makeText(this, "暂时没有配送时间", Toast.LENGTH_SHORT).show();
        }

    }
}
