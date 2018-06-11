package com.tengxianzx.xu;

import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;

public class AboutA extends Base
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.abouta);
		ActionBar actionbar=getSupportActionBar();
		if(actionbar != null){
			actionbar.setDisplayHomeAsUpEnabled(true);
		}
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:   //返回键的id
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
