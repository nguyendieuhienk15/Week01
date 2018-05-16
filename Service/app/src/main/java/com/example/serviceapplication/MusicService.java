package com.example.serviceapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {

    MediaPlayer player;
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.shells);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }
}
