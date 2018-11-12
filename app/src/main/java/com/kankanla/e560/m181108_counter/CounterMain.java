package com.kankanla.e560.m181108_counter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.IOException;


public class CounterMain extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "### CounterMain ###";
    private Button button1, button2, button3, button4;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private SoundPool soundPool;
    private final String Shared_FILE_NAME = "Counter";
    private final String Shared_counter1 = "counter1";
    private final String Shared_counter2 = "counter2";
    private final String Shared_counter3 = "counter3";
    private final String Shared_counter4 = "counter4";
    private String Shared_SCREED = "SCREED_TOUCH";
    private String Shared_SOUND = "SCREED_SOND";
    private boolean BUTTON_TOUCH_TRUE = true;
    private boolean BUTTON_TOUCH_FALSE = false;
    private final int SCREEN_TOUCH_ON = 0;
    private final int SCREEN_TOUCH_OFF = 1;
    private final int TOUCH_SOUND_ON = 0;
    private final int TOUCH_SOUND_OFF = 1;
    private final int BUTTON_3_SHOW_MODE_1 = 0;
    private final int BUTTON_3_SHOW_MODE_2 = 1;
    private final int BUTTON_3_SHOW_MODE_3 = 2;
    private String BT3_MODE;
    private int c, d, e, f, cl;
    private Point point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_main);
        setActivity();
        setsharedPreferences();
        try {
            setSoundPool();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        button1 = findViewById(R.id.count1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.count2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.count3);
        button3.setOnClickListener(this);
        button4 = findViewById(R.id.count4);
        button4.setOnClickListener(this);
        setAPP();
        GoogleAdmob();
    }

    public void setAPP() {
        Log.d(TAG, "setAPP");
        if (sharedPreferences.getInt(Shared_SCREED, SCREEN_TOUCH_ON) != SCREEN_TOUCH_ON) {
            button1.setEnabled(this.BUTTON_TOUCH_FALSE);
            button2.setEnabled(this.BUTTON_TOUCH_FALSE);
        }
    }

    private void setsharedPreferences() {
        Log.d(TAG, "setsharedPreferences");
        sharedPreferences = getSharedPreferences(Shared_FILE_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        menu.add(1, 13, 1, R.string.COUNTER3_MODE_SET);
        menu.add(1, 11, 1, R.string.TOUCH_BOTTON_SET);
        menu.add(1, 12, 1, R.string.TOUCH_SOUND_SET);

        menu.add(2, 14, 2, R.string.COUNTER1_CLEAR);
        menu.add(2, 15, 2, R.string.COUNTER2_CLEAR);
        menu.add(2, 16, 2, R.string.COUNTER3_CLEAR);
        menu.add(2, 17, 2, R.string.COUNTER4_CLEAR);
        menu.add(3, 18, 2, R.string.About);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        switch (item.getItemId()) {
            case 11:
                if (SCREEN_TOUCH_OFF != sharedPreferences.getInt(Shared_SCREED, SCREEN_TOUCH_ON)) {
                    button1.setEnabled(this.BUTTON_TOUCH_FALSE);
                    button2.setEnabled(this.BUTTON_TOUCH_FALSE);
                    Toast.makeText(this, getString(R.string.TOUCH_BOTTON_TOAST_OFF), Toast.LENGTH_SHORT).show();
                    editor.putInt("SCREED_TOUCH", SCREEN_TOUCH_OFF).apply();
                } else {
                    button1.setEnabled(this.BUTTON_TOUCH_TRUE);
                    button2.setEnabled(this.BUTTON_TOUCH_TRUE);
                    Toast.makeText(this, getString(R.string.TOUCH_BOTTON_TOAST_ON), Toast.LENGTH_SHORT).show();
                    editor.putInt("SCREED_TOUCH", SCREEN_TOUCH_ON).apply();
                }
                break;
            case 12:
                if (TOUCH_SOUND_OFF != sharedPreferences.getInt(Shared_SOUND, TOUCH_SOUND_ON)) {
                    editor.putInt(Shared_SOUND, this.TOUCH_SOUND_OFF).apply();
                    Toast.makeText(this, getString(R.string.TOUCH_SOUND_OFF), Toast.LENGTH_SHORT).show();
                } else {
                    editor.putInt(Shared_SOUND, this.TOUCH_SOUND_ON).apply();
                    Toast.makeText(this, getString(R.string.TOUCH_SOUND_ON), Toast.LENGTH_SHORT).show();
                }
                break;
            case 13:
                int temp = sharedPreferences.getInt(BT3_MODE, 0);
                if (temp < 2) {
                    editor.putInt(BT3_MODE, temp + 1).apply();
                } else {
                    editor.putInt(BT3_MODE, 0).apply();
                }

                switch (temp) {
                    case 0:
                        Toast.makeText(this, getString(R.string.COUNTER1) + " - " + getString(R.string.COUNTER2), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(this, getString(R.string.COUNTER1) + " + " + getString(R.string.COUNTER2), Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(this, getString(R.string.COUNTER3), Toast.LENGTH_SHORT).show();
                        break;
                }
                showButtonText();
                break;
            case 14:
                Toast.makeText(this, getString(R.string.COUNTER_CLEAR), Toast.LENGTH_SHORT).show();
                editor.putInt(Shared_counter1, 0).apply();
                showButtonText();
                break;
            case 15:
                Toast.makeText(this, getString(R.string.COUNTER_CLEAR), Toast.LENGTH_SHORT).show();
                editor.putInt(Shared_counter2, 0).apply();
                showButtonText();
                break;
            case 16:
                Toast.makeText(this, getString(R.string.COUNTER_CLEAR), Toast.LENGTH_SHORT).show();
                editor.putInt(Shared_counter3, 0).apply();
                showButtonText();
                break;
            case 17:
                Toast.makeText(this, getString(R.string.COUNTER_CLEAR), Toast.LENGTH_SHORT).show();
                editor.putInt(Shared_counter4, 0).apply();
                showButtonText();
                break;

            case 18:
                AboutDialog aboutDialog = new AboutDialog(this);
                aboutDialog.show();
                break;
        }
        soundPool.play(cl, 0.5f, 0.5f, 0, 0, 1);
        soundPool.play(e, 0.5f, 0.5f, 0, 0, 1);
        return super.onOptionsItemSelected(item);
    }

    private void showButtonText() {
        Log.d(TAG, "showButtonText");
        button1.setText(String.valueOf(sharedPreferences.getInt(Shared_counter1, 0)));
        button2.setText(String.valueOf(sharedPreferences.getInt(Shared_counter2, 0)));
        button4.setText(String.valueOf(sharedPreferences.getInt(Shared_counter4, 0)));

        switch (sharedPreferences.getInt(BT3_MODE, BUTTON_3_SHOW_MODE_1)) {
            case BUTTON_3_SHOW_MODE_1:
                button3.setEnabled(this.BUTTON_TOUCH_TRUE);
                button3.setText(String.valueOf(sharedPreferences.getInt(Shared_counter3, 0)));
                break;
            case BUTTON_3_SHOW_MODE_2:
                button3.setEnabled(this.BUTTON_TOUCH_FALSE);
                button3.setText(String.valueOf(sharedPreferences.getInt(Shared_counter1, 0) - sharedPreferences.getInt(Shared_counter2, 0)));
                break;
            case BUTTON_3_SHOW_MODE_3:
                button3.setEnabled(this.BUTTON_TOUCH_FALSE);
                button3.setText(String.valueOf(sharedPreferences.getInt(Shared_counter1, 0) + sharedPreferences.getInt(Shared_counter2, 0)));
                break;
        }
    }

    private void setSoundPool() throws IOException {
        Log.d(TAG, "setSoundPool");
        AssetManager assetManager = getAssets();
        AudioAttributes audioAttributes = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setFlags(AudioAttributes.FLAG_HW_AV_SYNC)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(5)
                    .build();
        } else {
            soundPool = new SoundPool(5, AudioManager.STREAM_RING, 1);
        }
        c = soundPool.load(assetManager.openFd("C.mp3"), 0);
        d = soundPool.load(assetManager.openFd("D.mp3"), 0);
        e = soundPool.load(assetManager.openFd("E.mp3"), 0);
        f = soundPool.load(assetManager.openFd("F.mp3"), 0);
        cl = soundPool.load(assetManager.openFd("F.mp3"), 0);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(final SoundPool soundPool, int sampleId, int status) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        soundPool.play(c, 0.5f, 0.5f, 0, 0, 1);
                        soundPool.play(d, 0.5f, 0.5f, 0, 0, 1);
                        soundPool.play(e, 0.5f, 0.5f, 0, 0, 1);
                        soundPool.play(f, 0.5f, 0.5f, 0, 0, 1);
                    }
                }).start();
            }
        });
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    private void Count(String key, Button button) {
        Log.d(TAG, "Count");
        int temp = sharedPreferences.getInt(key, 0) + 1;
        sharedPreferences.edit().putInt(key, temp).apply();
        button.setText(String.valueOf(temp));
        if (sharedPreferences.getInt(this.Shared_SOUND, this.TOUCH_SOUND_ON) == TOUCH_SOUND_ON) {
            switch (key) {
                case "counter1":
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            soundPool.play(c, 1.0f, 1.0f, 1, 0, 1);
                        }
                    }).start();
                    break;
                case "counter2":
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            soundPool.play(e, 1.0f, 1.0f, 1, 0, 1);
                        }
                    }).start();
                    break;
                case "counter3":
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            soundPool.play(d, 1.0f, 1.0f, 1, 0, 1);
                        }
                    }).start();
                    break;
                case "counter4":
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            soundPool.play(f, 1.0f, 1.0f, 1, 0, 1);
                        }
                    }).start();
                    break;
            }
        }
    }

    private void setActivity() {
        Log.d(TAG, "setActivity");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Display display = getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart");
        showButtonText();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        showButtonText();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        soundPool.play(f, 1.0f, 1.0f, 1, 0, 1);
        soundPool.play(e, 1.0f, 1.0f, 1, 0, 1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        soundPool.release();
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
        if (button1.isEnabled() && button2.isEnabled()) {
            button1.setEnabled(this.BUTTON_TOUCH_FALSE);
            button2.setEnabled(this.BUTTON_TOUCH_FALSE);
            editor.putInt("SCREED_TOUCH", SCREEN_TOUCH_OFF).apply();
        }
        if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
            Count("counter1", button1);
            showButtonText();
            return true;
        }
        if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
            Count("counter2", button2);
            showButtonText();
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
                showButtonText();
                break;
            case R.id.count2:
                Count("counter2", button2);
                showButtonText();
                break;
            case R.id.count3:
                Count("counter3", button3);
                showButtonText();
                break;
            case R.id.count4:
                Count("counter4", button4);
                showButtonText();
                break;
        }
    }

    public class AboutDialog extends AlertDialog {
        private Context context;

        public AboutDialog(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            LinearLayout li = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.about_layout, null);
            TextView textView = li.findViewById(R.id.textView3);
            Linkify.addLinks(textView, Linkify.WEB_URLS);
            setContentView(li);
        }
    }


    protected void GoogleAdmob() {
        Log.d(TAG, "GoogleAdmob");
        MobileAds.initialize(this, getString(R.string.admob_app_id));
        AdView adView = new AdView(this);
        adView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black));

        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.admob_1));

        AdRequest.Builder adRequest = new AdRequest.Builder();
        adRequest.addTestDevice(getString(R.string.addTestDeviceH));
        adRequest.addTestDevice(getString(R.string.addTestDeviceASUS));
        adRequest.addTestDevice(getString(R.string.addTestDeviceMI));
        adView.loadAd(adRequest.build());

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        RelativeLayout layout = findViewById(R.id.rel_admob);
        layout.addView(adView, -1, layoutParams);
    }
}
