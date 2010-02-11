package edu.purdue.jtk.sampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class SampleApp extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toast.makeText(this, "hello there", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "more toast", Toast.LENGTH_SHORT).show();
    }
}