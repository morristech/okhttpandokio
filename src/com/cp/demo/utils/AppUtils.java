package com.cp.demo.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;

/**
 * 
 * App��صĹ�����
 * 
 * @author ChenP
 * @date 2016��8��10�� ����2:18:47
 */
public class AppUtils {

	private AppUtils() {
		throw new UnsupportedOperationException("u can't fuck me...");
	}

	/**
	 * ��װApp
	 * 
	 * @param context
	 * @param filePath
	 * @return void
	 * @author ChenP
	 * @date 2016��8��10�� ����2:19:22
	 */
	public static void installApp(Context context, String filePath) {
		installApp(context, new File(filePath));
	}

	/**
	 * ��װApp
	 * 
	 * @param context
	 * @param file
	 * @return void
	 * @author ChenP
	 * @date 2016��8��10�� ����2:19:32
	 */
	public static void installApp(Context context, File file) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	/**
	 * ж��ָ�������App
	 * 
	 * @param context
	 * @param packageName
	 * @return void
	 * @author ChenP
	 * @date 2016��8��10�� ����2:19:43
	 */
	public void uninstallApp(Context context, String packageName) {
		Intent intent = new Intent(Intent.ACTION_DELETE);
		intent.setData(Uri.parse("package:" + packageName));
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	/**
	 * ��װApp��Ϣ��Bean��
	 */
	public static class AppInfo {

		private String name;
		private Drawable icon;
		private String packageName;
		private String versionName;
		private int versionCode;
		private boolean isSD;
		private boolean isUser;

		public Drawable getIcon() {
			return icon;
		}

		public void setIcon(Drawable icon) {
			this.icon = icon;
		}

		public boolean isSD() {
			return isSD;
		}

		public void setSD(boolean SD) {
			isSD = SD;
		}

		public boolean isUser() {
			return isUser;
		}

		public void setUser(boolean user) {
			isUser = user;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPackageName() {
			return packageName;
		}

		public void setPackageName(String packagName) {
			this.packageName = packagName;
		}

		public int getVersionCode() {
			return versionCode;
		}

		public void setVersionCode(int versionCode) {
			this.versionCode = versionCode;
		}

		public String getVersionName() {
			return versionName;
		}

		public void setVersionName(String versionName) {
			this.versionName = versionName;
		}

		/**
		 * @param name
		 *            ���
		 * @param icon
		 *            ͼ��
		 * @param packageName
		 *            ����
		 * @param versionName
		 *            �汾��
		 * @param versionCode
		 *            �汾Code
		 * @param isSD
		 *            �Ƿ�װ��SD��
		 * @param isUser
		 *            �Ƿ����û�����
		 */
		public AppInfo(String name, Drawable icon, String packageName,
				String versionName, int versionCode, boolean isSD,
				boolean isUser) {
			this.setName(name);
			this.setIcon(icon);
			this.setPackageName(packageName);
			this.setVersionName(versionName);
			this.setVersionCode(versionCode);
			this.setSD(isSD);
			this.setUser(isUser);
		}

		/*
		 * @Override public String toString() { return getName() + "\n" +
		 * getIcon() + "\n" + getPackagName() + "\n" + getVersionName() + "\n" +
		 * getVersionCode() + "\n" + isSD() + "\n" + isUser() + "\n"; }
		 */
	}

	/**
	 * ��ȡ��ǰApp��Ϣ
	 * 
	 * @param context
	 * @return
	 * @return AppInfo
	 * @author ChenP
	 * @date 2016��8��10�� ����2:20:20
	 */
	public static AppInfo getAppInfo(Context context) {
		PackageManager pm = context.getPackageManager();
		PackageInfo pi = null;
		try {
			pi = pm.getPackageInfo(context.getApplicationContext()
					.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return pi != null ? getBean(pm, pi) : null;
	}

	/**
	 * �õ�AppInfo��Bean
	 * 
	 * @param pm
	 * @param pi
	 * @return
	 * @return AppInfo
	 * @author ChenP
	 * @date 2016��8��10�� ����2:20:48
	 */
	private static AppInfo getBean(PackageManager pm, PackageInfo pi) {
		ApplicationInfo ai = pi.applicationInfo;
		String name = ai.loadLabel(pm).toString();
		Drawable icon = ai.loadIcon(pm);
		String packageName = pi.packageName;
		String versionName = pi.versionName;
		int versionCode = pi.versionCode;
		boolean isSD = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != ApplicationInfo.FLAG_SYSTEM;
		boolean isUser = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != ApplicationInfo.FLAG_SYSTEM;
		return new AppInfo(name, icon, packageName, versionName, versionCode,
				isSD, isUser);
	}

	/**
	 * ��ȡ�����Ѱ�װApp��Ϣ
	 * 
	 * @param context
	 * @return
	 * @return List<AppInfo>
	 * @author ChenP
	 * @date 2016��8��10�� ����2:21:04
	 */
	public static List<AppInfo> getAllAppsInfo(Context context) {
		List<AppInfo> list = new ArrayList<AppInfo>();
		PackageManager pm = context.getPackageManager();
		// ��ȡϵͳ�а�װ�����������Ϣ
		List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
		for (PackageInfo pi : installedPackages) {
			if (pi != null) {
				list.add(getBean(pm, pi));
			}
		}
		return list;
	}

	/**
	 * ��ݰ����ȡ��ͼ
	 * 
	 * @param context
	 * @param packageName
	 * @return
	 * @return Intent
	 * @author ChenP
	 * @date 2016��8��10�� ����2:21:21
	 */
	private static Intent getIntentByPackageName(Context context,
			String packageName) {
		return context.getPackageManager().getLaunchIntentForPackage(
				packageName);
	}

	/**
	 * ��ݰ����ж�App�Ƿ�װ
	 * 
	 * @param context
	 * @param packageName
	 * @return
	 * @return boolean
	 * @author ChenP
	 * @date 2016��8��10�� ����2:21:31
	 */
	public static boolean isInstallApp(Context context, String packageName) {
		return getIntentByPackageName(context, packageName) != null;
	}

	/**
	 * ��ָ�������App
	 * 
	 * @param context
	 * @param packageName
	 * @return
	 * @return boolean
	 * @author ChenP
	 * @date 2016��8��10�� ����2:21:41
	 */
	public static boolean openAppByPackageName(Context context,
			String packageName) {
		Intent intent = getIntentByPackageName(context, packageName);
		if (intent != null) {
			context.startActivity(intent);
			return true;
		}
		return false;
	}

	/**
	 * ��ָ�������AppӦ����Ϣ����
	 * 
	 * @param context
	 * @param packageName
	 * @return void
	 * @author ChenP
	 * @date 2016��8��10�� ����2:21:54
	 */
	public static void openAppInfo(Context context, String packageName) {
		Intent intent = new Intent();
		intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
		intent.setData(Uri.parse("package:" + packageName));
		context.startActivity(intent);
	}

	/**
	 * ��������App��Ϣ����
	 * 
	 * @param context
	 * @param info
	 * @return void
	 * @author ChenP
	 * @date 2016��8��10�� ����2:22:09
	 */
	public static void shareAppInfo(Context context, String info) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, info);
		context.startActivity(intent);
	}

	/**
	 * �жϵ�ǰApp����ǰ̨���Ǻ�̨
	 * 
	 * @param context
	 * @return
	 * @return boolean
	 * @author ChenP
	 * @date 2016��8��10�� ����2:22:19
	 */
	public static boolean isAppBackground(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		@SuppressWarnings("deprecation")
		List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}
		return false;
	}
}
