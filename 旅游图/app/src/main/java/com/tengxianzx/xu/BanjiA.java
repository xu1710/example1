package com.tengxianzx.xu;

import android.os.Bundle;
import java.util.List;
import org.litepal.crud.DataSupport;
import android.util.Log;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import java.util.ArrayList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.*;

public class BanjiA extends Base
{
	private List<Traver> travelList = new ArrayList<Traver>();
	List<STravel> stravels;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.banjia);
		ActionBar actionbar=getSupportActionBar();
		if(actionbar != null){
			actionbar.setDisplayHomeAsUpEnabled(true);
		}
		intis();
		//获取实例
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.banjiarecy1);
		//指定recycleview布局方式为线性
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
		//适配器实例并适配
        TravelAdapter adapter = new TravelAdapter(travelList);
        recyclerView.setAdapter(adapter);
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
	
	public void intis(){
		stravels= DataSupport.findAll(STravel.class);
		for(STravel stravel:stravels){
			Traver apple = new Traver(stravel.getSname(),stravel.getSid());
            travelList.add(apple);
			Log.d("mainn",stravel.getSname());
			Log.d("maini",stravel.getSid()+"");
		}
	}
}
