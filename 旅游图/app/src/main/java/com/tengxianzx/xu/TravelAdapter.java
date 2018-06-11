package com.tengxianzx.xu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.ViewHolder>
{

	private List<Traver> mFruitList;

	static class ViewHolder extends RecyclerView.ViewHolder
	{
		View fruitView;
		TextView fruitImage;
		TextView fruitName;

		public ViewHolder(View view)
		{
			super(view);
			fruitView = view;
			fruitImage = (TextView) view.findViewById(R.id.travelitemTextView1);
			fruitName = (TextView) view.findViewById(R.id.travelitemTextView2);
		}
	}

	public TravelAdapter(List<Traver> fruitList)
	{
		mFruitList = fruitList;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.travel_item, parent, false);
		final ViewHolder holder = new ViewHolder(view);
		holder.fruitView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					int position = holder.getAdapterPosition();
					Traver fruit = mFruitList.get(position);
					Toast.makeText(v.getContext(), "you clicked view " + fruit.getName(), Toast.LENGTH_SHORT).show();
				}
			});
		holder.fruitImage.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					int position = holder.getAdapterPosition();
					Traver fruit = mFruitList.get(position);
					Toast.makeText(v.getContext(), "you clicked image " + fruit.getName(), Toast.LENGTH_SHORT).show();
				}
			});
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position)
	{
		Traver fruit = mFruitList.get(position);
		holder.fruitImage.setText(fruit.Sid());
		holder.fruitName.setText(fruit.getName());
	}

	@Override
	public int getItemCount()
	{
		return mFruitList.size();
	}

}
