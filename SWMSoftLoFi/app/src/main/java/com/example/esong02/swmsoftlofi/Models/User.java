package com.example.esong02.swmsoftlofi.Models;

/**
 * Created by esong02 on 2017-06-25.
 */

public class User {
    private String name;
    private String pass;
    private boolean loggedIn;

    public User(String name, String pass, boolean loggedIn){
        this.name = name;
        this.pass = pass;
        this.loggedIn = loggedIn;
    }

    public void setLogin(boolean b){
        loggedIn = b;
    }

    public boolean getLogin(){
        return loggedIn;
    }

    public String getPass(){
        return pass;
    }

    public String getName(){
        return name;
    }
}
