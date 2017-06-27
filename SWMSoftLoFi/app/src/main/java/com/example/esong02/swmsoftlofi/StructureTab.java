package com.example.esong02.swmsoftlofi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StructureTab extends Fragment{

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listPropertyHeader;
    private HashMap<String, List<LID>> listHash;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getContext();

        View rootView = inflater.inflate(R.layout.asset_tab, container, false);

        listView = (ExpandableListView) rootView.findViewById(R.id.firstListView);
        initData();
        listAdapter = new ExpandableListAdapter(context,listPropertyHeader,listHash);
        listView.setAdapter(listAdapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

      /* You must make use of the View v, find the view by id and extract the text as below*/

                TextView tv= (TextView) v.findViewById(R.id.lblListItem);

                String data= tv.getText().toString();

                Toast.makeText(context, data, Toast.LENGTH_SHORT).show();

                return true;  // i missed this
            }
        });

        return rootView;
    }

    private void initData(){
        listHash = new HashMap<>();
        listPropertyHeader = new ArrayList<>();

        listPropertyHeader.add("Structure 1");
        listPropertyHeader.add("Structure 2");
        listPropertyHeader.add("Structure 3");

        List<LID> lid1 = new ArrayList<>();  //Bioretention LID
        List<LID> lid2 = new ArrayList<>(); //Permeable LID
        List<LID> lid3 = new ArrayList<>(); //Green Roof LID

        lid1.add(new LID("Outlet 1","Outlet"));
        lid1.add(new LID("Spillway 1","Spillway"));
        lid1.add(new LID("Channel 1","SWM Channel"));

        lid2.add(new LID("Manhole 1","Manhole"));
        lid2.add(new LID("Head Wall 1","HeadWall"));

        listHash.put(listPropertyHeader.get(0),lid1);
        listHash.put(listPropertyHeader.get(1),lid2);
        listHash.put(listPropertyHeader.get(2),lid3);

    }

}