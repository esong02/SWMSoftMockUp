package com.example.esong02.swmsoftlofi.Activities;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.esong02.swmsoftlofi.Adapters.PagerAdapter;
import com.example.esong02.swmsoftlofi.Models.User;
import com.example.esong02.swmsoftlofi.Obsolete.MyTasksActivity;
import com.example.esong02.swmsoftlofi.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    private static final User sampleU = new User("Civica","pass",false);
    private static final User adminU = new User("Admin","pass",false);
    private static final User inspectorU = new User("Inspector","pass",false);
    public static List<String> myTaskF;
    public static List<String> myTaskS;
    public static List<String> myTaskL;
    private boolean filter = false;
    private boolean firstState = true;
    private int tabState = 0; //can only be 0-2
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private DrawerLayout mdrawerLayout;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private LinearLayout customBar;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        if(savedInstanceState != null)
        {
            filter = savedInstanceState.getBoolean("filter");
            tabState = savedInstanceState.getInt("Tab");
            Toast.makeText(MainActivity.this, "SavedInstance",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "NewInstance",Toast.LENGTH_SHORT).show();
        }
        */

        assignUserTasks();//populate user tasks filter;

        if (!sampleU.getLogin()){

            //User Login
            final Dialog alertDialog = new Dialog(MainActivity.this);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.setContentView(R.layout.login_dialog);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            //alertDialog.setCancelable(false);

            final EditText pass = (EditText) alertDialog.findViewById(R.id.passField);
            final Button loginBtn = (Button) alertDialog.findViewById(R.id.loginBtn);
            final EditText uName = (EditText) alertDialog.findViewById(R.id.userField);

            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (uName.getText().toString().toLowerCase().equals(sampleU.getName().toLowerCase())) {
                        if (pass.getText().toString().toLowerCase().equals(sampleU.getPass().toLowerCase())) {
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        } else {
                            pass.setHint("Invalid Password");
                        }
                    }else{
                        uName.setHint("Invalid Username");
                    }
                }
            });

            alertDialog.show();
            sampleU.setLogin(true);
        }

        //Custom Toolbar xml: custom_actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        customBar = (LinearLayout) toolbar.findViewById(R.id.customActionBarLayer);
        setSupportActionBar(toolbar);

        final ImageButton settingBtn = (ImageButton)toolbar.findViewById(R.id.action_settings);
        final ImageButton filterTaskBtn = (ImageButton)toolbar.findViewById(R.id.filter_tasks);
        ImageButton findBtn = (ImageButton)toolbar.findViewById(R.id.action_search);
        ImageButton syncBtn = (ImageButton)toolbar.findViewById(R.id.action_sync_db);
        final TextView filterTasklbl = (TextView)toolbar.findViewById(R.id.filterTaskLabel);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.setting_spinner);
        arrayAdapter.add("Help");
        arrayAdapter.add("About");
        arrayAdapter.add("Feedback");

        final Spinner settingSpn = (Spinner) findViewById(R.id.settingSpinner);
        settingSpn.setAdapter(arrayAdapter);
        settingSpn.setSelection(2);


        settingBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                settingSpn.setVisibility(View.INVISIBLE);
                settingSpn.performClick();

                settingSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, "Selected: " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                        settingSpn.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        settingSpn.setVisibility(View.GONE);
                    }
                });

            }
        });

        filterTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filter == false) {
                    //Filtering Now
                    filter = true;
                    Toast.makeText(MainActivity.this, "Filtering by User's tasks", Toast.LENGTH_SHORT).show();
                    adapter.tab1.filterNow();
                    adapter.tab2.filterNow();
                    adapter.tab3.filterNow();

                    filterTaskBtn.setImageResource(R.drawable.cancel_small);
                    filterTasklbl.setText("Cancel");
                }else{
                    //Already Filtered
                    filter = false;
                    Toast.makeText(MainActivity.this,"Clearing filter . . ", Toast.LENGTH_SHORT).show();
                    adapter.tab1.clearFilter();
                    adapter.tab2.clearFilter();
                    adapter.tab3.clearFilter();
                    filterTaskBtn.setImageResource(R.drawable.ic_assignment_ind_black_24dp);
                    filterTasklbl.setText("My Tasks");
                }
            }
        });

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Under Construction",Toast.LENGTH_SHORT).show();
            }
        });

        syncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sync DB function
                Dialog syncDialog = new Dialog(MainActivity.this);
                syncDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                syncDialog.setContentView(R.layout.sync_db);
                syncDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                syncDialog.show();
            }
        });


        //Navigation Layout
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //Tab Layout
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        TabLayout.Tab tab1 = tabLayout.newTab().setText("Facility");
        tab1.setCustomView(R.layout.fac_tab_header);
        tabLayout.addTab(tab1);

        TabLayout.Tab tab2 = tabLayout.newTab().setText("Structure");
        tab2.setCustomView(R.layout.struc_tab_header);
        tabLayout.addTab(tab2);

        TabLayout.Tab tab3 = tabLayout.newTab().setText("L.I.D.");
        tab3.setCustomView(R.layout.lid_tab_header);
        tabLayout.addTab(tab3);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewPager.setCurrentItem(tabState);//saves tab position
        setTabColor(tabState);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                setTabState(tab.getPosition());
                setTabColor(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void openDrawer(){
            mdrawerLayout.openDrawer(Gravity.START);
        }

    public void closeDrawer(){
            mdrawerLayout.closeDrawer(Gravity.START);
        }

    public void setTabState(int position){
        tabState = position;
    }

    public void setTabColor(int position){

        if (tabState == 0){
            viewPager.setBackgroundResource(R.color.orange_yellow);
        }else if (tabState == 1){
            viewPager.setBackgroundResource(R.color.lime_green);
        }else if (tabState == 2){
            viewPager.setBackgroundResource(R.color.light_blue);
        }else{
            Toast.makeText(MainActivity.this, "Error on tab color", Toast.LENGTH_SHORT).show();
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

        } else if (id == R.id.closeNavBar) {
            //closeDrawer();
        } else if (id == R.id.about) {

        } else if (id == R.id.feedback) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void assignUserTasks(){

        myTaskF = new ArrayList<>();
        myTaskS = new ArrayList<>();
        myTaskL = new ArrayList<>();

        myTaskF.add("1 - Pondview2");
        myTaskF.add("16 - Keele/407");
        myTaskF.add("24 - Fieldgate ");

        myTaskS.add("10-3 - Bridge");
        myTaskS.add("10-5 - Bridge");
        myTaskS.add("11-4 - Culvert");

        myTaskL.add("2 - Site 2");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //TODO Auto Generated Stub
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putBoolean("filter",filter);
        outState.putInt("Tab",tabState);
    }
}
