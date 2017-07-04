package com.example.esong02.swmsoftlofi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esong02 on 2017-06-27.
 */

public class FSListAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<String> objects;
    private String aType;

    public FSListAdapter(Context context, int resource,List<String> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }//end constructor

    public void setAssetType(String type){
        this.aType = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(resource,parent,false);

        final TextView title = (TextView) row.findViewById(R.id.lblListItem);
        TextView type = (TextView) row.findViewById(R.id.type);
        ImageButton inspect = (ImageButton)row.findViewById(R.id.inspectAction);
        ImageButton pInspect = (ImageButton)row.findViewById(R.id.pInspectAction);
        ImageButton infoBtn = (ImageButton)row.findViewById(R.id.infoAction);

        Log.d("Position",position + "");
        title.setText(objects.get(position));//prevent
        type.setText("");
        row.setBackgroundResource(R.drawable.info_background);
        row.setPadding(20,5,0,5);

        inspect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, InspectionActivity.class);
                intent.putExtra("Activity",title.getText());
                intent.putExtra("Asset Type",aType);
                context.startActivity(intent);
            }
        });

        pInspect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Listview of Past Inspections

                final Dialog alertDialog = new Dialog(context);
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.past_inspections);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                ImageButton cancelInfoBtn = (ImageButton)alertDialog.findViewById(R.id.cancelPastInspectionButton);
                cancelInfoBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Listview of Past Inspections

                final Dialog alertDialog = new Dialog(context);
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.asset_info_dialog);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                ImageButton cancelInfoBtn = (ImageButton)alertDialog.findViewById(R.id.cancelAssetInfoButton);
                cancelInfoBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        return row;
    }
}
