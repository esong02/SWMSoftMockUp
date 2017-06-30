package com.example.esong02.swmsoftlofi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private List<String> passList;
    private boolean filterNow = false;
    //private FacilityTab tab1;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    public void setFilter(List<String> pList){
        filterNow = true;
        passList = pList;
        //Log.d("Filter", "" + passList.size());
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FacilityTab tab1 = new FacilityTab();
                //tab1.filterFS = passList;
                /*
                if (filterNow = true) {
                    tab1.passList(passList, filterNow);
                }else{
                    tab1.passList(null, false);
                    tab1.clearFilter();
                }
                */

                return tab1;
            case 1:
                StructureTab tab2 = new StructureTab();
                return tab2;
            case 2:
                LIDTab tab3 = new LIDTab();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
