# content2picture
先看需求：  当内容已经超出了手机可显示的范围时，要截取这些所有的内容，从而生成所谓的”长截图”.  没什么难点，利用了webview的特点，和scrollview 的view的绘制，生成bitmap。

主要代码：

//这是scrollview的

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
        saveBitmapToCamera(context, bmp, null);
    }

//这是webview的，利用了webview的api

private static Bitmap captureWebView(WebView webView) {
        Picture snapShot = webView.capturePicture();
        Bitmap bmp = Bitmap.createBitmap(snapShot.getWidth(),
                snapShot.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        snapShot.draw(canvas);
        return bmp;
    }

代码粗略，只实现了功能部分，在图片生成的时候，未使用线程，如果还有其他比较好的方案，可以相互交流下
