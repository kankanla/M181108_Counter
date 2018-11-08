package com.kankanla.e560.m181108_counter;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class CounterMain extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "### CounterMain ###";
    private Button button1, button2, button3, button4;
    private SharedPreferences sharedPreferences;
    private Point point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_main);
        setActivity();
        button1 = findViewById(R.id.count1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.count2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.count3);
        button3.setOnClickListener(this);
        button4 = findViewById(R.id.count4);
        button4.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("Counter", MODE_PRIVATE);
    }

    private void showButtonText() {
        Log.d(TAG, "showButtonText");
        button1.setText(String.valueOf(sharedPreferences.getInt("counter1", 0)));
        button2.setText(String.valueOf(sharedPreferences.getInt("counter2", 0)));
        button3.setText(String.valueOf(sharedPreferences.getInt("counter3", 0)));
        button4.setText(String.valueOf(sharedPreferences.getInt("counter4", 0)));
    }

    private void Count(String key, Button button) {
        Log.d(TAG, "Count");
        int temp = sharedPreferences.getInt(key, 0) + 1;
        sharedPreferences.edit().putInt(key, temp).apply();
        button.setText(String.valueOf(temp));
    }

    private void setActivity() {
        Log.d(TAG, "setActivity");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        showButtonText();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();


    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown");
        if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
            return true;
        }
        if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyUp");
        if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
            Count("counter1", button1);
            return true;
        }
        if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
            Count("counter2", button2);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick");
        switch (v.getId()) {
            case R.id.count1:
                Count("counter1", button1);
                break;
            case R.id.count2:
                Count("counter2", button2);
                break;
            case R.id.count3:
                Count("counter3", button3);
                break;
            case R.id.count4:
                Count("counter4", button4);
                break;
        }
    }
}
