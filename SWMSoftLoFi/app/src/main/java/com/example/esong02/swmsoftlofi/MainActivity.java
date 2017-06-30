package com.example.esong02.swmsoftlofi;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final User sampleU = new User("Civica","pass",false);
    private static final User adminU = new User("Admin","pass",false);
    private static final User inspectorU = new User("Inspector","pass",false);
    public static List<String> myTasksF = new ArrayList<String>();
    public static List<String> myTasksS = new ArrayList<String>();
    public static List<String> myTasksL = new ArrayList<String>();
    //private PagerAdapter adapter;
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
        //Log.d("Filter Name",FacilityTab.filterFS.get(0));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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
            Toast.makeText(MainActivity.this, "Filtering", Toast.LENGTH_SHORT).show();
            myTasksF.add("Facility 1");//filter test
            myTasksS.add("Structure 2");//structure test

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
