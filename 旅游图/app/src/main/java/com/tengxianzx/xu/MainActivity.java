package com.tengxianzx.xu;

import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ImageView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;

import org.litepal.tablemanager.Connector;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends Base
{
	private int shujuku;
	private long firstTime = 0;
	private String packName="com.tengxianzx.xu";
	private String DB_NAME="StudentTravel.db";
	private ImageView miv1,miv2;
	private DrawerLayout drawer;
	private RequestManager glideRequest;
	private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		setContentView(R.layout.cehua_all);
		Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar_w);
		setSupportActionBar(toolbar);
		ActionBar actionbar=getSupportActionBar();
		actionbar.setTitle("旅游图");
		init();
		SharedPreferences sp=getSharedPreferences("login", MODE_PRIVATE);
		shujuku = sp.getInt("shujuku", 0);
		if (shujuku == 0)
		{
			SharedPreferences sp1 = getSharedPreferences("login", MODE_PRIVATE);
			SharedPreferences.Editor e= sp1.edit();
			e.putInt("shujuku", 2);
			e.commit();
			//Connector.getDatabase();
			//SAdd.adds();
		}
		LogUtil.d("main", shujuku + "");
		initn();
		isexit();
    }

	//实例化控件
	public void init()
	{
		miv1 = (ImageView) findViewById(R.id.mainImageView1);
		miv2 = (ImageView) findViewById(R.id.mainImageView2);
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		navigationView = (NavigationView) findViewById(R.id.cehua);
		glideRequest = Glide.with(this);
		glideRequest.load(R.drawable.mxianz)
			.transform(new GlideCircleTransform(this))
			.into(miv2);
		Glide.with(this)
			.load(R.drawable.mbanji)
			.transform(new GlideCircleTransform(this))
			.into(miv1);
	}

	//侧滑菜单点击事件
	public void initn()
	{
		navigationView.setNavigationItemSelectedListener(new 
			NavigationView.OnNavigationItemSelectedListener(){
				@Override
				public boolean onNavigationItemSelected(MenuItem p1)
				{
					switch (p1.getItemId())
					{
						case R.id.item1:
							Intent mtx=new Intent(MainActivity.this, XindeA.class);
							startActivity(mtx);
							break;
						case R.id.item2:
							Intent mta=new Intent(MainActivity.this, AboutA.class);
							startActivity(mta);
							break;
						default:
					}
					drawer.closeDrawer(GravityCompat.START);
					return true;
				}
			});
	}

	//成绩查询
	public void banjibtn(View p1)
	{
		Intent mtb1=new Intent(MainActivity.this, BanjiA.class);
		startActivity(mtb1);
	}

	//学校信息
	public void xianzbtn(View p1)
	{
		Toast.makeText(MainActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
	}

	//双击退出
	@Override
    public void onBackPressed()
	{
        long secondTime = System.currentTimeMillis();
		if (secondTime - firstTime > 1500)
		{
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        }
		else
		{
            Acollector.finishall();
        }
	}
	//设置时间不能进入
	public void isexit()
	{
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int date=year * 1000 + month * 100 + day;
		if (date > 2018618)
		{
			Acollector.finishall();
		}
	}
}
