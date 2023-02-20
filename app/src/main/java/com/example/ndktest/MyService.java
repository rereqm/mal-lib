package com.example.ndktest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.mylibrary.jjava;

public class MyService extends Service {
    private Thread thread;

    @Override
    public void onCreate() {
        super.onCreate();
        // Инициализация службы
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Запуск фонового потока
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(jjava.plus(1,2));
                // Выполнение долговременных операций
            }
        });
        thread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // Остановка фонового потока
        thread.interrupt();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}