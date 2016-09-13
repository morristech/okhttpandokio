package com.cld.okhttp;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.cp.demo.ui.util.CldMainUtil;

/**
 * 
 * 异步上传文件
 * 
 * @author ChenP
 * @date 2016年9月13日 下午3:20:36
 */
public class CldTest3Activity extends Activity {

	OkHttpClient client;

	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType
			.parse("text/x-markdown; charset=utf-8");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.java_basic_detail_layout);
		super.onCreate(savedInstanceState);
		((TextView) findViewById(R.id.top_title)).setText("异步上传文件");

		postAsynFile();

	}

	private void postAsynFile() {
		OkHttpClient mOkHttpClient = new OkHttpClient();
		File file = new File(CldMainUtil.getCacheDir(CldTest3Activity.this)
				.toString() + "/up.txt");
		Request request = new Request.Builder()
				.url("https://api.github.com/markdown/raw")
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file)).build();

		mOkHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						((TextView) findViewById(R.id.btn_detail_con))
								.setText("请求失败");
					}
				});
			}

			@Override
			public void onResponse(Call call, final Response response)
					throws IOException {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						((TextView) findViewById(R.id.btn_detail_con))
								.setText(CldMainUtil.getContent(response));
					}
				});
			}
		});
	}

}
