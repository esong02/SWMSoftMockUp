package com.example.esong02.swmsoftlofi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FacilityTab extends Fragment{

    private ListView listView;
    private List<String> listPropertyHeader;
    private HashMap<String, List<LID>> listHash;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getContext();

        View rootView = inflater.inflate(R.layout.f_s_tab, container, false);

        initData();

        FSListAdapter myAdapter = new FSListAdapter(context, R.layout.lst_item, listPropertyHeader);
        listView = (ListView) rootView.findViewById(R.id.facilityStructureListView);
        listView.setAdapter(myAdapter);

        return rootView;
    }

    private void initData(boolean c){
        listPropertyHeader = new ArrayList<>();


        listPropertyHeader.add("Facility 1");
        listPropertyHeader.add("Facility 2");
        listPropertyHeader.add("Facility 3");
    }

    private void initData(){
        listHash = new HashMap<>();
        listPropertyHeader = new ArrayList<>();


        listPropertyHeader.add("Facility 1");
        listPropertyHeader.add("Facility 2");
        listPropertyHeader.add("Facility 3");


        List<LID> lid1 = new ArrayList<>();
        List<LID> lid2 = new ArrayList<>();
        List<LID> lid3 = new ArrayList<>();

        lid1.add(new LID("Outlet 1","Outlet"));
        lid1.add(new LID("Spillway 1","Spillway"));
        lid1.add(new LID("Channel 1","Channel"));

        lid2.add(new LID("Manhole 1","Manhole"));
        lid2.add(new LID("Channel 2","SWM Channel"));
        lid2.add(new LID("Manhole 2","Manhole"));
        lid2.add(new LID("Outlet 2","Outlet"));

        lid3.add(new LID("Spillway 2","Spillway"));
        lid3.add(new LID("Spillway 3","Spillway"));
        lid3.add(new LID("Channel 3","Channel"));

        listHash.put(listPropertyHeader.get(0),lid1);
        listHash.put(listPropertyHeader.get(1),lid2);
        listHash.put(listPropertyHeader.get(2),lid3);

    }
}
