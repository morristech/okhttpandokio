package com.cp.demo.ui.util;

import java.io.File;

import okhttp3.Response;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
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

	public static final int cacheSize = 10 * 1024 * 1024;// 10M

	public static void log(String TAG, Response response) {
		Log.i(CldMainUtil.TAG, TAG + "getPostHttp response=" + response);
		Log.i(CldMainUtil.TAG,
				TAG + "getPostHttp cache response=" + response.cacheResponse());
		Log.i(CldMainUtil.TAG,
				TAG + "getPostHttp network response="
						+ response.networkResponse());
	}

	public static String getContent(Response response) {
		StringBuilder mBuilder = new StringBuilder();
		mBuilder.append("request =" + response.request().toString() + "\n\n");
		mBuilder.append("头部信息:\n");
		mBuilder.append(response.headers().toString() + "\n");
		mBuilder.append("缓存是否为空=" + (response.cacheResponse() == null) + "\n\n");
		mBuilder.append("返回码code=" + response.code() + "\n\n");
		mBuilder.append("http协议protocol=" + response.protocol().toString()
				+ "\n\n");
		if(response.networkResponse()!=null){
			mBuilder.append("networkResponse=" + response.networkResponse().toString()
					+ "\n\n");
		}

		return mBuilder.toString();
	}

	public static File getCacheDir(Activity mContext) {
		return mContext.getExternalCacheDir().getAbsoluteFile();
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
		mListView.setPadding(0, SizeUtils.dp2px(mContext, 10), 0,
				SizeUtils.dp2px(mContext, 30));
		// 是否允许ViewGroup在padding中绘制
		mListView.setClipToPadding(false);
	}

}
