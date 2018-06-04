/*多线程任务，简单的android封装继承;
*使用方法：new.DownTask().execute();
*/
package com.xujingrong.example;
import android.os.AsyncTask;
import android.app.*;

public class DownloadTask extends AsyncTask<Void,Integer,Boolean>
{
	@Override
	protected void onPreExecute()
	{
		//显示进度对话框，下载开始前
		//ProgressDialog.show();
	}

	@Override
	protected Boolean doInBackground(Void... params)
	{
		//下载方法,耗时任务
		return true;
	}

	@Override
	protected void onProgressUpdate(Integer... values)
	{
		//下载进度条更新，UI
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Boolean result)
	{
		//下载完成后取消提示框，
		super.onPostExecute(result);
	}

	
}
