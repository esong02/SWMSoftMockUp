package com.example.esong02.swmsoftlofi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LIDTab extends Fragment{

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listSiteHeader;
    private HashMap<String, List<LID>> listHash;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getContext();

        View rootView = inflater.inflate(R.layout.asset_tab, container, false);

        listView = (ExpandableListView) rootView.findViewById(R.id.firstListView);
        initData();
        listAdapter = new ExpandableListAdapter(context,listSiteHeader,listHash);
        listView.setAdapter(listAdapter);

        /*
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

      // You must make use of the View v, find the view by id and extract the text as below

                TextView tv= (TextView) v.findViewById(R.id.lblListItem);
                String data= tv.getText().toString();
                Toast.makeText(context, data, Toast.LENGTH_SHORT).show();

                ImageButton inspect = (ImageButton)v.findViewById(R.id.inspectAction);

                inspect.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        Toast.makeText(context, "Inspect Selected . . ", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(getActivity(), InspectionActivity.class);
                        //startActivity(intent);

                    }
                });


                return true;
            }
        });
        */

        return rootView;
    }

    private void initData(){
        listHash = new HashMap<>();
        listSiteHeader = new ArrayList<>();

        listSiteHeader.add("Site 1");
        listSiteHeader.add("Site 2");
        listSiteHeader.add("Site 3");

        List<LID> lid1 = new ArrayList<>();  //Bioretention LID
        List<LID> lid2 = new ArrayList<>(); //Permeable LID
        List<LID> lid3 = new ArrayList<>(); //Green Roof LID

        //lids

        lid1.add(new LID("Rain Garden 1","Bioretention"));
        lid1.add(new LID("Bioretention Cell 1","Bioretention"));
        lid1.add(new LID("Roof Garden 1","Bioretention"));

        lid2.add(new LID("Stormwater Planter 1","Bioretention"));
        lid2.add(new LID("Grid Paver 1","Permeable Pavement"));

        lid3.add(new LID("Eco - Roof 1","Greenroof"));

        listHash.put(listSiteHeader.get(0),lid1);
        listHash.put(listSiteHeader.get(1),lid2);
        listHash.put(listSiteHeader.get(2),lid3);


    }


}
