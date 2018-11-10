package com.kankanla.e560.m181108_counter;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "### MainActivity ###";
    private Button button1, button2;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivity();
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.mainbt1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.mainbt2);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainbt1:
                intent = new Intent(this, CounterMain.class);
                startActivity(intent);
                break;
            case R.id.mainbt2:
//                intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
                break;
        }

    }

    private void setActivity() {
        Log.d(TAG, "setActivity");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
