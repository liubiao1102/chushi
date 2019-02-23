package com.example.administrator.chushi.fragment.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.bean.AboutUsBean;
import com.example.administrator.chushi.utils.BaseDialog;
import com.example.administrator.chushi.utils.MyContants;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {
    private ImageView tv_back;
    private Button btn_phone;
    private WebView webView;
    private String datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initView();
        initData();
    }

    private void initData() {
        OkGo.get(MyContants.BASEURL + "Bottom/tel")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        AboutUsBean aboutUsBean=gson.fromJson(s,AboutUsBean.class);
                        if (aboutUsBean.getCode()==200){
                            datas = aboutUsBean.getDatas();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(AboutUsActivity.this, "请检查网络或重试", Toast.LENGTH_SHORT).show();
                        Log.e("请求失败", "失败原因：" + response);
                    }
                });
    }

    private void initView() {
        btn_phone= (Button) findViewById(R.id.btn_phone);
        btn_phone.setOnClickListener(this);
        tv_back= (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView = (WebView) findViewById(R.id.webView);

        //WebView加载web资源
        webView.loadUrl("http://118.190.159.169/chushishare/about.html");

        //js
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return false;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成

                } else {
                    // 加载中

                }

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_phone:
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
        tv_content.setText("确认拨打电话："+datas);
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
                Intent dialIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + datas));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                dialog.close();
            }
        });
    }

}
