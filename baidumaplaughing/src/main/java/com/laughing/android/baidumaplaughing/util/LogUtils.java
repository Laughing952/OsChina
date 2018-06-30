package com.laughing.android.baidumaplaughing.util;

import android.util.Log;

public class LogUtils {
	//开关用来控制是否输出Log，app发布时 IS_DEBUG_VERSION = false
//	private static final boolean IS_DEBUG_VERSION = false;
	private static final boolean IS_DEBUG_VERSION = true;

	public static final void LOGI(String tag, String msg){
		if(IS_DEBUG_VERSION){
			Log.i(tag, msg);
		}
	}
	
	public static final void LOGW(String tag, String msg){
		if(IS_DEBUG_VERSION){
			Log.w(tag, msg);
		}
	}
	
	public static final void LOGE(String tag, String msg){
		if(IS_DEBUG_VERSION){
			Log.e(tag, msg);
		}
	}
	
	public static final void LOGV(String tag, String msg){
		if(IS_DEBUG_VERSION){
			Log.v(tag, msg);
		}
	}
	
	public static final void LOGD(String tag, String msg){
		if(IS_DEBUG_VERSION){
			Log.d(tag, msg);
		}
	}

}
