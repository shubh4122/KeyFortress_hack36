package com.android.keyfortress_hack36.model;

public class Creds {
    String appImg, username, appName;
    String password;

    public Creds(){}

    public Creds(String appName, String username, String password) {
        this.appName = appName;
        this.username = username;
        this.password = password;
    }

//    public Creds(String appImg, String appName, String username, String password) {
//        this.appImg = appImg;
//        this.appName = appName;
//        this.username = username;
//        this.password = password;
//    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppImg() {
        return appImg;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setAppImg(String appImg) {
        this.appImg = appImg;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
