package com.xujingrong.example;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ThirdA extends Base
{
	WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thirda);
		webView=(WebView) findViewById(R.id.thirdaWebView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient());
		webView.loadUrl("https://www.baidu.com");
	}
	
}
