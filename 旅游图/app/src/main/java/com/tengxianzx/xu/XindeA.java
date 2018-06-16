package com.tengxianzx.xu;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.app.ActionBar;

import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;

public class XindeA extends Base
{
	private TextView xdTextView1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xindea);
		ActionBar actionbar=getSupportActionBar();
		if (actionbar != null)
		{
			actionbar.setDisplayHomeAsUpEnabled(true);
		}
		xdTextView1 = (TextView) findViewById(R.id.xindeaTextView1);
		inputfile();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.xindea_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				this.finish();
				break;
			case R.id.xindea_item:
				Acollector.finishall();
				break;
			default:
		}
		return super.onOptionsItemSelected(item);
	}

	public void inputfile()
	{
		String string9 = "";
		String string8;
		InputStreamReader input;
		InputStream input1;
		BufferedReader buf;
		try
		{
			input1 = getResources().openRawResource(R.raw.xin);
			input = new InputStreamReader(input1, "UTF-8");
			buf = new BufferedReader(input);
			while ((string8 = buf.readLine()) != null)
			{
					string9 += string8;
					string9 += "\n";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		xdTextView1.setText(string9);
	}

}
