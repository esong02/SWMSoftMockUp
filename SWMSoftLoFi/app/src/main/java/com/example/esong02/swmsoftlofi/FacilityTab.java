package com.example.esong02.swmsoftlofi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FacilityTab extends Fragment{

    private ListView listView;
    private List<String> listPropertyHeader;
    private List<String> filteredList = new ArrayList<>();
    private HashMap<String, List<LID>> listHash;
    private Context context;
    public FSListAdapter myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        View rootView = inflater.inflate(R.layout.f_s_tab, container, false);

        initData(true);
        filteredList.clear();
        filteredList.addAll(listPropertyHeader);

        myAdapter = new FSListAdapter(context, R.layout.list_item_viewflipper, filteredList);
        myAdapter.setAssetType("Facility");

        listView = (ListView) rootView.findViewById(R.id.facilityStructureListView);
        listView.setAdapter(myAdapter);

        return rootView;
    }

    public void clearFilter(){
        filteredList.clear();
        filteredList.addAll(listPropertyHeader);
        myAdapter.notifyDataSetChanged();
    }

    public void filterNow(){
        if (!MainActivity.myTaskF.isEmpty()) {
            filteredList.clear();
            for (String lph : listPropertyHeader) {
                for (String f: MainActivity.myTaskF) {
                    if (lph.contains(f)) {
                        filteredList.add(f);
                    }
                }
            }
        }
        myAdapter.notifyDataSetChanged();
    }

    private void initData(boolean c){
        listPropertyHeader = new ArrayList<>();

        listPropertyHeader.add("1 - Pondview2");
        listPropertyHeader.add("2 - Oakbank Thornbank Pond");
        listPropertyHeader.add("5 - Lakeview Estates Ltd");
        listPropertyHeader.add("8 - Trullwrook Investments - Pond A");
        listPropertyHeader.add("9 - Concord Storm Drainage - Pond B");
        listPropertyHeader.add("10 - Pond 10");
        listPropertyHeader.add("11 - Sugarbush Developments Ph 1");
        listPropertyHeader.add("12 - Sugarbush Developments Ph 1");
        listPropertyHeader.add("14 - Pond 1 - Woodland Acres");
        listPropertyHeader.add("15 - Thornhill/Vaughan Community - Pond D (Marta Payne Park)");
        listPropertyHeader.add("16 - Keele/407");
        listPropertyHeader.add("17 - Pond 17");
        listPropertyHeader.add("20 - Langstaff Eco - Park");
        listPropertyHeader.add("21 - Concord Storm Drainage - Pond A");
        listPropertyHeader.add("22 - Pond 22 - Aldridge Estates");
        listPropertyHeader.add("23 - Maple Neighbourhood 2 Pond");
        listPropertyHeader.add("24 - Fieldgate Pond");
        listPropertyHeader.add("31 - Maplewood Villages Ltd");
        listPropertyHeader.add("32 - Keele-Steele-Jane Indus. Area");
        listPropertyHeader.add("36 - CNR Industrial Subdivision");
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
