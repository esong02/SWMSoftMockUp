package com.example.esong02.swmsoftlofi;

/**
 * Created by esong02 on 2017-06-26.
 */

public class Component{

    public String name;
    public String type;
    //public String description;
    //public String comments;
    //public int rating;
    //public boolean hasPhoto;
    //private ArrayList photoList;
    //private int photoIds;

    public Component(){
        super();
    }

    public Component(String name, String type){
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

}//end class
