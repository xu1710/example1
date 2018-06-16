package com.tengxianzx.xu;

import android.os.Bundle;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;

public class Base extends AppCompatActivity
{
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
    }
	
	@Override
    protected void onPause()
	{
        super.onPause();
    }
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Acollector.removea(this);
	}
}
