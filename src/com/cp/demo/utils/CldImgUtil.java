package com.cp.demo.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

public class CldImgUtil {

	public static CldImgUtil mImageUtil;

	public static CldImgUtil getInstance() {
		if (mImageUtil == null) {
			mImageUtil = new CldImgUtil();
		}
		return mImageUtil;
	}

	/**
	 * Drawable->Bitmap
	 * 
	 * @param drawable
	 * @return
	 * @return Bitmap
	 * @author ChenP
	 * @date 2016��7��29�� ����2:57:50
	 */
	public Bitmap drawableToBitmap(Drawable drawable) {
		// Bitmap bitmap = Bitmap
		// .createBitmap(
		// drawable.getIntrinsicWidth(),
		// drawable.getIntrinsicHeight(),
		// drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
		// : Bitmap.Config.RGB_565);
		// Canvas canvas = new Canvas(bitmap);
		// // canvas.setBitmap(bitmap);
		// drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
		// drawable.getIntrinsicHeight());
		// drawable.draw(canvas);
		BitmapDrawable bd = (BitmapDrawable) drawable;
		return bd.getBitmap();
	}

	/**
	 * ��ͼƬ���浽SD����
	 * 
	 * @param drawable
	 * @param mSavePath
	 * @return void
	 * @author ChenP
	 * @date 2016��7��29�� ����3:03:39
	 */
	public void saveToCard(Drawable drawable, String mSavePath) {
		if (drawable == null || TextUtils.isEmpty(mSavePath)) {
			return;
		}
		Bitmap mBitmap = drawableToBitmap(drawable);
		saveToCard(mBitmap, mSavePath);
	}

	/**
	 * ��ͼƬ���浽SD����
	 * 
	 * @param mBitmap
	 * @param mSavePath
	 * @return void
	 * @author ChenP
	 * @date 2016��7��29�� ����2:59:29
	 */
	public void saveToCard(Bitmap mBitmap, String mSavePath) {
		if (mBitmap == null || TextUtils.isEmpty(mSavePath)) {
			return;
		}
		File f = new File(mSavePath);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			mBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
