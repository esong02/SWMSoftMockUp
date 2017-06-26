package com.example.esong02.swmsoftlofi;

/**
 * Created by esong02 on 2017-06-21.
 */

public class Item {

    public String name;
    public String description;
    public String comments;
    public int rating;
    public boolean hasPhoto;
    //private ArrayList photoList;
    //private int photoIds;

    public Item(){
        super();
    }

    public Item(String name, String description, String comments, int rating, boolean hasPhoto){
        this.name= name;
        this.description = description;
        this.comments = comments;
        this.rating = rating;
        this.hasPhoto = hasPhoto;
    }//end constructor


    private String getName(){
        return name;
    }

    private void setName(String name){
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

    public boolean getPhoto(){
        return hasPhoto;
    }

    public void setPhoto(boolean hasPhoto){
        this.hasPhoto = hasPhoto;
    }


}//end class
