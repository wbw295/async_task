package com.example.asynctask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Context mContext;
	private static final String TAG = "ASYNC_TASK";

	private Button execute;
	private Button cancel;

	private ProgressBar progressBar;
	private TextView textView;

	private MyTask myTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mContext = this;
		findView();
		setEvent();

	}

	private void findView() {
		execute = (Button) findViewById(R.id.execute);
		cancel = (Button) findViewById(R.id.cancel);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		textView = (TextView) findViewById(R.id.textView1);
	}

	private void setEvent() {
		execute.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				myTask = new MyTask();
				myTask.execute("http://www.baidu.com");
				execute.setEnabled(false);
				cancel.setEnabled(true);

			}
		});

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				if (myTask != null) {
					myTask.cancel(true);
				} else {

					Toast.makeText(mContext, "not init myTask",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class MyTask extends AsyncTask<String, Integer, String> {

		@Override
		protected void onPreExecute() {
			// TODO 自动生成的方法存根
			super.onPreExecute();
			Log.i(TAG, "onPreExecute() called");
			textView.setText("loading");
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO 自动生成的方法存根
			Log.i(TAG, "doInBackground(Params... params) called");

			try {
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(params[0]);
				HttpResponse response = client.execute(get);
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();
					long total = entity.getContentLength();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					int count = 0;
					int length = -1;
					while ((length = is.read(buf)) != -1) {
						baos.write(buf, 0, length);
						count += length;
						publishProgress((int) ((count / (float) total) * 100));
						Thread.sleep(500);
					}

					return new String(baos.toByteArray(), "gb2312");

				}

			} catch (ClientProtocolException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO 自动生成的方法存根
			super.onProgressUpdate(values);
			Log.i(TAG, "onProgressUpdate(Progress... progresses) called");
			progressBar.setProgress(values[0]);
			textView.setText("loading..." + values[0] + "%");
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO 自动生成的方法存根
			super.onPostExecute(result);
			Log.i(TAG, "onPostExecute(Result result) called");
			textView.setText(result);
			execute.setEnabled(true);
			cancel.setEnabled(false);
		}

		@Override
		protected void onCancelled() {
			// TODO 自动生成的方法存根
			super.onCancelled();
			Log.i(TAG, "onCancelled() called");
			progressBar.setProgress(0);
			execute.setEnabled(true);
			cancel.setEnabled(false);
		}
	}

}
