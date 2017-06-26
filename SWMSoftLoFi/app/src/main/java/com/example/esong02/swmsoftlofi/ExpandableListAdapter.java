package com.example.esong02.swmsoftlofi;

/**
 * Created by esong02 on 2017-06-20.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by esong02 on 2017-06-10.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHashMap;
    private HashMap<String,HashMap<String,List<String>>> propertyList;



    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listHashMap) {
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
        //convertView.setBackgroundResource(R.drawable.property_header);
        convertView.setBackgroundResource(R.drawable.info_background);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String)getChild(groupPosition,childPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lst_item,null);

        }

        TextView txtListChild = (TextView)convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);


        if (childText.equals("Outlet 1") || childText.equals("Outlet 2")){
            convertView.setBackgroundResource(R.drawable.outlet);
        }else if(childText.equals("Spillway 1") || childText.equals("Spillway 2") || childText.equals("Spillway 3")){
            convertView.setBackgroundResource(R.drawable.spillway);
        }else if(childText.equals("Channel 1") || childText.equals("Channel 2") || childText.equals("Channel 3")){
            convertView.setBackgroundResource(R.drawable.swm_channel);
        }else if(childText.equals("Headwall 1")){
            convertView.setBackgroundResource(R.drawable.swm_headwall);
        }else if(childText.equals("Manhole 1") || childText.equals("Manhole 2")){
            convertView.setBackgroundResource(R.drawable.man_hole);
        }else if (childText.equals("Rain Garden 1") || childText.equals("Bioretention Cell 1") || childText.equals("Stormwater Planter 1")){
            convertView.setBackgroundResource(R.drawable.bioretention_background);
        }else if (childText.equals("Grid Paver 1")){
            convertView.setBackgroundResource(R.drawable.ppavement_background);
        }else if (childText.equals("Eco - Roof 1") || childText.equals("Roof Garden 1")){
            convertView.setBackgroundResource(R.drawable.greenroof_background);
        }else{
            //todo
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}