package com.cp.demo.ui.util;

import java.lang.ref.WeakReference;

import android.os.Handler;

/**
 * 
 * 更新UI的Handler基类<br/>
 * 避免匿名自定义Handler，而导致的内存泄露<br/>
 * 使用时定义静态内部类，该类简化了弱引用的使用流程<br/>
 * 
 * @author ChenP
 * @date 2016年8月29日 上午11:04:32
 */
public class BaseUIHandler<T> extends Handler {

	/**
	 * UI界面对象的弱引用对象
	 */
	protected WeakReference<T> refInstance;

	/**
	 * 构造函数
	 *
	 * @param target
	 *            UI界面对象实例
	 */
	protected BaseUIHandler(T target) {
		refInstance = new WeakReference<T>(target);
	}

	/**
	 * 获取UI对象实例（根据弱引用对象获取，不会导致内存泄露）
	 * 
	 * @return
	 * @return T
	 * @author ChenP
	 * @date 2016年8月29日 上午11:05:22
	 */
	protected T get() {
		return refInstance.get();
	}
}
