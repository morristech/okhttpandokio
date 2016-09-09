package com.cp.demo.ui.util;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.cld.okhttp.R;

/**
 * 
 * 加载网页链接
 * 
 * @author ChenP
 * @date 2016年8月16日 下午10:54:08
 */
public class CldWebActivity extends Activity {

	private TextView top_title;
	private WebView web_show;

	private String mUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.web_activity);
		//
		// Bundle mBundle = getIntent().getExtras();
		// if (mBundle != null) {
		// mUrl = mBundle.getString("url", "");
		// }
		//
		// top_title = (TextView) findViewById(R.id.top_title);
		// top_title.setText("加载中...");
		// web_show = (WebView) findViewById(R.id.web_show);
		// setParams(web_show);
		// web_show.loadUrl(mUrl);
		// web_show.setWebViewClient(new WebViewClient() {
		// @Override
		// public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// view.loadUrl(url);
		// return true;
		// }
		//
		// // @Override
		// // public void onPageFinished(WebView view, String url) {
		// //
		// view.loadUrl("javascript：window.HTMLOUT.getContentWidth(document.getElementsByTagName('html')[0].scrollWidth);");
		// // // super.onPageFinished(view, url);
		// // }
		// });
		//
		// web_show.setWebChromeClient(new WebChromeClient() {
		// @Override
		// public void onReceivedTitle(WebView view, String title) {
		// top_title.setText(title);
		// super.onReceivedTitle(view, title);
		// }
		// });

		// web_show.addJavascriptInterface(new JavaScriptInterface(),
		// "HTMLOUT");
		// 获取webview的宽度:网络说可以通过javascript获取，没有测试通过:
		// http://www.android100.org/html/201311/19/4804.html
	}

	class JavaScriptInterface {
		public void getContentWidth(String value) {
			if (!TextUtils.isEmpty(value)) {
				int mWebWidth = Integer.parseInt(value);
				Log.i(CldMainUtil.TAG, "getContentWidth=" + mWebWidth);
			}
		}
	}

	/**
	 * 设置WebView的参数
	 * 
	 * @param web_show
	 * @return void
	 * @author ChenP
	 * @date 2016年8月16日 下午11:14:46
	 */
	private void setParams(WebView web_show) {
		WebSettings webSettings = web_show.getSettings();
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setUseWideViewPort(true);// 关键点
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setDisplayZoomControls(false);
		webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
		webSettings.setAllowFileAccess(true); // 允许访问文件
		webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
		webSettings.setSupportZoom(true); // 支持缩放

		webSettings.setLoadWithOverviewMode(true);

		// web_show.setSaveEnabled(true);
	}

}
