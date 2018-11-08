package com.kankanla.e560.m181108_counter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class CounterMain2 extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "### CounterMain2 ###";
    private SharedPreferences sharedPreferences;
    private CounterService counterService;
    private Button button1, button2, button3, button4;
    private Point point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setService();
        setActivity();
//        setContentView(R.layout.activity_counter_main2);
        setContentView(R.layout.activity_counter_main);
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    private void Count(String key, Button button) {
        Log.d(TAG, "Count");
        int temp = sharedPreferences.getInt(key, 0) + 1;
        sharedPreferences.edit().putInt(key, temp).apply();
        button.setText(String.valueOf(temp));
    }

    private void showButtonText() {
        Log.d(TAG, "showButtonText");
        button1.setText(String.valueOf(sharedPreferences.getInt("counter1", 0)));
        button2.setText(String.valueOf(sharedPreferences.getInt("counter2", 0)));
        button3.setText(String.valueOf(sharedPreferences.getInt("counter3", 0)));
        button4.setText(String.valueOf(sharedPreferences.getInt("counter4", 0)));
    }

    @Override
    public void onClick(View v) {
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

    private void setActivity() {
        Log.d(TAG, "setActivity");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void setService() {
        Intent intent = new Intent(this, CounterService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                CounterService.ServiceBinder binder = (CounterService.ServiceBinder) service;
                counterService = binder.getServer();
                counterService.ServieDome();
                counterService.setContext(CounterMain2.this);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }
}
