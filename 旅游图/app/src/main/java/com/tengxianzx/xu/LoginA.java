package com.tengxianzx.xu;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.content.SharedPreferences;

public class LoginA extends Base implements View.OnClickListener
{
	int lpassw;
	String lpass="xu1510";
	Button lbtn1;
	EditText let1;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logina);
		lbtn1 = (Button) findViewById(R.id.loginaButton1);
		lbtn1.setOnClickListener(this);
		let1 = (EditText) findViewById(R.id.loginaEditText1);
	}

	@Override
	public void onClick(View p1)
	{
		switch (p1.getId())
		{
			case R.id.loginaButton1:
				if (let1.getText().toString().equals(lpass))
				{
					SharedPreferences sp1 = getSharedPreferences("login", MODE_PRIVATE);
					SharedPreferences.Editor e= sp1.edit();
					e.putInt("lpassw", 2);
					e.commit();
					Intent intent = new Intent();
					intent.setClass(LoginA.this, MainActivity.class);
					LoginA.this.startActivity(intent);
					LoginA.this.finish();
				}
				break;
			default:
		}
	}


}
