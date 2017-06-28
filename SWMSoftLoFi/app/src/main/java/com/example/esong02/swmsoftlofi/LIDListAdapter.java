package com.example.esong02.swmsoftlofi;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by esong02 on 2017-06-10.
 */

public class LIDListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<LID>> listHashMap;

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

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lst_item,null);
        }

        final TextView txtListChild = (TextView)convertView.findViewById(R.id.lblListItem);
        TextView type = (TextView)convertView.findViewById(R.id.type);
        ImageButton inspect = (ImageButton)convertView.findViewById(R.id.inspectAction);
        ImageButton pInspect = (ImageButton)convertView.findViewById(R.id.pInspectAction);
        ImageButton infoBtn = (ImageButton)convertView.findViewById(R.id.infoAction);

        if (getChild(groupPosition,childPosition) instanceof LID){
            LID lid = (LID) getChild(groupPosition,childPosition);
            txtListChild.setText(lid.getName());
            type.setText(lid.getType());
            convertView.findViewById(R.id.subLayer).setBackgroundResource(R.drawable.sub_info_background);
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
        return convertView;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}