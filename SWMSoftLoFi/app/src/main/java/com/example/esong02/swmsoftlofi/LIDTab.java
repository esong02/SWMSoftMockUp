package com.example.esong02.swmsoftlofi;

import android.content.Context;
import android.content.Intent;
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

public class LIDTab extends Fragment{

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listPropertyHeader;
    private HashMap<String, List<String>> listHash;
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

                Intent intent = new Intent(getActivity(), InspectionActivity.class);
                startActivity(intent);

                return true;  // i missed this
            }
        });

        return rootView;
    }

    private void initData(){
        listHash = new HashMap<>();
        listPropertyHeader = new ArrayList<>();

        listPropertyHeader.add("Site 1");
        listPropertyHeader.add("Site 2");
        listPropertyHeader.add("Site 3");

        List<String> lid1 = new ArrayList<>();  //Bioretention LID
        List<String> lid2 = new ArrayList<>(); //Permeable LID
        List<String> lid3 = new ArrayList<>(); //Green Roof LID

        //lids

        lid1.add("Rain Garden 1");
        lid1.add("Bioretention Cell 1");
        lid1.add("Roof Garden 1");

        lid2.add("Stormwater Planter 1");
        lid2.add("Grid Paver 1");

        lid3.add("Eco - Roof 1");

        listHash.put(listPropertyHeader.get(0),lid1);
        listHash.put(listPropertyHeader.get(1),lid2);
        listHash.put(listPropertyHeader.get(2),lid3);

    }

}
