package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.bean.ReturnSeccsess;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.example.administrator.chushi.view.RatingBarView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

public class ShopPingJiaActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout back_layout;
    private RatingBarView ratingBarView;
    private ImageView iv_tupian;
    private TextView tv_shop_name,tv_shop_num,tv_shop_price,tv_fabiao;
    private EditText edt_content;
    private String shop_pic;
    private String shop_name;
    private String shop_num;
    private String shop_price;
    private String product_id;
    private String order_id;
    private String userid;
    private String token;
    int mposition=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_ping_jia);
        initView();
        initData();
    }

    private void initData() {
        Glide.with(ShopPingJiaActivity.this).load(shop_pic).into(iv_tupian);
        tv_shop_name.setText( "商品名称："+shop_name);
        tv_shop_num.setText("商品数量：" + shop_num);
        tv_shop_price.setText( "¥：" +shop_price);
    }

    private void initView() {
        shop_pic = getIntent().getStringExtra("shop_pic");
        shop_name = getIntent().getStringExtra("shop_name");
        shop_num = getIntent().getStringExtra("shop_num");
        shop_price = getIntent().getStringExtra("shop_price");
        //商品id
        product_id = getIntent().getStringExtra("product_id");
        //订单id
        order_id = getIntent().getStringExtra("order_id");

        iv_tupian= (ImageView) findViewById(R.id.iv_tupian);
        tv_shop_name= (TextView) findViewById(R.id.tv_shop_name);
        tv_shop_num= (TextView) findViewById(R.id.tv_shop_num);
        tv_shop_price= (TextView) findViewById(R.id.tv_shop_price);
        tv_fabiao= (TextView) findViewById(R.id.tv_fabiao);
        tv_fabiao.setOnClickListener(this);
        edt_content= (EditText) findViewById(R.id.edt_content);
        back_layout= (LinearLayout) findViewById(R.id.back_layout);
        back_layout.setOnClickListener(this);
        ratingBarView = (RatingBarView) findViewById(R.id.ratingBarView);
        ratingBarView.setRatingCount(5);//设置RatingBarView总数
        ratingBarView.setSelectedCount(1);//设置RatingBarView选中数
        ratingBarView.setSelectedIconResId(R.drawable.xingxing1);//设置RatingBarView选中的图片id
        ratingBarView.setNormalIconResId(R.drawable.xingxing2);//设置RatingBarView正常图片id
        ratingBarView.setClickable(true);//设置RatingBarView是否可点击
        ratingBarView.setChildPadding(4);//设置RatingBarView的子view的padding
        ratingBarView.setChildMargin(3);//设置RatingBarView的子view左右之间的margin
        ratingBarView.setChildDimension(20);//设置RatingBarView的子view的宽高尺寸
        ratingBarView.setOnRatingBarClickListener(new RatingBarView.RatingBarViewClickListener() {
            @Override
            public void onRatingBarClick(LinearLayout parent, View childView, int position) {
                mposition=position;
                Log.e("tag", String.valueOf(childView instanceof ImageView) + "," + position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_layout:
                finish();
                break;
            case R.id.tv_fabiao:
                if (edt_content.getText().toString().equals("")){
                    Toast.makeText(this, "请输入要评论的内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                initNet();
                break;
        }
    }

    private void initNet() {
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
        OkGo.get(MyContants.BASEURL+"Order/info_pingjia/")
                .tag(this)
                .params("userid",userid)
                .params("token",token)
                .params("order_id",order_id)
                .params("star",mposition)
                .params("content",edt_content.getText().toString())
                .params("product_id",product_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ReturnSeccsess returnSeccsess = gson.fromJson(s, ReturnSeccsess.class);
                        if (returnSeccsess.getCode()==200){
                            Toast.makeText(ShopPingJiaActivity.this, "发表成功", Toast.LENGTH_SHORT).show();
                            EventMessage eventMessage = new EventMessage();
                            eventMessage.setMsg("refresh");
                            EventBus.getDefault().postSticky(eventMessage);
                            Intent intent=getIntent();
                            intent.putExtra("","yiwancheng");
                            setResult(0,intent);
                            finish();

                        }else {
                            Toast.makeText(ShopPingJiaActivity.this, returnSeccsess.getMsg()+"", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(ShopPingJiaActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });


    }
}
