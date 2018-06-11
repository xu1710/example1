package com.xujingrong.example;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.net.Uri;
import android.widget.EditText;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.widget.ImageView;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.text.TextUtils;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.support.v4.content.ContextCompat;
import java.io.File;
import android.support.v4.content.FileProvider;
import android.os.Build;
import android.provider.MediaStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.database.Cursor;
import android.provider.DocumentsContract;
import android.content.ContentUris;
import android.annotation.TargetApi;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends Base
{
	private EditText editText0;
	long firstTime = 0;
	String mdata="mainextra";
	private ImageView imagev;
	private IntentFilter intentf;
	private NetworkChangeReceiver netcr;
	private Uri imageUri;
	private static final int TAKE_PHOTO=2;
	private static final int CHOOSE_PHOTO=3;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		//Log.d("MainActivity", "look");
		//恢复离开后被清除的内容
		if (savedInstanceState != null)
		{
			String s=savedInstanceState.getString("m_key");
			Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
		}
		//读取，设置EditText的字符串
		editText0 = (EditText) findViewById(R.id.mainEditText1);
		//editText0.clearFocus();
		String edits=load();//读取文件
		if(!TextUtils.isEmpty(edits)){
			editText0.setText(edits);
			editText0.setSelection(edits.length());//设置光标
			//editText0.clearFocus();
			Toast.makeText(this, "读取成功", Toast.LENGTH_SHORT).show();
		}
		//设置ImageView中的图片
		imagev=(ImageView) findViewById(R.id.mainImageView1);
		Glide.with(this)
			.load(R.drawable.ic_launcher)
			//.placeholder(R.drawable.loading)
			//.error(R.drawable.error)
			.diskCacheStrategy(DiskCacheStrategy.NONE)
			.override(100, 100)
			.into(imagev);
		//imagev.setImageResource(R.drawable.ic_launcher);
		//进度条可见与否
		/*
		 ProgressBar progressb=(ProgressBar) findViewById(R.id.mainProgressBar1);
		 progressb.setVisibility(View.VISIBLE);//可见
		 //progressb.setVisibility(View.GONE);//不可见
		 */
		//intent打开网页
		Button btnm1=(Button) findViewById(R.id.mainButton1);
		btnm1.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					Intent mti=new Intent(Intent.ACTION_VIEW);
					mti.setData(Uri.parse("https://www.baidu.com"));
					startActivity(mti);
					Toast.makeText(MainActivity.this, "mbtn1", Toast.LENGTH_SHORT).show();
				}
			});
		Button btnmbai=(Button) findViewById(R.id.mainButtonbai);
		btnmbai.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					Intent mtt=new Intent(MainActivity.this,ThirdA.class);
					startActivity(mtt);
					//Toast.makeText(MainActivity.this, "mbtn1", Toast.LENGTH_SHORT).show();
				}
			});
		//intent打开电话
		Button btnm2=(Button) findViewById(R.id.mainButton2);
		btnm2.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.
					permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
						ActivityCompat.requestPermissions(MainActivity.this,new 
						String[]{Manifest.permission.CALL_PHONE},1);
					}else{
						call();
					}
					/*
					Intent mti=new Intent(Intent.ACTION_DIAL);
					mti.setData(Uri.parse("tel:10086"));
					startActivity(mti);
					Toast.makeText(MainActivity.this, "mbtn2", Toast.LENGTH_SHORT).show();
					*/
				}
			});
		//文件形式保存
		Button btnm3=(Button) findViewById(R.id.mainButton3);
		btnm3.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					//读取editview内容并保存
					String edits=editText0.getText().toString();
					savee(edits);
					Toast.makeText(MainActivity.this, "文件可能保存成功", Toast.LENGTH_SHORT).show();
				}
			});
		//打开摄像头拍照
		Button btnm4=(Button) findViewById(R.id.mainButton4);
		btnm4.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					// 创建File对象，用于存储拍照后的图片
					File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
					try {
						if (outputImage.exists()) {
							outputImage.delete();
						}
						outputImage.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (Build.VERSION.SDK_INT < 24) {
						imageUri = Uri.fromFile(outputImage);
					} else {
						imageUri = FileProvider.getUriForFile(MainActivity.this, "com.example.cameraalbumtest.fileprovider", outputImage);
					}
					// 启动相机程序
					Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
					intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
					startActivityForResult(intent, TAKE_PHOTO);
					Toast.makeText(MainActivity.this, "paizhao", Toast.LENGTH_SHORT).show();
				}
			});

		//读取相册
		Button btnm5=(Button) findViewById(R.id.mainButton5);
		btnm5.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
						ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 2);
					} else {
						openAlbum();
					}
					Toast.makeText(MainActivity.this, "duquxc", Toast.LENGTH_SHORT).show();
				}
			});
		//建立网络变化广播监控
		intentf = new IntentFilter();
		intentf.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		netcr = new NetworkChangeReceiver();
		//LocalBroadcastManager lbm=LocalBroadcastManager.getInstance(this);//注册本地广播实例
		registerReceiver(netcr, intentf);
		//lbm.registerReceiver(netcr,intentf);//注册本地广播
    }
	//映射右上角菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	//添加右上角菜单点击事件
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.add:
				//intent的使用
				//Intent mts=new Intent(MainActivity.this,SecondA.class);
				Intent mts=new Intent("com.xujingrong.example.ACTION_START");
				mts.addCategory("android.intent.category.MYCATEGORY");
				mts.putExtra("extra", mdata);
				//startActivity(mts);
				startActivityForResult(mts, 1);
				//SecondA.ActionStart(MainActivity.this,"hh");
				break;
			case R.id.remove:
				Intent mtfo=new Intent(MainActivity.this,FourA.class);
				startActivity(mtfo);
				break;
			case R.id.five:
				Intent mtfi=new Intent(MainActivity.this,FiveA.class);
				startActivity(mtfi);
				break;
			case R.id.six:
				Intent mtsix=new Intent(MainActivity.this,SixA.class);
				startActivity(mtsix);
				break;
			default:
		}
		return true;
	}
	//startActivityForResult的回传数据读取，自动调用此函数
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode)
		{
			case 1:
				if (resultCode == RESULT_OK)
				{
					String returndata=data.getStringExtra("sreturndata");
					Toast.makeText(MainActivity.this, returndata, Toast.LENGTH_SHORT).show();
				}
				break;
			case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        imagev.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
			default:
				break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	//保存离开后被清除的activity的内容
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		// TODO: Implement this method
		super.onSaveInstanceState(outState);
		String s="你想恢复的内容";
		outState.putString("m_key", s);
	}
	//双击返回键退出
	@Override
    public void onBackPressed()
	{
        long secondTime = System.currentTimeMillis();

		if (secondTime - firstTime > 1500)
		{
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        }
		else
		{
            Acollector.finishall();
        }
	}
	//建立网络监控广播类
	class NetworkChangeReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context p1, Intent p2)
		{
			ConnectivityManager cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo nwi=cm.getActiveNetworkInfo();
			if (nwi != null && nwi.isAvailable())
			{
				Toast.makeText(MainActivity.this, "网络已连接", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(MainActivity.this, "网络已断开", Toast.LENGTH_SHORT).show();
			}
		}
	}
	//复写注销广播器
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		//读取editview内容并保存
		String edits=editText0.getText().toString();
		savee(edits);
		//注销广播
		unregisterReceiver(netcr);
		//lbm.unregisterReceiver(netcr);//注销本地广播
	}
	//文件格式保存数据
	public void savee(String s)
	{
		FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("mdata.txt", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	//文件格式读取数据
	public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("mdata.txt");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
	//直接打电话10086
	private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
	//判断是否有打电话，读取SD卡权限
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
			case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
				break;
            default:
        }
    }
	//打开相册
	private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
    }
	
	@TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imagev.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }
}
