package com.tengxianzx.xu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TravelAdapterl extends ArrayAdapter<Traver> {

    private int resourceId;

    public TravelAdapterl(Context context, int textViewResourceId,
                        List<Traver> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Traver fruit = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (TextView) view.findViewById (R.id.travelitemTextView1);
            viewHolder.fruitName = (TextView) view.findViewById (R.id.travelitemTextView2);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.fruitImage.setText(fruit.Sid());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }

    class ViewHolder {

        TextView fruitImage;

        TextView fruitName;

    }

}
