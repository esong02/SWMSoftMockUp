package com.example.esong02.swmsoftlofi;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    private PagerAdapter adapter;
    private DrawerLayout mdrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //filter test
        myTaskF.add("Facility 1");
        myTaskS.add("Structure 2");
        myTaskL.add("Site 3");

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

        //Custom Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton settingBtn = (ImageButton)toolbar.findViewById(R.id.action_settings);
        ImageButton inspectionTypeBtn = (ImageButton)toolbar.findViewById(R.id.inspection_type);
        final ImageButton filterTaskBtn = (ImageButton)toolbar.findViewById(R.id.filter_tasks);
        ImageButton findBtn = (ImageButton)toolbar.findViewById(R.id.action_search);
        ImageButton syncBtn = (ImageButton)toolbar.findViewById(R.id.action_sync_db);
        final TextView filterTasklbl = (TextView)toolbar.findViewById(R.id.filterTaskLabel);

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017-07-05
            }
        });

        inspectionTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inspection Type
                final Dialog iTypeDialog = new Dialog(MainActivity.this);
                iTypeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                iTypeDialog.setContentView(R.layout.inspection_type_dialog);
                iTypeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                ImageButton cancelBtn = (ImageButton) iTypeDialog.findViewById(R.id.iTypeCancelButton);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iTypeDialog.dismiss();
                    }
                });

                iTypeDialog.show();
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

                    filterTaskBtn.setImageResource(R.drawable.ic_cancel_white_24dp);
                    filterTasklbl.setText("Cancel");
                }else{
                    //Already Filtered
                    filter = false;
                    Toast.makeText(MainActivity.this,"Clearing filter . . ", Toast.LENGTH_SHORT).show();
                    adapter.tab1.clearFilter();
                    adapter.tab2.clearFilter();
                    adapter.tab3.clearFilter();
                    filterTaskBtn.setImageResource(R.drawable.ic_assignment_ind_white_24dp);
                    filterTasklbl.setText("My Tasks");
                }
            }
        });

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017-07-05
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

        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Facility"));
        tabLayout.addTab(tabLayout.newTab().setText("Structure"));
        tabLayout.addTab(tabLayout.newTab().setText("L.I.D."));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        //adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                //Log.d("Tab"," #"+tab.getPosition());
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

/*
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

                case R.id.filter_tasks:
                    if (filter == false) {
                        //Filtering Now
                        filter = true;
                        Toast.makeText(MainActivity.this, "Filtering by User's tasks", Toast.LENGTH_SHORT).show();
                        adapter.tab1.filterNow();
                        adapter.tab2.filterNow();
                        adapter.tab3.filterNow();

                        item.setIcon(R.drawable.ic_cancel_white_24dp);
                    }else{
                        //Already Filtered
                        filter = false;
                        Toast.makeText(MainActivity.this,"Clearing filter . . ", Toast.LENGTH_SHORT).show();
                        adapter.tab1.clearFilter();
                        adapter.tab2.clearFilter();
                        adapter.tab3.clearFilter();
                        item.setIcon(R.drawable.ic_assignment_ind_white_24dp);
                    }

                    return true;

                case R.id.inspection_type:

                    //Inspection Type
                    final Dialog iTypeDialog = new Dialog(this);
                    iTypeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    iTypeDialog.setContentView(R.layout.inspection_type_dialog);
                    iTypeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                    ImageButton cancelBtn = (ImageButton) iTypeDialog.findViewById(R.id.iType_Cancel_Button);
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            iTypeDialog.dismiss();
                        }
                    });

                    iTypeDialog.show();
                    return true;

                case R.id.action_gis_view:
                    openDrawer();
                    return true;

                case R.id.action_sync_db:

                    //Actual Sync DB function
                    Dialog syncDialog = new Dialog(this);
                    syncDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    syncDialog.setContentView(R.layout.sync_db);
                    syncDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    syncDialog.show();
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    */

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

}
