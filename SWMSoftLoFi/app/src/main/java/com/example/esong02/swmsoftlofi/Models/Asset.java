package com.example.esong02.swmsoftlofi.Models;

/**
 * Created by esong02 on 2017-07-19.
 */

public class Asset {

    public String name;
    public String type;

    public Asset(String name, String type){
        this.name = name;
        this.type = type;
    }//end constructor

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return type;
    }
}
