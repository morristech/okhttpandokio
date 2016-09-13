package com.cld.okhttp;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.cp.demo.ui.util.CldMainUtil;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * 
 * 异步下载文件
 * 
 * @author ChenP
 * @date 2016年9月13日 下午3:20:36
 */
public class CldTest5Activity extends Activity {

	OkHttpClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.main_layout);
		super.onCreate(savedInstanceState);
		((TextView) findViewById(R.id.top_title)).setText("异步下载文件");

		try {
			cancelTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void cancelTest() throws Exception {
		Request request = new Request.Builder().url(
				"http://httpbin.org/delay/2").build();

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

}
