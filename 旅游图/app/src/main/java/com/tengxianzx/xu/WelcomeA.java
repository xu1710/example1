package com.tengxianzx.xu;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.content.Intent;
import android.content.SharedPreferences;

public class WelcomeA extends Base
{
	int passw;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (getActionBar() != null)
		{
			getActionBar().hide();
		}
		SharedPreferences sp=getSharedPreferences("login", MODE_PRIVATE);
		passw = sp.getInt("lpassw", 0);
		sp.getInt("shujuku", 0);
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
				@Override
				public void run()
				{
					if (passw == 0)
					{
						Intent intent = new Intent();
						intent.setClass(WelcomeA.this, LoginA.class);
						WelcomeA.this.startActivity(intent);
						WelcomeA.this.finish();
					}
					else
					{
						Intent intent = new Intent();
						intent.setClass(WelcomeA.this, MainActivity.class);
						WelcomeA.this.startActivity(intent);
						WelcomeA.this.finish();
					}
				}
			}, 500);
	}
}
