package com.jarlen.picture;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

/**
 * 从ScrollView 和webview中截取图片
 * 
 * @author jarlen
 * 
 */
public class WebviewToPictureActivity extends Activity implements
		OnClickListener {

	private Button testBtn;

	private WebView mWebView;
	
	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_webview_test);
		
		mWebView = (WebView) findViewById(R.id.webView);
		mWebView.setDrawingCacheEnabled(true);//
		
		mProgressDialog = new ProgressDialog(this);

		mWebView.loadUrl("http://blog.csdn.net/jarlen/article/details/46931093");
		mWebView.setVisibility(View.GONE);
		
		mProgressDialog.show();

		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				mWebView.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				mWebView.setVisibility(View.VISIBLE);
				mProgressDialog.dismiss();
				super.onPageFinished(view, url);
			}

		});

		testBtn = (Button) findViewById(R.id.testBtn);
		testBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {

		if (view.getId() == R.id.testBtn) {
			ContentToPictureUtils.webviewContent2Png(this, mWebView);
			Toast.makeText(this, "已保存至相册", Toast.LENGTH_SHORT).show();
		}

	}

}
