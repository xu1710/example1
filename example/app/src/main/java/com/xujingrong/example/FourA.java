package com.xujingrong.example;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FourA extends Base implements View.OnClickListener
{
	TextView responseText;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.foura);
        Button sendRequest = (Button) findViewById(R.id.fouraButton1);
        responseText = (TextView) findViewById(R.id.fouraTextView1);
        sendRequest.setOnClickListener(this);
	}

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fouraButton1) {
			sendRequestWithHttpURLConnection();
            //sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithHttpURLConnection() {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
				@Override
				public void run() {
					HttpURLConnection connection = null;
					BufferedReader reader = null;
					try {
						URL url = new URL("http://www.baidu.com");
						connection = (HttpURLConnection) url.openConnection();
						connection.setRequestMethod("GET");
						connection.setConnectTimeout(8000);
						connection.setReadTimeout(8000);
						InputStream in = connection.getInputStream();
						// 下面对获取到的输入流进行读取
						reader = new BufferedReader(new InputStreamReader(in));
						StringBuilder response = new StringBuilder();
						String line;
						while ((line = reader.readLine()) != null) {
							response.append(line);
						}
						showResponse(response.toString());
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (reader != null) {
							try {
								reader.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if (connection != null) {
							connection.disconnect();
						}
					}
				}
			}).start();
    }
	private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// 在这里进行UI操作，将结果显示到界面上
					responseText.setText(response);
				}
			});
    }
}
