package com.example.esong02.swmsoftlofi.Models;

import java.util.List;

/**
 * Created by esong02 on 2017-07-19.
 */

public class Component {
    private String name;
    private String iconLink;
    private List<Item> tasks;
    private boolean isComplete;

    public Component(String n, List<Item> t){
        name = n;
        tasks = t;
    }

    public String getName(){
        return name;
    }

    public List<Item> getTasks(){
        return tasks;
    }

    public int taskCount(){
        return tasks.size();
    }

    public boolean confirmComplete(){
        return isComplete;
    }

}
