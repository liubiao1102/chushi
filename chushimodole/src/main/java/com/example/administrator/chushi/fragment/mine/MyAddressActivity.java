package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static com.example.administrator.chushi.R.id.tv_name;

public class MyAddressActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView address_recycler;
    private MyAddressAdapter myAddressAdapter;
    private List<String> picList = new ArrayList<>();
    private ImageView tv_back;
    private TextView add_address;
    private List<AddressLieBiaoBean.DataBean> data;
    private TextView tv_moren;
    private CheckBox check_ischeck;
    private String id;
    private String userid;
    private String token;
    private String iddelete;
    private String username;
    private String address;
    private String area;
    private String mobile;
    private String c_id;
    private String v_id;
    private String x_id;
    private String address_id;
    private String name = null;
    private String phone;
    private String quyu;
    private String xxdz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        initView();
    }

    private void initView() {
        add_address = (TextView) findViewById(R.id.add_address);
        add_address.setOnClickListener(this);
        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        address_recycler = (RecyclerView) findViewById(R.id.address_recycler);
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
                            Toast.makeText(MyAddressActivity.this, "还没有添加地址呢，快去添加吧", Toast.LENGTH_SHORT).show();
                        }
                        initData();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(MyAddressActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initData() {
        myAddressAdapter = new MyAddressAdapter(R.layout.address_item, data);
        address_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        address_recycler.setNestedScrollingEnabled(false);
        address_recycler.setAdapter(myAddressAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.add_address:
                Intent intent = new Intent(MyAddressActivity.this, BianJiAddressActivity.class);
                intent.putExtra("username", "");
                startActivityForResult(intent,0);
                break;
        }
    }

    class MyAddressAdapter extends BaseQuickAdapter<AddressLieBiaoBean.DataBean, BaseViewHolder> {

        public MyAddressAdapter(@LayoutRes int layoutResId, @Nullable List<AddressLieBiaoBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, AddressLieBiaoBean.DataBean item) {
            helper.setChecked(R.id.check_ischeck, item.isChecked());
            //            helper.setChecked(R.id.tv_moren,item.isChecked());
            String status = item.getStatus();
            helper.getView(R.id.tv_bianji).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < data.size(); i++) {
                        if (helper.getAdapterPosition() == i) {
                            username = myAddressAdapter.getData().get(i).getUsername();
                            address = myAddressAdapter.getData().get(i).getAddress();
                            area = myAddressAdapter.getData().get(i).getArea();
                            mobile = myAddressAdapter.getData().get(i).getMobile();
                            c_id = myAddressAdapter.getData().get(i).getC_id();
                            v_id = myAddressAdapter.getData().get(i).getV_id();
                            x_id = myAddressAdapter.getData().get(i).getX_id();
                            address_id = myAddressAdapter.getData().get(i).getId();
                        }
                    }
                    Intent intent = new Intent(MyAddressActivity.this, BianJiAddressActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("address", address);
                    intent.putExtra("area", area);
                    intent.putExtra("mobile", mobile);
                    intent.putExtra("c_id", c_id);
                    intent.putExtra("v_id", v_id);
                    intent.putExtra("x_id", x_id);
                    intent.putExtra("address_id", address_id);
                    startActivityForResult(intent, 0);

                }
            });
            helper.getView(R.id.tv_Delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < data.size(); i++) {
                        if (helper.getAdapterPosition() == i) {
                            iddelete = myAddressAdapter.getData().get(i).getId();
                            myAddressAdapter.remove(i);
                            initDelete();
                        }
                    }
                    myAddressAdapter.notifyDataSetChanged();
                }


            });
            helper.setText(tv_name, item.getUsername());
            helper.setText(R.id.tv_phone, item.getMobile());
            helper.setText(R.id.tv_address, item.getArea() + "" + item.getAddress());

            tv_moren = helper.getView(R.id.tv_moren);
            check_ischeck = helper.getView(R.id.check_ischeck);
            if (status.equals("1")) {
                check_ischeck.setChecked(true);
                tv_moren.setText("默认地址");
            } else if (status.equals("0")) {
                check_ischeck.setChecked(false);
                tv_moren.setText("设为默认");
            }
            helper.getView(R.id.tv_moren).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < data.size(); i++) {
                        check_ischeck.setChecked(false);
                        if (helper.getAdapterPosition() == i) {
                            id = myAddressAdapter.getData().get(i).getId();
                            myAddressAdapter.getData().get(i).setChecked(true);
                            initNetMoRen();
                        } else {
                            myAddressAdapter.getData().get(i).setChecked(false);
                        }
                    }
                    myAddressAdapter.notifyDataSetChanged();
                }
            });

        }

    }

    private void initDelete() {
        OkGo.get(MyContants.BASEURL + "Address/del/")
                .tag(this)
                .params("address_id", iddelete)
                .params("userid", userid)
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        Toast.makeText(MyAddressActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(MyAddressActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initNetMoRen() {
        OkGo.get(MyContants.BASEURL + "address/edit/")
                .tag(this)
                .params("address_id", id)
                .params("userid", userid)
                .params("token", token)
                .params("status", "1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        Toast.makeText(MyAddressActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                        initNet();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(MyAddressActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
//            name = data.getStringExtra("name");
//            phone = data.getStringExtra("phone");
//            quyu = data.getStringExtra("quyu");
//            xxdz = data.getStringExtra("xxdz");
            initNet();
        }
    }
}
