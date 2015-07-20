package com.jarlen.picture;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ScrollView;

@SuppressWarnings("deprecation")
public class ContentToPictureUtils {

	public static String DCIMCamera_PATH = Environment
			.getExternalStorageDirectory() + "/DCIM/Camera/";
	
	/**
	 * webView
	 * 
	 * @param context
	 * @param webView
	 */
	public static void webviewContent2Png(Context context, WebView webView) {
		Bitmap bmp = null;
		bmp = captureWebView(webView);
		// new Thread(new WorkThread(bmp)).start();
		saveBitmapToCamera(context, bmp, null);
	}

	/**
	 * mScrollView
	 * 
	 * @param context
	 * @param scrollView
	 */
	public static void scrollviewContent2Png(Context context,
			ScrollView scrollView) {
		Bitmap bmp = null;
		bmp = getBitmapByView(scrollView);
		// new Thread(new WorkThread(bmp)).start();
		saveBitmapToCamera(context, bmp, null);
	}

	private static Bitmap captureWebView(WebView webView) {
		Picture snapShot = webView.capturePicture();
		Bitmap bmp = Bitmap.createBitmap(snapShot.getWidth(),
				snapShot.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bmp);
		snapShot.draw(canvas);
		return bmp;
	}

	public static Boolean saveBitmapToCamera(Context context, Bitmap bm,
			String name) {

		File file = null;

		if (name == null || name.equals("")) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			Date curDate = new Date(System.currentTimeMillis());
			name = formatter.format(curDate) + ".jpg";
		}

		file = new File(DCIMCamera_PATH, name);
		if (file.exists()) {
			file.delete();
		}

		try {
			FileOutputStream out = new FileOutputStream(file);
			bm.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;

		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}

		Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		Uri uri = Uri.fromFile(file);
		intent.setData(uri);
		context.sendBroadcast(intent);

		return true;
	}

	public static Bitmap getBitmapByView(ScrollView scrollView) {
		int h = 0;
		Bitmap bitmap = null;

		for (int i = 0; i < scrollView.getChildCount(); i++) {
			h += scrollView.getChildAt(i).getHeight();
			scrollView.getChildAt(i).setBackgroundColor(
					Color.parseColor("#ffffff"));
		}

		bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
				Bitmap.Config.RGB_565);
		final Canvas canvas = new Canvas(bitmap);
		scrollView.draw(canvas);
		return bitmap;
	}

}
