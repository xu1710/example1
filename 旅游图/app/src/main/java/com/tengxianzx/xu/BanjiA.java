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
//import android.support.v7.widget.;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.support.v7.widget.LinearLayoutManager;

public class BanjiA extends Base
{
	//private List<Traver> travelList = new ArrayList<Traver>();
	
	List<String> travelList=new ArrayList<String>();
	List<Traver> travelList1=new ArrayList<>();
	List<STravel> namedb=new ArrayList<STravel>();;
	List<STravel> stravels=new ArrayList<STravel>();;
	ListView banjilst;
	TravelAdapterl adp;
	String[] strArrayTrue;
	private String[] data={"f","d","g","de","fd","dfr","fdd","cfg","ldk"};
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.banjia1);
		ActionBar actionbar=getSupportActionBar();
		if(actionbar != null){
			actionbar.setDisplayHomeAsUpEnabled(true);
		}
		intis();
		//Log.d("hhh",data);
		adp=new TravelAdapterl(BanjiA.this,R.layout.travel_item,travelList1);
		//adp=new ArrayAdapter<String>(BanjiA.this,android.R.layout.simple_list_item_1,travelList);
		banjilst=(ListView) findViewById(R.id.banjia1ListView1);
		banjilst.setAdapter(adp);
		
		/*
		//获取实例
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.banjiarecy1);
		//指定recycleview布局方式为线性
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
		//适配器实例并适配
        TravelAdapter adapter = new TravelAdapter(travelList1);
        recyclerView.setAdapter(adapter);
		*/
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
			
			Traver s=new Traver(stravel.getSname(),stravel.getSid());
			travelList1.add(s);
			
			
			//travelList.add(stravel.getSname());
			//Log.d("mainn",stravel.getSname());
			//Log.d("maini",stravel.getSid()+"");
			
			
		}
		//namedb=DataSupport.select("sname").find(STravel.class);
	}
}
