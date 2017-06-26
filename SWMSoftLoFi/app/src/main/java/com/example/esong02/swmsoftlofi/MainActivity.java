package com.example.esong02.swmsoftlofi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cocosw.bottomsheet.BottomSheet;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final User currentU = new User("Sample","civica",false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.property_window);
        //setContentView(R.layout.inspection_form);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.photo_list_dialog);
        //setContentView(R.layout.lid_inspection);
        //setContentView(R.layout.inspection_list);
        //setContentView(R.layout.inspection_assignment);
        //setContentView(R.layout.gis_view);
        //setContentView(R.layout.photo_list_dialog);
        //setContentView(R.layout.intro_dialog);

        Context context;



        if (currentU.getLogin() == false){

            //User Login
            final Dialog alertDialog = new Dialog(MainActivity.this);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.setContentView(R.layout.intro_dialog);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            alertDialog.setCancelable(false);

            final EditText pass = (EditText) alertDialog.findViewById(R.id.passCode);
            final Button loginBtn = (Button) alertDialog.findViewById(R.id.loginBtn);
            final TextView uName = (TextView) alertDialog.findViewById(R.id.userName);
            uName.setText("Welcome Back, " + currentU.getName());
            //final String password = "civica";
            // if button is clicked, close the custom dialog
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pass.getText().toString().equals(currentU.getPass())){
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }else{
                        uName.setText("Invalid Password");
                        uName.setTextColor(Color.RED);
                    }
                }
            });

            alertDialog.show();
            currentU.setLogin(true);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setHomeButtonEnabled(true);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Facilities"));
        tabLayout.addTab(tabLayout.newTab().setText("Structures"));
        tabLayout.addTab(tabLayout.newTab().setText("Low Impact Developments"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //Inspection Form

        //Bottom Sheet for Activities
        /*
        new BottomSheet.Builder(this)
                .title("Activities")
                .grid() // <-- important part
                .sheet(R.menu.bottom_sheet_inspection)
                .listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {

                            //Filter by local favourites
                            case R.id.inspection:

                                break;

                            //Filter by drinks with ratings
                            case R.id.p_inspection:

                                //filter all drinks that have a rating of at least 1
                                //'test is dummy string'
                                break;
                            default:
                                break;
                        }
                    }
                }).show();
                */


        //Listview of Past Inspections
        /*
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
        builderSingle.setIcon(R.drawable.ic_search_white);
        builderSingle.setTitle("Completed Inspections");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_item);
        arrayAdapter.add("Inspection Date: 7/9/2003");
        arrayAdapter.add("Inspection Date: 8/19/2016");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(MainActivity.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected Item is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });

        builderSingle.show();
        */

        //User Login
        // get prompts.xml view
        /*
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.intro_dialog, null);

        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        // create alert dialog
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        */

        // show it
        //alertDialog.show();

        //New Intro Dialog

        /*
        Dialog alertDialog = new Dialog(this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(R.layout.intro_dialog);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        */

        //User Icon
        /*
        Dialog alertDialog = new Dialog(this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(R.layout.login_list);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        */

        /*
        ITasks myDataArray[] = new ITasks[]{
                new ITasks("Pondview2","Complete", "Northeast corner\nof Hwy 400 (15km)"),
                new ITasks("Lakeview\nEstates", "Pending", "Btwn Dundurn Cr &\nRosedale Heights (5km)"),
                new ITasks("Oakbank\nThornbank", "Pending", "N side of Centre St btwn\nErica & Oakbank (3km)"),
                new ITasks("Trullwrook\nInvestment", "Complete", "Part Lot 7\nConcession (50km)")
        };

        InspectionAdapter myAdapter = new InspectionAdapter(this, R.layout.item_task, myDataArray);
        ListView myList = (ListView) findViewById(R.id.inspections_todo);
        myList.setAdapter(myAdapter);
        */
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

    /*
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.user_info) {
            // Handle the camera action
        } else if (id == R.id.inspection_tasks) {

        } else if (id == R.id.photos) {

        } else if (id == R.id.help) {

        } else if (id == R.id.about) {

        } else if (id == R.id.feedback) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    */

}
