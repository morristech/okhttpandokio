/*
 * @Title CldAnimationUtils.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author ChenP
 * @date 2015年9月23日 上午10:29:04
 * @version 1.0
 */
package com.cp.demo.utils;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * 动画工具类
 * 
 * @author ChenP
 * @date 2015年9月23日 上午10:29:04
 */
public class CldAnimUtil {

	public static final long TIME = 2000;// 转动一圈的默认时间

	/**
	 * 
	 * 旋转方式 正序 反序
	 * 
	 * @author ChenP
	 * @date 2015年9月23日 上午10:35:21
	 */
	public enum ORDERTYPE {
		POSITIVE, OPPOSITE;
	}

	/**
	 * 围绕自身旋转动画-只旋转一次
	 * 
	 * @param fromDegrees
	 *            开始旋转的角度
	 * @param toDegrees
	 *            旋转结束之后的角度
	 * @return
	 * @return RotateAnimation
	 * @author ChenP
	 * @date 2015年9月23日 上午11:30:09
	 */
	public static RotateAnimation loadRouteOnce(float fromDegrees,
			float toDegrees) {
		return loadRoute(fromDegrees, toDegrees, 0);
	}

	/**
	 * 围绕自身旋转动画-一直旋转
	 * 
	 * @param fromDegrees
	 *            开始旋转的角度
	 * @param toDegrees
	 *            旋转结束之后的角度
	 * @return
	 * @return RotateAnimation
	 * @author ChenP
	 * @date 2015年9月23日 上午11:30:09
	 */
	public static RotateAnimation loadRouteAlways(float fromDegrees,
			float toDegrees) {
		return loadRoute(fromDegrees, toDegrees, Animation.INFINITE);
	}

	/**
	 * 围绕自身旋转动画
	 * 
	 * @param fromDegrees
	 *            开始旋转的角度
	 * @param toDegrees
	 *            旋转结束之后的角度
	 * @param repeatCount
	 *            重复次数，如果一直循环，用Animation.INFINITE。
	 * @return
	 * @return RotateAnimation
	 * @author ChenP
	 * @date 2015年9月23日 上午11:30:09
	 */
	public static RotateAnimation loadRoute(float fromDegrees, float toDegrees,
			int repeatCount) {
		RotateAnimation gpsLocAni = null;
		gpsLocAni = new RotateAnimation(fromDegrees, toDegrees,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		long time = (long) (toDegrees - fromDegrees) * TIME / 360;
		gpsLocAni.setDuration(time);
		if (repeatCount < 0) {
			gpsLocAni.setRepeatCount(Animation.INFINITE);
		} else {
			gpsLocAni.setRepeatCount(repeatCount);
		}

		// gpsLocAni.setRepeatMode(Animation.RESTART);
		gpsLocAni.setFillAfter(true);
		gpsLocAni.setInterpolator(new LinearInterpolator());// 匀速转动
		return gpsLocAni;
	}

}
