package com.cp.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastDialog {

	private static Toast toast = null;

	/**
	 * 显示Toast提示
	 * 
	 * @param cxt
	 * @param str
	 * @return void
	 * @author ChenP
	 * @date 2016年7月14日 下午5:23:22
	 */
	public static void showToast(Context context, String str) {
		// final Context context = HFModesManager.getCurrentContext();
		if (null == context) {
			return;
		}
		if (toast != null) {
			toast.cancel();
			toast = null;
		}
		toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
		toast.show();

		// if (CldModeUtils.isAppToBackground(context)) {
		// // 当前应用处于后台，停止执行
		// return;
		// }
		// ((Activity) context).runOnUiThread(new Runnable() {
		//
		// @Override
		// public void run() {
		// Toast.makeText(context, strMsg, Toast.LENGTH_LONG).show();
		// }
		// });
	}

	/**
	 * 显示Toast
	 * 
	 * @param strMsgId
	 *            显示内容StringId
	 * @return void
	 * @author ChenP
	 * @date 2016年7月14日 下午5:27:36
	 */
	public static void showToast(final Context context, final int strMsgId) {
		// final Context context = HFModesManager.getCurrentContext();
		if (context != null) {
			// if (CldModeUtils.isAppToBackground(context)) {
			// // 当前应用处于后台，停止执行
			// return;
			// }
			((Activity) context).runOnUiThread(new Runnable() {

				@Override
				public void run() {
					Toast.makeText(context, context.getString(strMsgId),
							Toast.LENGTH_LONG).show();
				}
			});
		}
	}

	/**
	 * 显示固定时长的Toast提示
	 * 
	 * @param context
	 * @param text
	 * @param duration
	 * @return void
	 * @author ChenP
	 * @date 2016年7月14日 下午5:23:52
	 */
	public static void showToast(Context context, String text, int duration) {

		if (toast != null) {
			toast.cancel();
			toast = null;
		}

		toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	/**
	 * 在指定位置显示指定时长的Toast提示
	 * 
	 * @param context
	 * @param text
	 * @param duration
	 * @param gravity
	 * @return void
	 * @author ChenP
	 * @date 2016年7月14日 下午5:25:24
	 */
	public static void showToast(Context context, String text, int duration,
			int gravity) {

		if (toast != null) {
			toast.cancel();
			toast = null;
		}

		toast = Toast.makeText(context, text, duration);

		switch (gravity) {

		case Gravity.TOP:// 椤堕儴灞呬腑鏄剧ず
		case Gravity.TOP | Gravity.CENTER_HORIZONTAL:
			toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 20);
			break;

		default:
			break;
		}

		toast.show();
	}

	/**
	 * 消除Toast提示
	 * 
	 * @return void
	 * @author ChenP
	 * @date 2016年7月14日 下午5:24:31
	 */
	public static void dismiss() {

		if (toast != null) {
			toast.cancel();
			toast = null;
		}
	}
}
