package com.example.esong02.swmsoftlofi.Models;

/**
 * Created by esong02 on 2017-06-21.
 */

public class Item {

    private String name;
    private String description;
    private String comments;
    private int rating;
    private boolean hasPhoto;
    private boolean complete = false;
    //private ArrayList photoList;
    //private int photoIds;

    public Item(String name, String description, String comments, int rating, boolean hasPhoto){
        this.name= name;
        this.description = description;
        this.comments = comments;
        this.rating = rating;
        this.hasPhoto = hasPhoto;
    }//end constructor

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getComments(){
        return comments;
    }

    public void setComments(String comments){
        this.comments = comments;
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public boolean confirmPhoto(){
        return hasPhoto;
    }

    public void setPhoto(boolean hasPhoto){
        this.hasPhoto = hasPhoto;
    }

    public boolean isComplete(){ return complete; }

    public void setComplete(boolean c){ complete = c; }

}//end class
