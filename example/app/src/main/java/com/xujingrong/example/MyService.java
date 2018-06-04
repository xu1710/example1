package com.xujingrong.example;

import android.os.IBinder;
import android.content.Intent;
import android.app.Service;
import android.os.Binder;
import android.util.Log;
import android.app.PendingIntent;
import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.graphics.BitmapFactory;

public class MyService extends Service
{
	public MyService(){
	}
	private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {

        public void startDownload() {
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
	@Override
	public void onCreate()
	{
		super.onCreate();
		Log.d("MyService", "start");
		//前台服务，有通知栏
		Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
			.setContentTitle("This is content title")
			.setContentText("This is content text")
			.setWhen(System.currentTimeMillis())
			.setSmallIcon(R.drawable.ic_launcher)
			.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
			.setContentIntent(pi)
			.build();
        startForeground(1, notification);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.d("MyService", "command");
		//子线程中运行服务
		new Thread(new Runnable(){
				@Override
				public void run()
				{
					//逻辑
					stopSelf();//自停止
				}
			}).start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d("MyService", "ondestroy");
	}
	
}
