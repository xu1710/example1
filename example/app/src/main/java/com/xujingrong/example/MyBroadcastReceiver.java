//广播接收器
package com.xujingrong.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
		//为了简便实现代码，注释以下部分来实现强制下线功能
        //Toast.makeText(context, "received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
        //abortBroadcast();//截断广播
    }

}

