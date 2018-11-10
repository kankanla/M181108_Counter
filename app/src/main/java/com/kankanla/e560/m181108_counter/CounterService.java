package com.kankanla.e560.m181108_counter;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class CounterService extends Service {
    private final String TAG = "### CounterService ####";
    private SharedPreferences sharedPreferences;
    private ServiceBinder serviceBinder = new ServiceBinder();
    private Context context;
    private BroadcastReceiver broadcastReceiver;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        sharedPreferences = getSharedPreferences("Counter", MODE_PRIVATE);
    }

    public void ServieDome() {
        Log.d(TAG, "ServieDome");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return serviceBinder;
    }

    public class ServiceBinder extends Binder {

        public CounterService getServer() {
            return CounterService.this;
        }
    }
}
