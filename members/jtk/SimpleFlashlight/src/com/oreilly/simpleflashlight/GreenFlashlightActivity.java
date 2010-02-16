package com.oreilly.simpleflashlight;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GreenFlashlightActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("TAG", "got here");
		setContentView(R.layout.green);
		
		Button button = (Button) findViewById(R.id.red_button);
		
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
}
