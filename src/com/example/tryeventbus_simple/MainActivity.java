package com.example.tryeventbus_simple;

import com.harvic.other.FirstEvent;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btn;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		EventBus.getDefault().register(this);

		btn = (Button) findViewById(R.id.btn_try);
		tv = (TextView)findViewById(R.id.tv);

		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						SecondActivity.class);
				startActivity(intent);
			}
		});
	}

	public void onEventMainThread(FirstEvent event) {

		String msg = "onEventMainThread�յ�����Ϣ��" + event.getMsg();
		Log.d("harvic", msg);
		tv.setText(msg);
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
