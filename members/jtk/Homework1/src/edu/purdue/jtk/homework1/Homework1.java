package edu.purdue.jtk.homework1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Homework1 extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button = (Button) findViewById(R.id.dial);
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
		        Intent intent = new Intent(Intent.ACTION_DIAL);
		        startActivity(intent);
			}
		});
    }
}