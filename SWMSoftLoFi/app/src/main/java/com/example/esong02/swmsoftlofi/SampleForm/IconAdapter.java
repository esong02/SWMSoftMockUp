package com.example.esong02.swmsoftlofi.SampleForm;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.esong02.swmsoftlofi.Models.Item;
import com.example.esong02.swmsoftlofi.R;
import com.example.esong02.swmsoftlofi.SampleForm.CardStackUI.CardStackAdapter;
import com.example.esong02.swmsoftlofi.SampleForm.CardStackUI.CardStackFragment;

import java.util.List;

/**
 * Created by esong02 on 2017-07-19.
 *
 * Functions by
 * 1. Splitting List into 3 Sub List
 * 2. Creating an Adapter for each Sub List (Sub List will populate elements in a row)
 * 3. Creating an Adapter for the parent list to hold each sub list (Parent List will populate Sub Lists as rows)
 *
 *
 *
 */

public class IconAdapter extends BaseAdapter {

    private Context context;
    private int resource;
    private List<String> objects;
    private List<Item> items;

    private String aType;
    private ViewFlipper vf;
    private ViewFlipper ve;

    private CardStackAdapter cardAdapter;
    private ViewPager cardPager;
    private View taskView;

    public IconAdapter(Context context, int resource,List<Item> objects) {
        super();
        this.context=context;
        this.resource=resource;
        this.items=objects;

    }//end constructor

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater= ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource,parent,false);

        }

        ImageButton imgBtn = (ImageButton) convertView.findViewById(R.id.componentIconButton);
        TextView mText = (TextView) convertView.findViewById(R.id.componentLabel);

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardAdapter.setItem(items.get(position));

                List<Fragment> fragments = cardAdapter.getFragments();

                //populate fragments

                for (Fragment frag : fragments){
                    CardStackFragment card = (CardStackFragment) frag;
                    card.descriptionTxt.setText(items.get(position).getDescription());
                    card.cText.setText(items.get(position).getComments());
                    card.rating = items.get(position).getRating();
                }

                //cardAdapter.notifyDataSetChanged();
                //cardPager.findViewWithTag(position);
                //Log.d("Pos","Item #" + position);
                taskView.setVisibility(View.VISIBLE);
                cardPager.setVisibility(View.VISIBLE);

                final ImageButton cancelBtn = (ImageButton) taskView.findViewById(R.id.cancelTaskButton);
                ImageButton completeBtn = (ImageButton) taskView.findViewById(R.id.completeTaskButton);

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //cardAdapter.setBundle();
                        //cardAdapter.notifyDataSetChanged();
                        taskView.setVisibility(View.GONE);
                        cardPager.setVisibility(View.GONE);
                    }
                });

                completeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        taskView.setVisibility(View.GONE);
                        cardPager.setVisibility(View.GONE);
                    }
                });

                Toast.makeText(context, "Selected "+ items.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        mText.setText(items.get(position).getName());

        return convertView;
    }

    //Non Override method


    public void setCardAdapter(CardStackAdapter cAdapter){
        //Set CardAdapter

        cardAdapter = cAdapter;

    }

    public void setCardPager(ViewPager pager){
        cardPager = pager;
    }


    public void setTaskView(View tV){
        taskView = tV;
    }

}
