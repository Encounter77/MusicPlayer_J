package com.example.administrator.musicplayer_j;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MusicInfo extends AppCompatActivity {
    private String name;
    private TextView tv,lyc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_info);
        Intent intent = getIntent();
        tv = (TextView) findViewById(R.id.info);
        lyc = (TextView) findViewById(R.id.lyrics);
        name = intent.getStringExtra("MusicName");
        tv.setText("您正在收听的曲目是："+name);
        lyc.setText(R.string.d1lyr);
    }
}
