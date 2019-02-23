package com.example.administrator.chushi.fragment.order;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.activity.LoginActivity;
import com.example.administrator.chushi.base.BaseFragment;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.db.LitePalHelper;
import com.example.administrator.chushi.db.ShopCarBean;
import com.example.administrator.chushi.utils.BaseDialog;
import com.example.administrator.chushi.utils.MyUtils;
import com.example.administrator.chushi.view.AmountView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */

public class ShoppingCar2Fragment extends BaseFragment implements View.OnClickListener {
    private TextView tv_jiesuan, tv_Delete;
    private TextView allxuan_tv, tv_total_price;
    private CheckBox cb_allChecked;
    private Button btn_login;
    private RecyclerView recycler_shopcar;
    private boolean isEditing;
    private ShopCarAdapter mShopCarAdapter;
    private List<ShopCarBean> mList = new ArrayList<>();
    private boolean isAllChecked;
    private LinearLayout all_list_layout, ll_login, ll_bottom;
    private String userid;
    private String token;
    private TextView tv_nomore;
    private Boolean isevent = false;
    private String checkshoppingcar="";
    private ImageView tv_back;
    private AmountView amountView;
    private ProgressDialog dialog;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_order, null);
        if (!isevent) {
            EventBus.getDefault().register(this);
            isevent = true;
        }
        if (getActivity().getIntent()!=null){
            checkshoppingcar = getActivity(). getIntent().getStringExtra("checkshoppingcar");
        }
        tv_back= (ImageView) view.findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkshoppingcar!=null&&!checkshoppingcar.equals("")){
                    getActivity().finish();
                }else {

                }
            }
        });

        tv_jiesuan = (TextView) view.findViewById(R.id.tv_jiesuan);
        tv_jiesuan.setOnClickListener(this);
        tv_Delete = (TextView) view.findViewById(R.id.tv_Delete);
        tv_total_price = (TextView) view.findViewById(R.id.tv_total_price);
        tv_Delete.setOnClickListener(this);
        allxuan_tv = (TextView) view.findViewById(R.id.allxuan_tv);
        cb_allChecked = (CheckBox) view.findViewById(R.id.cb_allChecked);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        recycler_shopcar = (RecyclerView) view.findViewById(R.id.recycler_shopcar);
        all_list_layout = (LinearLayout) view.findViewById(R.id.all_list_layout);
        ll_login = (LinearLayout) view.findViewById(R.id.ll_login);
        ll_bottom = (LinearLayout) view.findViewById(R.id.ll_bottom);
        all_list_layout.setOnClickListener(this);
        tv_nomore= (TextView) view.findViewById(R.id.tv_nomore);
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage messageEvent) {
       if (messageEvent.getMsg().equals("clearcar")){
           LitePalHelper.deleteAll();
           initData();
        }else if (messageEvent.getMsg().equals("deletecar")){
           deleteAllCheckedItemIds();
           initData();
       }else if (messageEvent.getMsg().equals("back")){
           initData();
       }
    }
    @Override
    protected void initData() {
        userid = MyUtils.getUserid(mContext);
        token = MyUtils.getToken(mContext);
        recycler_shopcar.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_shopcar.setNestedScrollingEnabled(true);
        mList = LitePalHelper.search();
        if (mList == null || mList.size() == 0) {
            ll_bottom.setVisibility(View.GONE);
            recycler_shopcar.setVisibility(View.GONE);
            tv_Delete.setVisibility(View.GONE);
            if (userid.equals("")&&token.equals("")){
                tv_nomore.setText("还没有登录，请先登录");
                ll_login.setVisibility(View.VISIBLE);
                btn_login.setVisibility(View.VISIBLE);
                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mContext,LoginActivity.class));
                    }
                });
            }else {
                ll_login.setVisibility(View.VISIBLE);
                tv_nomore.setText("购物车空空如也");
                btn_login.setVisibility(View.GONE);

            }

            return;
        } else {
            ll_login.setVisibility(View.GONE);
            ll_bottom.setVisibility(View.VISIBLE);
            recycler_shopcar.setVisibility(View.VISIBLE);
            tv_Delete.setVisibility(View.VISIBLE);
            isEditing=false;
            tv_Delete.setText("编辑");
            tv_jiesuan.setText("结算");
        }
        mShopCarAdapter = new ShopCarAdapter(R.layout.shopping_car_recycler_item, mList);
        recycler_shopcar.setAdapter(mShopCarAdapter);
        if (isAllChecked()) {
            cb_allChecked.setChecked(true);
        } else {
            cb_allChecked.setChecked(false);
        }
        refreshJiesuan();
        refreshDelete();
        mShopCarAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //                mShopCarAdapter.getData().get(position).setChecked(
                //                        !mShopCarAdapter.getData().get(position).isChecked());
                mList.get(position).setChecked(!mList.get(position).isChecked());
                if (isAllChecked()) {
                    cb_allChecked.setChecked(true);
                } else {
                    cb_allChecked.setChecked(false);
                }
                mShopCarAdapter.setNewData(mList);
                mShopCarAdapter.notifyDataSetChanged();
                LitePalHelper.updateCheck(mList.get(position).getGoodId(), mList.get(position).isChecked());
                refreshJiesuan();
                refreshDelete();
            }
        });
    }

    private void refreshTotalPrice() {
        if (!isnoOneChecked()) {
            //            mList = LitePalHelper.search();
            tv_total_price.setText("￥" + getAllCheckedItemsTotalPrice() + "元");
        } else {
            tv_total_price.setText("￥0.00元");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initData();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.tv_jiesuan:
                if (isnoOneChecked()) {
                    Toast.makeText(mContext, "请至少选中一款产品", Toast.LENGTH_SHORT).show();
                } else {
                    if (tv_jiesuan.getText().toString().contains("结算")) {
                        goPay();
                    } else {
                        //删除
                        deleteAllCheckedItemIds();
                        initData();
                    }
                }
                refreshTotalPrice();
                break;
            case R.id.all_list_layout:
                isAllChecked();
                if (isAllChecked) {
                    cb_allChecked.setChecked(false);
                    SetAllDontChecked();
                } else if (!isAllChecked) {
                    cb_allChecked.setChecked(true);
                    SetAllChecked();
                }
                isAllChecked = !isAllChecked;
                mShopCarAdapter.setNewData(mList);
                mShopCarAdapter.notifyDataSetChanged();
                refreshTotalPrice();
                refreshJiesuan();
                refreshDelete();
                break;
            case R.id.tv_Delete:
                if (isEditing) {
                    tv_Delete.setText("编辑");
                    tv_jiesuan.setText("结算");
                } else {
                    tv_Delete.setText("完成");
                    tv_jiesuan.setText("删除");
                }
                isEditing = !isEditing;
                refreshJiesuan();
                refreshDelete();
                break;
        }
    }

    private void goPay() {
        dialog = new ProgressDialog(mContext);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("正在加载...");
        dialog.show();
//        OkGo.get(MyContants.BASEURL + "Order/add")
//                .tag(this)
//                .params("userid", MyUtils.getUserid(mContext))
//                .params("token", MyUtils.getToken(mContext))
//                .params("product_id", getAllProduct_id())
//                .params("num", getAllNum())
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Gson gson = new Gson();
//                        OrderBean orderBean = gson.fromJson(s, OrderBean.class);
//                        if (orderBean.getCode()==200){
                            Intent intent = new Intent(mContext, SureToZhiFuActivity.class);
                            intent.putExtra("product_id", getAllProduct_id());
                            intent.putExtra("num", getAllNum());
                            startActivity(intent);
                            dialog.dismiss();
//                            deleteAllCheckedItemIds();
                            initData();
//                        }else {
//                            Toast.makeText(mContext, ""+orderBean.getMsg(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        Toast.makeText(mContext, "请检查网络或重试", Toast.LENGTH_SHORT).show();
//                        Log.e("请求失败", "失败原因：" + response);
//                    }
//                });

    }

    private void refreshJiesuan(){
        if (!isnoOneChecked()){
            if (!isEditing)
            tv_jiesuan.setText("结算("+getAllCheckedItem().size()+")");
        }else {
            if (!isEditing)
            tv_jiesuan.setText("结算");
        }
    }
    private void refreshDelete(){
        if (!isnoOneChecked()){
            if (isEditing)
            tv_jiesuan.setText("刪除("+getAllCheckedItem().size()+")");
        }else {
            if (isEditing)
            tv_jiesuan.setText("刪除");
        }
    }

    class ShopCarAdapter extends BaseQuickAdapter<ShopCarBean, BaseViewHolder> {

        public ShopCarAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCarBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final ShopCarBean item) {
            helper.setText(R.id.tv_goods_name, item.getName())
                    .setText(R.id.tv_goods_price, "￥" + item.getPrice())
                    .setChecked(R.id.check_box, item.isChecked()).addOnClickListener(R.id.check_box);
            Glide.with(mContext).load(item.getImgUrl()).into((ImageView) helper.getView(R.id.iv_record_house));
            amountView = helper.getView(R.id.mAmountView);
            amountView.setGoods_storage(item.getMaxCount());//这行和下面一行的顺序弄了我一天
            amountView.setAmount(item.getCount());
            amountView.setmZeroListener(new AmountView.OnAmountZeroListener() {
                @Override
                public void onZeroChange(View view, int amount) {
                        int adapterPosition = helper.getAdapterPosition();
                        showDialog(Gravity.CENTER, R.style.Alpah_aniamtion,adapterPosition,amount);
                }
            });
            amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    if (amount>item.getMaxCount()){
                        Toast.makeText(mContext, "商品库存不足", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    mList.get(helper.getAdapterPosition()).setCount(amount);//刷新内存中的
                    LitePalHelper.updateCount(item.getGoodId(), amount);
                    refreshTotalPrice();
                }
            });
            if (isnoOneChecked()) {
                tv_total_price.setText("0.00元");
            }
            refreshTotalPrice();
        }
    }
    private void showDialog(int grary, int animationStyle, final int position,final int amount) {
        BaseDialog.Builder builder = new BaseDialog.Builder(mContext);
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
        tv_content.setText("是否删除该商品？");
        tv_canel.setText("取消");
        tv_canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭dialog
                mList.get(position).setCount(1);//刷新内存中的
                LitePalHelper.updateCount(mList.get(position).getGoodId(), 1);
                mShopCarAdapter.notifyDataSetChanged();
                dialog.close();

            }
        });
        TextView tv_yes = dialog.getView(R.id.tv_yes);
        tv_yes.setText("删除");
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mList.get(position).getGoodId();
                LitePalHelper.deleteOne(id);
                dialog.close();
                initData();
            }
        });
    }
    private List<ShopCarBean> getAllCheckedItem() {
        mList = LitePalHelper.search();
        List<ShopCarBean> list = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isChecked()) {
                list.add(mList.get(i));
            }
        }
        return list;
    }

    private float getAllCheckedItemsTotalPrice() {
        float price = 0;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isChecked()) {
                price += Float.parseFloat(mList.get(i).getPrice()) * mList.get(i).getCount();
            }
        }
        return price;
    }

    private void deleteAllCheckedItemIds() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isChecked()) {
                String id = mList.get(i).getGoodId();
                LitePalHelper.deleteOne(id);
            }
        }
    }

    private boolean isnoOneChecked() {
        for (ShopCarBean shopCarBean : mList) {
            if (shopCarBean.isChecked()) {
                return false;
            }
        }
        isAllChecked=false;
        return true;
    }

    private boolean isAllChecked() {
        for (ShopCarBean shopCarBean : mList) {
            if (!shopCarBean.isChecked()) {
                isAllChecked=false;
                return false;
            }
        }
        isAllChecked=true;
        return true;
    }

    private void SetAllChecked() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setChecked(true);
            LitePalHelper.updateCheck(mList.get(i).getGoodId(), mList.get(i).isChecked());
        }
    }

    private void SetAllDontChecked() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setChecked(false);
            LitePalHelper.updateCheck(mList.get(i).getGoodId(), mList.get(i).isChecked());
        }
    }

    private String  getAllProduct_id() {
        List<ShopCarBean> allCheckedItem = getAllCheckedItem();
        if (allCheckedItem == null || allCheckedItem.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < allCheckedItem.size(); i++) {
            builder.append(allCheckedItem.get(i).getGoodId() + ",");
        }
        String substring = builder.substring(builder.toString().length() - 1);
        if (substring.equals(",")) {//如果最后一个字符是逗号就去掉最后的那个逗号
            String substring1 = builder.substring(0, builder.toString().length() - 1);
            return substring1;
        } else {
            return builder.toString();
        }
    }

    private String getAllNum() {
        List<ShopCarBean> allCheckedItem = getAllCheckedItem();
        if (allCheckedItem == null || allCheckedItem.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < allCheckedItem.size(); i++) {
            builder.append(allCheckedItem.get(i).getCount() + ",");
        }
        String substring = builder.substring(builder.toString().length() - 1);
        if (substring.equals(",")) {//如果最后一个字符是逗号就去掉最后的那个逗号
            String substring1 = builder.substring(0, builder.toString().length() - 1);
            return substring1;
        } else {
            return builder.toString();
        }
    }
}
