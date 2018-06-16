package com.tengxianzx.xu;

import android.app.Activity;

import java.util.List;
import java.util.ArrayList;

public class Acollector
{
	public static List<Activity> as=new ArrayList<>();
	//添加每个activity进来
	public static void adda(Activity a){
		as.add(a);
	}
	//移除activity
	public static void removea(Activity a){
		as.remove(a);
	}
	//结束每个activity
	public static void finishall(){
		for(Activity a:as){
			if(!a.isFinishing()){
				a.finish();
			}
		}
	}
}
