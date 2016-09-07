package com.example.tryeventbus_simple;

import com.harvic.other.FirstEvent;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends Activity {
	private Button btn_FirstEvent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		btn_FirstEvent = (Button) findViewById(R.id.btn_first_event);

		btn_FirstEvent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EventBus.getDefault().post(
						new FirstEvent("FirstEvent btn clicked"));
			}
		});


	}

}
