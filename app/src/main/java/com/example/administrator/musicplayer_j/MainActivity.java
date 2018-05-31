package com.example.administrator.musicplayer_j;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //成员变量
    private EditText my_userId = null;
    private EditText my_userPwd = null;
    private CheckBox my_remPwd = null;
    private TextView my_chanPwd = null;
    private Button my_loginButton = null;
    private Button my_registButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载主视图
        setContentView(R.layout.activity_main);
        //根据ID获取指定控件
        my_userId = (EditText) findViewById(R.id.login_edit_account);
        my_userPwd = (EditText) findViewById(R.id.login_edit_pwd);
        my_remPwd = (CheckBox) findViewById(R.id.login_Remember);
        my_chanPwd = (TextView) findViewById(R.id.login_change_pwd);
        my_loginButton = (Button) findViewById(R.id.login_btn_login);
        my_registButton = (Button) findViewById(R.id.login_btn_reg);

        //设置登录按钮点击事件
        my_loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(login_Check());
            }
        });
        //设置注册按钮点击事件
        my_registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UserRegist.class);
                startActivity(intent);
            }
        });
    }



    public void login(boolean checkResult){
        //创建intent对象,进行数据的传递
        Intent intent = new Intent();
        if(checkResult){
            //启动activity的设置,setClass(当前activity的context--.this,要打开activity的context--.class)
            intent.setClass(MainActivity.this, MusicActivity.class);
            //启动activity
            MainActivity.this.startActivity(intent);
        }else{
            //当登录失败时浮动显示信息
            Toast toast = Toast.makeText(getApplicationContext(),"您的账号或者密码有误,请重新输入！",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public boolean login_Check(){
        UserDataManager users = UserRegist.ReturnUsers();
        String userName_c = my_userId.getText().toString();
        String userPwd_c = my_userPwd.getText().toString();
        Log.i("1", "login_Check: " +userName_c + " "+ userPwd_c + " "+ users.getUsers().size());
        for(int i=0;i<users.getUsers().size();i++){
            if(users.getUsers().get(i).getUserName().equals(userName_c) && users.getUsers().get(i).getUserPwd().equals(userPwd_c)){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (((keyCode == KeyEvent.KEYCODE_BACK) ||
                (keyCode == KeyEvent.KEYCODE_HOME))
                && event.getRepeatCount() == 0) {
            dialog_Exit(MainActivity.this);
        }
        return false;
    }

    public static void dialog_Exit(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确定要退出吗?");
        builder.setTitle("提示");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        android.os.Process.killProcess(android.os.Process
                                .myPid());
                    }
                });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }
}
