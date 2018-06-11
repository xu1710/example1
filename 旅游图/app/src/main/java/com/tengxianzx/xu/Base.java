package com.tengxianzx.xu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.IntentFilter;

public class Base extends AppCompatActivity
{

	//private ForceOfflineReceiver receiver;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Acollector.adda(this);
	}
    @Override
    protected void onResume()
	{
        super.onResume();
      /*  IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xujingrong.example.MY_BROADCAST");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);*/
    }
	@Override
    protected void onPause()
	{
        super.onPause();
       /* if (receiver != null)
		{
            unregisterReceiver(receiver);
            receiver = null;
        }*/
    }
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Acollector.removea(this);
	}
}
