package com.tengxianzx.xu;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePalApplication;
import android.os.Environment;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyApplication extends Application
{
	private static Context context;
	@Override
	public void onCreate()
	{
		context = getApplicationContext();
		createDatabase();
		LitePalApplication.initialize(context);
	}

	public static Context getContext()
	{
		return context;
	}

	private void createDatabase()
	{
        final int BUFFER_SIZE = 1024 * 10;
        final String DB_NAME = "StudentTravel.db"; //保存的数据库文件名
        final String PACKAGE_NAME = "com.tengxianzx.xu";
        final String DB_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME;  //在手机里存放数据库的位置
        final String dbPath = DB_PATH + "/databases/";
        final String dbfile = dbPath + DB_NAME;
        try
		{
            if (!(new File(dbfile).exists()))
			{//判断数据库文件是否存在，若不存在则执行导入
                ///
                File filepath = new File(dbPath);
                if (!filepath.exists())
				{
                    filepath.mkdirs();
                }
                ///
                if (filepath.exists())
				{
                    InputStream is = context.getResources().openRawResource(
						R.raw.studenttravel); //欲导入的数据库
                    FileOutputStream fos = new FileOutputStream(dbfile);
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int count = 0;
                    while ((count = is.read(buffer)) > 0)
					{
                        fos.write(buffer, 0, count);
                    }
                    fos.close();
                    is.close();
                }
            }
        }
		catch (FileNotFoundException e)
		{
        }
		catch (IOException e)
		{
        }
    }
}
