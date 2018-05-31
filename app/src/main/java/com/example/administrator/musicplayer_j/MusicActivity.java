package com.example.administrator.musicplayer_j;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView listView;
    private TextView info;
    private ImageButton play,pause,stop;
    //定义专辑图标数组
    private int[] musicImg  = {R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5};;
    //定义歌曲名称数组
    private String[] musicName = {"d1","d2","d3","d4","d5"};
    private String runingMusicName;
    private Intent operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_main);
        //初始化控件
        init();
        //初始化音乐信息
        musicInfoInit();
        //设置按钮点击事件
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent info = new Intent(MusicActivity.this,MusicInfo.class);
        info.putExtra("MusicName",runingMusicName);
        startActivity(info);
        return true;
    }

    public void init(){
        listView = (ListView) findViewById(R.id.musicListView);
        info = (TextView) findViewById(R.id.musicinfo);
        play = (ImageButton) findViewById(R.id.play);
        pause = (ImageButton) findViewById(R.id.pause);
        stop = (ImageButton) findViewById(R.id.stop);
    }

    public void musicInfoInit(){
        //初始化数据
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        int length = musicName.length;
        for(int i=0 ; i<length ; i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("music_imageView", musicImg[i]);
            map.put("music_title", musicName[i]);
            data.add(map);
        }
        //适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(MusicActivity.this,
                data, R.layout.music_item, new String[] { "music_imageView","music_title" },
                new int[] { R.id.music_imageView,R.id.music_title });
        listView.setAdapter(simpleAdapter);
        //为listview添加点击事件监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                runingMusicName = musicName[position];
                Toast.makeText(getApplicationContext(),"您已选定"+musicName[position]+"，请点击播放按钮！",Toast.LENGTH_SHORT).show();
                Log.i("111", "onItemClick: " + musicName[position]);
                info.setText("正在播放曲目："+musicName[position]);
            }
        });
    }

    @Override
    public void onClick(View v) {
        operation = new Intent(MusicActivity.this,MusicService.class);
        switch (v.getId()){
            case R.id.play:
                operation.putExtra("op","play");
                break;
            case R.id.pause:
                operation.putExtra("op","pause");
                break;
            case R.id.stop:
                operation.putExtra("op","stop");
                break;
        }
        operation.putExtra("name",runingMusicName);
        startService(operation);
    }
}
