package com.xujingrong.example;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Button;
import android.os.IBinder;
import android.content.ServiceConnection;
import android.os.Message;
import android.content.ComponentName;
import android.content.Intent;

public class FiveA extends Base implements View.OnClickListener
{
	final static int UPDATA_EDIT=1;
	TextView feditt;
	
	private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }
    };
	
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			switch(msg.what){
				case UPDATA_EDIT:
					feditt.setText("kk");
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fivea);
		feditt=(TextView) findViewById(R.id.fiveaTextView1);
		//线程改UI
		Button btn1=(Button) findViewById(R.id.fiveaButton1);
		btn1.setOnClickListener(this);
		//打开服务
		Button btn2=(Button) findViewById(R.id.fiveaButton2);
		btn2.setOnClickListener(this);
		//关闭服务
		Button btn3=(Button) findViewById(R.id.fiveaButton3);
		btn3.setOnClickListener(this);
		//绑定服务
		Button btn4=(Button) findViewById(R.id.fiveaButton4);
		btn4.setOnClickListener(this);
		//解绑服务
		Button btn5=(Button) findViewById(R.id.fiveaButton5);
		btn5.setOnClickListener(this);
		//打开intentservice
		Button btn6=(Button) findViewById(R.id.fiveaButton6);
		btn6.setOnClickListener(this);
	}

	@Override
	public void onClick(View p1)
	{
		switch(p1.getId()){
			case R.id.fiveaButton1:
				new Thread(new Runnable(){
						@Override
						public void run()
						{
							Message msg=new Message();
							msg.what=UPDATA_EDIT;
							handler.sendMessage(msg);
						}
					}).start();
				break;
			case R.id.fiveaButton2:
				Intent starts=new Intent(this,MyService.class);
				startService(starts);
				break;
			case R.id.fiveaButton3:
				Intent stops=new Intent(this,MyService.class);
				stopService(stops);
				break;
			case R.id.fiveaButton4:
				Intent startis=new Intent(this,MyService.class);
				bindService(startis,connection,BIND_AUTO_CREATE);
				break;
			case R.id.fiveaButton5:
				unbindService(connection);
				break;
			case R.id.fiveaButton6:
				Intent startintents=new Intent(this,MyIntentService.class);
				startService(startintents);
				break;
			default:
				break;
		}
	}
}
