package com.jarlen.picture;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

/**
 * 从ScrollView 和webview中截取图片
 * 
 * @author jarlen
 * 
 */
public class ScrollViewToPictureActivity extends Activity implements
		OnClickListener {

	private Button testBtn;

	private ScrollView mScrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_scrollview_test);
		mScrollView = (ScrollView) findViewById(R.id.scrollview_sv);

		testBtn = (Button) findViewById(R.id.testBtn);
		testBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {

		if (view.getId() == R.id.testBtn) {

			ContentToPictureUtils.scrollviewContent2Png(this, mScrollView);
			Toast.makeText(this, "已保存至相册", Toast.LENGTH_SHORT).show();
		}

	}

}
