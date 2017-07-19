package com.example.esong02.swmsoftlofi.Models;

import java.util.List;

/**
 * Created by esong02 on 2017-07-19.
 */

public class Asset {

    public String name;
    public String type;
    public List<Component> componentList;

    public Asset(String name, String type){
        this.name = name;
        this.type = type;
        //Populate Component List
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

    public int componentCount(){
        return componentList.size();
    }

    public List<Component> getComponents(){
        return componentList;
    }
}
