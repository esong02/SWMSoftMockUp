package com.example.esong02.swmsoftlofi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.HashMap;
import java.util.List;

import com.example.esong02.swmsoftlofi.Activities.InspectionActivity;
import com.example.esong02.swmsoftlofi.Models.*;
import com.example.esong02.swmsoftlofi.R;

/**
 * Created by esong02 on 2017-06-10.
 */

public class LIDListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<LID>> listHashMap;
    private ViewFlipper vf;
    private ViewFlipper ve;

    public LIDListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<LID>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group,null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        convertView.setBackgroundResource(R.drawable.info_background);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        //if (convertView == null){
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View row = inflater.inflate(R.layout.list_item_viewflipper,null);
            //convertView = inflater.inflate(R.layout.list_item_activity,null);
        //}

        LID lid = (LID) getChild(groupPosition,childPosition);
        //Initial List Item
        final TextView txtListChild = (TextView)row.findViewById(R.id.lblListItem);
        final TextView type = (TextView)row.findViewById(R.id.type);
        ImageButton inspect = (ImageButton)row.findViewById(R.id.inspectAction);
        ImageButton pInspect = (ImageButton)row.findViewById(R.id.pInspectAction);
        ImageButton infoBtn = (ImageButton)row.findViewById(R.id.infoAction);

        if (getChild(groupPosition,childPosition) instanceof LID){
            //LID lid = (LID) getChild(groupPosition,childPosition);
            txtListChild.setText(lid.getName());
            type.setText(lid.getType());
            row.findViewById(R.id.subLayer).setBackgroundResource(R.drawable.sub_info_background);
        }

        inspect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, InspectionActivity.class);
                intent.putExtra("Activity",txtListChild.getText());
                intent.putExtra("Asset Type","LID");
                context.startActivity(intent);
            }
        });


        //Initial List Item Buttons
        pInspect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ve = (ViewFlipper) row.findViewById(R.id.expandItem);
                ve.setInAnimation(context, R.anim.expand);
                ve.setOutAnimation(context, R.anim.collapse);
                ve.showNext();
            }
        });


        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vf = (ViewFlipper) row.findViewById(R.id.flipItem);
                vf.setInAnimation(context, R.anim.grow_from_middle);
                vf.setOutAnimation(context, R.anim.shrink_to_middle);
                vf.showNext();
            }
        });

        //Expanded List Item aka Past Inspection
        final TextView title2 = (TextView) row.findViewById(R.id.lblListItem2);
        final TextView type2 = (TextView) row.findViewById(R.id.type2);
        ImageButton inspect2 = (ImageButton)row.findViewById(R.id.inspectAction2);
        ImageButton cancelPIns = (ImageButton)row.findViewById(R.id.cancelAction);
        ImageButton info2 = (ImageButton)row.findViewById(R.id.infoAction2);

        if (getChild(groupPosition,childPosition) instanceof LID){
            title2.setText(lid.getName());
            type2.setText(lid.getType());
            row.findViewById(R.id.subLayer).setBackgroundResource(R.drawable.sub_info_background);
        }

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
                Intent intent = new Intent(context, InspectionActivity.class);
                intent.putExtra("Activity",title2.getText());
                intent.putExtra("Asset Type","LID");
                context.startActivity(intent);
            }
        });


        //Asset Info

        //Asset Info
        ImageButton backTActy = (ImageButton)row.findViewById(R.id.backToActivity);
        backTActy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vf = (ViewFlipper) row.findViewById(R.id.flipItem);
                vf.setInAnimation(context, R.anim.grow_from_middle);
                vf.setOutAnimation(context, R.anim.shrink_to_middle);
                vf.showPrevious();
            }
        });
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
        */
        return row;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}