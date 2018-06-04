package com.xujingrong.example;

import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class Base extends AppCompatActivity
{
	private ForceOfflineReceiver receiver;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Acollector.adda(this);
	}
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xujingrong.example.MY_BROADCAST");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);
    }
	@Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Acollector.removea(this);
	}
	class ForceOfflineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. Please try to login again.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Acollector.finishall(); // 销毁所有活动
						Intent intent = new Intent(context, MainActivity.class);
						context.startActivity(intent); // 重新启动LoginActivity
					}
				});
            builder.show();
        }

    }
	/*
	@Override
    public void onBackPressed() {
        //1.点击的时间差如果大于2000，则提示用户点击两次退出
        long mExitTime = 0;
		if (System.currentTimeMillis() - mExitTime > 2000)
		{
            //2.保存当前时间
            mExitTime  = System.currentTimeMillis();
            //3.提示
            showShortToast("再按一次退出");
        } else {
            //4.点击的时间差小于2000，调用父类onBackPressed方法执行退出。
            super.onBackPressed();
        }*/
    
}
