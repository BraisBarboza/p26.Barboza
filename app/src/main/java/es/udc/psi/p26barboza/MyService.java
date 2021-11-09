package es.udc.psi.p26barboza;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    int count;
    Thread countThread ;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        count = intent.getIntExtra(MainActivity.extra_key, 20);
        countThread= new CountThread(count);
        countThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        countThread.interrupt();
        super.onDestroy();
    }
}
class CountThread extends Thread {
    private final int count;
    CountThread(int count) {
        this.count = count;
    }
    public void run() {
        // tarea pesada
        for (int i = 0; i < count && !Thread.currentThread().isInterrupted(); i++) {
            Log.d("_TAG", "LocalService Final Count: " + count + " cuenta actual " + i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}