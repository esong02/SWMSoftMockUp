package com.example.esong02.swmsoftlofi.SampleFormOne.CardStackUI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.esong02.swmsoftlofi.Models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esong02 on 2017-07-19.
 */

public class CardStackAdapter extends FragmentStatePagerAdapter {

    private Item item; //Item reference
    private List<Item> itemList;
    private Bundle bundle = new Bundle();
    private FragmentManager fm;
    private List<Fragment> fragments = new ArrayList<>();

    public CardStackAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public Fragment getItem(int position) {
        Fragment cards = new CardStackFragment();

        fragments.add(cards);

        setBundle();
        cards.setArguments(bundle);
        //String tag = "p" + position;

        //fm.beginTransaction().add(cards,tag).commit();
        return cards;

    }

    public List<Fragment> getFragments(){
        return fragments;
    }

    //Pass Information to Card as Bundle here;
    public void setBundle(){
        bundle.clear();

        String description = item.getDescription();
        int rating = item.getRating();
        String comments = item.getComments();
        boolean hasPhoto = item.confirmPhoto();

        //Log.d("Position","Position: " + position); //fragment or page number
        Log.d("Name","Name: " + item.getName());
        Log.d("Description","Description" + item.getDescription());

        bundle.putString("Description", description);
        bundle.putInt("Rating", rating);
        bundle.putString("Comments", comments);
        bundle.putBoolean("HasPhoto", hasPhoto);

    }

    //will be changed to item list later on
    public void setItem(Item i){
        item = i;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
