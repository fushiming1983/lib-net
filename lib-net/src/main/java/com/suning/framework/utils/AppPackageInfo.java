package com.suning.framework.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.android.volley.BuildConfig;

/**
 * @author 13120678
 *
 * App的包信息
 */
public class AppPackageInfo {

	/**
	 * 设备的宽度
	 */
	private int mWidth;
	/**
	 * 设备的高度
	 */
	private int mHeight;
	/**
	 * 设备的密度
	 */
	private float mDensity;
	/**
	 * 包名
	 */
	private String mPackageName;
	/**
	 * 包名最后的名字  aaa.bbb.ccc (取ccc)
	 */
	private String mAppName;
	/**
	 * 应用UID
	 */
	private int mUid;
	/**
	 * 版本名
	 */
	private String mVersionName;
	/**
	 * 版本号
	 */
	private int mVersionCode;

	public AppPackageInfo(Context context) {
		// 获取设备的属性
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metric = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metric);
		mWidth = metric.widthPixels;
		mHeight = metric.heightPixels;
		mDensity = metric.density;

		mPackageName = context.getPackageName();
		mAppName = mPackageName.substring(mPackageName.lastIndexOf(".") + 1,mPackageName.length());


		// 获取版本号 版本名
		mVersionCode = BuildConfig.VERSION_CODE;
		mVersionName = BuildConfig.VERSION_NAME;

		// 获取应用UID
		PackageManager packageManager = context.getPackageManager();
		for(ApplicationInfo info : packageManager.getInstalledApplications(0)){
			int uid = info.uid;
			String packageName = info.packageName;
			if(packageName.equals(mPackageName)){
				mUid = uid;
				break;
			}
		}
	}


	public int getWidth() {
		return mWidth;
	}


	public void setWidth(int mWidth) {
		this.mWidth = mWidth;
	}


	public int getHeight() {
		return mHeight;
	}


	public void setHeight(int mHeight) {
		this.mHeight = mHeight;
	}


	public float getDensity() {
		return mDensity;
	}


	public void setDensity(float mDensity) {
		this.mDensity = mDensity;
	}


	public String getPackageName() {
		return mPackageName;
	}


	public void setPackageName(String mPackageName) {
		this.mPackageName = mPackageName;
	}


	public String getAppName() {
		return mAppName;
	}


	public void setAppName(String mAppName) {
		this.mAppName = mAppName;
	}


	public int getUid() {
		return mUid;
	}


	public void setUid(int mUid) {
		this.mUid = mUid;
	}


	public String getVersionName() {
		return mVersionName;
	}


	public void setVersionName(String mVersionName) {
		this.mVersionName = mVersionName;
	}


	public int getVersionCode() {
		return mVersionCode;
	}


	public void setVersionCode(int mVersionCode) {
		this.mVersionCode = mVersionCode;
	}

}
