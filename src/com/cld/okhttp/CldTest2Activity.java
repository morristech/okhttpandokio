package com.cld.okhttp;

import java.io.IOException;

import com.cp.demo.ui.util.CldMainUtil;

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
import android.widget.Toast;

/**
 * 
 * 异步POST请求
 * 
 * @author ChenP
 * @date 2016年9月13日 下午3:20:36
 */
public class CldTest2Activity extends Activity {

	OkHttpClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.java_basic_detail_layout);
		super.onCreate(savedInstanceState);
		((TextView) findViewById(R.id.top_title)).setText("异步POST请求");

		getPostHttp();

	}

	/**
	 * 通过post方式请求
	 * 
	 * @return void
	 * @author ChenP
	 * @date 2016年8月23日 下午3:37:38
	 */
	public void getPostHttp() {
		OkHttpClient mOkHttpClient = new OkHttpClient();
		RequestBody formBody = new FormBody.Builder().add("size", "10").build();
		Request request = new Request.Builder()
				.url("http://api.1-blog.com/biz/bizserver/article/list.do")
				.post(formBody).build();
		Call call = mOkHttpClient.newCall(request);
		call.enqueue(new Callback() {

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

			@Override
			public void onFailure(Call call, IOException e) {

			}

		});

	}

}
