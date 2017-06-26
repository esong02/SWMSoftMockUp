package com.example.esong02.swmsoftlofi;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by esong02 on 2017-06-12.
 */

public class InspectionAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private ITasks[] objects;

    public InspectionAdapter(Context context, int resource, ITasks[] objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }//end constructor

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(resource,parent,false);
        TextView title = (TextView) row.findViewById(R.id.assetName);
        TextView progress = (TextView) row.findViewById(R.id.progressTrack);
        TextView locations = (TextView) row.findViewById(R.id.location);

        if(objects[position].progress.equals("Complete")){
            row.setBackgroundResource(R.drawable.green_task);
        }else{
            row.setBackgroundResource(R.drawable.orange_task);
        }

        title.setText((CharSequence)objects[position].aName);
        progress.setText((CharSequence) objects[position].progress);
        locations.setText((CharSequence) objects[position].location);
        return row;
    }
}
