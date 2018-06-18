package com.tengxianzx.xu;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.content.Context;
import android.graphics.Color;

import java.util.List;

public class TravelAdapterl extends ArrayAdapter<Traver>
{
	private Context c;
    private int resourceId;
	private List<Traver> o;
	private static int i=0;
    public TravelAdapterl(Context context, int textViewResourceId,
						  List<Traver> objects)
	{
        super(context, textViewResourceId, objects);
		this.c = context;
        this.resourceId = textViewResourceId;
		this.o = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
	{
        Traver fruit = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null)
		{
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = view.findViewById(R.id.travelitemTextView1);
            viewHolder.fruitName = view.findViewById(R.id.travelitemTextView2);
			viewHolder.major = view.findViewById(R.id.travel_itemTextView3);
			viewHolder.trall = view.findViewById(R.id.travelitemLinearLayout1);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        }
		else
		{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.fruitImage.setText(fruit.getlid() + "");
        viewHolder.fruitName.setText(fruit.getName() + "");
		viewHolder.major.setText(fruit.getlcollege() + "  " + fruit.getmajor());
		switch (i)
		{
			case 0:
				viewHolder.trall.setBackgroundColor(Color.parseColor("#5566ffff"));
				i = 1;
				break;
			case 1:
				viewHolder.trall.setBackgroundColor(Color.parseColor("#5566ff66"));
				i = 2;
				break;
			case 2:
				viewHolder.trall.setBackgroundColor(Color.parseColor("#33ffcc00"));
				i = 3;
				break;
			case 3:
				viewHolder.trall.setBackgroundColor(Color.parseColor("#5500cc00"));
				i = 4;
				break;
			case 4:
				viewHolder.trall.setBackgroundColor(Color.parseColor("#5599ffff"));
				i = 5;
				break;
			case 5:
				viewHolder.trall.setBackgroundColor(Color.parseColor("#5500ff99"));
				i = 0;
				break;
			default:
		}
        return view;
    }

    class ViewHolder
	{
        TextView fruitImage,fruitName,major;
		LinearLayout trall;
    }

}
