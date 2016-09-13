package com.cld.okhttp;

import java.io.IOException;

import com.cp.demo.ui.util.BaseUIHandler;
import com.cp.demo.ui.util.CldMainUtil;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/**
 * 
 * 异步GET请求
 * 
 * @author ChenP
 * @date 2016年9月13日 下午3:20:36
 */
public class CldTest1Activity extends Activity {

	OkHttpClient client;

	TextView btn_detail_con;

	private MyHandler myHandler = new MyHandler(this);

	static class MyHandler extends BaseUIHandler<CldTest1Activity> {

		protected MyHandler(CldTest1Activity target) {
			super(target);
		}

		@Override
		public void handleMessage(Message msg) {
			CldTest1Activity mGet = get();
			if (mGet != null) {
				if (msg.obj instanceof String) {
					mGet.showContent((String) msg.obj);
				}
			}
			super.handleMessage(msg);
		}

	}

	private void showContent(String mCon) {
		btn_detail_con.setText(mCon);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.java_basic_detail_layout);
		super.onCreate(savedInstanceState);
		((TextView) findViewById(R.id.top_title)).setText("异步GET请求");

		btn_detail_con = (TextView) findViewById(R.id.btn_detail_con);

		// 需要在线程中执行execute
		new Thread(new Runnable() {

			@Override
			public void run() {
				getGetHttp();
			}
		}).start();

	}

	/**
	 * 必须在线程中执行
	 * 
	 * @return void
	 * @author ChenP
	 * @date 2016年9月13日 下午3:50:18
	 */
	public void getGetHttp() {
		try {
			OkHttpClient client = new OkHttpClient()
					.newBuilder()
					.cache(new Cache(CldMainUtil
							.getCacheDir(CldTest1Activity.this),
							CldMainUtil.cacheSize)).build();// 指定缓存路径
			Request request = new Request.Builder().url(
					"https://publicobject.com/helloworld.txt").build();
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				Message mMessage = myHandler.obtainMessage();
				mMessage.obj = CldMainUtil.getContent(response);
				mMessage.what = 0;
				myHandler.sendMessage(mMessage);
				response.body().close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			Log.i(CldMainUtil.TAG, "IOException =");
		}
	}

}
