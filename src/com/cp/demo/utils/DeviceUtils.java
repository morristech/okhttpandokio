package com.cp.demo.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;

/**
 * 
 * �豸��صĹ�����
 * 
 * @author ChenP
 * @date 2016��8��10�� ����2:23:18
 */
public class DeviceUtils {

	private DeviceUtils() {
		throw new UnsupportedOperationException("u can't fuck me...");
	}

	/**
	 * ��ȡ�豸MAC��ַ
	 * 
	 * @param context
	 * @return
	 * @return String
	 * @author ChenP
	 * @date 2016��8��10�� ����2:23:28
	 */
	public static String getMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		String macAddress = info.getMacAddress().replace(":", "");
		return macAddress == null ? "" : macAddress;
	}

	/**
	 * ��ȡ�豸MAC��ַ(�����Ȩ�� android.permission.ACCESS_WIFI_STATE)
	 * 
	 * @return
	 * @return String
	 * @author ChenP
	 * @date 2016��8��10�� ����2:24:00
	 */
	public static String getMacAddress() {
		String macAddress = null;
		try {
			Process pp = Runtime.getRuntime().exec(
					"cat /sys/class/net/wlan0/address");
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader reader = new LineNumberReader(ir);
			macAddress = reader.readLine().replace(":", "");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return macAddress == null ? "" : macAddress;
	}

	/**
	 * ��ȡ�豸���̣���Xiaomi
	 * 
	 * @return
	 * @return String
	 * @author ChenP
	 * @date 2016��8��10�� ����2:24:21
	 */
	public static String getManufacturer() {
		String MANUFACTURER = Build.MANUFACTURER;
		return MANUFACTURER;
	}

	/**
	 * ��ȡ�豸�ͺţ���MI2SC
	 * 
	 * @return
	 * @return String
	 * @author ChenP
	 * @date 2016��8��10�� ����2:24:32
	 */
	public static String getModel() {
		String model = Build.MODEL;
		if (model != null) {
			model = model.trim().replaceAll("\\s*", "");
		} else {
			model = "";
		}
		return model;
	}

	/**
	 * ��ȡ�豸SD���Ƿ����
	 * 
	 * @return
	 * @return boolean
	 * @author ChenP
	 * @date 2016��8��10�� ����2:24:41
	 */
	public static boolean isSDCardEnable() {
		return Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState());
	}

	/**
	 * ��ȡ�豸SD��·��
	 * 
	 * @return
	 * @return String
	 * @author ChenP
	 * @date 2016��8��10�� ����2:24:51
	 */
	public static String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator;
	}
}
