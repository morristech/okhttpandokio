package com.cld.okhttp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.cp.demo.ui.util.CldMainUtil;

public class MainActivity extends Activity {

	private ListView main_list;
	private MainListAdapter mListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.main_layout);
		super.onCreate(savedInstanceState);
		((TextView) findViewById(R.id.top_title)).setText("OkHttp");
		main_list = (ListView) findViewById(R.id.main_list);
		mListAdapter = new MainListAdapter(MainActivity.this, getList());
		main_list.setAdapter(mListAdapter);

		CldMainUtil.setListPading(MainActivity.this, main_list);

		main_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					startActivity(new Intent(MainActivity.this,
							CldTest1Activity.class));
					break;
				case 1:
					startActivity(new Intent(MainActivity.this,
							CldTest2Activity.class));
					break;
				case 2:
					startActivity(new Intent(MainActivity.this,
							CldTest3Activity.class));
					break;
				case 3:
					startActivity(new Intent(MainActivity.this,
							CldTest4Activity.class));
					break;

				default:
					break;
				}

			}
		});

	}

	private List<String> getList() {
		List<String> mList = new ArrayList<String>();
		mList.add("异步GET请求");
		mList.add("异步POST请求");
		mList.add("异步上传文件");
		mList.add("异步下载文件");
		return mList;
	}

	public String ex() {
		StringBuilder mBuilder = new StringBuilder();
		mBuilder.append("1.是一款高效的HTTP客户端\n");
		mBuilder.append("2.连接同一个地址的链接共享同一个socket\n");
		mBuilder.append("3.通过连接池来减小相应延迟\n");
		mBuilder.append("4.有透明的GZIP压缩，请求缓存等优势\n");
		return mBuilder.toString();
	}

}
