package com.example.esong02.swmsoftlofi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.List;

import com.example.esong02.swmsoftlofi.SampleFormOne.IconInspectionForm;
import com.example.esong02.swmsoftlofi.R;

/**
 * Created by esong02 on 2017-06-27.
 */

public class FSListAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<String> objects;
    private String aType;
    private ViewFlipper vf;
    private ViewFlipper ve;

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
        final View row = inflater.inflate(resource,parent,false);

        //Initial List Item
        final TextView title = (TextView) row.findViewById(R.id.lblListItem);
        TextView type = (TextView) row.findViewById(R.id.type);
        ImageButton inspect = (ImageButton)row.findViewById(R.id.inspectAction);
        ImageButton pastInspect = (ImageButton)row.findViewById(R.id.pInspectAction);
        ImageButton info = (ImageButton)row.findViewById(R.id.infoAction);
        title.setText(objects.get(position));
        type.setText("");

        //Expanded List Item aka Past Inspection
        final TextView title2 = (TextView) row.findViewById(R.id.lblListItem2);
        TextView type2 = (TextView) row.findViewById(R.id.type2);
        ImageButton inspect2 = (ImageButton)row.findViewById(R.id.inspectAction2);
        ImageButton cancelPIns = (ImageButton)row.findViewById(R.id.cancelAction);
        ImageButton info2 = (ImageButton)row.findViewById(R.id.infoAction2);
        title2.setText(objects.get(position));
        type2.setText("");

        //Asset Info
        ImageButton backTActy = (ImageButton)row.findViewById(R.id.backToActivity);

        //Populate old list item
        //ImageButton pInspect = (ImageButton)row.findViewById(R.id.pInspectAction);
        //ImageButton infoBtn = (ImageButton)row.findViewById(R.id.infoAction);

        //Initial List Item Buttons
        pastInspect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ve = (ViewFlipper) row.findViewById(R.id.expandItem);
                ve.setInAnimation(context, R.anim.expand);
                ve.setOutAnimation(context, R.anim.collapse);
                ve.showNext();
            }
        });


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vf = (ViewFlipper) row.findViewById(R.id.flipItem);
                vf.setInAnimation(context, R.anim.grow_from_middle);
                vf.setOutAnimation(context, R.anim.shrink_to_middle);
                vf.showNext();
            }
        });


        inspect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Intent intent = new Intent(context, InspectionActivity.class);
                Intent intent = new Intent(context, IconInspectionForm.class);
                intent.putExtra("Activity",title.getText());
                intent.putExtra("Asset Type",aType);
                context.startActivity(intent);
            }
        });

        //Expanded List Item

        cancelPIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ve = (ViewFlipper) row.findViewById(R.id.expandItem);
                ve.setInAnimation(context, R.anim.expand);
                ve.setOutAnimation(context, R.anim.collapse);
                ve.showPrevious();
            }
        });


        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vf = (ViewFlipper) row.findViewById(R.id.flipItem);
                vf.setInAnimation(context, R.anim.grow_from_middle);
                vf.setOutAnimation(context, R.anim.shrink_to_middle);
                vf.showNext();
            }
        });


        inspect2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Intent intent = new Intent(context, InspectionActivity.class);

                Intent intent = new Intent(context, IconInspectionForm.class);
                intent.putExtra("Activity",title.getText());
                intent.putExtra("Asset Type",aType);
                context.startActivity(intent);
            }
        });


        //Asset Info

        backTActy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vf = (ViewFlipper) row.findViewById(R.id.flipItem);
                vf.setInAnimation(context, R.anim.grow_from_middle);
                vf.setOutAnimation(context, R.anim.shrink_to_middle);
                vf.showPrevious();
            }
        });


        //Old List Item

        /*
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
                //General Information

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
        */
        return row;
    }
}
