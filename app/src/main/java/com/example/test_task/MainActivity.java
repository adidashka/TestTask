package com.example.test_task;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TestTaskCallback {

    private FrameLayout frameLayout;
    private Button button;
    private WebView webView;
    private TextView textView;

    private int count = 0;
    private ArrayList<ResponseA> myListA = new ArrayList<>();
    private GetData getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frameLayout);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());

        textView = new TextView(this);
        textView.setTextSize(20);

        getData = new GetData(this);
        getData.gettingListA();
    }

    public void getItemB(int count) {
        getData.gettingitemB(myListA.get(count).getId());
    }

    @Override
    public void onClick(View v) {
        if (myListA!=null) {
            switch (v.getId()){
                case R.id.button:
                    if (count<(myListA.size()-1)){
                        count++;
                        getItemB(count);
                    }
                    else {
                        count = 0;
                        getItemB(count);
                    }
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void onGetListACallback(List<ResponseA> response) {
        if (response!=null) {
            for (int i = 0; i<response.size(); i++)
            myListA.addAll(response);
            getItemB(count);
        }
    }

    @Override
    public void onGetItemBCallback(ResponseB response) {
        if (response != null) {
            switch (response.getType()){
                case "text":
                    textView.setText(response.getContents());
                    frameLayout.removeAllViews();
                    frameLayout.addView(textView);
                    break;

                case "webview":
                    webView.loadUrl(response.getUrl());
                    frameLayout.removeAllViews();
                    frameLayout.addView(webView);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }
}
