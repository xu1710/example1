package com.tengxianzx.xu;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.TextView;
import android.content.DialogInterface;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.ActionBar;

import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;
import junit.framework.Test;
import java.util.Collections;

public class BanjiA extends Base
{
	private List<Traver> travelList1=new ArrayList<>();
	private List<STravel> stravels;
	private ListView banjilst;
	private TravelAdapterl adp;
	private LinearLayout inflate,inflate1;
	private EditText usernameET;
	private AlertDialog.Builder builder,builder1;
	private int iii=0;
	private String spassw,passw,xm,dx,zy,cs,cj,xh,paixu="id asc";//desc
	private String [] sshengs,zhuanyess;
	private TextView a,b,c,d,e,f;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.banjia1);
		ActionBar actionbar=getSupportActionBar();
		if (actionbar != null)
		{
			actionbar.setDisplayHomeAsUpEnabled(true);
		}
		intis(paixu);
		list(travelList1);
		//Log.i("ii", "j");
	}
	//读取数据库
	public void intis(String px)
	{
		if (travelList1 != null)
		{
			travelList1.clear();
			iii = 0;
		}
		stravels = DataSupport.order(px).find(STravel.class);
		sshengs = new String[stravels.size()];
		zhuanyess = new String[stravels.size()];
		for (STravel stravel:stravels)
		{
			String shengs=stravel.getSsheng();
			String zhuanyes=stravel.getSmajor();
			sshengs[iii] = shengs;
			zhuanyess[iii] = zhuanyes;
			iii = iii + 1;
			Traver s=new Traver(stravel.getSname(), stravel.getSid(),
								stravel.getSdaxue(),
								zhuanyes, shengs,
								stravel.getId(), stravel.getSpassword(),
								stravel.getSchengji());
			travelList1.add(s);
		}
	}

	//设置listview
	public void list(List<Traver> l)
	{
		adp = new TravelAdapterl(BanjiA.this, R.layout.travel_item, l);
		banjilst = (ListView) findViewById(R.id.banjia1ListView1);
		banjilst.setAdapter(adp);
		banjilst.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					Traver t=travelList1.get(p3);
					spassw = t.getlpassw();
					xh = t.getlid();
					xm = t.getName();
					dx = t.getlcollege();
					zy = t.getmajor();
					cs = t.getlsheng();
					cj = t.getlchengji();
					if (!spassw.equals("_"))
					{
						queren();
					}
					else
					{
						xinxi();
					}
				}
			});
	}

	//映射菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.banji_menu, menu);
		return true;
    }

	//菜单选择事件,返回键
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.item1://筛选省
				sshengs = sqc(sshengs);
				ListAlbert(sshengs, "ssheng");
				break;
			case R.id.item2://筛选专业，
				zhuanyess = sqc(zhuanyess);
				ListAlbert(zhuanyess, "smajor");
				break;
			case R.id.itempx1:
				if (!paixu.equals("sid asc"))
				{
					paixu = "sid asc";
				}
				travelList1 = test1(travelList1, paixu);
				list(travelList1);
				break;
			case R.id.itempx2:
				if (!paixu.equals("schengji desc"))
				{
					paixu = "schengji desc";
				}
				travelList1 = test1(travelList1, paixu);
				list(travelList1);
				break;
			case R.id.item4:
				Acollector.finishall();
				break;
			case android.R.id.home:   //返回键的id
                this.finish();
                return false;
			default:
		}
		return true;
	}
	
	//信息框
	public void xinxi()
	{
		builder1 = new AlertDialog.Builder(this);
		inflate1 = (LinearLayout) getLayoutInflater().inflate(R.layout.xinxi_bj, null);
		builder1.setTitle("具体信息");
		builder1.setView(inflate1);//自定义的布局view
		a = inflate1.findViewById(R.id.xinxi_bjTextView1xh);
		b = inflate1.findViewById(R.id.xinxi_bjTextView2xm);
		c = inflate1.findViewById(R.id.xinxi_bjTextView3dx);
		d = inflate1.findViewById(R.id.xinxi_bjTextView4zy);
		e = inflate1.findViewById(R.id.xinxi_bjTextView5cs);
		f = inflate1.findViewById(R.id.xinxi_bjTextView6cj);
		a.setText("学号 " + xh);
		b.setText("姓名 " + xm);
		c.setText("大学 " + dx);
		d.setText("专业 " + zy);
		e.setText("城市 " + cs);
		f.setText("成绩 " + cj);
		builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which)
				{}
			});
		builder1.create().show();
	}
	
	//确认提示框
	public void queren()
	{
		builder = new AlertDialog.Builder(this);
		inflate = (LinearLayout) getLayoutInflater().inflate(R.layout.mima_al, null);
		builder.setTitle("请输入查询密码");
		builder.setCancelable(false);
		builder.setView(inflate);//自定义的布局view
		usernameET = inflate.findViewById(R.id.dialog_edit);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which)
				{
					passw = usernameET.getText().toString();
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(usernameET.getWindowToken(), 0);
					if (passw.equals(spassw))
					{
						xinxi();
					}
					else
					{
						Toast.makeText(BanjiA.this, "密码错误", Toast.LENGTH_LONG).show();
					}
				}
			});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which)
				{}
			});
		builder.create().show();
	}
	//筛选提示框
	public String ListAlbert(final String[] s, final String ss)
	{
		AlertDialog.Builder builder4;
		builder4 = new AlertDialog.Builder(this);
		builder4.setTitle("请选择一项");
		builder4.setCancelable(false);
		builder4.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which)
				{}
			});
		builder4.setItems(s, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					List<Traver> lasttraver = new ArrayList<>();
					if(lasttraver!=null){
						lasttraver.clear();
					}
					if(ss.equals("ssheng")){
						for(Traver t:travelList1){
							String a=t.getlsheng();
							if(a.equals(s[p2])){
								lasttraver.add(t);
							}
						}
					}
					if(ss.equals("smajor")){
						for(Traver t:travelList1){
							String a=t.getmajor();
							if(a.equals(s[p2])){
								lasttraver.add(t);
							}
						}
					}
					list(lasttraver);
				}
			});
		builder4.create().show();
		return null;
	}
	//数组去重
	public String [] sqc(String[] l)
	{
		Set<String> set = new HashSet<>();  
        for (int i=0;i < l.length;i++)
		{  
            set.add(l[i]);  
        }  
        return  set.toArray(new String[set.size()]);  
	}

	//排序
    public List<Traver> test1(List<Traver> list, final String s)
	{
        Collections.sort(list, new Comparator<Traver>() {
				@Override
				public int compare(Traver o1, Traver o2)
				{
					if (s.equals("schengji desc"))
					{
						if (Integer.parseInt(o1.getlchengji()) < Integer.parseInt(o2.getlchengji()))
						{
							return 1;
						}
						else if (Integer.parseInt(o1.getlchengji()) == Integer.parseInt(o2.getlchengji()))
						{
							return 0;
						}
						else
						{
							return -1;
						}
					}
					if(s.equals("sid asc")){
						if (Integer.parseInt(o1.getlid()) > Integer.parseInt(o2.getlid()))
						{
							return 1;
						}
						else if (Integer.parseInt(o1.getlid()) == Integer.parseInt(o2.getlid()))
						{
							return 0;
						}
						else
						{
							return -1;
						}
					}
					return -1;
				}
			});
		return list;
    }
}
