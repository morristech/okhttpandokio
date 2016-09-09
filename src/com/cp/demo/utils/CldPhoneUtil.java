package com.cp.demo.utils;
///*
// * @Title CldPhoneCall.java
// * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
// * @author Zhucw
// * @date 2015年8月5日 上午11:15:58
// * @version 1.0
// */
//package com.cp.demo.api;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnCancelListener;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Handler;
//import android.text.TextUtils;
//import android.widget.Toast;
//import cnv.hf.widgets.HFModesManager;
//
//import com.cld.cm.util.RegexHelper;
//import com.cld.cm.util.control.CldPromptDialog;
//import com.cld.cm.util.control.CldPromptDialog.PromptDialogListener;
//import com.cld.device.CldPhoneManager;
//import com.cld.log.CldLog;
//
///**
// * 
// * 电话工具类
// * 
// * @author ChenP
// * @date 2016年7月14日 下午5:54:11
// */
//public class CldPhoneUtil {
//
//	/**
//	 * 拨打电话(直接拨打，没有弹出框)
//	 * 
//	 * @param context
//	 *            上下文
//	 * @param phoneStr
//	 *            可以是一组电话
//	 * @return void
//	 * @author ChenP
//	 * @date 2016年7月14日 下午5:54:26
//	 */
//	public static void makePhoneCalls(final Context context, String phoneStr) {
//
//		if (TextUtils.isEmpty(phoneStr) && context != null) {
//			Toast.makeText(context, "没有电话", Toast.LENGTH_SHORT).show();
//			return;
//		}
//
//		// 电话数组
//		String[] phones = null;
//		phones = RegexHelper.parsePhoneNumbers(phoneStr);
//
//		// 提取不到电话，直接返回原始电话信息
//		if (phones == null) {
//			phones = new String[] { phoneStr };
//		}
//
//		// 最后处理区号，比如在每个电话前都加区号（如果没有），并且去掉“-”分隔符
//		String regionStr = null;
//		for (int i = 0; i < phones.length; i++) {
//
//			// 获取区号
//			int regionIndex = phones[i].indexOf("-");
//			if (regionIndex != -1) {
//				// 如果有区号，保存该区号
//				regionStr = phones[i].substring(0, regionIndex);
//			}
//
//			if (phones[i].contains("-")) {
//				// 去掉-
//				phones[i] = phones[i].replaceAll("-", "");
//			} else {
//				if (regionStr != null) {
//					// 加上区号
//					phones[i] = regionStr + phones[i];
//				}
//			}
//		}
//
//		// 只有一个电话，直接拨打电话
//		if (phones.length == 1) {
//			makeAPhoneCall(context, phones[0]);
//		} else if (phones.length > 1) {
//			final String[] tels = phones;
//			AlertDialog mSelectTelDlg = null;
//			if (null == mSelectTelDlg) {
//				// 有多个电话，需要弹出列表框供用户选择
//				AlertDialog.Builder builder = new AlertDialog.Builder(context);
//				mSelectTelDlg = builder
//						.setCancelable(true)
//						.setTitle("请选择要拨打的电话")
//						.setItems(phones,
//								new DialogInterface.OnClickListener() {
//
//									@Override
//									public void onClick(DialogInterface dialog,
//											int which) {
//										// 返回对应选择的项
//										makeAPhoneCall(context, tels[which]);
//									}
//								}).setOnCancelListener(new OnCancelListener() {
//
//							@Override
//							public void onCancel(DialogInterface dialog) {
//							}
//						}).create();
//			}
//			mSelectTelDlg.setCanceledOnTouchOutside(true);
//			mSelectTelDlg.show();
//		}
//	}
//
//	/**
//	 * 拨打电话(弹出对话框确认)
//	 * 
//	 * @param mobileNum
//	 * @return void
//	 * @author ChenP
//	 * @date 2016年5月26日 上午11:30:49
//	 */
//	public static void callphone(final String mobileNum) {
//		if (TextUtils.isEmpty(mobileNum) || mobileNum.equals("暂无号码")) {
//			ToastDialog.showToast(HFModesManager.getContext(), "获取号码为空");
//			return;
//		}
//		if (!CldPhoneManager.isSimReady()) {// 判断是否有sim卡
//			ToastDialog.showToast(HFModesManager.getContext(), "无sim卡");
//			return;
//		}
//		CldPromptDialog.createPromptDialog(HFModesManager.getCurrentContext(),
//				"", CldPhoneUtil.PhoneNumberFormatter(mobileNum), "呼叫", "取消",
//				new PromptDialogListener() {
//
//					@Override
//					public void onSure() {
//						String phone = mobileNum;
//						if (!mobileNum.startsWith("tel:")) {
//							phone = "tel:" + mobileNum;
//						}
//						final String telString = phone;
//						/**
//						 * 拨打一键通电话意图
//						 */
//						new Handler().postDelayed(new Runnable() {
//
//							@Override
//							public void run() {
//								Intent callIntent = new Intent(
//										Intent.ACTION_CALL, Uri
//												.parse(telString));
//								Context mContext = HFModesManager
//										.getCurrentMode().getContext();
//								mContext.startActivity(callIntent);
//							}
//						}, 700);
//					}
//
//					@Override
//					public void onCancel() {
//
//					}
//				});
//	}
//
//	/**
//	 * 拨打电话(没有弹出框)
//	 * 
//	 * @param context
//	 * @param phoneStr
//	 *            单个电话号码
//	 * @return void
//	 * @author ChenP
//	 * @date 2016年7月14日 下午5:54:26
//	 */
//	public static void makeAPhoneCall(Context context, String phoneNumber) {
//		try {
//			if (phoneNumber.trim().length() == 0 && context != null) {
//				Toast.makeText(context, "电话号码是空的", Toast.LENGTH_SHORT).show();
//				return;
//			}
//
//			// 弹出拨号窗口
//			Intent myIntentDial = new Intent(Intent.ACTION_DIAL,
//					Uri.parse("tel:" + phoneNumber));
//			if (context != null) {
//				context.startActivity(myIntentDial);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 格式化电话号码为:"4-4-4"
//	 * 
//	 * @param number
//	 * @return
//	 * @return String
//	 * @author ChenP
//	 * @date 2016年7月14日 下午5:56:45
//	 */
//	public static String PhoneNumberFormatter(String number) {
//		CldLog.i("call_num", number);
//		StringBuffer buffer = new StringBuffer();
//		if (!TextUtils.isEmpty(number)) {
//			int len = number.length();// 字符串长度
//			// 如果电话号码大于9位数按规则添加“-”，小于10位直接显示
//			if (len > 9) {
//				String strArray[] = new String[len];
//				// 字符串转换成倒序数组
//				for (int i = len - 1; i >= 0; i--) {
//					strArray[len - 1 - i] = number.charAt(i) + "";
//				}
//
//				// 如果10位数电话号码"3-3-4"
//				if (len == 10) {
//					for (int i = 0; i < strArray.length; i++) {
//						buffer.append(strArray[i]);
//						if (i == 3 || i == 6) {
//							// 如果当前刚好3位，当前又是第一个不添加“-”
//							if (i != strArray.length - 1) {
//								buffer.append("-");
//							}
//						}
//					}
//				} else {// 11、12位 "x-4-4"
//					int indexNum = 1;
//					// 从电话号码后面开始添加一个“-”
//					for (int i = 0; i < strArray.length; i++) {
//						buffer.append(strArray[i]);
//						if (indexNum == 4) {
//							// 如果当前刚好四位，当前又是第一个不添加“-”
//							if (i != strArray.length - 1) {
//								buffer.append("-");
//							}
//							indexNum = 0;
//						}
//						indexNum++;
//					}
//				}
//				buffer.reverse();
//			} else {
//				buffer = new StringBuffer(number);
//			}
//		}
//		CldLog.i("call_num", buffer.toString());
//		return buffer.toString();
//	}
//
//}
