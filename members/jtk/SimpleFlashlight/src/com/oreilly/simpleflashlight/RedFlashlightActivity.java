package com.oreilly.simpleflashlight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RedFlashlightActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button = (Button) findViewById(R.id.green_button);
        button.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent(RedFlashlightActivity.this,
        				GreenFlashlightActivity.class);
        		startActivity(intent);
        	}
        });
    }
}