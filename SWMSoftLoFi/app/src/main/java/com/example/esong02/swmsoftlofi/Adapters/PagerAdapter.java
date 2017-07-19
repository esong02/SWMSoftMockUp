package com.example.esong02.swmsoftlofi.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.esong02.swmsoftlofi.Tabs.FacilityTab;
import com.example.esong02.swmsoftlofi.Tabs.LIDTab;
import com.example.esong02.swmsoftlofi.Tabs.StructureTab;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public FacilityTab tab1;
    public StructureTab tab2;
    public LIDTab tab3;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 = new FacilityTab();
                return tab1;
            case 1:
                tab2 = new StructureTab();
                return tab2;
            case 2:
                tab3 = new LIDTab();
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
