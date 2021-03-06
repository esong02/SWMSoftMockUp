package com.example.esong02.swmsoftlofi;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

    private Toolbar mToolbar;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inspection_form);

        Intent i= getIntent();
        Bundle b = i.getExtras();
        String title = "";
        String assetType = "";

        if (b != null)
        {
            title = (String) b.get("Activity");
            assetType = (String) b.get("Asset Type");
        }

        LinearLayout ll = (LinearLayout) findViewById(R.id.inspectionTopLayer);

        // Set the support action bar
        mToolbar = (Toolbar) findViewById(R.id.inspectionBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
        mToolbar.setTitleTextColor(Color.BLACK);

        listView = (ExpandableListView) findViewById(R.id.iListview);

        if (assetType.equals("LID")){
            lidData();
            ll.setBackgroundResource(R.color.light_blue);
        }else if (assetType.equals("Facility")){
            facilityData();
            ll.setBackgroundResource(R.color.orange_yellow);
        }else if (assetType.equals("Structure")){
            structureData();
            ll.setBackgroundResource(R.color.lime_green);
        }else{
            error();
        }

        //final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(InspectionActivity.this, android.R.layout.select_dialog_singlechoice);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(InspectionActivity.this, R.layout.spinner_item);
        arrayAdapter.add("Assumption Inspection");
        arrayAdapter.add("Routine Maintenance");
        arrayAdapter.add("Performance Verification");

        /*
        final Dialog dialog = new Dialog(InspectionActivity.this);
        dialog.setContentView(R.layout.inspection_type_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        ListView dialogList = (ListView) dialog.findViewById(R.id.inspectionTypeList);
        //final TextView iTypeHeader = (TextView) findViewById(R.id.ilabel);
        dialogList.setAdapter(arrayAdapter);
        dialogList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(InspectionActivity.this, "Selected: " + parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                //iTypeHeader.setText((String)parent.getItemAtPosition(position));
                dialog.dismiss();
            }
        });

        ImageButton cancelBtn = (ImageButton) dialog.findViewById(R.id.iTypeCancelButton);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        ImageButton iTypeSelect = (ImageButton) findViewById(R.id.iTypes);
        iTypeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(InspectionActivity.this);
                dialog.setContentView(R.layout.inspection_type_dialog);
                dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                ListView dialogList = (ListView) dialog.findViewById(R.id.inspectionTypeList);
                final TextView iTypeHeader = (TextView) findViewById(R.id.ilabel);
                dialogList.setAdapter(arrayAdapter);
                dialogList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(InspectionActivity.this, "Selected: " + parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                        iTypeHeader.setText((String)parent.getItemAtPosition(position));
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        */


        final Spinner iTypeDropDown = (Spinner) findViewById(R.id.iTypeSpinner);
        iTypeDropDown.setAdapter(arrayAdapter);
        //iTypeDropDown.setPopupBackgroundResource(R.color.transparent);
        iTypeDropDown.setPopupBackgroundResource(R.drawable.spinner_background);
        //iTypeDropDown.setElevation(5);
        iTypeDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(InspectionActivity.this, "Selected: " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listAdapter = new ItemListAdapter(InspectionActivity.this,componentHeader,listHash);
        listView.setAdapter(listAdapter);

    }

    private void error(){
        listHash = new HashMap<>();
        componentHeader = new ArrayList<>();
        componentHeader.add("ERROR");//1

        List<Item> component1 = new ArrayList<>();//Error

        component1.add(new Item("Error","Not Working", "", 0, false));

        listHash.put(componentHeader.get(0),component1);
    }

    private void structureData(){
        listHash = new HashMap<>();
        componentHeader = new ArrayList<>();

        componentHeader.add("Inlet");//1
        componentHeader.add("Outlet");//2
        componentHeader.add("Structure");//3

        List<Item> component1 = new ArrayList<>();//Inlet
        List<Item> component2 = new ArrayList<>();//Outlet
        List<Item> component3 = new ArrayList<>();//Structure

        component1.add(new Item("Inlet","No obstruction/debris, standing water or sediment accumulation", "", 0, false));
        component2.add(new Item("Outlet","No obstruction/debris, standing water or sediment acumulation", "", 0, false));
        component3.add(new Item("Structure"," ","",0, false));

        listHash.put(componentHeader.get(0),component1);
        listHash.put(componentHeader.get(1),component2);
        listHash.put(componentHeader.get(2),component3);
    }

    private void facilityData(){
        listHash = new HashMap<>();
        componentHeader = new ArrayList<>();

        componentHeader.add("Bench Mark");//1
        componentHeader.add("Emergency Spillway");//2
        componentHeader.add("Emergency Spillway: Grating");//3
        componentHeader.add("Facility");//4
        componentHeader.add("Fence");//5
        componentHeader.add("Inlet Channel");//6
        componentHeader.add("Manhole 1");//7
        componentHeader.add("Manhole 2");//8
        componentHeader.add("Outlet-Back");//9
        componentHeader.add("Outlet-Back: Baffle Blocks");//10
        componentHeader.add("Outlet-Back: Grate");//11
        componentHeader.add("Outlet-Back: Headwall");//12
        componentHeader.add("Outlet-Back: Pipe");//13
        componentHeader.add("Outlet-Front");//14
        componentHeader.add("Outlet-Front: Grate");//15
        componentHeader.add("Outlet-Front: Headwall");//16
        componentHeader.add("Outlet-Front: Pipe");//17

        List<Item> component1 = new ArrayList<>();//Bench Mark
        List<Item> component2 = new ArrayList<>();//Emergency Spillway
        List<Item> component3 = new ArrayList<>();//Emergency Spillway: Grating
        List<Item> component4 = new ArrayList<>();//Facility
        List<Item> component5 = new ArrayList<>();//Fence
        List<Item> component6 = new ArrayList<>();//Inlet Channel
        List<Item> component7 = new ArrayList<>();//Manhole 1
        List<Item> component8 = new ArrayList<>();//Manhole 2
        List<Item> component9 = new ArrayList<>();//Outlet Back
        List<Item> component10 = new ArrayList<>();//OB: Baffle Blocks
        List<Item> component11 = new ArrayList<>();//OB: Grate
        List<Item> component12 = new ArrayList<>();//OB: Headwall
        List<Item> component13 = new ArrayList<>();//OB: Pipe
        List<Item> component14 = new ArrayList<>();//OF
        List<Item> component15 = new ArrayList<>();//OF: Grate
        List<Item> component16 = new ArrayList<>();//OF: Headwall
        List<Item> component17 = new ArrayList<>();//OF: Pipe

        component1.add(new Item("Bench Mark","Make sure to record any offset from the actual bench mark", "", 0, false));
        component2.add(new Item("Emergency Spillway","Check for structural condition (crackling, flaking, broken seperating, leaning), obstructions", "", 0, false));
        component3.add(new Item("Emergency Spillway: Grating","Check grate bars for rust, bent, broken, open/closed, lock","",0, false));
        component4.add(new Item("Facility","Check for garbage, erosion, proper functioning and correct water levels","",0, false));
        component5.add(new Item("Fence","Check for structural condition (broken, leaning)", "",0, false));
        component6.add(new Item("Inlet Channel","Check for standing water, structural integrity and in-steam erosion", "",0, false));
        component7.add(new Item("Manhole 1","Check integrity of cover (rust, bent, broken, open/closed, lock-bolts), selling around erosion", "",0, false));
        component8.add(new Item("Manhole 2","Check integrity of cover (rust, bent, broken, open/closed, lock-bolts), setting around erosion",  "",0, false));
        component9.add(new Item("Outlet-Back","Check for obstructions/debris", "",0, false));
        component10.add(new Item("Outlet-Back: Baffle Blocks","Check for structural condition (crackling,flaking)", "",0, false));
        component11.add(new Item("Outlet-Back: Grate","Check grate bars for rust, bent, broken, open.close lock", "",0, false));
        component12.add(new Item("Outlet-Back: Headwall","Filter Bed Erosion", "",0, false));
        component13.add(new Item("Outlet-Back: Pipe","", "",0, false));
        component14.add(new Item("Outlet-Front","Check for obstruction/debris", "",0, false));
        component15.add(new Item("Outlet-Front: Grate","Check grate bars for rust, bent, broken open/closed, lock", "",0, false));
        component16.add(new Item("Outlet-Front: Headwall","Check for structural condition (crackling/flaking)", "",0, false));
        component17.add(new Item("Outlet-Front: Pipe","Check for obstructions/debris built up", "",0, false));

        listHash.put(componentHeader.get(0),component1);
        listHash.put(componentHeader.get(1),component2);
        listHash.put(componentHeader.get(2),component3);
        listHash.put(componentHeader.get(3),component4);
        listHash.put(componentHeader.get(4),component5);
        listHash.put(componentHeader.get(5),component6);
        listHash.put(componentHeader.get(6),component7);
        listHash.put(componentHeader.get(7),component8);
        listHash.put(componentHeader.get(8),component9);
        listHash.put(componentHeader.get(9),component10);
        listHash.put(componentHeader.get(10),component11);
        listHash.put(componentHeader.get(11),component12);
        listHash.put(componentHeader.get(12),component13);
        listHash.put(componentHeader.get(13),component14);
        listHash.put(componentHeader.get(14),component15);
        listHash.put(componentHeader.get(15),component16);
        listHash.put(componentHeader.get(16),component17);
    }

    private void lidData(){
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
