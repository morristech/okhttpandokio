package com.cld.okhttp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.cp.demo.ui.util.CldMainUtil;

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
public class CldTest4Activity extends Activity {

	OkHttpClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.java_basic_detail_layout);
		super.onCreate(savedInstanceState);
		((TextView) findViewById(R.id.top_title)).setText("异步下载文件");

		downAsynFile();

	}

	private void downAsynFile() {
		OkHttpClient mOkHttpClient = new OkHttpClient();
		String url = "http://img.my.csdn.net/uploads/201603/26/1458988468_5804.jpg";
		Request request = new Request.Builder().url(url).build();
		mOkHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

			}

			@Override
			public void onResponse(Call call, final Response response) {

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						((TextView) findViewById(R.id.btn_detail_con))
								.setText(CldMainUtil.getContent(response));
					}
				});

				InputStream inputStream = response.body().byteStream();
				FileOutputStream fileOutputStream = null;
				try {
					fileOutputStream = new FileOutputStream(CldMainUtil
							.getCacheDir(CldTest4Activity.this).toString()
							+ "/down.jpg");
					byte[] buffer = new byte[2048];
					int len = 0;
					while ((len = inputStream.read(buffer)) != -1) {
						fileOutputStream.write(buffer, 0, len);
					}
					fileOutputStream.flush();
				} catch (IOException e) {
					Log.i("wangshu", "IOException");
					e.printStackTrace();
				}

			}
		});
	}
}
