package com.cp.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

/**
 * 
 * 字符串工具类
 * 
 * @author ChenP
 * @date 2016年8月1日 上午11:05:14
 */
public class CldStringUtil {

	public static CldStringUtil mCldStringUtil;

	public static CldStringUtil getInstance() {
		if (mCldStringUtil == null) {
			mCldStringUtil = new CldStringUtil();
		}
		return mCldStringUtil;
	}

	/**
	 * 格式化字符串
	 * 
	 * @param mForString
	 *            原有的字符串
	 * @param mSpltS
	 *            分割的字符(比如"/")
	 * @return
	 * @return List<String>
	 * @author ChenP
	 * @date 2016年8月3日 上午10:35:41
	 */
	public List<String> spltContent(String mForString, String mSpltS) {
		List<String> mList = new ArrayList<String>();
		if (TextUtils.isEmpty(mForString) || TextUtils.isEmpty(mSpltS)) {
			mList.add(mForString);
			return mList;
		}
		String[] mSString = mForString.split(mSpltS);
		for (String mS : mSString) {
			mList.add(mS);
		}
		return mList;
	}

	/**
	 * 正则表达式提取电话
	 * 
	 * @see 注意，只能提取这样的格式: 1.带区号的 ，区号可以带圆括号，（可同时带分机号），举例：(0755)-123-075。
	 *      2.不带区号的(即连续的数字 )，举例：13165464
	 * @param phoneString
	 *            电话字符串
	 */
	public String[] parsePhoneNumbers(String phoneString) {
		// 把(或)符号去掉
		phoneString = phoneString.replaceAll("\\(|\\)", "");
		// 获取区号
		Pattern pattern = Pattern
				.compile("(?<=^|\\D)\\(?(\\d{1,5})\\)?(?=\\-+\\d+)");
		Matcher matcher = pattern.matcher(phoneString);
		String regionNo = null;
		while (matcher.find()) {
			regionNo = matcher.group(1);
			if (!TextUtils.isEmpty(regionNo)) {
				// 找到区号，则结束寻找
				System.out.println("find region : " + regionNo);
				break;
			}
		}
		// 获取电话
		Pattern phonePattern = Pattern
				.compile("(?<!\\d|\\-)\\(?\\d{1,5}\\)?(\\-+\\d+)+(?!\\d|\\-)|(?<!\\d|\\-)\\d+(?!\\d|\\-)");
		Matcher phonematcher = phonePattern.matcher(phoneString);
		ArrayList<String> list = new ArrayList<String>();
		while (phonematcher.find()) {
			String tel = phonematcher.group(0);
			if (tel != null && tel.trim().length() > 0) {
				if (tel.contains("-")) {
					// 有区号，则去掉-分隔符
					tel = tel.replaceAll("-", "");
				}
				// 没有-分隔符，即没有区号
				else {

					// 如果之前已经解析到区号
					if (!TextUtils.isEmpty(regionNo)) {

						// 如果不是手机号码
						if (tel.trim().length() != 11) {
							// 在号码前加上区号
							tel = regionNo + tel;
						}
					}
				}

				list.add(tel);
			}
		}

		// 解析到电话号码
		if (list.size() > 0) {

			String[] phones = new String[list.size()];
			phones = list.toArray(phones);

			return phones;
		} else {
			System.out.println("no phone number!");
			return null;
		}
	}

	/**
	 * 判断输入的字符是否是汉字
	 * 
	 * @param a
	 * @return
	 * @return boolean
	 * @author ChenP
	 * @date 2016年8月5日 下午3:10:05
	 */
	public boolean isChinese(char a) {
		int v = (int) a;
		return (v >= 19968 && v <= 171941);
	}

	/**
	 * 判断输入的字符串是否是汉字
	 * 
	 * @param s
	 * @return
	 * @return boolean
	 * @author ChenP
	 * @date 2016年8月5日 下午3:10:16
	 */
	public boolean containsChinese(String s) {
		if (TextUtils.isEmpty(s))
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (isChinese(s.charAt(i)))
				return true;
		}
		return false;
	}

	/**
	 * 判断输入的字符串是否符合邮箱的格式
	 * 
	 * @Description TODO
	 * @param str
	 * @return
	 * @return boolean
	 */
	public boolean isEmailFormat(String str) {
		if (TextUtils.isEmpty(str))
			return false;

		// 电子邮件
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(str);
		boolean isMatched = matcher.matches();

		return isMatched;
	}

	/**
	 * 是否包含特殊字符
	 * 
	 * @param mContent
	 * @return
	 * @return boolean
	 * @author ChenP
	 * @date 2016年8月1日 上午11:09:37
	 */
	public boolean isContainSpecChar(String mContent) {
		String regx = "[ `~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(mContent);
		if (m.find()) {
			return true;
		} else {
			return false;
		}

	}

}
