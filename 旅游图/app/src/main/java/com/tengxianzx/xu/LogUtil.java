package com.tengxianzx.xu;

import android.util.Log;

public class LogUtil
{
	public static final int V=1;
	public static final int D=2;
	public static final int I=3;
	public static final int W=4;
	public static final int E=5;
	public static final int N=6;
	//修改l的值，改变Log
	public static final int l=V;
	
	public static void v(String tag,String msg){
		if(l<=V){
			Log.v(tag,msg);
		}
	}

	public static void d(String tag,String msg){
		if(l<=D){
			Log.d(tag,msg);
		}
	}

	public static void w(String tag,String msg){
		if(l<=W){
			Log.w(tag,msg);
		}
	}

	public static void i(String tag,String msg){
		if(l<=I){
			Log.i(tag,msg);
		}
	}

	public static void e(String tag,String msg){
		if(l<=E){
			Log.e(tag,msg);
		}
	}
}
