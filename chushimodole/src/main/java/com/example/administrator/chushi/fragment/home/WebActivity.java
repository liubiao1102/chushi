package com.example.administrator.chushi.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.chushi.R;
import com.example.administrator.chushi.base.BaseActivity;
import com.example.administrator.chushi.utils.MyUtils;


public class WebActivity extends BaseActivity {
    private WebView webView;
    private ImageView tv_back;
    private String mresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        tv_back= (ImageView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int i=21;
        final String result = getIntent().getStringExtra("result");
        mresult=result.substring(0,i);

        webView= (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
//                    Toast.makeText(WebActivity.this, "网页加载完成", Toast.LENGTH_SHORT).show();

                } else {

                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(WebActivity.this, "加载出错了", Toast.LENGTH_SHORT).show();
            }
        });
        if (mresult.equals("http://114.215.83.139")){
            webView.loadUrl(result+"&userId="+ MyUtils.getUserid(WebActivity.this));

        }else {
            Toast.makeText(this, "扫描错误,请使用正确的二维码", Toast.LENGTH_SHORT).show();
        }

    }
}
