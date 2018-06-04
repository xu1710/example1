/*实现引用布局自带返回*/
package com.xujingrong.example;

import android.widget.LinearLayout;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;

public class TitleLayout extends LinearLayout
{public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        Button titleBack = (Button) findViewById(R.id.title_back);
        Button titleEdit = (Button) findViewById(R.id.title_edit);
        titleBack.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					((Activity) getContext()).finish();
				}
			});
        titleEdit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getContext(), "You clicked Edit button",
								   Toast.LENGTH_SHORT).show();
				}
			});
    }
}
