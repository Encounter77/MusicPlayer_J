package com.example.administrator.musicplayer_j;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class MusicService extends Service {

    private MediaPlayer musicPlayer;
    private String onPlayMusicName;
    private String op;
    private String temp;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onPlayMusicName = intent.getStringExtra("name");
        op = intent.getStringExtra("op");
        Log.i("222", "onStartCommand: " + onPlayMusicName +" " +op);
        //点击列表歌曲快捷播放
        if(!onPlayMusicName.equals(temp)){
            if(musicPlayer != null){
                musicPlayer.stop();
                try {
                    musicPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            judgmentMusicName(onPlayMusicName);
        }
        temp = onPlayMusicName;
        operation(op);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    public void judgmentMusicName(String name){
        switch (name){
            case "d1" :
                musicPlayer = MediaPlayer.create(this,R.raw.d1);
                break;
            case "d2" :
                musicPlayer = MediaPlayer.create(this,R.raw.d2);
                break;
            case "d3" :
                musicPlayer = MediaPlayer.create(this,R.raw.d3);
                break;
            case "d4" :
                musicPlayer = MediaPlayer.create(this,R.raw.d4);
                break;
            case "d5" :
                musicPlayer = MediaPlayer.create(this,R.raw.d5);
                break;
        }
        musicPlayer.start();
    }


    public void stop(){
        Log.i("INFO","stop");
        if (musicPlayer!=null){
            musicPlayer.stop();
            try {
                musicPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void pause(){
        Log.i("INFO","pause");
        if (musicPlayer!=null&&musicPlayer.isPlaying())musicPlayer.pause();
    }

    private void play(){
        Log.i("INFO","play");
        if (!musicPlayer.isPlaying())musicPlayer.start();
    }


    public void operation(String op){
        if (op.equals("pause")) {
            pause();
        } else if (op.equals("stop")) {
            stop();
        } else  {
            play();
        }
    }
}
