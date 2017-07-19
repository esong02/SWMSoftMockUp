package com.example.esong02.swmsoftlofi.Tabs;

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

import com.example.esong02.swmsoftlofi.Adapters.FSListAdapter;
import com.example.esong02.swmsoftlofi.Activities.MainActivity;
import com.example.esong02.swmsoftlofi.Models.*;
import com.example.esong02.swmsoftlofi.R;

public class StructureTab extends Fragment{

    private ListView listView;
    private List<String> listPropertyHeader;
    private List<String> filteredList = new ArrayList<>();
    private HashMap<String, List<Asset>> listHash;
    private Context context;
    private FSListAdapter myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        View rootView = inflater.inflate(R.layout.f_s_tab, container, false);

        initData();
        filteredList.clear();
        filteredList.addAll(listPropertyHeader);

        myAdapter = new FSListAdapter(context, R.layout.list_item_viewflipper, filteredList);
        myAdapter.setAssetType("Structure");

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
        if (!MainActivity.myTaskS.isEmpty()) {
            filteredList.clear();
            for (String lph : listPropertyHeader) {
                for (String f: MainActivity.myTaskS) {
                    if (lph.contains(f)) {
                        filteredList.add(f);
                    }
                }
            }
        }
        myAdapter.notifyDataSetChanged();
    }

    private void initData(){
        listHash = new HashMap<>();
        listPropertyHeader = new ArrayList<>();

        listPropertyHeader.add("2-10 - Culvert");
        listPropertyHeader.add("2-12 - Culvert");
        listPropertyHeader.add("10-10 - Culvert");
        listPropertyHeader.add("10-11 - Culvert");
        listPropertyHeader.add("10-12 - Culvert");
        listPropertyHeader.add("10-13 - Bridge");
        listPropertyHeader.add("10-2 - Bridge");
        listPropertyHeader.add("10-20 - Culvert");
        listPropertyHeader.add("10-3 - Bridge");
        listPropertyHeader.add("10-4 - Bridge");
        listPropertyHeader.add("10-5 - Bridge");
        listPropertyHeader.add("10-7 - Bridge");
        listPropertyHeader.add("10-8 - Bridge");
        listPropertyHeader.add("11-4 - Culvert");
        listPropertyHeader.add("11-5 - Culvert");
        listPropertyHeader.add("11-6 - Bridge");
        listPropertyHeader.add("15-2 - Culvert");
        listPropertyHeader.add("15-3 - Culvert");
        listPropertyHeader.add("19-12 - Culvert");
        listPropertyHeader.add("19-5 - Culvert");
        listPropertyHeader.add("19-7 - Culvert");
        listPropertyHeader.add("21-6 - Culvert");
        listPropertyHeader.add("21-9 - Culvert");
        listPropertyHeader.add("22-2 - Culvert");
        listPropertyHeader.add("22-7 - Culvert");
        listPropertyHeader.add("22-8 - Culvert");


    }

}