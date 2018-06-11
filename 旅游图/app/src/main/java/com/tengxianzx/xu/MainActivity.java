package com.tengxianzx.xu;

import android.os.Bundle;
import org.litepal.tablemanager.Connector;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.RequestManager;
import android.view.View;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;
import android.support.annotation.NonNull;
import android.content.Intent;

public class MainActivity extends Base
{
	private long firstTime = 0;
	private ImageView miv1;
	private ImageView miv2;
	private DrawerLayout drawer;
	private NavigationView navigationView;
	private RequestManager glideRequest;
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
		glideRequest = Glide.with(this);
		glideRequest.load(R.drawable.mxianz)
			.transform(new GlideCircleTransform(this))
			.into(miv2);
		Glide.with(this)
			.load(R.drawable.mbanji)
			.transform(new GlideCircleTransform(this))
			.into(miv1);
		Connector.getDatabase();
		SAdd.adds();
		Log.d("main", "kk");
		initn();
    }
	
	//实例化控件
	public void init(){
		miv1 = (ImageView) findViewById(R.id.mainImageView1);
		miv2 = (ImageView) findViewById(R.id.mainImageView2);
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		navigationView = (NavigationView) findViewById(R.id.cehua);
	}
	
	//侧滑菜单点击事件
	public void initn(){
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

				@Override
				public boolean onNavigationItemSelected(MenuItem p1){
					switch(p1.getItemId()){
						case R.id.item1:
							//Intent mte=new Intent(MainActivity.this,Experience.class);
							//startActivity(mte);
							Toast.makeText(MainActivity.this,"敬请期待",Toast.LENGTH_SHORT).show();
							break;
						case R.id.item2:
							Intent mta=new Intent(MainActivity.this,AboutA.class);
							startActivity(mta);
							//Toast.makeText(MainActivity.this,"敬请期待",Toast.LENGTH_SHORT).show();
							break;
						default:
					}
					drawer.closeDrawer(GravityCompat.START);
					return true;
				}
			});
	}
	
	//成绩查询
	public void banjibtn(View p1){
		Toast.makeText(MainActivity.this,"banji",Toast.LENGTH_SHORT).show();
		Intent mtb1=new Intent(MainActivity.this,BanjiA.class);
		startActivity(mtb1);
	}
	
	//学校信息
	public void xianzbtn(View p1){
		Toast.makeText(MainActivity.this,"xianz",Toast.LENGTH_SHORT).show();
	}
	
	//双击退出
	@Override
    public void onBackPressed(){
        long secondTime = System.currentTimeMillis();
		if (secondTime - firstTime > 1500){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        }else{
            Acollector.finishall();
        }
	}

}
