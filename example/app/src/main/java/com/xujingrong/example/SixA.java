/*oncreate里面交换注释即可转换list和recycleview

*/
package com.xujingrong.example;
import android.view.View;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.List;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.*;
import java.util.Random;

public class SixA extends Base// implements View.OnClickListener
{
	//private String[] data={"a","b","c"};//简单实用listview
	private List<Fruit> fruitList = new ArrayList<Fruit>();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sixa);
		ListView slist=(ListView) findViewById(R.id.sixaListView1);
		////简单使用listview
		////ArrayAdapter<String> adapter=new ArrayAdapter<String>(SixA.this,android.R.layout.simple_list_item_1,data);
		////slist.setAdapter(adapter);
		initFruits(); // 初始化水果数据
        FruitAdapter adapter = new FruitAdapter(SixA.this, R.layout.fruitadapter, fruitList);
        slist.setAdapter(adapter);
		//点击事件
        slist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
										int position, long id) {
					Fruit fruit = fruitList.get(position);
					Toast.makeText(SixA.this, fruit.getName(), Toast.LENGTH_SHORT).show();
				}
			});
		
		/*
		setContentView(R.layout.sixaa);
		initFruits(); // 初始化水果数据
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		//横向
		//
        //StaggeredGridLayoutManager layoutManager = new
		//	StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
		//
		//纵向
		LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter2 adapter = new FruitAdapter2(fruitList);
        recyclerView.setAdapter(adapter);
		*/
    }

    private void initFruits() {
        for (int i = 0; i < 3; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
            fruitList.add(mango);
			//横向
			/*
			Fruit mango = new Fruit(getRandomLengthName("Mango"), R.drawable.mango_pic);
            fruitList.add(mango);
			*/
        }
	}
	//横向
    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}
