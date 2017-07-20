package com.example.esong02.swmsoftlofi.SampleForm.CardStackUI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.esong02.swmsoftlofi.Models.Item;

import java.util.List;

/**
 * Created by esong02 on 2017-07-19.
 */

public class CardStackAdapter extends FragmentStatePagerAdapter {

    private Item items;
    private List<Item> itemList;
    private Bundle bundle = new Bundle();

    public CardStackAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position) {

        Fragment cards = new CardStackFragment();

        //Pass Information to Card as Bundle here;
        String description = items.getDescription();
        int rating = items.getRating();
        String comments = items.getComments();
        boolean hasPhoto = items.confirmPhoto();

        bundle.putString("Description", description);
        bundle.putInt("Rating", rating);
        bundle.putString("Comments", comments);
        bundle.putBoolean("HasPhoto", hasPhoto);

        cards.setArguments(bundle);

        return cards;

    }

    public void clearBundle(){
        bundle.clear();
    }

    public void setItem(Item i){
        items = i;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return 3;

    }

}
