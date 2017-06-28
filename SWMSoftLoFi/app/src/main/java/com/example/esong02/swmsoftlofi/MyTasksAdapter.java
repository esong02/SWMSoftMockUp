package com.example.esong02.swmsoftlofi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by esong02 on 2017-06-12.
 */

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
        //ImageView completeIcn = (ImageView) row.findViewById(R.id.completeIcon);


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
                //Intent intent = new Intent(context, InspectionActivity.class);
                //intent.putExtra("Activity",title.getText());
                //intent.putExtra("Asset Type","Facility");
            }
        });

        return row;
    }
}
