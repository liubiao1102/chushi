package com.example.administrator.chushi.fragment.mine;

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

public class YiShiYongFragment extends BaseFragment {
    private RecyclerView weishiyong_recycler;
    private List<String> picList = new ArrayList<>();
    private AllDingDanItemAdapter allDingDanItemAdapter;
    private String userid;
    private String token;
    private List<YouHuiJuanBean.DataBean.UsedBean> used;

    @Override
    protected View initView() {
        View view=View.inflate(mContext, R.layout.activity_yishiyong_fragment,null);
        weishiyong_recycler= (RecyclerView) view.findViewById(R.id.weishiyong_recycler);
        userid = MyUtils.getUserid(mContext);
        token = MyUtils.getToken(mContext);
        return view;
    }

    @Override
    protected void initData() {
        initNet();

    }

    private void initNet() {
        OkGo.get(MyContants.BASEURL+"User/ticket/")
                .tag(this)
                .params("userid",userid)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();

                        YouHuiJuanBean mHomeFragmentBean = gson.fromJson(s, YouHuiJuanBean.class);
                        if (mHomeFragmentBean.getCode()==200){
                            used = mHomeFragmentBean.getData().getUsed();
                            initAdapter();

                        }else {
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
        if (allDingDanItemAdapter == null) {
            allDingDanItemAdapter = new AllDingDanItemAdapter(R.layout.mine_yishiyong_item, used);
        }
        weishiyong_recycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        weishiyong_recycler.setNestedScrollingEnabled(false);
        weishiyong_recycler.setAdapter(allDingDanItemAdapter);
    }

    class AllDingDanItemAdapter extends BaseQuickAdapter<YouHuiJuanBean.DataBean.UsedBean, BaseViewHolder> {

        public AllDingDanItemAdapter(@LayoutRes int layoutResId, @Nullable List<YouHuiJuanBean.DataBean.UsedBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, YouHuiJuanBean.DataBean.UsedBean item) {
            if (item.getDiejia().equals("1")){
                helper.setText(R.id.tv_is_manjian,"可同满减/满赠活动一起使用");
            }else {
                helper.setText(R.id.tv_is_manjian,"不参与满减/满赠活动");
            }
            helper.setText(R.id.tv_price,item.getMoney());
            helper.setText(R.id.tv_title,item.getTitle());
            helper.setText(R.id.tv_shengyu,"有效期至："+item.getEndtime());
            helper.setText(R.id.tv_manjian,item.getRule());

        }
    }
}
