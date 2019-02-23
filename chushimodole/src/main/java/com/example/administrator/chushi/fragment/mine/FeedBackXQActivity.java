package com.example.administrator.chushi.fragment.mine;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.EventMessage;
import com.example.administrator.chushi.utils.BaseDialog;

import org.greenrobot.eventbus.EventBus;

public class FeedBackXQActivity extends BaseActivity implements View.OnClickListener {
    private ImageView img_xq,tv_back;
    private TextView tv_delete;
    private String img;
    private int position;
    private int mposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_xq);

        img = getIntent().getStringExtra("img");
        mposition = getIntent().getIntExtra("position", this.position);
        initView();
    }

    private void initView() {
        img_xq= (ImageView) findViewById(R.id.img_xq);
        tv_back= (ImageView) findViewById(R.id.tv_back);
        tv_delete= (TextView) findViewById(R.id.tv_delete);
        tv_delete.setOnClickListener(this);
        tv_back.setOnClickListener(this);
        Glide.with(FeedBackXQActivity.this).load(img).into(img_xq);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_delete:
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
        tv_content.setText("确认删除此相片？");
        tv_canel.setText("取消");
        tv_canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭dialog
                dialog.close();
            }
        });
        TextView tv_yes = dialog.getView(R.id.tv_yes);
        tv_yes.setText("确定");
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventMessage eventMessageone = new EventMessage();
                eventMessageone.setMsg("deleteimg");
                eventMessageone.setNum(mposition);
                EventBus.getDefault().postSticky(eventMessageone);
                dialog.close();
                finish();
            }
        });
    }
}
