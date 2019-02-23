package com.example.administrator.chushi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.SearchHistoryEntity;
import com.example.administrator.chushi.fragment.good.FenLeiXQActivity;
import com.example.administrator.chushi.utils.BaseDialog;
import com.example.administrator.chushi.utils.SpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener, TextView.OnEditorActionListener {
    private EditText et_search;
    private TextView tv_back,tv_wujilu;
    private ImageView iv_delete;
    private RecyclerView recycler_lishi;
    private List<SearchHistoryEntity> mHistoryList = new ArrayList<>();
    private RecyclerHistoryAdapter mHistoryAdapter;
    private int position;
    private RelativeLayout relative_lishi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        et_search = (EditText) findViewById(R.id.et_search);
        et_search.setOnEditorActionListener(this);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_wujilu = (TextView) findViewById(R.id.tv_wujilu);
        tv_back.setOnClickListener(this);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        iv_delete.setOnClickListener(this);
        recycler_lishi = (RecyclerView) findViewById(R.id.recycler_lishi);
        relative_lishi= (RelativeLayout) findViewById(R.id.relative_lishi);
        initData();
        initListener();
    }
    private void initListener() {
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    //判断本地数据中有没有存在搜索过的数据，查重
    private boolean isHasSelectData(String content) {
        if (mHistoryList == null || mHistoryList.size() == 0) {
            return false;
        }
        for (int i = 0; i < mHistoryList.size(); i++) {
            if (mHistoryList.get(i).getContent().equals(content)) {
                position = i;
                return true;
            }
        }
        return false;
    }

    private void doSavehistory(String content) {

        if (isHasSelectData(content)) {//查重
            mHistoryList.remove(position);
        }
        //后来搜索的文字放在集合中的第一个位置
        mHistoryList.add(0, new SearchHistoryEntity(content));

        if (mHistoryList.size() == 10) {//实现本地历史搜索记录最多不超过十个
            mHistoryList.remove(9);
        }
        //将这个mHistoryListData保存到sp中，其实sp中保存的就是这个mHistoryListData集合
        saveHistory();
    }

    /**
     * 保存历史查询记录
     */
    private void saveHistory() {
        SpUtils.putString(this, "history",
                new Gson().toJson(mHistoryList));//将java对象转换成json字符串进行保存
    }

    /**
     * 获取历史查询记录
     *
     * @return
     */
    private List<SearchHistoryEntity> getHistory() {
        String historyJson = SpUtils.getString(this, "history", "");
        if (historyJson != null && !historyJson.equals("")) {//必须要加上后面的判断，因为获取的字符串默认值就是空字符串
            //将json字符串转换成list集合
            return new Gson().fromJson(historyJson, new TypeToken<List<SearchHistoryEntity>>() {
            }.getType());
        }
        return new ArrayList<SearchHistoryEntity>();
    }


    private void initData() {
        recycler_lishi.setLayoutManager(new GridLayoutManager(this, 3));
        recycler_lishi.setNestedScrollingEnabled(false);
        mHistoryList = getHistory();//从本地取出来
        if (mHistoryList != null && mHistoryList.size() > 0) {
            mHistoryAdapter = new RecyclerHistoryAdapter(R.layout.history_item, mHistoryList);
            recycler_lishi.setAdapter(mHistoryAdapter);
            relative_lishi.setVisibility(View.VISIBLE);
        }else {
            relative_lishi.setVisibility(View.GONE);
            tv_wujilu.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH)
        {
            doSavehistory(et_search.getText().toString());
            mHistoryList = getHistory();//从本地取出来
            if (mHistoryList != null && mHistoryList.size() > 0) {
                if (mHistoryAdapter != null) {
                    mHistoryAdapter.notifyDataSetChanged();//刷新一下界面
                } else {
                    mHistoryAdapter = new RecyclerHistoryAdapter(R.layout.history_item, mHistoryList);
                    recycler_lishi.setAdapter(mHistoryAdapter);
                }
            }
            Intent intent=new Intent(SearchActivity.this, FenLeiXQActivity.class);
            intent.putExtra("name",et_search.getText().toString());
            intent.putExtra("tag","");
            startActivity(intent);
            finish();

        }
        return false;
    }

    class RecyclerHistoryAdapter extends BaseQuickAdapter<SearchHistoryEntity, BaseViewHolder> {

        public RecyclerHistoryAdapter(@LayoutRes int layoutResId, @Nullable List<SearchHistoryEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SearchHistoryEntity item) {
            final TextView tv_history = helper.getView(R.id.tv_history);
            tv_history.setText(item.getContent());
            tv_history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(SearchActivity.this, FenLeiXQActivity.class);
                    intent.putExtra("name",tv_history.getText().toString());
                    intent.putExtra("tag","");
                    startActivity(intent);
                    finish();
                }
            });
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_delete:
                showDialog(Gravity.CENTER, R.style.Alpah_aniamtion);
                break;
        }
    }

    private void showDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_history)
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
        tv_canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭dialog
                dialog.close();
            }
        });
        TextView tv_yes = dialog.getView(R.id.tv_yes);
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHistoryList.clear();
                saveHistory();
                if (mHistoryAdapter != null) {
                    mHistoryAdapter.getData().clear();//如果不加这句的话第二次删除就会不生效，真不知道为什么
                    mHistoryAdapter.notifyDataSetChanged();
                    initData();
                }
                dialog.close();
            }
        });
    }

}
