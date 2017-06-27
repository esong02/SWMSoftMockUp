package com.example.esong02.swmsoftlofi;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by esong02 on 2017-06-25.
 */

public class InspectionActivity extends AppCompatActivity{
    private ExpandableListView listView;
    private ItemListAdapter listAdapter;
    private List<String> componentHeader;
    private HashMap<String, List<Item>> listHash;
    private Context context;

    private CoordinatorLayout mCLayout;
    private FloatingActionButton mFAB;

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lid_form);

        // Set the support action bar
        mToolbar = (Toolbar) findViewById(R.id.inspectionBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Elm Drive");


        //public Item(String name, String description, String comments, int rating){

        listView = (ExpandableListView) findViewById(R.id.iListview);
        //View footerView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.inspection_footer, null, false);
        //listView.addFooterView(footerView);
        initData();
        listAdapter = new ItemListAdapter(InspectionActivity.this,componentHeader,listHash);
        listView.setAdapter(listAdapter);

    }


    private void initData(){
        listHash = new HashMap<>();
        componentHeader = new ArrayList<>();

        componentHeader.add("Contributing Drainage Area");//1
        componentHeader.add("Inlet");//2
        componentHeader.add("Pretreatment");//3
        componentHeader.add("Perimeter");//4
        componentHeader.add("Filter Bed");//5
        componentHeader.add("Vegetation");//6
        componentHeader.add("Overflow Outlets");//7
        componentHeader.add("Monitoring Well");//8

        List<Item> component1 = new ArrayList<>();//Contributing Drainage Area
        List<Item> component2 = new ArrayList<>();//Inlet
        List<Item> component3 = new ArrayList<>();//Pretreatment
        List<Item> component4 = new ArrayList<>();//Perimeter
        List<Item> component5 = new ArrayList<>();//Filter Bed
        List<Item> component6 = new ArrayList<>();//Vegetation
        List<Item> component7 = new ArrayList<>();//Overflowing Outlet
        List<Item> component8 = new ArrayList<>();//Monitoring Well


        //component1.add(new Item("Contributing Drainage Area",new String[]{"Check for Contributing Drainage Area Conditon","Check for Trash and Debris"}, "Not much trash and debris", 2));
        component1.add(new Item("Contributing Drainage Area","Check for Contributing Drainage Area Conditon", "", 0, false));
        component1.add(new Item("Contributing Drainage Area","Trash and Debris", "", 0, false));

        //component2.add(new Item("Inlet",new String[]{"Check for Structural Integrity","Check for Obstruction", "Check for Inlet Erosion","Check for Trash and Debris"},"Where is it",3));
        component2.add(new Item("Inlet","Inlet/Flow Spreader/Outlet Structural Integrity","",0, false));
        component2.add(new Item("Inlet","Inlet/Flow Spreader/Outlet Obstruction","",0, false));
        component2.add(new Item("Inlet","Inlet Erosion", "",0, false));
        component2.add(new Item("Inlet","Trash and Debris", "",0, false));

        //component3.add(new Item("Pretreatment",new String[]{"Check for Sediment Accumulation","Check for Trash and Debris"},  "",0));
        component3.add(new Item("Pretreatment","Check for Sediment Accumulation", "",0, false));
        component3.add(new Item("Pretreatment","Trash and Debris",  "",0, false));

        //component4.add(new Item("Perimeter",new String[]{"Check for Trash and Debris"}, "I'm lost and stranded in the middle of nowhere",1));
        component4.add(new Item("Perimeter","Trash and Debris", "",0, false));

        //component5.add(new Item("Filter Bed",new String[]{"Look for Standing Water","Check for Trash and Debris","Check for Filter Bed Erosion","Measure Mulch Depth","Filter Bed Sediment Accumulation","Filter Bed Surface Sinking","Check Dams","Sediment Accumulation Testing"}, "",0));
        component5.add(new Item("Filter Bed","Standing Water", "",0, false));
        component5.add(new Item("Filter Bed","Trash and Debris", "",0, false));
        component5.add(new Item("Filter Bed","Filter Bed Erosion", "",0, false));
        component5.add(new Item("Filter Bed","Mulch Depth", "",0, false));
        component5.add(new Item("Filter Bed","Filter Bed Sediment Accumulation", "",0, false));
        component5.add(new Item("Filter Bed","Filter Bed Surface Sinking", "",0, false));
        component5.add(new Item("Filter Bed","Check Dams", "",0, false));
        component5.add(new Item("Filter Bed","Sediment Accumulation Testing", "",0, false));

        //component6.add(new Item("Vegetation",new String[]{"Vegetation Cover","Vegetation Condition","Vegetation Composition"},"",0));
        component6.add(new Item("Vegetation","Vegetation Cover", "",0, false));
        component6.add(new Item("Vegetation","Vegetation Condition", "",0, false));
        component6.add(new Item("Vegetation","Vegetation Composition",  "",0, false));

        //component8.add(new Item("Overflow Outlets",new String[]{"Overflow Outlet Obstruction"}, "",0));
        component7.add(new Item("Overflow Outlets","Overflow Outlet Obstruction", "",0, false));

        //component7.add(new Item("Monitoring Well",new String[]{"Monitoring Well Condition"}, "",0));
        component8.add(new Item("Monitoring Well","Monitoring Well Condition", "",0, false));

        listHash.put(componentHeader.get(0),component1);
        listHash.put(componentHeader.get(1),component2);
        listHash.put(componentHeader.get(2),component3);
        listHash.put(componentHeader.get(3),component4);
        listHash.put(componentHeader.get(4),component5);
        listHash.put(componentHeader.get(5),component6);
        listHash.put(componentHeader.get(6),component7);
        listHash.put(componentHeader.get(7),component8);
    }
}
