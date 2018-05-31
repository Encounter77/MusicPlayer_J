package com.example.administrator.musicplayer_j;


import java.util.ArrayList;
import java.util.List;

public class UserDataManager {
    private List<UserData> users = new ArrayList<UserData>();

    public UserDataManager() {
        users.add(0,new UserData("zhangsan","123"));
        users.add(1,new UserData("lisi","1234"));
        users.add(2,new UserData("wangwu","12345"));
        users.add(3,new UserData("zhaoliu","123456"));
        users.add(4,new UserData("guoqi","1234567"));
    }

    public List<UserData> getUsers() {
        return users;
    }

    public void setUsers(List users) {
        this.users = users;
    }

    public void addUser(UserData userData){
        users.add(userData);
    }
}
