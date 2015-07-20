package com.jarlen.picture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class TestMainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_main);

		findViewById(R.id.scroll_test).setOnClickListener(this);
		findViewById(R.id.webview_test).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.scroll_test:

			Intent ScrollViewIntent = new Intent(this,
					ScrollViewToPictureActivity.class);

			this.startActivity(ScrollViewIntent);

			break;
		case R.id.webview_test:

			Intent WebviewIntent = new Intent(this,
					WebviewToPictureActivity.class);

			this.startActivity(WebviewIntent);

			break;

		default:
			break;
		}

	}

}
