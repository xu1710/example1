package com.xujingrong.example;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.content.Context;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.content.DialogInterface;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.widget.CheckBox;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.util.Log;
import android.app.PendingIntent;
import android.app.NotificationManager;
import android.app.Notification;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.media.MediaPlayer;
import java.io.File;
import android.os.Environment;
import android.widget.VideoView;
import android.widget.MediaController;

public class SecondA extends Base implements View.OnClickListener
{
	String sdata;
	String sreturn="sreturndata";
	private EditText sedit1;
	private MyDatabaseHelper dbHelper;
	private MediaPlayer mediaPlayer = new MediaPlayer();
	private VideoView videoView;
	private MediaController mediaController;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seconda);
		//隐藏标题栏
		ActionBar actionbar = getSupportActionBar();
        if (actionbar != null)
		{
            actionbar.hide();
		}
		//提取intent传过来的数据
		Intent sgetdata=getIntent();
		sdata = sgetdata.getStringExtra("extra");
		//log
		//Log.d("SecondA","hh");
		dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);
		//videoview实例化
		videoView=(VideoView) findViewById(R.id.secondaVideoView1);
		mediaController=new MediaController(this);
		videoView.setMediaController(mediaController);
		mediaController.setMediaPlayer(videoView);
		//edittext实例化
		sedit1 = (EditText) findViewById(R.id.secondaEditText1);
		//读取sharedpreference内容
		SharedPreferences sharedp= getSharedPreferences("sdata", MODE_PRIVATE);
		String es=sharedp.getString("name", "");
		sedit1.setText(es);
		//提示intent传过来的数据
		Button btns1=(Button) findViewById(R.id.secondaButton1);
		btns1.setOnClickListener(this);
		//ProgressDialog
		Button btns2=(Button) findViewById(R.id.secondaButton2);
		btns2.setOnClickListener(this);
		//AlertDialog
		Button btns3=(Button) findViewById(R.id.secondaButton3);
		btns3.setOnClickListener(this);
		//发送广播
		Button btns4=(Button) findViewById(R.id.secondaButton4);
		btns4.setOnClickListener(this);
		//使用sharedpreference保存数据
		Button btns5=(Button) findViewById(R.id.secondaButton5);
		btns5.setOnClickListener(this);
		//设置复选框checkbox
		CheckBox checkb1=(CheckBox) findViewById(R.id.secondaCheckBox1);
		checkb1.setChecked(true);
		if (checkb1.isChecked())
		{
			Toast.makeText(this, "cherk", Toast.LENGTH_SHORT).show();
		}
		//创建,删除数据库
		Button btns6=(Button) findViewById(R.id.secondaButton6);
		btns6.setOnClickListener(this);
		//添加数据
		Button btns7=(Button) findViewById(R.id.secondaButton7);
		btns7.setOnClickListener(this);
		//升级数据库
		Button btns8=(Button) findViewById(R.id.secondaButton8);
		btns8.setOnClickListener(this);
		//查询数据库
		Button btns9=(Button) findViewById(R.id.secondaButton9);
		btns9.setOnClickListener(this);
		//发送通知
		Button btns10=(Button) findViewById(R.id.secondaButton10);
		btns10.setOnClickListener(this);
		//播放音乐
		Button btns11=(Button) findViewById(R.id.secondaButton11);
		btns11.setOnClickListener(this);
		//暂停音乐
		Button btns12=(Button) findViewById(R.id.secondaButton12);
		btns12.setOnClickListener(this);
		//停止音乐
		Button btns13=(Button) findViewById(R.id.secondaButton13);
		btns13.setOnClickListener(this);
		//播放视频
		Button btns14=(Button) findViewById(R.id.secondaButton14);
		btns14.setOnClickListener(this);
		//暂停视频
		Button btns15=(Button) findViewById(R.id.secondaButton15);
		btns15.setOnClickListener(this);
		//停止视频
		Button btns16=(Button) findViewById(R.id.secondaButton16);
		btns16.setOnClickListener(this);
		//音乐播放准备
		initMediaPlayer();
		//视频播放准备
		initVideoPath();
	}
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.secondaButton1:
				Toast.makeText(this, sdata, Toast.LENGTH_SHORT).show();
				break;
			case R.id.secondaButton2:
				ProgressDialog progressDialog = new ProgressDialog(SecondA.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
				break;
			case R.id.secondaButton3:
				AlertDialog.Builder alertdialog=new AlertDialog.Builder(SecondA.this);
				alertdialog.setTitle("This is AlertDialog");
                alertdialog.setMessage("Loading...");
                alertdialog.setCancelable(true);
				alertdialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface p1, int p2)
						{}
					});
				alertdialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface p1, int p2)
						{}
					});
				alertdialog.show();
				break;
			case R.id.secondaButton4:
				Intent sb=new Intent("com.xujingrong.example.MY_BROADCAST");
				sendBroadcast(sb);//标准广播
				//sendOrderedBroadcast(sb,null);//有序广播
				//lacalboadcastm.registerReceiver(intent);//发送本地广播
				break;
			case R.id.secondaButton5:
				//EditText sedit2=(EditText) findViewById(R.id.secondaEditText1);
				String xu=sedit1.getText().toString();
				sedit1.getText().clear();
				SharedPreferences.Editor sharedpeditor=getSharedPreferences("sdata", MODE_PRIVATE).edit();
				sharedpeditor.putString("name", xu);
				sharedpeditor.apply();
				break;
			case R.id.secondaButton6:
				dbHelper.getWritableDatabase();
				//删除
				///SQLiteDatabase db = dbHelper.getWritableDatabase();
                ///db.delete("Book", "pages > ?", new String[] { "500" });
				break;
			case R.id.secondaButton7:
				SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // 开始组装第一条数据
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values); // 插入第一条数据
                values.clear();
                // 开始组装第二条数据
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values); // 插入第二条数据
				break;
			case R.id.secondaButton8:
				SQLiteDatabase db2 = dbHelper.getWritableDatabase();
                ContentValues values2 = new ContentValues();
                values2.put("price", 10.99);
                db2.update("Book", values2, "name = ?", new String[] { "The Da Vinci Code" });
				break;
			case R.id.secondaButton9:
				// 查询Book表中所有的数据
				SQLiteDatabase db3 = dbHelper.getWritableDatabase();
                Cursor cursor = db3.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        // 遍历Cursor对象，取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("SecondA", "book name is " + name);
                        Log.d("SecondA", "book author is " + author);
                        Log.d("SecondA", "book pages is " + pages);
                        Log.d("SecondA", "book price is " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
				break;
			case R.id.secondaButton10:
				Intent intent = new Intent(this, MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);//第二个参数用不到，第四个参数有四个值，通常为零
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//管理通知
                Notification notification = new NotificationCompat.Builder(this)
					.setContentTitle("This is content title")
					.setContentText("This is content text")
					.setWhen(System.currentTimeMillis())//显示时间
					.setSmallIcon(R.drawable.ic_launcher)
					.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
					.setContentIntent(pi)////点击逻辑
					//        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
					//        .setVibrate(new long[]{0, 1000, 1000, 1000})
					//        .setLights(Color.GREEN, 1000, 1000)
					.setDefaults(NotificationCompat.DEFAULT_ALL)//系统默认提示
					//        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))//长文字
					.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)))//大图
					.setPriority(NotificationCompat.PRIORITY_MAX)//重要指数max，high，low,min
					.setAutoCancel(true)//自动取消
					.build();
                manager.notify(1, notification);//执行
				//manager.cancel(1);//取消方式，写在oncreate里
                break;
			case R.id.secondaButton11:
				if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start(); // 开始播放
					}
				break;
			case R.id.secondaButton12:
				if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause(); // 暂停播放
					}
				break;
			case R.id.secondaButton13:
				if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset(); // 停止播放
                    initMediaPlayer();
                }
				break;
			case R.id.secondaButton14:
				if (!videoView.isPlaying()) {
                    videoView.start(); // 开始播放
				}
				break;
			case R.id.secondaButton15:
				if (videoView.isPlaying()) {
                    videoView.pause(); // 暂停播放
				}
				break;
			case R.id.secondaButton16:
				if (videoView.isPlaying()) {
                    videoView.resume();//重新开始
                }
				break;
			default:
				break;
		}
	}
	//重写返回键,实现intent的startactivityforresult的数据回传
	@Override
	public void onBackPressed()
	{
		Intent srtm=new Intent();
		srtm.putExtra("sreturndata", sreturn);
		setResult(RESULT_OK, srtm);
		finish();
		super.onBackPressed();
	}
	//音乐播放准备
	private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
            mediaPlayer.setDataSource(file.getPath()); // 指定音频文件的路径
            mediaPlayer.prepare(); // 让MediaPlayer进入到准备状态
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	//视频播放准备
	private void initVideoPath() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "movie.mp4");
            videoView.setVideoPath(file.getPath()); // 指定视频文件的路径
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	/*
	private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(), "movie.mp4");
        videoView.setVideoPath(file.getPath()); // 指定视频文件的路径
    }
	*/
	//其他活动启动此活动时方便数据传递
	public static void ActionStart(Context context, String date)
	{
		Intent intent=new Intent(context, SecondA.class);
		intent.putExtra("yy", date);
		context.startActivity(intent);
	}
	//重写销毁
	@Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
		if(videoView != null){
			videoView.suspend();
		}
    }
}
