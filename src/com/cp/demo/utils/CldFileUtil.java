package com.cp.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.text.TextUtils;

/**
 * 
 * 文件读取工具类
 * 
 * @author ChenP
 * @date 2016年7月29日 上午11:48:49
 */
public class CldFileUtil {

	public static CldFileUtil mFileUtil;

	public static CldFileUtil getInstance() {
		if (mFileUtil == null) {
			mFileUtil = new CldFileUtil();
		}
		return mFileUtil;
	}

	/**
	 * 从文件中读取数据，转换为字节
	 * 
	 * @param file
	 * @return
	 * @return byte[]
	 * @author ChenP
	 * @date 2016年7月29日 上午11:50:50
	 */
	public byte[] fileToByte(File file) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}

	/**
	 * 从文件中读取数据，转换为字节
	 * 
	 * @param mPath
	 *            文件路径字符串
	 * @return
	 * @return byte[]
	 * @author ChenP
	 * @date 2016年7月29日 上午11:58:18
	 */
	public byte[] fileToByte(String mPath) {
		if (TextUtils.isEmpty(mPath)) {
			return null;
		}
		File file = new File(mPath);
		return fileToByte(file);
	}

	/**
	 * 删除文件
	 * 
	 * @param mPath
	 * @return
	 * @return boolean
	 * @author ChenP
	 * @date 2016年7月29日 下午12:02:53
	 */
	public boolean deleteFile(String mPath) {
		if (TextUtils.isEmpty(mPath)) {
			return false;
		}
		File file = new File(mPath);
		if (file.exists()) {// 删除文件
			file.delete();
		}
		return true;
	}

	/**
	 * 读取Assets中的文件内容
	 * 
	 * @param name
	 * @return
	 * @return String
	 * @author ChenP
	 * @date 2016年8月24日 下午5:41:30
	 */
	public String getAssetsContent(Activity mContext, String name) {
		String text = "";
		try {
			InputStream is = mContext.getAssets().open(name);
			int size = is.available();

			// Read the entire asset into a local byte buffer.
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			// Convert the buffer into a string.
			text = new String(buffer, "UTF-8");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (TextUtils.isEmpty(text)) {
			text = "读取文件失败啦，请检查你的路径是否正确";
		}
		return text;
	}

	/**
	 * 根据全路径获取文件名
	 * 
	 * @return
	 * @return String
	 * @author ChenP
	 * @date 2016年8月3日 下午6:31:51
	 */
	public String getFileName(String mPath) {
		if (TextUtils.isEmpty(mPath)) {
			return mPath;
		}
		if (!mPath.contains("/")) {
			return mPath;
		}
		List<String> mTypeList = CldStringUtil.getInstance().spltContent(mPath,
				"/");
		return mTypeList.get(mTypeList.size() - 1);
	}

}
