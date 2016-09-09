package com.cp.demo.ui.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;

import com.cp.demo.utils.SizeUtils;

/**
 * 
 * 主工具类
 * 
 * @author ChenP
 * @date 2016年7月8日 上午9:49:35
 */
public class CldMainUtil {

	public static final String TAG = "cldlog cp";// 注释

	/**
	 * 打开某个网页
	 * 
	 * @param mContext
	 * @param url
	 * @return void
	 * @author ChenP
	 * @date 2016年8月16日 下午11:00:34
	 */
	public static void openUrl(Activity mContext, String url) {

		if (TextUtils.isEmpty(url)) {// 如果传递的值为空，默认打开百度网页
			url = "http://baidu.com";
		}
		Intent web_intent = new Intent();
		web_intent.setClass(mContext, CldWebActivity.class);
		Bundle mBundle = new Bundle();
		mBundle.putString("url", url);
		web_intent.putExtras(mBundle);
		mContext.startActivity(web_intent);
	}

	/**
	 * 设置ListView的Padding值
	 * 
	 * @param mContext
	 * @param mListView
	 * @return void
	 * @author ChenP
	 * @date 2016年8月16日 下午11:46:13
	 */
	public static void setListPading(Context mContext, ListView mListView) {
		mListView.setPadding(0, SizeUtils.dp2px(mContext, 10), 0, SizeUtils.dp2px(mContext, 30));
		// 是否允许ViewGroup在padding中绘制
		mListView.setClipToPadding(false);
	}

}
