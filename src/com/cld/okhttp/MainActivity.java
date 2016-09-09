package com.cld.okhttp;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cp.demo.ui.util.CldMainUtil;

public class MainActivity extends Activity {

	OkHttpClient client = new OkHttpClient();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.main_layout);
		super.onCreate(savedInstanceState);
		((TextView) findViewById(R.id.top_title)).setText("OkHttp");

		File sdcache = getExternalCacheDir();
		int cacheSize = 10 * 1024 * 1024;// 10M
		// client.setCache(new Cache(sdcache.getAbsoluteFile(), cacheSize));//
		// 设置缓存

		new Thread(new Runnable() {

			@Override
			public void run() {
				getGetHttp();// 需要在线程中执行execute
				// getPostHttp();
				// getPostHttp();
			}
		}).start();

		// try {
		// cancelTest();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// getGetHttp2();// 使用Okhttp的县城切换

	}

	// private void simpleDo(){
	// SubjectPost
	// }

	public String ex() {
		StringBuilder mBuilder = new StringBuilder();
		mBuilder.append("1.是一款高效的HTTP客户端\n");
		mBuilder.append("2.连接同一个地址的链接共享同一个socket\n");
		mBuilder.append("3.通过连接池来减小相应延迟\n");
		mBuilder.append("4.有透明的GZIP压缩，请求缓存等优势\n");
		return mBuilder.toString();
	}

	public void getGetHttp() {
		try {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(
					"http://publicobject.com/helloworld.txt").build();
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				Log.i(CldMainUtil.TAG, "getHttp =" + response.code());
				Log.i(CldMainUtil.TAG, "getHttp body="
						+ response.body().toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			Log.i(CldMainUtil.TAG, "IOException =");
		}
	}

	public void getGetHttp2() {
		Request request = new Request.Builder().url(
				"http://publicobject.com/helloworld.txt").build();
		// client.newCall(request).enqueue(new Callback() {
		//
		// @Override
		// public void onResponse(Response response) throws IOException {
		// // !!!!!!!!!!!非UI线程
		// if (response.isSuccessful()) {
		// Log.i(CldMainUtil.TAG, "getHttp =" + response.code());
		// Log.i(CldMainUtil.TAG, "getHttp body="
		// + response.body().toString());
		// }
		// }
		//
		// @Override
		// public void onFailure(Request response, IOException arg1) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
	}

	/**
	 * 通过post方式请求
	 * 
	 * @return void
	 * @author ChenP
	 * @date 2016年8月23日 下午3:37:38
	 */
	public void getPostHttp() {
		// try {
		// FormBody formBody = FormBody.Builder().add("platform", "android")
		// .add("name", "bug").add("subject", "XXXXXXXXXXXXXXX")
		// .build();
		//
		// Request request = new Request.Builder()
		// .url("http://publicobject.com/helloworld.txt")
		// .put(formBody).build();
		// /**
		// * 第一次请求
		// */
		// Response response = client.newCall(request).execute();
		// if (response.isSuccessful()) {
		// log("第一次请求:", response);
		// }
		// /**
		// * 第二次请求
		// */
		// Response response2 = client.newCall(request).execute();//
		// // /按道理应该使用缓存，但是打log没有，没找到是什么原因？？？？？
		// if (response.isSuccessful()) {
		// log("第二次应该有缓存:", response2);
		// }
		// /**
		// * 第三次请求
		// */
		// request = request.newBuilder()
		// .cacheControl(CacheControl.FORCE_NETWORK).build();// ---强制使用网络
		// Response response3 = client.newCall(request).execute();//
		// if (response3.isSuccessful()) {
		// log("第三次强制使用网络:", response3);
		// }
		// /**
		// * 第四次请求
		// */
		// request = request.newBuilder()
		// .cacheControl(CacheControl.FORCE_CACHE).build();// ---强制使用缓存
		// Response response4 = client.newCall(request).execute();//
		// if (response4.isSuccessful()) {
		// log("第三次强制使用网络:", response4);
		// } else {
		// Log.i(CldMainUtil.TAG, "getPostHttp response fail=" + response4);
		// }
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	private void cancelTest() throws Exception {
		Request request = new Request.Builder().url(
				"http://httpbin.org/delay/2") // This
												// URL
												// is
												// served
												// with
												// a
												// 2
												// second
												// delay.
				.build();

		final long startNanos = System.nanoTime();
		final Call call = client.newCall(request);

		Timer executor = new Timer();
		// Schedule a job to cancel the call in 1 second.
		executor.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.printf("%.2f Canceling call.%n",
						(System.nanoTime() - startNanos) / 1e9f);
				call.cancel();
				System.out.printf("%.2f Canceled call.%n",
						(System.nanoTime() - startNanos) / 1e9f);
			}
		}, 1, 1000);

		try {
			System.out.printf("%.2f Executing call.%n",
					(System.nanoTime() - startNanos) / 1e9f);
			Response response = call.execute();
			System.out.printf("call is cancel:" + call.isCanceled() + "%n");
			System.out.printf(
					"%.2f Call was expected to fail, but completed: %s%n",
					(System.nanoTime() - startNanos) / 1e9f, response);
		} catch (IOException e) {
			System.out.printf("%.2f Call failed as expected: %s%n",
					(System.nanoTime() - startNanos) / 1e9f, e);
		}
	}

	private void log(String TAG, Response response) {
		// Log.i(CldMainUtil.TAG, TAG + "getPostHttp code=" + response.code());
		// Log.i(CldMainUtil.TAG, TAG + "getPostHttp response body="
		// + response.body().toString());
		Log.i(CldMainUtil.TAG, TAG + "getPostHttp response=" + response);
		Log.i(CldMainUtil.TAG,
				TAG + "getPostHttp cache response=" + response.cacheResponse());
		Log.i(CldMainUtil.TAG,
				TAG + "getPostHttp network response="
						+ response.networkResponse());
	}

}
