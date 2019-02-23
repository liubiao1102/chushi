package com.example.administrator.chushi.fragment.mine;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;

public class HelpTowXQActivity extends BaseActivity {
    private WebView webView;
    private String class_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_tow_xq);
        initView();
        initData();
    }
    private void initView() {
        class_id = getIntent().getStringExtra("class_id");
        ImageView tv_back= (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initData() {
        webView = (WebView) findViewById(R.id.webView);

        //WebView加载web资源
        webView.loadUrl("http://118.190.159.169/chushishare/help.html?id="+class_id);

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


}
