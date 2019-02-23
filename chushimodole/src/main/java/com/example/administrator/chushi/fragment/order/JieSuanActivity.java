package com.example.administrator.chushi.fragment.order;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.base.PayResult;
import com.example.administrator.chushi.bean.AliPayBean;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.GoPayBean;
import com.example.administrator.chushi.bean.WXPayBean;
import com.example.administrator.chushi.utils.MyContants;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Response;

public class JieSuanActivity extends BaseActivity implements View.OnClickListener {
    private long minute = 15;//这是分钟
    private long second = 0;//这是分钟后面的秒数。这里是以30分钟为例的，所以，minute是30，second是0
    private Timer timer;
    private TimerTask timerTask;
    private TextView tv_time,tv_order_number,tv_price;
    private ImageView tv_back;
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    //这是接收回来处理的消息
    private String WX_APPID = "wxdbbb1928fdfa069d";// 微信appid
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (minute == 0) {
                if (second == 0) {
                    tv_time.setTextSize(20);
                    tv_time.setText("超时未支付，请返回重新提交");
                    EventMessage eventMessage = new EventMessage();
                    eventMessage.setMsg("refresh");
                    EventBus.getDefault().postSticky(eventMessage);
                    finish();
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    if (timerTask != null) {
                        timerTask = null;
                    }
                } else {
                    second--;
                    if (second >= 10) {
                        tv_time.setText("0" + minute + ":" + second);
                    } else {
                        tv_time.setText("0" + minute + ":0" + second);
                    }
                }
            } else {
                if (second == 0) {
                    second = 59;
                    minute--;
                    if (minute >= 10) {
                        tv_time.setText(minute + ":" + second);
                    } else {
                        tv_time.setText("0" + minute + ":" + second);
                    }
                } else {
                    second--;
                    if (second >= 10) {
                        if (minute >= 10) {
                            tv_time.setText(minute + ":" + second);
                        } else {
                            tv_time.setText("0" + minute + ":" + second);
                        }
                    } else {
                        if (minute >= 10) {
                            tv_time.setText(minute + ":0" + second);
                        } else {
                            tv_time.setText("0" + minute + ":0" + second);
                        }
                    }
                }
            }
        }

    };
    private long time;
    private CheckBox check_zfb,check_wx;
    private String Tag="";
    private TextView tv_tozhifu;
    private static final int SDK_PAY_FLAG = 0;
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    Toast.makeText(JieSuanActivity.this, " " + payResult.getResultStatus(), Toast.LENGTH_SHORT).show();
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        EventMessage eventMessage = new EventMessage();
                        eventMessage.setMsg("refresh");
                        EventBus.getDefault().postSticky(eventMessage);
                        Toast.makeText(JieSuanActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        //自己写的跳转到自定义的支付宝支付成功界面
                        finish();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                    /*
                    "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，
                    最终交易是否成功以服务端异步通知为准（小概率状态）
                     */
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(JieSuanActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(JieSuanActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
//                            btn_yes.setClickable(true);
                        }     }
                    break;
                }
                default:

                    break;
            }
        }
    };

                        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_suan);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, WX_APPID, false);
// 将该app注册到微信
        api.registerApp(WX_APPID);
        initView();
        initData();
    }

    private void initView() {
        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_order_number = (TextView) findViewById(R.id.tv_order_number);
        tv_price = (TextView) findViewById(R.id.tv_price);
        check_zfb= (CheckBox) findViewById(R.id.check_zfb);
        check_wx= (CheckBox) findViewById(R.id.check_wx);
        check_zfb.setOnClickListener(this);
        check_wx.setOnClickListener(this);
        tv_tozhifu= (TextView) findViewById(R.id.tv_tozhifu);
        tv_tozhifu.setOnClickListener(this);
    }

    private void initData() {
        String dingdan = getIntent().getStringExtra("dingdan");
        String price = getIntent().getStringExtra("price");
        String number = getIntent().getStringExtra("number");
        String shixiao_time = getIntent().getStringExtra("shixiao_time");
        if (dingdan.equals("dingdan")){
            tv_price.setText("¥"+price);
            tv_order_number.setText(number);
            Tag=number;
            //后台返回的时间戳是秒格式的
            time = Long.parseLong(shixiao_time)-System.currentTimeMillis()/1000;
        }else {
            GoPayBean.DataEntity dataEntity= (GoPayBean.DataEntity) getIntent().getSerializableExtra("data");
            tv_price.setText("¥"+dataEntity.getPrice());
            tv_order_number.setText(dataEntity.getNumber());
            Tag=dataEntity.getNumber();
            //后台返回的时间戳是秒格式的
            time = Long.parseLong(dataEntity.getShixiao_time())-System.currentTimeMillis()/1000;
        }

//        Toast.makeText(this, time+"", Toast.LENGTH_SHORT).show();
        minute= time/60;
        second= time % 60;
//        Toast.makeText(this, MyUtils.getDateToStringTime(System.currentTimeMillis()+""), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "时间"+time+" 分钟"+minute+" 秒"+second, Toast.LENGTH_SHORT).show();

        tv_time.setText(minute + ":" + second);

        timerTask = new TimerTask() {

            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 0, 1000);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_tozhifu:
                //微信
                if (check_wx.isChecked()&&!check_zfb.isChecked()){
                    initNetWXPay();
                }else {//支付宝
                    initNetZFBPay();
                }
                break;
            case R.id.check_zfb:
                check_wx.setChecked(false);
                break;
            case R.id.check_wx:
                check_zfb.setChecked(false);
                break;
        }
    }

    private void initNetZFBPay() {
        OkGo.get(MyContants.BASEURL+"Payment/pay/type/ali/")
                .tag(JieSuanActivity.this)
                .params("order_num",Tag)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        final AliPayBean zfbPayBean = gson.fromJson(s, AliPayBean.class);
                        Runnable payRunnable = new Runnable() {

                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(JieSuanActivity.this);
                                String result = alipay.pay(zfbPayBean.getDatas().getOrderStr(), true);//调用支付接口，获取支付结果
                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };

                    // 必须异步调用，支付或者授权的行为需要在独立的非ui线程中执行
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(JieSuanActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initNetWXPay() {

        OkGo.get(MyContants.BASEURL+"Payment/pay/type/wx/")
                .tag(JieSuanActivity.this)
                .params("order_num",Tag)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        PayReq req = new PayReq();
                        WXPayBean wxPayBean = gson.fromJson(s, WXPayBean.class);
                        WXPayBean.DatasEntity datas = wxPayBean.getDatas();
                        req.appId = datas.getAppid();// 微信开放平台审核通过的应用APPID
                        req.partnerId = datas.getPartnerid();// 微信支付分配的商户号
                        req.prepayId = datas.getPrepayid();// 预支付订单号，app服务器调用“统一下单”接口获取
                        req.nonceStr = datas.getNoncestr();// 随机字符串，不长于32位，服务器小哥会给咱生成
                        req.timeStamp = datas.getTimestamp();// 时间戳，app服务器小哥给出
                        req.packageValue = datas.getPackage1();// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
                        req.sign = datas.getSign();// 签名，服务器小哥给出
                    //                        req.extData = "app data"; // optional
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                        api.sendReq(req);//调起支付
                        finish();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(JieSuanActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask = null;
        }
        minute = -1;
        second = -1;
        super.onDestroy();
    }
}
