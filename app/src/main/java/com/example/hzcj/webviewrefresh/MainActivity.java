package com.example.hzcj.webviewrefresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    public WebView webview;
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //重新刷新页面
                webview.loadUrl(webview.getUrl());
            }
        });
//        swipeLayout.setColorScheme(
//                getResources().getColor(R.color.colorPrimary),
//                getResources().getColor(R.color.colorAccent),
//                getResources().getColor(R.color.colorPrimaryDark),
//                getResources().getColor(R.color.colorAccent));
        swipeLayout.setColorSchemeColors( getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(R.color.colorAccent));

//        swipeLayout.setColorScheme(R.color.holo_blue_bright);
//        swipeLayout.setColorScheme(R.color.holo_blue_bright,
//                R.color.holo_green_light, R.color.holo_orange_light,
//                R.color.holo_red_light);

        webview = (WebView)findViewById(R.id.webview);

//        webview.loadUrl("http://blog.csdn.net/h7870181");
        webview.loadUrl("http://www.baidu.com");
        //添加javaScript支持
        webview.getSettings().setJavaScriptEnabled(true);
        //取消滚动条
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        //触摸焦点起作用
        webview.requestFocus();
        //点击链接继续在当前browser中响应
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //设置进度条
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    //隐藏进度条
                    swipeLayout.setRefreshing(false);
                } else {
                    if (!swipeLayout.isRefreshing())
                        swipeLayout.setRefreshing(true);
                }

                super.onProgressChanged(view, newProgress);
            }
        });

    }
}
