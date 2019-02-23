package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.AddressLieBiaoBean;
import com.example.administrator.chushi.utils.MyContants;
import com.example.administrator.chushi.utils.MyUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class CheckAddressActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView address_recycler;
    private MyAddressAdapter myAddressAdapter;
    private ImageView tv_back;
    private TextView add_address;
    private List<AddressLieBiaoBean.DataBean> data;
    private String userid;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_address);
        initView();
        initData();
    }

    private void initView() {
        add_address= (TextView) findViewById(R.id.add_address);
        add_address.setOnClickListener(this);
        tv_back= (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        address_recycler= (RecyclerView) findViewById(R.id.address_recycler);
        initNet();
    }
    private void initNet() {
        userid = MyUtils.getUserid(this);
        token = MyUtils.getToken(this);
        OkGo.get(MyContants.BASEURL + "Address/lists/")
                .tag(this)
                .params("userid", userid)
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        AddressLieBiaoBean addressLieBiaoBean = gson.fromJson(s, AddressLieBiaoBean.class);
                        if (addressLieBiaoBean.getCode() == 200) {
                            data = addressLieBiaoBean.getData();
                        } else {
                            Toast.makeText(CheckAddressActivity.this, "还没有添加地址呢，快去添加吧", Toast.LENGTH_SHORT).show();
                        }
                        initData();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(CheckAddressActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }
    private void initData() {
            myAddressAdapter = new MyAddressAdapter(R.layout.check_address_item, data);
        address_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        address_recycler.setNestedScrollingEnabled(false);
        address_recycler.setAdapter(myAddressAdapter);
        myAddressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = getIntent();
                if (data.size()<=0){
                    intent.putExtra("username","");
                    intent.putExtra("mobile", "");
                    intent.putExtra("address","");
                    intent.putExtra("area", "");
                    intent.putExtra("c_id","");
                    intent.putExtra("v_id","");
                    intent.putExtra("x_id","");
                }else {
                    intent.putExtra("username", data.get(position).getUsername());
                    intent.putExtra("mobile", data.get(position).getMobile());
                    intent.putExtra("address", data.get(position).getAddress());
                    intent.putExtra("area", data.get(position).getArea());
                    intent.putExtra("c_id", data.get(position).getC_id());
                    intent.putExtra("v_id", data.get(position).getV_id());
                    intent.putExtra("x_id", data.get(position).getX_id());
                }
                setResult(0, intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                Intent intent = getIntent();
                intent.putExtra("username", "");
                setResult(0, intent);
                finish();
                break;
            case R.id.add_address:
                Intent intent1=new Intent(CheckAddressActivity.this,BianJiAddressActivity.class);
                intent1.putExtra("username", "");
                startActivityForResult(intent1,0);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("username", "");
        setResult(0, intent);
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            initNet();
        }
    }
    class MyAddressAdapter extends BaseQuickAdapter<AddressLieBiaoBean.DataBean, BaseViewHolder>{

        public MyAddressAdapter(@LayoutRes int layoutResId, @Nullable List<AddressLieBiaoBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, AddressLieBiaoBean.DataBean item) {
            helper.setText(R.id.tv_name,item.getUsername());
            helper.setText(R.id.tv_phone,item.getMobile());
            helper.setText(R.id.tv_address, item.getArea() + "" + item.getAddress());
            TextView tv_moren = helper.getView(R.id.tv_moren);
            String status = item.getStatus();
                tv_moren.setText(status);
            if (status.equals("1")){
                tv_moren.setText("默认地址");
            }else if (status.equals("0")){
                tv_moren.setText("");
            }
        }

    }
}
