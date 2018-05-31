package com.example.administrator.musicplayer_j;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UserRegist extends AppCompatActivity {

    private EditText account,password,password_r;
    private Button submit,reset;
    private static UserDataManager users = new UserDataManager();


    public UserRegist(EditText account, EditText password, EditText password_r, Button submit, Button reset, UserDataManager users) {
        this.account = account;
        this.password = password;
        this.password_r = password_r;
        this.submit = submit;
        this.reset = reset;
        this.users = users;
    }

    public UserRegist() {
        UserDataManager users = new UserDataManager();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        //控件初始化
        init();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册信息核查
                if(regist_check(password.getText().toString(),password_r.getText().toString())){
                    UserData user = registUser(account.getText().toString(),password.getText().toString());
                    users.getUsers().add(user);
                    Log.i("res", "onClick: " + users.getUsers().size());
                    Toast.makeText(UserRegist.this,"注册成功，请返回主页面登录！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserRegist.this,"您两次输入的密码不一致，请重新输入！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account.setText("");
                password.setText("");
                password_r.setText("");
            }
        });
    }

    public void init(){
        account = (EditText) findViewById(R.id.regist_account);
        password = (EditText) findViewById(R.id.regist_pwd);
        password_r = (EditText) findViewById(R.id.regist_pwd_r);
        submit = (Button) findViewById(R.id.regist_submit);
        reset = (Button) findViewById(R.id.regist_reset);
    }

    public UserData registUser(String name,String pwd){
        return new UserData(name,pwd);
    }

    public boolean regist_check(String pwd,String pwd_r){
        if(pwd.equals(pwd_r)){
            return true;
        }
        return false;
    }

    public static UserDataManager ReturnUsers(){
        return users;
    }
}
