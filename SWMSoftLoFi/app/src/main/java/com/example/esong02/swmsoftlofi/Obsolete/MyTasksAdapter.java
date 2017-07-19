package com.example.esong02.swmsoftlofi.Obsolete;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by esong02 on 2017-06-12.
 */

import com.example.esong02.swmsoftlofi.R;

public class MyTasksAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private MyTasks[] objects;

    public MyTasksAdapter(Context context, int resource, MyTasks[] objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }//end constructor

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(resource,parent,false);
        final TextView title = (TextView) row.findViewById(R.id.assetName);
        TextView locations = (TextView) row.findViewById(R.id.location);
        TextView distance = (TextView) row.findViewById(R.id.distance);

        if(objects[position].progress.equals("Complete")){
            row.setBackgroundResource(R.drawable.green_task);
        }else{
            row.setBackgroundResource(R.drawable.orange_task);
        }


        title.setText((CharSequence) objects[position].aName);
        locations.setText((CharSequence) objects[position].location);
        distance.setText(Integer.toString(objects[position].currentDistance));
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,title.getText() + "selected",Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }
}
