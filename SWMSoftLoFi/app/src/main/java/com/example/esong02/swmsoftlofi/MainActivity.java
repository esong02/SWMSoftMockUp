package com.example.esong02.swmsoftlofi;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final User sampleU = new User("Civica","pass",false);
    private static final User adminU = new User("Admin","pass",false);
    private static final User inspectorU = new User("Inspector","pass",false);
    public static List<String> myTaskF = new ArrayList<String>();
    public static List<String> myTaskS = new ArrayList<String>();
    public static List<String> myTaskL = new ArrayList<String>();
    private boolean filter = false;
    private ViewPager viewPager;
    //private PagerAdapter adapter;
    List<WeakReference<Fragment>> fragList = new ArrayList<WeakReference<Fragment>>();
    //Test
    private DrawerLayout mdrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*

        if (!sampleU.getLogin()){

            //User Login
            final Dialog alertDialog = new Dialog(MainActivity.this);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.setContentView(R.layout.login_dialog);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            alertDialog.setCancelable(false);

            final EditText pass = (EditText) alertDialog.findViewById(R.id.passField);
            final Button loginBtn = (Button) alertDialog.findViewById(R.id.loginBtn);
            final EditText uName = (EditText) alertDialog.findViewById(R.id.userField);

            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (uName.getText().toString().equals(sampleU.getName())) {
                        if (pass.getText().toString().equals(sampleU.getPass())) {
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        } else {
                            pass.setText("Invalid Password");
                        }
                    }else{
                        uName.setText("Invalid Username");
                    }
                }
            });

            alertDialog.show();
            sampleU.setLogin(true);
        }

        */

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Facility"));
        tabLayout.addTab(tabLayout.newTab().setText("Structure"));
        tabLayout.addTab(tabLayout.newTab().setText("L.I.D."));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //myTasksS.add("Structure 2");
        //Log.d("Filter Name",FacilityTab.filterFS.get(0));

        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //adapter.setFilter(myTasksFS);
                viewPager.setCurrentItem(tab.getPosition());
                //Toast.makeText(MainActivity.this, tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onAttachFragment (Fragment fragment) {
        fragList.add(new WeakReference(fragment));
    }

    public List<Fragment> getActiveFragments() {
        ArrayList<Fragment> ret = new ArrayList<Fragment>();
        for(WeakReference<Fragment> ref : fragList) {
            Fragment f = ref.get();
            if(f != null) {
                if(f.isVisible()) {
                    ret.add(f);
                }
            }
        }
        return ret;
    }

    public void openDrawer(){
        mdrawerLayout.openDrawer(Gravity.START);
    }

    public void closeDrawer(){
        mdrawerLayout.closeDrawer(Gravity.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.listview_bar, menu);
        //getMenuInflater().inflate(R.menu.gisview_bar, menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_gis_view:
                openDrawer();
                return true;
            case R.id.action_sync_db:

                //Actual Sync DB function
                Dialog alertDialog = new Dialog(this);
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.sync_db);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                alertDialog.show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.user_info) {
            // Handle the camera action
        } else if (id == R.id.inspection_tasks) {
            Intent intent = new Intent(MainActivity.this, MyTasksActivity.class);
            intent.putExtra("Activity","My Inspection Tasks");
            startActivity(intent);
        } else if (id == R.id.photos) {
            if (filter == false) {
                //Filtering now
                filter = true;

                Fragment page = getSupportFragmentManager().findFragmentById(R.id.pager);
                // based on the current position you can then cast the page to the correct
                // class and call the method:

                Toast.makeText(MainActivity.this, "Filtering", Toast.LENGTH_SHORT).show();
                Log.d("Page",page.toString());
                if (viewPager.getCurrentItem() == 0 && page != null) {

                    myTaskF.add("Facility 1");//filter test
                    //myTaskS.add("Structure 2");//structure test
                    if (page instanceof FacilityTab) {
                        Log.d("instanceof","Facility");
                        FacilityTab fPage = (FacilityTab) page;
                        fPage.filterNow();
                    }
                }
               // List<Fragment> pages = getActiveFragments();

                //Log.d("Pages",pages.size() + "");


                //myTaskS.add("Structure 2");//structure test

                //myTaskL.add("Site 3");//lid test
            }else{
                //Already Filtered
                filter = false;
                Toast.makeText(MainActivity.this,"Defiltering", Toast.LENGTH_SHORT).show();
                myTaskL.clear();
                myTaskF.clear();
                myTaskS.clear();
            }
        } else if (id == R.id.closeNavBar) {
            closeDrawer();
        } else if (id == R.id.about) {

        } else if (id == R.id.feedback) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
