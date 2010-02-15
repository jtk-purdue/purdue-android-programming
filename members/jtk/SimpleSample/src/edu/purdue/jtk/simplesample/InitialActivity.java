package edu.purdue.jtk.simplesample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class InitialActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toast.makeText(this, "hello there", Toast.LENGTH_LONG).show();
    }
}